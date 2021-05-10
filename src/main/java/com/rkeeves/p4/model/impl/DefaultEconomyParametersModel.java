package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyParametersDTO;
import com.rkeeves.p4.model.EconomyParametersModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class DefaultEconomyParametersModel implements EconomyParametersModel {

    private final DoubleProperty kgPerBarrelProperty;

    private final DoubleProperty globalMarketDemandMultiplierProperty;

    private final IntegerProperty consumerCountProperty;

    private final DoubleProperty globalSellPriceMultiplierProperty;

    private final IntegerProperty workersPerWorkshopProperty;

    private final IntegerProperty wagePerWorkerProperty;

    private final IntegerProperty fixedPropertyTaxProperty;

    private final IntegerProperty fixedRunningCostProperty;

    public static DefaultEconomyParametersModel create(EconomyParametersDTO dto){
        return DefaultEconomyParametersModel.builder()
                .kgPerBarrelProperty(new SimpleDoubleProperty(dto.getKgPerBarrel()))
                .globalMarketDemandMultiplierProperty(new SimpleDoubleProperty(dto.getGlobalMarketDemandMultiplier()))
                .consumerCountProperty(new SimpleIntegerProperty(dto.getConsumerCount()))
                .globalSellPriceMultiplierProperty(new SimpleDoubleProperty(dto.getGlobalSellPriceFactor()))
                .workersPerWorkshopProperty(new SimpleIntegerProperty(dto.getWorkersPerWorkshop()))
                .wagePerWorkerProperty(new SimpleIntegerProperty(dto.getWagePerWorker()))
                .fixedPropertyTaxProperty(new SimpleIntegerProperty(dto.getFixedPropertyTax()))
                .fixedRunningCostProperty(new SimpleIntegerProperty(dto.getFixedRunningCost()))
                .build();
    }
}
