package com.rkeeves.p4.controller;

import javafx.scene.Node;

public interface MainController {

    void setOnNewClicked(Runnable sideEffect);

    void setOnOpenClicked(Runnable sideEffect);

    void setOnSaveAsClicked(Runnable sideEffect);

    void setOnQuitClicked(Runnable sideEffect);

    void setOnDefaultThemeClicked(Runnable sideEffect);

    void setOnLightThemeClicked(Runnable sideEffect);

    void resetContent(Node parametersView, Node ingredientsView, Node productsView, Node financeChartView);
}
