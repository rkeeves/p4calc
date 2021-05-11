package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductModel;
import javafx.beans.property.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
class MockProductModel implements ProductModel {

    private final IntegerProperty workshopCountProperty = new SimpleIntegerProperty();

    private final IntegerProperty requiredWorkerCountProperty  = new SimpleIntegerProperty();

    private final StringProperty nameProperty  = new SimpleStringProperty();

    private final IntegerProperty marketDemandFulfillmentRatioProperty = new SimpleIntegerProperty();

    private final DoubleProperty baseDemandInKgProperty = new SimpleDoubleProperty();

    private final DoubleProperty productionPerWorkshopProperty = new SimpleDoubleProperty();

    private final DoubleProperty basePriceProperty = new SimpleDoubleProperty();

    private final DoubleProperty baseDemandProperty = new SimpleDoubleProperty();

    private final DoubleProperty marketDemandProperty = new SimpleDoubleProperty();

    private final DoubleProperty sumDemandProperty = new SimpleDoubleProperty();

    private final DoubleProperty tradeRevenueProperty = new SimpleDoubleProperty();

    private final DoubleProperty workerWageProperty = new SimpleDoubleProperty();

    private final DoubleProperty propertyTaxProperty = new SimpleDoubleProperty();

    private final DoubleProperty workshopRunningCostProperty = new SimpleDoubleProperty();

    private final DoubleProperty expenseProperty = new SimpleDoubleProperty();

    private final DoubleProperty profitProperty = new SimpleDoubleProperty();

    private Map<ProductBasicPropertiesModel, DoubleProperty> ingredients = new HashMap<>();

    @Override
    public Optional<DoubleProperty> getIngredientQuantity(ProductBasicPropertiesModel ingredient) {
        return Optional.ofNullable(ingredients.get(ingredient));
    }

    @Override
    public Optional<DoubleProperty> getIngredientQuantity(String ingredientName) {
        for (var entry : ingredients.entrySet()) {
            var name = entry.getKey().getNameProperty().get();
            if(ingredientName.equals(name))
                return Optional.of(entry.getValue());
        }
        return Optional.empty();
    }
}
