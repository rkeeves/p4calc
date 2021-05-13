package com.rkeeves.p4.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class DefaultMainController implements MainController {

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

    @Override
    public void setOnNewClicked(Runnable sideEffect) {
        setMenuItemAction(menuItemNew, sideEffect);
    }

    @Override
    public void setOnOpenClicked(Runnable sideEffect) {
        setMenuItemAction(menuItemOpen, sideEffect);
    }

    @Override
    public void setOnSaveAsClicked(Runnable sideEffect) {
        setMenuItemAction(menuItemSaveAs, sideEffect);
    }

    @Override
    public void setOnQuitClicked(Runnable sideEffect) {
        setMenuItemAction(menuItemQuit, sideEffect);
    }

    @Override
    public void setOnDefaultThemeClicked(Runnable sideEffect) {
        setMenuItemAction(menuItemThemeDefault, sideEffect);
    }

    @Override
    public void setOnLightThemeClicked(Runnable sideEffect) {
        setMenuItemAction(menuItemThemeLight, sideEffect);
    }

    @Override
    public void resetContent(Node parametersView, Node ingredientsView, Node productsView, Node financeChartView) {
        tabParameters.setContent(parametersView);
        tabIngredients.setContent(ingredientsView);
        tabProducts.setContent(productsView);
        tabProfitsChart.setContent(financeChartView);
        tabPane.requestLayout();
    }

    private void setMenuItemAction(MenuItem menuItem, Runnable sideEffect){
        menuItem.setOnAction(event->sideEffect.run());
    }
}
