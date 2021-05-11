package com.rkeeves.p4.model.impl;

import java.util.List;
import java.util.Optional;

class DependencyMatrixFactory {

    static DependencyMatrix createDependencyMatrix(List<DefaultProductModel> productModels){
        var ingredientsLowerTriangleMatrix = createIngredientsLowerTriangleMatrix(productModels);
        return DependencyMatrix.createByLowerTriangleValues(ingredientsLowerTriangleMatrix);
    }

    static double[][] createIngredientsLowerTriangleMatrix(List<DefaultProductModel> productModels) {
        int size = productModels.size();
        double[][] matrix = new double[size][];
        for (int i = 0; i < size; i++) {
            var productDTO = productModels.get(i);
            matrix[i] = new double[i];
            for (int j = 0; j < i; j++) {
                var ingredientDTO = productModels.get(j);
                var quantity = productDTO.getIngredientQuantity(ingredientDTO);
                matrix[i][j] = quantity.flatMap(o-> Optional.of(o.doubleValue())).orElse(0.0);
            }
        }
        return matrix;
    }
}
