package com.rkeeves.p4;

import com.rkeeves.p4.controller.*;
import com.rkeeves.p4.dto.DefaultEconomyDTOService;
import com.rkeeves.p4.dto.EconomyDTOService;
import com.rkeeves.p4.io.*;
import com.rkeeves.p4.model.EconomyModelService;
import com.rkeeves.p4.model.impl.DefaultEconomyModelService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

public class CalculatorApplication extends Application {

    private JSONService jsonService;

    private FXMLService fxmlService;

    private EconomyModelService economyModelService;

    private EconomyDTOService economyDTOService;

    private StyleSheetService styleSheetService;

    private AlertController alertController;

    private FileChooserController fileChooserController;

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

    @Override
    public void stop() throws Exception {
        Logger.debug("Application stopping");
        super.stop();
    }
}
