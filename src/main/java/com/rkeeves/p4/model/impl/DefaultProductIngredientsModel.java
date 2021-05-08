package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductIngredientsModel;
import javafx.beans.property.DoubleProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultProductIngredientsModel implements ProductIngredientsModel {

    private final Map<ProductBasicPropertiesModel, DoubleProperty> ingredients;

    public static DefaultProductIngredientsModel create(){
        return DefaultProductIngredientsModel.builder()
                .ingredients(new HashMap<>())
                .build();
    }

    @Override
    public Optional<DoubleProperty> getIngredientQuantity(ProductBasicPropertiesModel ingredient) {
        return Optional.ofNullable(ingredients.get(ingredient));
    }

    @Override
    public Optional<DoubleProperty> getIngredientQuantity(String ingredientName) {
        for (var ingredientAndQuantityPair: ingredients.entrySet()) {
            if(ingredientAndQuantityPair.getKey().getNameProperty().get().equals(ingredientName)){
                return Optional.of(ingredientAndQuantityPair.getValue());
            }
        }
        return Optional.empty();
    }
}
