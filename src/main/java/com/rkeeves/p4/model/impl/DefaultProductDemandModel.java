package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.MathBindings;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.MutableProductDemandModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class DefaultProductDemandModel implements MutableProductDemandModel {

    private final NumberExpression baseDemandProperty;

    private final NumberExpression marketDemandProperty;

    private final DoubleProperty sumDemandProperty;

    public void bindSumDemandExpression(NumberExpression sumDemandExpression){
        sumDemandProperty.bind(sumDemandExpression);
    }

    static MutableProductDemandModel create(EconomyParametersModel economyParametersModel,
                                            ProductBasicPropertiesModel productBasicPropertiesModel){
        var baseDemand =  MathBindings.divideDefaultsIfDivisorZero(productBasicPropertiesModel.getBaseDemandInKgProperty(),
                economyParametersModel.getKgPerBarrelProperty(),
                0);
        var marketDemand = economyParametersModel.getGlobalMarketDemandMultiplierProperty()
                .multiply(economyParametersModel.getConsumerCountProperty())
                .multiply(productBasicPropertiesModel.getMarketDemandFulfillmentRatioProperty())
                .divide(100)
                .multiply(baseDemand);
        return DefaultProductDemandModel.builder()
                .baseDemandProperty(baseDemand)
                .marketDemandProperty(marketDemand)
                .sumDemandProperty(new SimpleDoubleProperty())
                .build();
    }
}
