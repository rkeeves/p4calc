package com.rkeeves.p4.controller;

import javafx.collections.ObservableList;

public interface StyleSheetService {

    ObservableList<String> getStyleSheets();

    void changeThemeToDefault();

    void changeThemeToLight();
}
