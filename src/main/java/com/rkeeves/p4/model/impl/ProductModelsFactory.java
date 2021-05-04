package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.javafx.ExpressionVector;
import com.rkeeves.p4.javafx.ExpressionVectorAdapter;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.ProductModel;
import javafx.beans.binding.NumberExpression;

import java.util.List;
import java.util.stream.Collectors;

import static com.rkeeves.p4.javafx.ExpressionSquareMatrices.multiplyMatrixWithVector;

public class ProductModelsFactory {

    public List<ProductModel> create(EconomyParametersModel constants, List<ProductDTO> productDTOs){
        var productModels = createProductModels(constants, productDTOs);
        var marketDemandsVector = createMarketDemandVector(productModels);
        var ingredientsLowerTriangleMatrix = createIngredientsLowerTriangleMatrix(productDTOs);
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(ingredientsLowerTriangleMatrix);
        var demandMatrix = new DemandMatrix(dependencyMatrix);
        var sumDemands = multiplyMatrixWithVector(demandMatrix, marketDemandsVector);
        bindSumDemandsToProductModels(productModels, sumDemands);
        populateIngredientMaps(productModels, dependencyMatrix);
        return mapToProductModels(productModels);
    }

    private double[][] createIngredientsLowerTriangleMatrix(List<ProductDTO> productDTOs) {
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

    private List<DefaultProductModel> createProductModels(EconomyParametersModel constants, List<ProductDTO> productDTOs){
        return productDTOs.stream()
                .sequential()
                .map(productDTO->DefaultProductModel.create(constants, productDTO))
                .collect(Collectors.toList());
    }

    private ExpressionVector createMarketDemandVector(List<DefaultProductModel> productModels){
        var marketDemandsArray = new NumberExpression[productModels.size()];
        for (int i = 0; i < productModels.size(); i++) {
            marketDemandsArray[i] = productModels.get(i).getMarketDemandProperty();
        }
        return new ExpressionVectorAdapter<>(marketDemandsArray);
    }

    private void bindSumDemandsToProductModels(List<DefaultProductModel> productModels, ExpressionVector sumDemands) {
        for (int i = 0; i < productModels.size(); i++) {
            productModels.get(i).bindSumDemandExpression(sumDemands.get(i));
        }
    }

    private void populateIngredientMaps(List<DefaultProductModel> productModels, DependencyMatrix dependencyMatrix) {
        for (int productIndex = 0; productIndex < productModels.size(); productIndex++) {
            var product = productModels.get(productIndex);
            var ingredients = product.getIngredients();
            for (int ingredientIndex = 0; ingredientIndex < productIndex; ingredientIndex++) {
                ingredients.put(productModels.get(ingredientIndex),dependencyMatrix.getElementOfLowerTriangle(productIndex,ingredientIndex));
            }
        }
    }

    private List<ProductModel> mapToProductModels(List<DefaultProductModel> defaultProductModels){
        return defaultProductModels.stream()
                .map(ProductModel.class::cast)
                .collect(Collectors.toList());
    }
}
