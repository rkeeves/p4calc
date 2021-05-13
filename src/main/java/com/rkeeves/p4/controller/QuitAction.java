package com.rkeeves.p4.controller;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;

@RequiredArgsConstructor
public class QuitAction implements Runnable {

    private final AlertController alertController;

    @Override
    public void run() {
        var quitChoice = alertController.showYesNoChoiceAlert("Quit", "Are you sure?");
        if(quitChoice.isEmpty() || quitChoice.get() != ButtonType.YES){
            Logger.debug("Quit was cancelled by user");
        }else {
            Platform.exit();
        }
    }
}
