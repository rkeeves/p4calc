package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.javafx.ExpressionSquareMatrices;
import javafx.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TestDependencyMatrixFactory {

    @ParameterizedTest
    @MethodSource("dependencyMatrix_provideTestCases")
    void createIngredientsLowerTriangleMatrix_WhenGivenValidProducts_ReturnsValidLowerTriangleMatrix(List<ProductDTO> dtos,
                                                                                                     double[][] expectedLowerTriangleMatrix,
                                                                                                     double[][] expectedDependencyMatrix){
        var actualLowerTriangleMatrix = DependencyMatrixFactory.createIngredientsLowerTriangleMatrix(dtos);
        assertArrayEquals(expectedLowerTriangleMatrix, actualLowerTriangleMatrix);
    }


    @ParameterizedTest
    @MethodSource("dependencyMatrix_provideTestCases")
    void createDependencyMatrix_WhenGivenValidProducts_ReturnsValidLowerTriangleMatrix(List<ProductDTO> dtos,
                                                                                       double[][] expectedLowerTriangleMatrix,
                                                                                       double[][] expectedDependencyMatrix){
        var actualDependencyMatrix = DependencyMatrixFactory.createDependencyMatrix(dtos);
        assertArrayEquals(expectedDependencyMatrix, ExpressionSquareMatrices.toArray(actualDependencyMatrix));
    }

    private static Stream<Arguments> dependencyMatrix_provideTestCases() {
        return Stream.of(caseZero(),
                caseOne(),
                caseTwo(),
                caseThree(),
                caseFour());
    }

    private static Arguments caseZero() {
        var expectedLowerTriangleMatrix = new double[][]{};
        var expectedDemandMatrix = new double[][]{};
        return Arguments.of(List.<ProductDTO>of(),
                expectedLowerTriangleMatrix,
                expectedDemandMatrix);
    }

    private static Arguments caseOne() {
        var productA = dto("A");
        var expectedLowerTriangleMatrix = new double[][]{
                {}
        };
        var expectedDemandMatrix = new double[][]{
                {0.0}
        };
        return Arguments.of(List.of(productA),
                expectedLowerTriangleMatrix,
                expectedDemandMatrix);
    }

    private static Arguments caseTwo() {
        var productA = dto("A");
        var productB  = dto("B", pair(productA,1.0));
        var expectedLowerTriangleMatrix = new double[][]{
                {},
                {1.0}
        };
        var expectedDemandMatrix = new double[][]{
                {0.0, 1.0},
                {1.0, 0.0}
        };
        return Arguments.of(List.of(productA,productB),
                expectedLowerTriangleMatrix,
                expectedDemandMatrix);
    }

    private static Arguments caseThree() {
        var productA = dto("A");
        var productB  = dto("B", pair(productA,1.0));
        var productC = dto("C", pair(productA,2.0), pair(productB,3.0));
        var expectedLowerTriangleMatrix = new double[][]{
                {},
                {1.0},
                {2.0, 3.0}
        };
        var expectedDemandMatrix = new double[][]{
                {0.0, 1.0, 5.0},
                {1.0, 0.0, 3.0},
                {2.0, 3.0, 0.0}
        };
        return Arguments.of(List.of(productA,productB,productC),
                expectedLowerTriangleMatrix,
                expectedDemandMatrix);
    }

    private static Arguments caseFour() {
        var productA = dto("A");
        var productB  = dto("B", pair(productA,1.0));
        var productC = dto("C", pair(productA,2.0), pair(productB,3.0));
        var productD = dto("D", pair(productA,4.0), pair(productB,5.0), pair(productC,6.0));

        var expectedLowerTriangleMatrix = new double[][]{
                {},
                {1.0},
                {2.0, 3.0},
                {4.0, 5.0, 6.0}
        };
        var expectedDemandMatrix = new double[][]{
                {0.0, 1.0, 5.0, 39.0},
                {1.0, 0.0, 3.0, 23.0},
                {2.0, 3.0, 0.0, 6.0},
                {4.0, 5.0, 6.0, 0.0}
        };
        return Arguments.of(List.of(productA,productB,productC, productD),
                expectedLowerTriangleMatrix,
                expectedDemandMatrix);
    }

    private static Pair<ProductDTO, Double> pair(ProductDTO dto, Double value){
        return new Pair<>(dto,value);
    }

    private static ProductDTO dto(String name, Pair<ProductDTO,Double>... ingredients){
        var dto = new ProductDTO();
        dto.setName(name);
        var ingredientMap = dto.getIngredients();
        for (var nameAndWeightPair : ingredients) {
            ingredientMap.put(nameAndWeightPair.getKey().getName(),nameAndWeightPair.getValue());
        }
        return dto;
    }
}
