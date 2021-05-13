package com.rkeeves.p4;

import com.rkeeves.p4.controller.*;
import com.rkeeves.p4.dtomap.DefaultEconomyDTOService;
import com.rkeeves.p4.dtomap.EconomyDTOService;
import com.rkeeves.p4.io.*;
import com.rkeeves.p4.model.EconomyModelService;
import com.rkeeves.p4.model.impl.DefaultEconomyModelService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

/**
 * This entity is the {@code JavaFX} {@code Application} itself.
 */
public class CalculatorApplication extends Application {

    private JSONService jsonService;

    private FXMLService fxmlService;

    private EconomyModelService economyModelService;

    private EconomyDTOService economyDTOService;

    private StyleSheetService styleSheetService;

    private AlertController alertController;

    private FileChooserController fileChooserController;

    /**
     * This method gets called after an {@code CalculatorApplication} instance is constructed.
     * This serves as a way to setup dependencies.
     */
    @Override
    public void init() {
        jsonService = new JacksonJSONService();
        fxmlService = new DefaultFXMLService();
        economyModelService = new DefaultEconomyModelService();
        economyDTOService = new DefaultEconomyDTOService();
        styleSheetService = new DefaultStyleSheetService();
        alertController = new AlertController(styleSheetService);
        fileChooserController = new FileChooserController();
        Logger.debug("Application initialized");
    }

    /**
     * This method gets called by {@code JavaFX} when the primary stage is ready.
     *
     * @param stage primary stage
     */
    @Override
    public void start(Stage stage) {
        try {
            Logger.debug("Application started");
            stage.setTitle("Calculator");
            stage.setMaximized(true);
            VBox vBox = fxmlService.load(new MainController(economyModelService,economyDTOService,jsonService,fxmlService,styleSheetService,alertController,fileChooserController),
                    "/fxml/mainView.fxml");

            stage.setScene(new Scene(vBox));
            var scene = stage.getScene();
            styleSheetService.changeThemeToLight();
            scene.getStylesheets().addAll(styleSheetService.getStyleSheets());
            stage.show();
        } catch (FXMLLoadFailedException e) {
            var runtimeExc = new RuntimeException("Wasn't able to load main view",e);
            Logger.error(runtimeExc, "Unrecoverable error: Main fxml load failed.");
            throw runtimeExc;
        }
    }

    /**
     * Called after {@code Platform.exit()} was called explicitly or all managed windows were closed.
     * This method was overridden only to facilitate logging.
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        Logger.debug("Application stopping");
        super.stop();
    }
}
