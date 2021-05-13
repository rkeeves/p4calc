package com.rkeeves.p4.controller;

import com.rkeeves.p4.io.FXMLLoadFailedException;
import com.rkeeves.p4.model.EconomyModel;

import java.util.Optional;

public interface EconomyModelService {

    void setEconomyModel(EconomyModel newEconomyModel) throws FXMLLoadFailedException;

    void addListener(EconomyModelPreChangeListener listener);

    Optional<EconomyModel> getEconomyModel();
}
