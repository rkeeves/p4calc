package com.rkeeves.p4.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DefaultStyleSheetService implements StyleSheetService{

    private final List<StyleSheetsChangeListener> listeners = new ArrayList<>();

    @Getter
    private final ObservableList<String> styleSheets =  FXCollections.observableArrayList();

    @Override
    public void addListener(StyleSheetsChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setStyleSheets(List<String> newStyleSheets) {
        styleSheets.clear();
        styleSheets.addAll(newStyleSheets);
        listeners.forEach(listener->listener.onStyleSheetsChange(styleSheets));
    }
}
