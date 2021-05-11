package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import com.rkeeves.p4.model.ProductDemandModel;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSumDemandVectorFactory {

    @ParameterizedTest
    @MethodSource("createSumDemandVector_provideTestCases")
    void createSumDemandVector_WhenGivenValidInput_ReturnValidVector(ExpressionSquareMatrix dependencyMatrix,
                                                                     List<? extends ProductDemandModel> demandModels,
                                                                     double[] expectedSumDemandVector){
        var actualSumDemandVector = SumDemandVectorFactory.createSumDemandVector(dependencyMatrix, demandModels);
        assertEquals(expectedSumDemandVector.length,actualSumDemandVector.size());
        for (int i = 0; i < expectedSumDemandVector.length; i++) {
            assertEquals(expectedSumDemandVector[i], actualSumDemandVector.get(i).doubleValue());
        }
    }

    private static Stream<Arguments> createSumDemandVector_provideTestCases() {
        return Stream.of(
                caseZero(),
                caseOne(),
                caseTwo(),
                caseThree(),
                caseFour()
        );
    }

    private static Arguments caseZero() {
        var demandArray = new double[][]{};
        var marketDemands = new double[]{};
        var sumDemands = new double[]{};
        return Arguments.of(makeDemandMatrix(demandArray),
                makeMarketDemandsModels(marketDemands),
                sumDemands);
    }
    
    private static Arguments caseOne() {
        var demandArray = new double[][]{
                {0.0}
        };
        var marketDemands = new double[]{1.0};
        var sumDemands = new double[]{1.0};
        return Arguments.of(makeDemandMatrix(demandArray),
                makeMarketDemandsModels(marketDemands),
                sumDemands);
    }

    private static Arguments caseTwo() {
        var demandArray = new double[][]{
                {0.0, 1.0},
                {1.0, 0.0}
        };
        var marketDemands = new double[]{1.0, 10.0};
        var sumDemands = new double[]{11.0, 10.0};
        return Arguments.of(makeDemandMatrix(demandArray),
                makeMarketDemandsModels(marketDemands),
                sumDemands);
    }

    private static Arguments caseThree() {
        var demandArray = new double[][]{
                {0.0, 1.0, 5.0},
                {1.0, 0.0, 3.0},
                {2.0, 3.0, 0.0}
        };
        var marketDemands = new double[]{1.0, 10.0, 100.0};
        var sumDemands = new double[]{511.0, 310.0, 100.0};
        return Arguments.of(makeDemandMatrix(demandArray),
                makeMarketDemandsModels(marketDemands),
                sumDemands);
    }

    private static Arguments caseFour() {
        var demandArray = new double[][]{
                {0.0, 1.0, 5.0, 39.0},
                {1.0, 0.0, 3.0, 23.0},
                {2.0, 3.0, 0.0, 6.0},
                {4.0, 5.0, 6.0, 0.0}
        };
        var marketDemands = new double[]{1.0, 10.0, 100.0, 1000.0};
        var sumDemands = new double[]{39511.0, 23310.0, 6100.0, 1000.0};
        return Arguments.of(makeDemandMatrix(demandArray),
                makeMarketDemandsModels(marketDemands),
                sumDemands);
    }

    private static ExpressionSquareMatrix makeDemandMatrix(double[][] demandArrayArray){
        return new SimpleExpressionSquareMatrix(demandArrayArray);
    }

    static class MockSumDemandModel implements ProductDemandModel{

        private final SimpleDoubleProperty marketDemand;

        MockSumDemandModel(double marketDemand){
            this.marketDemand = new SimpleDoubleProperty(marketDemand);
        }

        @Override
        public NumberExpression getBaseDemandProperty() {
            return null;
        }

        @Override
        public NumberExpression getMarketDemandProperty() {
            return marketDemand;
        }

        @Override
        public NumberExpression getSumDemandProperty() {
            return null;
        }
    }

    private static List<? extends ProductDemandModel> makeMarketDemandsModels(double[] marketDemands){
        return Arrays.stream(marketDemands)
                .mapToObj(MockSumDemandModel::new)
                .collect(Collectors.toList());
    }

    @RequiredArgsConstructor
    static class SimpleExpressionSquareMatrix implements ExpressionSquareMatrix {

        private final double[][] array;

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public NumberExpression get(int row, int col) {
            return new SimpleDoubleProperty(array[row][col]);
        }
    }
}
