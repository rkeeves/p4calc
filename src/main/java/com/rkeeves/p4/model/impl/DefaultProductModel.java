package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.model.*;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class DefaultProductModel implements MutableProductModel {

    private final ProductBasicPropertiesModel basicPropertiesModel;

    private final MutableProductDemandModel demandModel;

    private final ProductAssetModel assetModel;

    private final ProductFinancialModel financialModel;

    private final ProductIngredientsModel ingredientsModel;

    static DefaultProductModel create(EconomyParametersModel economyParametersModel, ProductDTO dto){
        var basicPropertiesModel = DefaultProductBasicPropertiesModel.create(dto);
        var demandModel = DefaultProductDemandModel.create(economyParametersModel, basicPropertiesModel);
        var assetModel = DefaultProductAssetModel.create(economyParametersModel, basicPropertiesModel, demandModel);
        var financialModel = DefaultProductFinancialModel.create(economyParametersModel,basicPropertiesModel, demandModel, assetModel);
        var ingredientsModel = DefaultProductIngredientsModel.create();
        return DefaultProductModel.builder()
                .basicPropertiesModel(basicPropertiesModel)
                .demandModel(demandModel)
                .assetModel(assetModel)
                .financialModel(financialModel)
                .ingredientsModel(ingredientsModel)
                .build();
    }

    @Override
    public StringProperty getNameProperty() {
        return basicPropertiesModel.getNameProperty();
    }

    @Override
    public IntegerProperty getMarketDemandFulfillmentRatioProperty() {
        return basicPropertiesModel.getMarketDemandFulfillmentRatioProperty();
    }

    @Override
    public DoubleProperty getBaseDemandInKgProperty() {
        return basicPropertiesModel.getBaseDemandInKgProperty();
    }

    @Override
    public DoubleProperty getProductionPerWorkshopProperty() {
        return basicPropertiesModel.getProductionPerWorkshopProperty();
    }

    @Override
    public DoubleProperty getBasePriceProperty() {
        return basicPropertiesModel.getBasePriceProperty();
    }

    @Override
    public NumberExpression getBaseDemandProperty() {
        return demandModel.getBaseDemandProperty();
    }

    @Override
    public NumberExpression getMarketDemandProperty() {
        return demandModel.getMarketDemandProperty();
    }

    @Override
    public NumberExpression getSumDemandProperty() {
        return demandModel.getSumDemandProperty();
    }

    public void bindSumDemandExpression(NumberExpression sumDemand) {
        demandModel.bindSumDemandExpression(sumDemand);
    }

    @Override
    public NumberExpression getWorkshopCountProperty() {
        return assetModel.getWorkshopCountProperty();
    }

    @Override
    public NumberExpression getRequiredWorkerCountProperty() {
        return assetModel.getRequiredWorkerCountProperty();
    }

    @Override
    public NumberExpression getTradeRevenueProperty() {
        return financialModel.getTradeRevenueProperty();
    }

    @Override
    public NumberExpression getWorkerWageProperty() {
        return financialModel.getWorkerWageProperty();
    }

    @Override
    public NumberExpression getPropertyTaxProperty() {
        return financialModel.getPropertyTaxProperty();
    }

    @Override
    public NumberExpression getWorkshopRunningCostProperty() {
        return financialModel.getWorkshopRunningCostProperty();
    }

    @Override
    public NumberExpression getExpenseProperty() {
        return financialModel.getExpenseProperty();
    }

    @Override
    public NumberExpression getProfitProperty() {
        return financialModel.getProfitProperty();
    }

    @Override
    public Map<ProductBasicPropertiesModel, DoubleProperty> getIngredients() {
        return ingredientsModel.getIngredients();
    }

    @Override
    public Optional<DoubleProperty> getIngredientQuantity(ProductBasicPropertiesModel ingredient) {
        return ingredientsModel.getIngredientQuantity(ingredient);
    }

    @Override
    public Optional<DoubleProperty> getIngredientQuantity(String ingredientName) {
        return ingredientsModel.getIngredientQuantity(ingredientName);
    }
}
