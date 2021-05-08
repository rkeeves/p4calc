package com.rkeeves.p4.controller;

import com.rkeeves.p4.javafx.component.TextFields;
import com.rkeeves.p4.model.EconomyParametersModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EconomyParametersController implements Wireable<EconomyParametersModel> {

    @FXML
    private TextField textFieldKgPerBarrel;

    @FXML
    private TextField textFieldGlobalMarketDemandMultiplier;

    @FXML
    private TextField textFieldConsumerCount;

    @FXML
    private TextField textFieldGlobalSellPriceMultiplier;

    @FXML
    private TextField textFieldWorkersPerWorkshop;

    @FXML
    private TextField textFieldWagePerWorker;

    @FXML
    private TextField textFieldFixedPropertyTax;

    @FXML
    private TextField textFieldFixedRunningCost;

    @Override
    public void wireModel(EconomyParametersModel parametersModel) {
        TextFields.bindBidirectional(textFieldKgPerBarrel, parametersModel.getKgPerBarrelProperty());
        TextFields.bindBidirectional(textFieldGlobalMarketDemandMultiplier, parametersModel.getGlobalMarketDemandMultiplierProperty());
        TextFields.bindBidirectional(textFieldConsumerCount, parametersModel.getConsumerCountProperty());
        TextFields.bindBidirectional(textFieldGlobalSellPriceMultiplier, parametersModel.getGlobalSellPriceMultiplierProperty());
        TextFields.bindBidirectional(textFieldWorkersPerWorkshop, parametersModel.getWorkersPerWorkshopProperty());
        TextFields.bindBidirectional(textFieldWagePerWorker, parametersModel.getWagePerWorkerProperty());
        TextFields.bindBidirectional(textFieldFixedPropertyTax, parametersModel.getFixedPropertyTaxProperty());
        TextFields.bindBidirectional(textFieldFixedRunningCost, parametersModel.getFixedRunningCostProperty());
    }
}
