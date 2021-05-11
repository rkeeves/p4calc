package com.rkeeves.p4.controller;

import com.rkeeves.p4.javafx.ui.AlertBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AlertController {

    private final StyleSheetService styleSheetService;

    public void showDefaultDTOLoadFailedAlert() {
        showOkOnlyAlert("Default Model Error","The default json file contains errors");
    }

    public void showModelResetFailedAlert() {
        showOkOnlyAlert("Model Error","Wasn't able to set up model");
    }

    public void showUserDTOLoadFailedAlert() {
        showOkOnlyAlert("JSON Error","Loading json file failed");
    }

    public void showDTOTransformToModelFailedAlert() {
        showOkOnlyAlert("JSON Semantic Error","The json data contained semantic errors");
    }

    public void showSaveAsFailedAlert() {
        showOkOnlyAlert("Save Error","Save failed");
    }

    public void showSaveAsSuccessAlert() {
        showOkOnlyAlert("Save As","Success");
    }

    public void showModelResetSuccessAlert() {
        showOkOnlyAlert("Load","Load succeeded");
    }

    public void showTriedSaveButModelDoesNotExist() {
        showOkOnlyAlert("Save","Nothing to save yet");
    }

    public Optional<ButtonType> showQuitLastChoiceAlert() {
        return showYesNoChoiceAlert("Quit","Are you sure?");
    }

    private void showOkOnlyAlert(String title, String headerText) {
        var alert = AlertBuilder.builder()
                .setAlertType(Alert.AlertType.CONFIRMATION)
                .setTitle(title)
                .setHeaderText(headerText)
                .setButtonTypes(List.of(ButtonType.OK))
                .setStyleSheets(styleSheetService.getStyleSheets())
                .build();
        alert.showAndWait();
    }

    private Optional<ButtonType> showYesNoChoiceAlert(String title, String headerText) {
        var alert = AlertBuilder.builder()
                .setAlertType(Alert.AlertType.CONFIRMATION)
                .setTitle(title)
                .setHeaderText(headerText)
                .setButtonTypes(List.of(ButtonType.YES, ButtonType.NO))
                .setStyleSheets(styleSheetService.getStyleSheets())
                .build();
        return alert.showAndWait();
    }
}
