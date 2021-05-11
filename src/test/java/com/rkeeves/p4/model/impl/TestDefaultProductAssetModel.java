package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dtomap.MockEconomyParametersModel;
import com.rkeeves.p4.dtomap.MockProductModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductDemandModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultProductAssetModel {

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_GivenValidDTO_ReturnsValidModel(EconomyParametersModel economyParametersModel,
                                                ProductBasicPropertiesModel basicPropertiesModel,
                                                ProductDemandModel demandModel){
        var model = DefaultProductAssetModel.create(economyParametersModel,basicPropertiesModel,demandModel);

        double productionPerWorkshop = basicPropertiesModel.getProductionPerWorkshopProperty().get();
        double sumDemand = demandModel.getSumDemandProperty().doubleValue();
        double workshopCountRational = productionPerWorkshop == 0 ? 0 : sumDemand / productionPerWorkshop;
        int workersPerWorkshop = economyParametersModel.getWorkersPerWorkshopProperty().get();

        int expectedWorkshopCount = (int) Math.ceil(workshopCountRational);
        int expectedRequiredWorkerCount = expectedWorkshopCount * workersPerWorkshop;
        assertEquals(expectedWorkshopCount, model.getWorkshopCountProperty().intValue());
        assertEquals(expectedRequiredWorkerCount, model.getRequiredWorkerCountProperty().intValue());
    }

    private static Stream<Arguments> create_provideTestCases(){
        var economyParameters0 = new MockEconomyParametersModel();
        economyParameters0.getWorkersPerWorkshopProperty().set(10);
        var productA = new MockProductModel();
        productA.getProductionPerWorkshopProperty().set(10.1);
        productA.getSumDemandProperty().set(1005.5);
        var productB = new MockProductModel();
        productA.getNameProperty().set("B");
        productA.getMarketDemandFulfillmentRatioProperty().set(130);
        productA.getBaseDemandInKgProperty().set(142.1);
        var productC = new MockProductModel();
        productA.getNameProperty().set("C");
        productA.getMarketDemandFulfillmentRatioProperty().set(0);
        productA.getBaseDemandInKgProperty().set(0);
        var economyParameters1 = new MockEconomyParametersModel();
        economyParameters0.getWorkersPerWorkshopProperty().set(1000);
        var economyParameters2 = new MockEconomyParametersModel();
        economyParameters0.getWorkersPerWorkshopProperty().set(0);
        return Stream.of(
                Arguments.of(economyParameters0, productA, productA),
                Arguments.of(economyParameters0, productB, productB),
                Arguments.of(economyParameters0, productC, productC),
                Arguments.of(economyParameters1, productA, productA),
                Arguments.of(economyParameters1, productB, productB),
                Arguments.of(economyParameters1, productC, productC),
                Arguments.of(economyParameters2, productA, productA),
                Arguments.of(economyParameters2, productB, productB),
                Arguments.of(economyParameters2, productC, productA)
        );
    }
}
