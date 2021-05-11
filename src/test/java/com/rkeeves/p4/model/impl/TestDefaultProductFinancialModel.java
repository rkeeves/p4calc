package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dtomap.MockEconomyParametersModel;
import com.rkeeves.p4.dtomap.MockProductModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.ProductAssetModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductDemandModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultProductFinancialModel {


    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_GivenValidDTO_ReturnsValidModel(EconomyParametersModel parametersModel,
                                                ProductBasicPropertiesModel basicPropertiesModel,
                                                ProductDemandModel demandModel,
                                                ProductAssetModel productAssetModel){
        var model = DefaultProductFinancialModel.create(parametersModel,
                basicPropertiesModel,
                demandModel,
                productAssetModel);

        double propertyTaxPerBuilding = parametersModel.getFixedPropertyTaxProperty().doubleValue();
        double sellPriceFactor = parametersModel.getGlobalSellPriceMultiplierProperty().doubleValue();
        double runningCostPerBuilding = parametersModel.getFixedRunningCostProperty().doubleValue();
        double wagePerWorker = parametersModel.getWagePerWorkerProperty().doubleValue();
        double basePrice = basicPropertiesModel.getBasePriceProperty().doubleValue();
        double marketDemand = demandModel.getMarketDemandProperty().doubleValue();
        double requiredWorkerCount = productAssetModel.getRequiredWorkerCountProperty().doubleValue();
        double workshopCount = productAssetModel.getWorkshopCountProperty().doubleValue();

        double expectedTradeRevenue = Math.floor(marketDemand * basePrice * sellPriceFactor);
        double expectedWorkerWage = requiredWorkerCount * wagePerWorker;
        double expectedPropertyTax = workshopCount * propertyTaxPerBuilding;
        double expectedWorkshopRunningCost = workshopCount * runningCostPerBuilding;
        double expectedExpense = expectedWorkerWage + expectedPropertyTax + expectedWorkshopRunningCost;
        double expectedProfit = expectedTradeRevenue - expectedExpense;

        assertEquals(expectedTradeRevenue, model.getTradeRevenueProperty().doubleValue());
        assertEquals(expectedWorkerWage, model.getWorkerWageProperty().doubleValue());
        assertEquals(expectedPropertyTax, model.getPropertyTaxProperty().doubleValue());
        assertEquals(expectedWorkshopRunningCost, model.getWorkshopRunningCostProperty().doubleValue());
        assertEquals(expectedExpense, model.getExpenseProperty().doubleValue());
        assertEquals(expectedProfit, model.getProfitProperty().doubleValue());
    }

    private static Stream<Arguments> create_provideTestCases(){
        var parameters0 = new MockEconomyParametersModel();
        parameters0.getGlobalSellPriceMultiplierProperty().set(0.8);
        parameters0.getWagePerWorkerProperty().set(10);
        parameters0.getFixedPropertyTaxProperty().set(10);
        parameters0.getFixedRunningCostProperty().set(103);

        var parameters1 = new MockEconomyParametersModel();
        parameters1.getGlobalSellPriceMultiplierProperty().set(1.4);
        parameters1.getWagePerWorkerProperty().set(11);
        parameters1.getFixedPropertyTaxProperty().set(15);
        parameters1.getFixedRunningCostProperty().set(13);

        var productA = new MockProductModel();
        productA.getBasePriceProperty().set(10.1);
        productA.getMarketDemandProperty().set(1005.5);
        productA.getRequiredWorkerCountProperty().set(455);
        productA.getWorkshopCountProperty().set(45);

        var productB = new MockProductModel();
        productB.getBasePriceProperty().set(106.1);
        productB.getMarketDemandProperty().set(175.5);
        productB.getRequiredWorkerCountProperty().set(55);
        productB.getWorkshopCountProperty().set(465);

        var productC = new MockProductModel();
        productC.getBasePriceProperty().set(17.1);
        productC.getMarketDemandProperty().set(15.5);
        productC.getRequiredWorkerCountProperty().set(5);
        productC.getWorkshopCountProperty().set(6);

        return Stream.of(
                Arguments.of(parameters0, productA, productA, productA),
                Arguments.of(parameters0, productB, productB, productB),
                Arguments.of(parameters0, productC, productC, productC),
                Arguments.of(parameters1, productA, productA, productA),
                Arguments.of(parameters1, productB, productB, productB),
                Arguments.of(parameters1, productC, productC, productC)
        );
    }
}
