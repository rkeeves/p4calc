package com.rkeeves.p4.javafx.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.List;

/**
 * A simple builder class to construct JavaFX alerts.
 */
public class AlertBuilder {

    private Alert.AlertType alertType = Alert.AlertType.CONFIRMATION;

    private String title = "Confirm";

    private String headerText = "";

    private String content = "";

    private List<ButtonType> buttonTypes = List.of(ButtonType.OK);

    private ObservableList<String> styleSheets = FXCollections.emptyObservableList();

    private AlertBuilder(){

    }

    /**
     * Returns a builder instance
     *
     * @return builder
     */
    public static AlertBuilder builder(){
        return new AlertBuilder();
    }

    /**
     * Sets the alertType for the builder
     *
     * @param alertType alert type
     * @return builder
     */
    public AlertBuilder setAlertType(Alert.AlertType alertType) {
        this.alertType = alertType;
        return this;
    }

    /**
     * Sets the title for the builder
     *
     * @param title title text
     * @return builder
     */
    public AlertBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the header text for the builder
     *
     * @param headerText header text
     * @return builder
     */
    public AlertBuilder setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    /**
     * Sets the content text for the builder
     *
     * @param content text content
     * @return builder
     */
    public AlertBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Sets the button types for the builder
     *
     * @param buttonTypes button types
     * @return builder
     */
    public AlertBuilder setButtonTypes(List<ButtonType> buttonTypes) {
        this.buttonTypes = buttonTypes;
        return this;
    }

    /**
     * Sets the style sheets for the builder
     *
     * @param styleSheets style sheets
     * @return builder
     */
    public AlertBuilder setStyleSheets(ObservableList<String> styleSheets) {
        this.styleSheets = styleSheets;
        return this;
    }

    /**
     * Constructs a new alert object
     *
     * @return alert isntance
     */
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
