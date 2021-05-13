package com.rkeeves.p4;

import com.rkeeves.p4.controller.*;
import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.dtomap.DefaultEconomyDTOService;
import com.rkeeves.p4.io.*;
import com.rkeeves.p4.model.impl.DefaultEconomyModelCreatorService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.util.List;

/**
 * This entity is the {@code JavaFX} {@code Application} itself.
 */
public class CalculatorApplication extends Application {

    private static final String MAIN_VIEW_FXML = "/fxml/mainView.fxml";

    private static final String DEFAULT_ECONOMY_DATA_JSON_RESOURCE_NAME = "/data/defaultEconomyData.json";

    private static final String BASE_CSS = resourceExternalForm("/css/base.css");

    private static final String LIGHT_CSS = resourceExternalForm("/css/light-theme.css");

    private static String resourceExternalForm(String resourceName){
        return DefaultStyleSheetService.class.getResource(resourceName).toExternalForm();
    }

    private FXMLService fxmlService;

    private StyleSheetService styleSheetService;

    private NewAction newAction;

    private OpenAction openAction;

    private SaveAsAction saveAsAction;

    private QuitAction quitAction;

    private ChangeStyleSheetAction defaultThemeAction;

    private ChangeStyleSheetAction lightThemeAction;

    private EconomyModelService economyModelService;

    private StageController stageController;
    /**
     * This method gets called after an {@code CalculatorApplication} instance is constructed.
     * This serves as a way to setup dependencies.
     */
    @Override
    public void init() {
        Logger.debug("Application initialization started");
        var jsonService = new JacksonJSONService();
        EconomyDTO defaultEconomyDTO;
        try {
            defaultEconomyDTO = jsonService.readFromResource(DEFAULT_ECONOMY_DATA_JSON_RESOURCE_NAME, EconomyDTO.class);
        } catch (JSONReadFailedException e) {
            Logger.error("Loading default economy json data failed.",e);
            Platform.exit();
            return;
        }
        fxmlService = new DefaultFXMLService();
        styleSheetService = new DefaultStyleSheetService();
        var economyModelService = new DefaultEconomyModelCreatorService();
        var economyDTOService = new DefaultEconomyDTOService();
        var alertController = new AlertController(styleSheetService);
        var fileChooserController = new FileChooserController();
        this.economyModelService = new DefaultEconomyModelService();
        newAction = new NewAction(defaultEconomyDTO, economyModelService, this.economyModelService, alertController);
        openAction = new OpenAction(fileChooserController,jsonService,economyModelService, this.economyModelService, alertController);
        saveAsAction = new SaveAsAction(this.economyModelService,jsonService,economyDTOService,fileChooserController,alertController);
        quitAction = new QuitAction(alertController);
        defaultThemeAction = new ChangeStyleSheetAction(styleSheetService, List.of(BASE_CSS));
        lightThemeAction = new ChangeStyleSheetAction(styleSheetService, List.of(BASE_CSS, LIGHT_CSS));
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
            var mainController = new DefaultMainController();
            VBox vBox = fxmlService.load(mainController,MAIN_VIEW_FXML);
            var scene = new Scene(vBox);
            stage.setTitle("Calculator");
            stage.setMaximized(true);
            stage.setScene(scene);
            stageController = StageController.builder()
                    .mainController(mainController)
                    .stage(stage)
                    .economyModelService(economyModelService)
                    .fxmlService(fxmlService)
                    .styleSheetService(styleSheetService)
                    .newAction(newAction)
                    .openAction(openAction)
                    .saveAsAction(saveAsAction)
                    .quitAction(quitAction)
                    .defaultThemeAction(defaultThemeAction)
                    .lightThemeAction(lightThemeAction)
                    .baseCSS(BASE_CSS)
                    .build();
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
