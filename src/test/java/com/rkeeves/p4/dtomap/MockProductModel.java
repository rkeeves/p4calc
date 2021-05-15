package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.model.MutableProductModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class MockProductModel implements MutableProductModel {

    private final StringProperty nameProperty  = new SimpleStringProperty("");

    private final IntegerProperty marketDemandFulfillmentRatioProperty = new SimpleIntegerProperty(100);

    private final DoubleProperty baseDemandInKgProperty = new SimpleDoubleProperty(2.5);

    private final DoubleProperty productionPerWorkshopProperty = new SimpleDoubleProperty(6.0);

    private final DoubleProperty basePriceProperty = new SimpleDoubleProperty(33.0);

    private final DoubleProperty baseDemandProperty = new SimpleDoubleProperty(2.5);

    private final DoubleProperty marketDemandProperty = new SimpleDoubleProperty();

    private final DoubleProperty sumDemandProperty = new SimpleDoubleProperty();

    private final IntegerProperty workshopCountProperty = new SimpleIntegerProperty();

    private final IntegerProperty requiredWorkerCountProperty  = new SimpleIntegerProperty();

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

    @Override
    public void bindSumDemandExpression(NumberExpression sumDemandExpression) {
        this.sumDemandProperty.bind(sumDemandExpression);
    }
}
