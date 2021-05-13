package com.rkeeves.p4.controller;

import com.rkeeves.p4.dtomap.EconomyDTOService;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JSONWriteFailedException;
import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;

@RequiredArgsConstructor
public class SaveAsAction implements Runnable{

    private final EconomyModelService economyModelService;

    private final JSONService jsonService;

    private final EconomyDTOService economyDTOService;

    private final FileChooserController fileChooserController;

    private final AlertController alertController;

    @Override
    public void run() {
        try {
            performSaveAs();
        } catch (JSONWriteFailedException e) {
            onSaveAsFailed(e);
        }
    }

    private void performSaveAs() throws JSONWriteFailedException {
        var optEconomyModel = economyModelService.getEconomyModel();
        if(optEconomyModel.isEmpty()){
            onSaveAsAndModelDoesNotExist();
            return;
        }
        var economyModel = optEconomyModel.get();
        var fileChoice = fileChooserController.chooseJSONFileForSave();
        if(fileChoice.isEmpty()){
            onSaveAsCancelled();
            return;
        }
        var dto = economyDTOService.toDto(economyModel);
        jsonService.writeToFile(fileChoice.get(),dto);
        onSaveAsSuccess();
    }

    private void onSaveAsAndModelDoesNotExist() {
        Logger.debug("User tried 'Save as' but model was null");
        alertController.showOkOnlyAlert("Save","Nothing to save yet");
    }

    private void onSaveAsCancelled() {
        Logger.debug("User cancelled 'Save as'");
    }

    private void onSaveAsFailed(JSONWriteFailedException e) {
        Logger.error(e, "'Save as' failed");
        alertController.showOkOnlyAlert("Save Error","Save failed");
    }

    private void onSaveAsSuccess() {
        Logger.debug("'Save as' succeeded");
        alertController.showOkOnlyAlert("Save As","Success");
    }
}
