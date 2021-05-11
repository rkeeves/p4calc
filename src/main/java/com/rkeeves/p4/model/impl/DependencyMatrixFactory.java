package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;

import java.util.List;

class DependencyMatrixFactory {

    static DependencyMatrix createDependencyMatrix(List<ProductDTO> productDTOs){
        var ingredientsLowerTriangleMatrix = createIngredientsLowerTriangleMatrix(productDTOs);
        return DependencyMatrix.createByLowerTriangleValues(ingredientsLowerTriangleMatrix);
    }

    static double[][] createIngredientsLowerTriangleMatrix(List<ProductDTO> productDTOs) {
        int size = productDTOs.size();
        double[][] matrix = new double[size][];
        for (int i = 0; i < size; i++) {
            var productDTO = productDTOs.get(i);
            matrix[i] = new double[i];
            for (int j = 0; j < i; j++) {
                var ingredientDTO = productDTOs.get(j);
                matrix[i][j] = productDTO.getIngredientWeightOrZero(ingredientDTO.getName());
            }
        }
        return matrix;
    }
}
