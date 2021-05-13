package com.rkeeves.p4.controller;

import com.rkeeves.p4.io.FXMLLoadFailedException;
import com.rkeeves.p4.model.EconomyModel;

public interface EconomyModelPreChangeListener {

    void preEconomyModelChange(EconomyModel newEconomyModel) throws FXMLLoadFailedException;
}
