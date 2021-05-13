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

    public void showOkOnlyAlert(String title, String headerText) {
        var alert = AlertBuilder.builder()
                .setAlertType(Alert.AlertType.CONFIRMATION)
                .setTitle(title)
                .setHeaderText(headerText)
                .setButtonTypes(List.of(ButtonType.OK))
                .setStyleSheets(styleSheetService.getStyleSheets())
                .build();
        alert.showAndWait();
    }

    public Optional<ButtonType> showYesNoChoiceAlert(String title, String headerText) {
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
