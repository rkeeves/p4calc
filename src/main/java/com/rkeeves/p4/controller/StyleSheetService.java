package com.rkeeves.p4.controller;

import javafx.collections.ObservableList;

import java.util.List;

public interface StyleSheetService {

    ObservableList<String> getStyleSheets();

    void addListener(StyleSheetsChangeListener listener);

    void setStyleSheets(List<String> styleSheets);
}
