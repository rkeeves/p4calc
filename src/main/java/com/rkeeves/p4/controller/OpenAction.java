package com.rkeeves.p4.controller;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.io.FXMLLoadFailedException;
import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.model.DTOInvalidException;
import com.rkeeves.p4.model.EconomyModelCreatorService;
import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;

@RequiredArgsConstructor
public class OpenAction implements Runnable{

    private final FileChooserController fileChooserController;

    private final JSONService jsonService;

    private final EconomyModelCreatorService economyModelCreatorService;

    private final EconomyModelService economyModelService;

    private final AlertController alertController;

    @Override
    public void run() {
        try {
            performOpen();
        } catch (JSONReadFailedException e) {
            onUserDTOLoadFailed(e);
        } catch (DTOInvalidException e) {
            onDTOTransformToModelFailed(e);
        } catch (FXMLLoadFailedException e) {
            onModelResetFailed(e);
        }
    }

    private void performOpen() throws JSONReadFailedException, DTOInvalidException, FXMLLoadFailedException {
        var fileChoice = fileChooserController.chooseJSONFileForOpen();
        if(fileChoice.isEmpty()){
            onOpenCancelled();
            return;
        }
        var economyDTO = jsonService.readFromFile(fileChoice.get(), EconomyDTO.class);
        var newEconomyModel = economyModelCreatorService.create(economyDTO);
        economyModelService.setEconomyModel(newEconomyModel);
        onModelResetSuccess();
    }

    private void onDTOTransformToModelFailed(DTOInvalidException e) {
        Logger.error(e,"Transforming DTO to model failed");
        alertController.showOkOnlyAlert("JSON Semantic Error",e.getMessage());
    }

    private void onModelResetSuccess() {
        Logger.debug("Model reset succeeded");
        alertController.showOkOnlyAlert("Load","Load succeeded");
    }

    private void onModelResetFailed(FXMLLoadFailedException e) {
        Logger.error(e, "Model reset failed");
        alertController.showOkOnlyAlert("Model Error","Wasn't able to set up model");
    }

    private void onOpenCancelled(){
        Logger.debug("User cancelled open");
    }

    private void onUserDTOLoadFailed(JSONReadFailedException e) {
        Logger.error(e, "User given DTO load from json file failed");
        alertController.showOkOnlyAlert("JSON Error","Loading json file failed");
    }
}
