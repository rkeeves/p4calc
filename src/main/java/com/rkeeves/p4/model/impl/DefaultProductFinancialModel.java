package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.MathBindings;
import com.rkeeves.p4.model.*;
import javafx.beans.binding.NumberExpression;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultProductFinancialModel implements ProductFinancialModel {

    private final NumberExpression tradeRevenueProperty;

    private final NumberExpression workerWageProperty;

    private final NumberExpression propertyTaxProperty;

    private final NumberExpression workshopRunningCostProperty;

    private final NumberExpression expenseProperty;

    private final NumberExpression profitProperty;

    public static DefaultProductFinancialModel create(EconomyParametersModel economyParametersModel,
                                                      ProductBasicPropertiesModel productBasicPropertiesModel,
                                                      ProductDemandModel productDemandModel,
                                                      ProductAssetModel productAssetModel){
        var marketDemand = productDemandModel.getMarketDemandProperty();
        var basePrice = productBasicPropertiesModel.getBasePriceProperty();
        var tradeRevenue = MathBindings.floor(marketDemand.multiply(basePrice).multiply(economyParametersModel.getGlobalSellPriceMultiplierProperty()));
        var workshopCount = productAssetModel.getWorkshopCountProperty();
        var workerWage = productAssetModel.getRequiredWorkerCountProperty().multiply(economyParametersModel.getWagePerWorkerProperty());
        var propertyTax = workshopCount.multiply(economyParametersModel.getFixedPropertyTaxProperty());
        var workshopRunningCost = workshopCount.multiply(economyParametersModel.getFixedRunningCostProperty());
        var expense = workerWage.add(propertyTax).add(workshopRunningCost);
        var profit = tradeRevenue.subtract(expense);
        return DefaultProductFinancialModel.builder()
                .tradeRevenueProperty(tradeRevenue)
                .workerWageProperty(workerWage)
                .propertyTaxProperty(propertyTax)
                .workshopRunningCostProperty(workshopRunningCost)
                .expenseProperty(expense)
                .profitProperty(profit)
                .build();
    }
}
