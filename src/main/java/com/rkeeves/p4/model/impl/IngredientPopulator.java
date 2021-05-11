package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionVector;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import com.rkeeves.p4.model.MutableProductModel;
import com.rkeeves.p4.model.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

class IngredientPopulator {

    private IngredientPopulator(){

    }

    static List<ProductModel> create(LowerTriangleMatrix lowerTriangleMatrix,
                                     ExpressionVector sumDemands,
                                     List<? extends MutableProductModel> productModels){
        for (int i = 0; i < productModels.size(); i++) {
            productModels.get(i).bindSumDemandExpression(sumDemands.get(i));
        }
        populateIngredientMaps(productModels, lowerTriangleMatrix);
        return productModels.stream()
                .map(ProductModel.class::cast)
                .collect(Collectors.toList());
    }

    static void populateIngredientMaps(List<? extends ProductModel> productModels, LowerTriangleMatrix lowerTriangleMatrix) {
        for (int productIndex = 0; productIndex < productModels.size(); productIndex++) {
            var product = productModels.get(productIndex);
            var ingredients = product.getIngredients();
            for (int ingredientIndex = 0; ingredientIndex < productIndex; ingredientIndex++) {
                ingredients.put(productModels.get(ingredientIndex),lowerTriangleMatrix.getElementOfLowerTriangle(productIndex,ingredientIndex));
            }
        }
    }
}
