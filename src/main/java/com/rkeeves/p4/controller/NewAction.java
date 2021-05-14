package com.rkeeves.p4.controller;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.io.FXMLLoadFailedException;
import com.rkeeves.p4.model.DTOInvalidException;
import com.rkeeves.p4.model.EconomyModelCreatorService;
import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;

@RequiredArgsConstructor
public class NewAction implements Runnable{

    private final EconomyDTO defaultEconomyDTO;

    private final EconomyModelCreatorService economyModelCreatorService;

    private final EconomyModelService economyModelService;

    private final AlertController alertController;

    @Override
    public void run() {
        try {
            performNew();
        }catch (DTOInvalidException e){
            onDTOTransformToModelFailed(e);
        } catch (FXMLLoadFailedException e) {
            onModelResetFailed(e);
        }
    }

    private void performNew() throws DTOInvalidException, FXMLLoadFailedException {
        var newEconomyModel = economyModelCreatorService.create(defaultEconomyDTO);
        economyModelService.setEconomyModel(newEconomyModel);
        onModelResetSuccess();
    }

    private void onDTOTransformToModelFailed(DTOInvalidException e) {
        Logger.error(e,"Transforming DTO to model failed");
        alertController.showOkOnlyAlert("JSON Semantic Error",e.getMessage());
    }

    private void onModelResetSuccess() {
        Logger.debug("Model reset succeeded");
        alertController.showOkOnlyAlert("New model","Loading new (default) model succeeded");
    }

    private void onModelResetFailed(FXMLLoadFailedException e) {
        Logger.error(e, "Model reset failed");
        alertController.showOkOnlyAlert("Model Error","Wasn't able to set up model");
    }
}
