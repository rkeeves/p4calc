package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.dtomap.MockEconomyParametersModel;
import com.rkeeves.p4.dtomap.MockProductModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import javafx.beans.property.SimpleDoubleProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultProductModel {

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenCalled_ReturnedObjectHonorsBasicPropertiesInterface(EconomyParametersModel parametersModel, ProductDTO productDTO){
        var model = DefaultProductModel.create(parametersModel, productDTO);
        var expected = DefaultProductBasicPropertiesModel.create(productDTO);
        assertEquals(expected.getNameProperty().get(), model.getNameProperty().get());
        assertEquals(expected.getBasePriceProperty().get(), model.getBasePriceProperty().get());
        assertEquals(expected.getBaseDemandInKgProperty().get(), model.getBaseDemandInKgProperty().get());
        assertEquals(expected.getMarketDemandFulfillmentRatioProperty().get(), model.getMarketDemandFulfillmentRatioProperty().get());
        assertEquals(expected.getProductionPerWorkshopProperty().get(), model.getProductionPerWorkshopProperty().get());
    }

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenCalled_ReturnedObjectHonorsDemandInterface(EconomyParametersModel parametersModel, ProductDTO productDTO){
        var model = DefaultProductModel.create(parametersModel, productDTO);
        var basicPropertiesModel = DefaultProductBasicPropertiesModel.create(productDTO);
        var expected = DefaultProductDemandModel.create(parametersModel, basicPropertiesModel);
        double newSumDemand = 45.5;
        assertEquals(expected.getBaseDemandProperty().doubleValue(), model.getBaseDemandProperty().doubleValue());
        assertEquals(expected.getMarketDemandProperty().doubleValue(), model.getMarketDemandProperty().doubleValue());
        assertEquals(expected.getSumDemandProperty().doubleValue(), model.getSumDemandProperty().doubleValue());
        model.bindSumDemandExpression(new SimpleDoubleProperty(newSumDemand));
        assertEquals(newSumDemand, model.getSumDemandProperty().doubleValue());
    }

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenCalled_ReturnedObjectHonorsAssetInterface(EconomyParametersModel parametersModel, ProductDTO productDTO){
        var model = DefaultProductModel.create(parametersModel, productDTO);
        var basicPropertiesModel = DefaultProductBasicPropertiesModel.create(productDTO);
        var demandModel = DefaultProductDemandModel.create(parametersModel, basicPropertiesModel);
        var expected = DefaultProductAssetModel.create(parametersModel, basicPropertiesModel, demandModel);
        assertEquals(expected.getRequiredWorkerCountProperty().doubleValue(), model.getRequiredWorkerCountProperty().doubleValue());
        assertEquals(expected.getWorkshopCountProperty().doubleValue(), model.getWorkshopCountProperty().doubleValue());
    }

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenCalled_ReturnedObjectHonorsFinancialInterface(EconomyParametersModel parametersModel, ProductDTO productDTO){
        var model = DefaultProductModel.create(parametersModel, productDTO);
        var basicPropertiesModel = DefaultProductBasicPropertiesModel.create(productDTO);
        var demandModel = DefaultProductDemandModel.create(parametersModel, basicPropertiesModel);
        var assetModel = DefaultProductAssetModel.create(parametersModel, basicPropertiesModel, demandModel);
        var expected = DefaultProductFinancialModel.create(parametersModel, basicPropertiesModel, demandModel, assetModel);
        assertEquals(expected.getExpenseProperty().doubleValue(), model.getExpenseProperty().doubleValue());
        assertEquals(expected.getProfitProperty().doubleValue(), model.getProfitProperty().doubleValue());
        assertEquals(expected.getPropertyTaxProperty().doubleValue(), model.getPropertyTaxProperty().doubleValue());
        assertEquals(expected.getTradeRevenueProperty().doubleValue(), model.getTradeRevenueProperty().doubleValue());
        assertEquals(expected.getWorkerWageProperty().doubleValue(), model.getWorkerWageProperty().doubleValue());
        assertEquals(expected.getWorkshopRunningCostProperty().doubleValue(), model.getWorkshopRunningCostProperty().doubleValue());
    }

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenCalled_ReturnedObjectHonorsIngredientsInterface(EconomyParametersModel parametersModel, ProductDTO productDTO){
        var model = DefaultProductModel.create(parametersModel, productDTO);
        var expected = DefaultProductIngredientsModel.create();
        assertEquals(expected.getIngredients(), model.getIngredients());
        var ingredientA = new MockProductModel();
        assertEquals(expected.getIngredientQuantity(ingredientA), model.getIngredientQuantity(ingredientA));
        assertEquals(expected.getIngredientQuantity("A"), model.getIngredientQuantity("A"));
    }

    private static Stream<Arguments> create_provideTestCases(){
        var parameters0 = new MockEconomyParametersModel();
        parameters0.getKgPerBarrelProperty().set(1200);
        parameters0.getGlobalMarketDemandMultiplierProperty().set(1.2);
        parameters0.getConsumerCountProperty().set(14000);
        parameters0.getGlobalSellPriceMultiplierProperty().set(1.4);
        parameters0.getWorkersPerWorkshopProperty().set(30);
        parameters0.getWagePerWorkerProperty().set(30);
        parameters0.getFixedPropertyTaxProperty().set(30);
        parameters0.getFixedRunningCostProperty().set(30);

        var parameters1 = new MockEconomyParametersModel();
        parameters1.getKgPerBarrelProperty().set(1900);
        parameters1.getGlobalMarketDemandMultiplierProperty().set(1.1);
        parameters1.getConsumerCountProperty().set(24000);
        parameters1.getGlobalSellPriceMultiplierProperty().set(1.1);
        parameters1.getWorkersPerWorkshopProperty().set(10);
        parameters1.getWagePerWorkerProperty().set(1);
        parameters1.getFixedPropertyTaxProperty().set(2);
        parameters1.getFixedRunningCostProperty().set(24);

        var parameters2 = new MockEconomyParametersModel();
        parameters2.getKgPerBarrelProperty().set(0);
        parameters2.getGlobalMarketDemandMultiplierProperty().set(0);
        parameters2.getConsumerCountProperty().set(0);
        parameters2.getGlobalSellPriceMultiplierProperty().set(0);
        parameters2.getWorkersPerWorkshopProperty().set(0);
        parameters2.getWagePerWorkerProperty().set(0);
        parameters2.getFixedPropertyTaxProperty().set(0);
        parameters2.getFixedRunningCostProperty().set(0);

        var productA = new ProductDTO();
        productA.setName("A");
        productA.setBasePrice(10.1);
        productA.setBaseDemandInKg(1005.5);
        productA.setMarketDemandFulfillmentRatio(455);
        productA.setProductionPerWorkshop(4.6);

        var productB = new ProductDTO();
        productB.setName("B");
        productB.setBasePrice(44.1);
        productB.setBaseDemandInKg(15.5);
        productB.setMarketDemandFulfillmentRatio(45);
        productB.setProductionPerWorkshop(6.5);

        var productC = new ProductDTO();
        productC.setName("C");
        productC.setBasePrice(44.1);
        productC.setBaseDemandInKg(15.5);
        productC.setMarketDemandFulfillmentRatio(45);
        productC.setProductionPerWorkshop(6.5);

        return Stream.of(
                Arguments.of(parameters0, productA),
                Arguments.of(parameters0, productB),
                Arguments.of(parameters0, productC),
                Arguments.of(parameters1, productA),
                Arguments.of(parameters1, productB),
                Arguments.of(parameters1, productC),
                Arguments.of(parameters2, productA),
                Arguments.of(parameters2, productB),
                Arguments.of(parameters2, productC)
        );
    }
}
