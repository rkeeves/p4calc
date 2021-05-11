package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dtomap.MockProductModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductIngredientsModel;
import javafx.beans.property.SimpleDoubleProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDefaultProductIngredientsModel {

    private MockProductModel ingredient(String name){
        var model = new MockProductModel();
        model.getNameProperty().set(name);
        return model;
    }

    private void addIngredients(ProductIngredientsModel model, ProductBasicPropertiesModel... ingredients){
        var ingredientsMap = model.getIngredients();
        for (var ingredient : ingredients) {
            ingredientsMap.put(ingredient, new SimpleDoubleProperty());
        }
    }

    @Test
    void create_GivenValidDTO_ReturnsValidModel(){
        var model = DefaultProductIngredientsModel.create();
        assertEquals(0,model.getIngredients().size());
    }

    @Test
    void getIngredientQuantity_GivenIngredientName_ReturnsIngredientWeightProperty(){
        String ingredientName = "A";
        double weight = 67.8;
        var ingredientA = ingredient(ingredientName);
        var ingredientB = ingredient("B");
        var ingredientC = ingredient("C");
        var model = DefaultProductIngredientsModel.create();
        model.getIngredients().put(ingredientA, new SimpleDoubleProperty(weight));
        addIngredients(model, ingredientB,ingredientC);
        var result = model.getIngredientQuantity(ingredientName);
        assertTrue(result.isPresent());
        assertEquals(weight,result.get().doubleValue());
    }

    @Test
    void getIngredientQuantity_GivenNonIngredientName_ReturnsEmpty(){
        var ingredientA = ingredient("A");
        var ingredientB = ingredient("B");
        var ingredientC = ingredient("C");
        var model = DefaultProductIngredientsModel.create();
        addIngredients(model, ingredientA, ingredientB,ingredientC);
        var result = model.getIngredientQuantity("D");
        assertTrue(result.isEmpty());
    }

    @Test
    void getIngredientQuantity_GivenIngredient_ReturnsIngredientWeightProperty(){
        String ingredientName = "A";
        double weight = 67.8;
        var ingredientA = ingredient(ingredientName);
        var ingredientB = ingredient("B");
        var ingredientC = ingredient("C");
        var model = DefaultProductIngredientsModel.create();
        model.getIngredients().put(ingredientA, new SimpleDoubleProperty(weight));
        addIngredients(model, ingredientB,ingredientC);
        var result = model.getIngredientQuantity(ingredientA);
        assertTrue(result.isPresent());
        assertEquals(weight,result.get().doubleValue());
    }

    @Test
    void getIngredientQuantity_GivenNonIngredient_ReturnsEmpty(){
        var ingredientA = ingredient("A");
        var ingredientB = ingredient("B");
        var ingredientC = ingredient("C");
        var ingredientD = ingredient("D");
        var model = DefaultProductIngredientsModel.create();
        addIngredients(model, ingredientA, ingredientB,ingredientC);
        var result = model.getIngredientQuantity(ingredientD);
        assertTrue(result.isEmpty());
    }
}
