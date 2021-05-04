package com.rkeeves.p4.model;

import javafx.beans.property.DoubleProperty;

import java.util.Map;
import java.util.Optional;

public interface ProductIngredientsModel {

    Map<ProductBasicPropertiesModel, DoubleProperty> getIngredients();

    Optional<DoubleProperty> getIngredientQuantity(String ingredientName);
}
