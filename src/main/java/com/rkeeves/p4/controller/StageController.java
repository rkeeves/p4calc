package com.rkeeves.p4.controller;

import com.rkeeves.p4.io.FXMLLoadFailedException;
import com.rkeeves.p4.io.FXMLService;
import com.rkeeves.p4.model.EconomyModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.stage.Stage;
import lombok.Builder;

import java.util.List;

public class StageController implements StyleSheetsChangeListener, EconomyModelPreChangeListener {

    private static final String ECONOMY_PARAMETERS_VIEW_FXML_RESOURCE_NAME = "/fxml/economyParametersView.fxml";

    private static final String INGREDIENTS_VIEW_FXML_RESOURCE_NAME = "/fxml/ingredientMatrixView.fxml";

    private static final String PRODUCTS_VIEW_FXML_RESOURCE_NAME = "/fxml/productsView.fxml";

    private static final String FINANCE_CHART_VIEW_FXML_RESOURCE_NAME = "/fxml/financeChartView.fxml";

    private final MainController mainController;

    private final Stage stage;

    private final FXMLService fxmlService;

    @Builder
    private StageController(MainController mainController,
                            Stage stage,
                            EconomyModelService economyModelService,
                            FXMLService fxmlService,
                            StyleSheetService styleSheetService,
                            Runnable newAction,
                            Runnable openAction,
                            Runnable saveAsAction,
                            Runnable quitAction,
                            Runnable defaultThemeAction,
                            Runnable lightThemeAction,
                            String baseCSS) {
        this.mainController = mainController;
        this.stage = stage;
        this.fxmlService = fxmlService;
        mainController.setOnNewClicked(newAction);
        mainController.setOnOpenClicked(openAction);
        mainController.setOnSaveAsClicked(saveAsAction);
        mainController.setOnQuitClicked(quitAction);
        mainController.setOnDefaultThemeClicked(defaultThemeAction);
        mainController.setOnLightThemeClicked(lightThemeAction);
        styleSheetService.addListener(this);
        styleSheetService.setStyleSheets(List.of(baseCSS));
        economyModelService.addListener(this);
    }

    private <M, W extends Wireable<M>> Node makeView(W wireable, M model , String fxmlResourceName) throws FXMLLoadFailedException {
        var viewRootNode = fxmlService.load(wireable, fxmlResourceName);
        wireable.wireModel(model);
        return viewRootNode;
    }

    @Override
    public void onStyleSheetsChange(ObservableList<String> styleSheets) {
        var scene = stage.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll(styleSheets);
    }

    @Override
    public void preEconomyModelChange(EconomyModel newEconomyModel) throws FXMLLoadFailedException {
        var parametersView = makeView(new EconomyParametersController(), newEconomyModel.getEconomyParametersModel(), ECONOMY_PARAMETERS_VIEW_FXML_RESOURCE_NAME);
        var ingredientsView = makeView(new IngredientsController(), newEconomyModel, INGREDIENTS_VIEW_FXML_RESOURCE_NAME);
        var productsView = makeView(new ProductsViewController(), newEconomyModel, PRODUCTS_VIEW_FXML_RESOURCE_NAME);
        var financeChartView = makeView(new FinanceChartController(), newEconomyModel, FINANCE_CHART_VIEW_FXML_RESOURCE_NAME);
        mainController.resetContent(parametersView, ingredientsView, productsView, financeChartView);
    }
}
