package com.rkeeves.p4.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

public class DefaultStyleSheetService implements StyleSheetService{

    private static final String baseCSS = resourceExternalForm("/css/base.css");

    private static final String lightCSS = resourceExternalForm("/css/light-theme.css");

    @Getter
    private ObservableList<String> styleSheets = FXCollections.observableArrayList(baseCSS);

    private static String resourceExternalForm(String resourceName){
        return DefaultStyleSheetService.class.getResource(resourceName).toExternalForm();
    }

    @Override
    public void changeThemeToDefault() {
        if(styleSheets.contains(lightCSS)){
            styleSheets.remove(lightCSS);
        }
    }

    @Override
    public void changeThemeToLight() {
        if(!styleSheets.contains(lightCSS)){
            styleSheets.add(lightCSS);
        }
    }
}
