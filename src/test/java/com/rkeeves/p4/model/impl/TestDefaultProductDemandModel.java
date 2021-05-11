package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dtomap.MockEconomyParametersModel;
import com.rkeeves.p4.dtomap.MockProductModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultProductDemandModel {

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_GivenValidDTO_ReturnsValidModel(EconomyParametersModel economyParametersModel,
                                                ProductBasicPropertiesModel basicPropertiesModel){
        var model = DefaultProductDemandModel.create(economyParametersModel,basicPropertiesModel);
        int consumerCount = economyParametersModel.getConsumerCountProperty().get();
        double kgToBarrel = economyParametersModel.getKgPerBarrelProperty().get();
        double globalMarketDemandMultiplier = economyParametersModel.getGlobalMarketDemandMultiplierProperty().get();
        double marketDemandFulfillmentRatio = basicPropertiesModel.getMarketDemandFulfillmentRatioProperty().get();

        double expectedBaseDemand = kgToBarrel == 0 ? 0 : basicPropertiesModel.getBaseDemandInKgProperty().get() / kgToBarrel;
        double expectedMarketDemand = globalMarketDemandMultiplier * consumerCount * marketDemandFulfillmentRatio / 100.0 * expectedBaseDemand;
        assertEquals(expectedBaseDemand, model.getBaseDemandProperty().doubleValue());
        assertEquals(expectedMarketDemand, model.getMarketDemandProperty().doubleValue());
        assertEquals(0.0, model.getSumDemandProperty().doubleValue());
    }

    private static Stream<Arguments> create_provideTestCases(){
        var economyParameters0 = new MockEconomyParametersModel();
        var productA = new MockProductModel();
        productA.getNameProperty().set("A");
        productA.getMarketDemandFulfillmentRatioProperty().set(10);
        productA.getBaseDemandInKgProperty().set(12.1);
        var productB = new MockProductModel();
        productA.getNameProperty().set("B");
        productA.getMarketDemandFulfillmentRatioProperty().set(130);
        productA.getBaseDemandInKgProperty().set(142.1);
        var productC = new MockProductModel();
        productA.getNameProperty().set("C");
        productA.getMarketDemandFulfillmentRatioProperty().set(130);
        productA.getBaseDemandInKgProperty().set(0);
        var economyParameters1 = new MockEconomyParametersModel();
        economyParameters1.getGlobalMarketDemandMultiplierProperty().set(1.8);
        economyParameters1.getKgPerBarrelProperty().set(1701);
        economyParameters1.getConsumerCountProperty().set(12345);
        var economyParameters2 = new MockEconomyParametersModel();
        economyParameters2.getKgPerBarrelProperty().set(0.0);
        return Stream.of(
                Arguments.of(economyParameters0, productA),
                Arguments.of(economyParameters0, productB),
                Arguments.of(economyParameters0, productC),
                Arguments.of(economyParameters1, productA),
                Arguments.of(economyParameters1, productB),
                Arguments.of(economyParameters1, productC),
                Arguments.of(economyParameters2, productA),
                Arguments.of(economyParameters2, productB),
                Arguments.of(economyParameters2, productC)
        );
    }
}
