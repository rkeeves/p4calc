package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDemandMatrix {

    @RequiredArgsConstructor
    static class SimpleExpressionSquareMatrix implements ExpressionSquareMatrix{

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

    @ParameterizedTest
    @MethodSource("get_provideTestCasesForCoordinatesInUpperTriangle")
    void get_WhenCoordinatesIsInUpperTriangle_ReturnsElement(int row,
                                                        int col,
                                                        double expectedDoubleValue,
                                                        SimpleExpressionSquareMatrix baseMatrix){
        var demandMatrix = new DemandMatrix(baseMatrix);
        assertEquals(expectedDoubleValue,demandMatrix.get(row,col).doubleValue());
    }

    private static Stream<Arguments> get_provideTestCasesForCoordinatesInUpperTriangle(){
        var underlyingMatrix = new double[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };
        int size = underlyingMatrix.length;
        SimpleExpressionSquareMatrix baseMatrix = new SimpleExpressionSquareMatrix(underlyingMatrix);
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int row = 0; row < size; row++)
            for (int col = row + 1; col < size; col++)
                listOfArguments.add(Arguments.of(row, col, underlyingMatrix[row][col], baseMatrix));
        return listOfArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("get_provideTestCasesForCoordinatesInMainDiagonal")
    void get_WhenCoordinatesIsInMainDiagonal_ReturnsOne(int row,
                                                             int col,
                                                             SimpleExpressionSquareMatrix baseMatrix){
        var demandMatrix = new DemandMatrix(baseMatrix);
        assertEquals(1.0,demandMatrix.get(row,col).doubleValue());
    }

    private static Stream<Arguments> get_provideTestCasesForCoordinatesInMainDiagonal(){
        var underlyingMatrix = new double[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };
        int size = underlyingMatrix.length;
        SimpleExpressionSquareMatrix baseMatrix = new SimpleExpressionSquareMatrix(underlyingMatrix);
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int row = 0; row < size; row++)
            listOfArguments.add(Arguments.of(row, row, baseMatrix));
        return listOfArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("get_provideTestCasesForCoordinatesInLowerTriangle")
    void get_WhenCoordinatesIsInLowerTriangle_ReturnsZero(int row,
                                                        int col,
                                                        SimpleExpressionSquareMatrix baseMatrix){
        var demandMatrix = new DemandMatrix(baseMatrix);
        assertEquals(0.0,demandMatrix.get(row,col).doubleValue());
    }

    private static Stream<Arguments> get_provideTestCasesForCoordinatesInLowerTriangle(){
        var underlyingMatrix = new double[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };
        int size = underlyingMatrix.length;
        SimpleExpressionSquareMatrix baseMatrix = new SimpleExpressionSquareMatrix(underlyingMatrix);
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int row = 0; row < size; row++)
            for (int col = 0; col < row; col++)
                listOfArguments.add(Arguments.of(row, col, baseMatrix));
        return listOfArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("size_provideTestCases")
    void size_Always_ReturnsMatrixSize(int expectedSize, SimpleExpressionSquareMatrix baseMatrix){
        var dependencyMatrix = new DemandMatrix(baseMatrix);
        assertEquals(expectedSize,dependencyMatrix.size());
    }

    private static Stream<Arguments> size_provideTestCases(){
        return Stream.of(
                Arguments.of(0, new SimpleExpressionSquareMatrix(new double[][]{
                })),
                Arguments.of(1, new SimpleExpressionSquareMatrix(new double[][]{
                        {0}
                })),
                Arguments.of(2, new SimpleExpressionSquareMatrix(new double[][]{
                        {0, 1},
                        {2, 3}
                })),
                Arguments.of(3, new SimpleExpressionSquareMatrix(new double[][]{
                        {0, 1, 2},
                        {3, 4, 5},
                        {6, 7, 8}
                }))
        );
    }
}
