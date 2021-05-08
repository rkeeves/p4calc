package com.rkeeves.p4.javafx.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.List;

public class AlertBuilder {

    private Alert.AlertType alertType = Alert.AlertType.CONFIRMATION;

    private String title = "Confirm";

    private String headerText = "";

    private String content = "";

    private List<ButtonType> buttonTypes = List.of(ButtonType.OK);

    private ObservableList<String> styleSheets = FXCollections.emptyObservableList();

    private AlertBuilder(){

    }

    public static AlertBuilder builder(){
        return new AlertBuilder();
    }

    public AlertBuilder setAlertType(Alert.AlertType alertType) {
        this.alertType = alertType;
        return this;
    }

    public AlertBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlertBuilder setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public AlertBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public AlertBuilder setButtonTypes(List<ButtonType> buttonTypes) {
        this.buttonTypes = buttonTypes;
        return this;
    }

    public AlertBuilder setStyleSheets(ObservableList<String> styleSheets) {
        this.styleSheets = styleSheets;
        return this;
    }

    public Alert build(){
        var alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.getButtonTypes().clear();
        alert.setContentText(content);
        var buttonTypesList = alert.getButtonTypes();
        buttonTypesList.clear();
        buttonTypesList.addAll(buttonTypes);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().addAll(styleSheets);
        return alert;
    }
}
