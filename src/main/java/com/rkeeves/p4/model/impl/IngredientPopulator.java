package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionVector;
import com.rkeeves.p4.model.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

class IngredientPopulator {

    private IngredientPopulator(){

    }

    static List<ProductModel> create(DependencyMatrix dependencyMatrix,
                                     ExpressionVector sumDemands,
                                     List<DefaultProductModel> productModels){
        bindSumDemandsToProductModels(productModels, sumDemands);
        populateIngredientMaps(productModels, dependencyMatrix);
        return productModels.stream()
                .map(ProductModel.class::cast)
                .collect(Collectors.toList());
    }

    static void bindSumDemandsToProductModels(List<DefaultProductModel> productModels, ExpressionVector sumDemands) {
        for (int i = 0; i < productModels.size(); i++) {
            productModels.get(i).bindSumDemandExpression(sumDemands.get(i));
        }
    }

    static void populateIngredientMaps(List<DefaultProductModel> productModels, DependencyMatrix dependencyMatrix) {
        for (int productIndex = 0; productIndex < productModels.size(); productIndex++) {
            var product = productModels.get(productIndex);
            var ingredients = product.getIngredients();
            for (int ingredientIndex = 0; ingredientIndex < productIndex; ingredientIndex++) {
                ingredients.put(productModels.get(ingredientIndex),dependencyMatrix.getElementOfLowerTriangle(productIndex,ingredientIndex));
            }
        }
    }
}
