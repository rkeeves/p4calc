package com.rkeeves.p4.controller;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.dto.EconomyDTOService;
import com.rkeeves.p4.io.*;
import com.rkeeves.p4.model.DTOInvalidException;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyModelService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;


@RequiredArgsConstructor
public class MainController {

    private static final String defaultEconomyDataJSONResourceName = "/data/defaultEconomyData.json";

    private static final String economyParametersViewFXMLResourceName = "/fxml/economyParametersView.fxml";

    private static final String ingredientsViewFXMLResourceName = "/fxml/ingredientMatrixView.fxml";

    private static final String productsViewFXMLResourceName = "/fxml/productsView.fxml";

    private static final String financeChartViewFXMLResourceName = "/fxml/financeChartView.fxml";

    private final EconomyModelService economyModelService;

    private final EconomyDTOService economyDTOService;

    private final JSONService jsonService;

    private final FXMLService fxmlService;

    private final StyleSheetService styleSheetService;

    private final AlertController alertController;

    private final FileChooserController fileChooserController;

    private EconomyModel economyModel;

    @FXML
    private Node rootNode;

    @FXML
    private MenuItem menuItemNew;

    @FXML
    private MenuItem menuItemOpen;

    @FXML
    private MenuItem menuItemSaveAs;

    @FXML
    private MenuItem menuItemQuit;

    @FXML
    private MenuItem menuItemThemeDefault;

    @FXML
    private MenuItem menuItemThemeLight;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabParameters;

    @FXML
    private Tab tabIngredients;

    @FXML
    private Tab tabProducts;

    @FXML
    private Tab tabProfitsChart;

    @FXML
    private void initialize(){
        menuItemNew.setOnAction(this::onNewClicked);
        menuItemOpen.setOnAction(this::onOpenClicked);
        menuItemSaveAs.setOnAction(this::onSaveAsClicked);
        menuItemQuit.setOnAction(this::onQuitClicked);
        menuItemThemeDefault.setOnAction(this::onChangeThemeToDefault);
        menuItemThemeLight.setOnAction(this::onChangeThemeToLight);
    }

    private void onNewClicked(ActionEvent evt){
        try {
            performNew();
        } catch (JSONReadFailedException e) {
            onDefaultDTOLoadFailed(e);
        }catch (DTOInvalidException e){
            onDTOTransformToModelFailed(e);
        } catch (FXMLLoadFailedException e) {
            onModelResetFailed(e);
        }
    }

    private void performNew() throws JSONReadFailedException, DTOInvalidException, FXMLLoadFailedException {
        var economyDTO =  jsonService.readFromResource(defaultEconomyDataJSONResourceName, EconomyDTO.class);
        var newEconomyModel = economyModelService.create(economyDTO);
        resetEconomyModel(newEconomyModel);
        onModelResetSuccess();
    }

    private void onOpenClicked(ActionEvent evt){
        try {
            performOpen();
        } catch (JSONReadFailedException e) {
            onUserDTOLoadFailed(e);
        } catch (DTOInvalidException e) {
            onDTOTransformToModelFailed(e);
        } catch (FXMLLoadFailedException e) {
            onModelResetFailed(e);
        }
    }

    private void performOpen() throws JSONReadFailedException, DTOInvalidException, FXMLLoadFailedException {
        var fileChoice = fileChooserController.chooseJSONFileForOpen();
        if(fileChoice.isEmpty()){
            onOpenCancelled();
            return;
        }
        var economyDTO = jsonService.readFromFile(fileChoice.get(), EconomyDTO.class);
        var newEconomyModel = economyModelService.create(economyDTO);
        resetEconomyModel(newEconomyModel);
        onModelResetSuccess();
    }

    private void onSaveAsClicked(ActionEvent evt){
        try {
            performSaveAs();
        } catch (JSONWriteFailedException e) {
            onSaveAsFailed(e);
        }
    }

    private void performSaveAs() throws JSONWriteFailedException {
        if(economyModel==null){
            onSaveAsAndModelDoesNotExist();
            return;
        }
        var fileChoice = fileChooserController.chooseJSONFileForSave();
        if(fileChoice.isEmpty()){
            onSaveAsCancelled();
            return;
        }
        var dto = economyDTOService.toDto(economyModel);
        jsonService.writeToFile(fileChoice.get(),dto);
        onSaveAsSuccess();
    }

    private void onQuitClicked(ActionEvent evt){
        var quitChoice = alertController.showQuitLastChoiceAlert();
        if(quitChoice.isEmpty()){
            onQuitCancelled();
            return;
        }
        if(quitChoice.get()==ButtonType.YES){
            Platform.exit();
        }else{
            onQuitCancelled();
        }
    }

    private void onChangeThemeToDefault(ActionEvent actionEvent){
        Logger.debug("Setting theme to default");
        styleSheetService.changeThemeToDefault();
        resetStyleSheets();
    }

    private void onChangeThemeToLight(ActionEvent actionEvent){
        Logger.debug("Setting theme to light");
        styleSheetService.changeThemeToLight();
        resetStyleSheets();
    }

    private void onSaveAsAndModelDoesNotExist() {
        Logger.debug("User tried 'Save as' but model was null");
        alertController.showTriedSaveButModelDoesNotExist();
    }

    private void onDefaultDTOLoadFailed(JSONReadFailedException e) {
        Logger.error(e,"Loading DTO from json file failed");
        alertController.showDefaultDTOLoadFailedAlert();
    }

    private void onDTOTransformToModelFailed(DTOInvalidException e) {
        Logger.error(e,"Transforming DTO to model failed");
        alertController.showDTOTransformToModelFailedAlert();
    }

    private void onModelResetSuccess() {
        Logger.debug("Model reset succeeded");
        alertController.showModelResetSuccessAlert();
    }

    private void onModelResetFailed(FXMLLoadFailedException e) {
        Logger.error(e, "Model reset failed");
        alertController.showModelResetFailedAlert();
    }

    private void onOpenCancelled(){
        Logger.debug("User cancelled open");
    }

    private void onUserDTOLoadFailed(JSONReadFailedException e) {
        Logger.error(e, "User given DTO load from json file failed");
        alertController.showUserDTOLoadFailedAlert();
    }

    private void onSaveAsCancelled() {
        Logger.debug("User cancelled 'Save as'");
    }

    private void onSaveAsFailed(JSONWriteFailedException e) {
        Logger.error(e, "'Save as' failed");
        alertController.showSaveAsFailedAlert();
    }

    private void onSaveAsSuccess() {
        Logger.debug("'Save as' succeeded");
        alertController.showSaveAsSuccessAlert();
    }

    private void onQuitCancelled(){
        Logger.debug("Quit was cancelled by user");
    }

    private void resetEconomyModel(EconomyModel newEconomyModel) throws FXMLLoadFailedException {
            // We perform the unsafe operations first, which can throw
            var parametersView = makeView(new EconomyParametersController(), newEconomyModel.getEconomyParametersModel(), economyParametersViewFXMLResourceName);
            var ingredientsView = makeView(new IngredientsController(), newEconomyModel, ingredientsViewFXMLResourceName);
            var productsView = makeView(new ProductsViewController(), newEconomyModel, productsViewFXMLResourceName);
            var financeChartView = makeView(new FinanceChartController(), newEconomyModel, financeChartViewFXMLResourceName);
            // These ones below only run if no exceptions were encountered
            tabParameters.setContent(parametersView);
            tabIngredients.setContent(ingredientsView);
            tabProducts.setContent(productsView);
            tabProfitsChart.setContent(financeChartView);
            tabPane.requestLayout();
            economyModel = newEconomyModel;
    }

    private <M, W extends Wireable<M>> Node makeView(W wireable, M model ,String fxmlResourceName) throws FXMLLoadFailedException {
        var financeChartView = fxmlService.load(wireable, fxmlResourceName);
        wireable.wireModel(model);
        return financeChartView;
    }

    private void resetStyleSheets(){
        var scene = rootNode.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll(styleSheetService.getStyleSheets());
    }
}
