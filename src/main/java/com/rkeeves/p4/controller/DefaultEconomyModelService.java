package com.rkeeves.p4.controller;

import com.rkeeves.p4.io.FXMLLoadFailedException;
import com.rkeeves.p4.model.EconomyModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DefaultEconomyModelService implements EconomyModelService {

    private EconomyModel economyModel;

    private final List<EconomyModelPreChangeListener> listeners = new ArrayList<>();

    @Override
    public void setEconomyModel(EconomyModel newEconomyModel) throws FXMLLoadFailedException {
        for (var listener : listeners) {
            listener.preEconomyModelChange(newEconomyModel);
        }
        economyModel = newEconomyModel;
    }

    @Override
    public void addListener(EconomyModelPreChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public Optional<EconomyModel> getEconomyModel() {
        return Optional.ofNullable(economyModel);
    }
}
