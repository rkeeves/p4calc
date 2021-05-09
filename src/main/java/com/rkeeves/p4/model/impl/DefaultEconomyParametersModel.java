package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyParametersDTO;
import com.rkeeves.p4.model.EconomyParametersModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.rkeeves.p4.javafx.Properties.doubleProp;
import static com.rkeeves.p4.javafx.Properties.intProp;

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
                .kgPerBarrelProperty(doubleProp(dto.getKgPerBarrel()))
                .globalMarketDemandMultiplierProperty(doubleProp(dto.getGlobalMarketDemandMultiplier()))
                .consumerCountProperty(intProp(dto.getConsumerCount()))
                .globalSellPriceMultiplierProperty(doubleProp(dto.getGlobalSellPriceFactor()))
                .workersPerWorkshopProperty(intProp(dto.getWorkersPerWorkshop()))
                .wagePerWorkerProperty(intProp(dto.getWagePerWorker()))
                .fixedPropertyTaxProperty(intProp(dto.getFixedPropertyTax()))
                .fixedRunningCostProperty(intProp(dto.getFixedRunningCost()))
                .build();
    }
}
