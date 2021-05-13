package com.rkeeves.p4.controller;


import javafx.collections.ObservableList;

public interface StyleSheetsChangeListener {

    void onStyleSheetsChange(ObservableList<String> newStyleSheets);
}
