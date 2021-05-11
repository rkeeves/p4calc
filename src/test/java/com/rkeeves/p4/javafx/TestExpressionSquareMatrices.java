package com.rkeeves.p4.javafx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestExpressionSquareMatrices {

    @RequiredArgsConstructor
    static class ExpressionSymmetricMatrixAdapter implements ExpressionSquareMatrix{

        private final double[][] array;

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public NumberExpression get(int row, int col) {
            return Bindings.createDoubleBinding(()->array[row][col]);
        }
    }

    static ExpressionVector vec(double[] defaultValues){
        NumberExpression[] array = new DoubleExpression[defaultValues.length];
        for (int i = 0; i < defaultValues.length; i++) {
            array[i] = new SimpleDoubleProperty(defaultValues[i]);
        }
        return new ExpressionVectorAdapter<>(array);
    }

    static ExpressionSymmetricMatrixAdapter symMatrix(double[][] matrix){
        return new ExpressionSymmetricMatrixAdapter(matrix);
    }

    @ParameterizedTest
    @MethodSource("doublePropertySquareMatrix_provideTestCases")
    void doublePropertySquareMatrix_WhenGivenSize_ReturnsPopulatedMatrix(int size){
        var matrix = ExpressionSquareMatrices.doublePropertySquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(0.0, matrix[i][j].doubleValue());
            }
        }
    }

    private static Stream<Arguments> doublePropertySquareMatrix_provideTestCases(){
        return Stream.of(
                Arguments.of(0),
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(10)
        );
    }

    @ParameterizedTest
    @MethodSource("toArray_provideTestCases")
    void toArray_WhenGivenExpressionMatrix_ReturnsValidPrimitiveDoubleMatrix(ExpressionSquareMatrix matrix, double[][] expectedMatrix){
        var actual = ExpressionSquareMatrices.toArray(matrix);
        assertArrayEquals(expectedMatrix, actual);
    }

    private static Stream<Arguments> toArray_provideTestCases(){
        var arr0 = new double[][]{};
        var arr1 = new double[][]{
                {1.0}
        };
        var arr2 = new double[][]{
                {1.0, 2.0},
                {3.0, 4.0}
        };
        var arr3 = new double[][]{
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        return Stream.of(
                Arguments.of(symMatrix(arr0),arr0),
                Arguments.of(symMatrix(arr1),arr1),
                Arguments.of(symMatrix(arr2),arr2),
                Arguments.of(symMatrix(arr3),arr3)
        );
    }

    @ParameterizedTest
    @MethodSource("multiplyMatrixWithVector_provideTestCasesForCompatibleSizes")
    void multiplyMatrixWithVector_WhenGivenMatrixAndVectorOfCompatibleSizes_ReturnsProduct(ExpressionSquareMatrix matrix,
                                                                                           ExpressionVector vector,
                                                                                           ExpressionVector expectedProductVector){
        var actualProductVector = ExpressionSquareMatrices.multiplyMatrixWithVector(matrix, vector);
        assertEquals(expectedProductVector.size(), actualProductVector.size());
        for (int i = 0; i < expectedProductVector.size(); i++) {
            assertEquals(expectedProductVector.get(i).doubleValue(),actualProductVector.get(i).doubleValue());
        }
    }

    private static Stream<Arguments> multiplyMatrixWithVector_provideTestCasesForCompatibleSizes(){
        var matrix0 = new double[][]{};
        var vector0 = new double[]{};
        var product0 = new double[]{};
        var matrix1 = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        var vector1 = new double[]{1,10,100};
        var product1 = new double[]{321, 654, 987};
        var matrix2 = new double[][]{
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1},
        };
        var vector2 = new double[]{100,10,1};
        var product2 = new double[]{987, 654, 321};
        var matrix3 = new double[][]{
                {1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8},
                {4, 5, 6, 7, 8, 9},
                {5, 6, 7, 8, 9, 1},
                {6, 7, 8, 9, 1, 2},
        };
        var vector3 = new double[]{100000,10000,1000,100,10,1};
        var product3 = new double[]{123456, 234567, 345678, 456789, 567891, 678912};
        return Stream.of(
                Arguments.of(symMatrix(matrix0),vec(vector0),vec(product0)),
                Arguments.of(symMatrix(matrix1),vec(vector1),vec(product1)),
                Arguments.of(symMatrix(matrix2),vec(vector2),vec(product2)),
                Arguments.of(symMatrix(matrix3),vec(vector3),vec(product3))
        );
    }

    @ParameterizedTest
    @MethodSource("multiplyMatrixWithVector_provideTestCasesForIncompatibleSizes")
    void multiplyMatrixWithVector_WhenGivenMatrixAndVectorOfIncompatibleSizes_Throws(ExpressionSquareMatrix matrix, ExpressionVector vector){
        assertThrows(IndexOutOfBoundsException.class, ()->ExpressionSquareMatrices.multiplyMatrixWithVector(matrix, vector));
    }

    private static Stream<Arguments> multiplyMatrixWithVector_provideTestCasesForIncompatibleSizes(){
        var matrix = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        var vector0 = new double[]{};
        var vector1 = new double[]{1};
        var vector2 = new double[]{1, 2};
        var vector4 = new double[]{1,2,3,4};
        return Stream.of(
                Arguments.of(symMatrix(matrix),vec(vector0)),
                Arguments.of(symMatrix(matrix),vec(vector1)),
                Arguments.of(symMatrix(matrix),vec(vector2)),
                Arguments.of(symMatrix(matrix),vec(vector4))
        );
    }
}
