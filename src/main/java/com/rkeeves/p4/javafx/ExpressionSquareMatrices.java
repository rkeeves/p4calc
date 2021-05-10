package com.rkeeves.p4.javafx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A static helper class for methods dealing with expression square matrices.
 */
public class ExpressionSquareMatrices {

    private ExpressionSquareMatrices(){

    }

    /**
     * Returns a fully populated matrix of "size" rows and "size" columns.
     *
     * @param size the amount of rows and colummns in the square matrix
     * @return square matrix
     */
    public static DoubleProperty[][] doublePropertySquareMatrix(int size){
        DoubleProperty[][] matrix = new DoubleProperty[size][];
        for (int i = 0; i < size; i++) {
            matrix[i] = new DoubleProperty[size];
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new SimpleDoubleProperty(0.0);
            }
        }
        return matrix;
    }

    /**
     * Returns the result of a matrix vector multiplication as an expression vector.
     *
     * @param symmetricMatrix matrix to multiply with
     * @param vector vector to multiply
     * @return the product vector
     */
    public static ExpressionVector multiplyMatrixWithVector(ExpressionSquareMatrix symmetricMatrix,
                                                            ExpressionVector vector){
        if(vector.size() != symmetricMatrix.size())
            throw new IndexOutOfBoundsException("Symmetric matrix and vector had incompatible sizes");
        NumberExpression[] resultVector = new NumberExpression[vector.size() ];
        for (int row = 0; row < resultVector.length; row++) {
            resultVector[row] = Bindings.createDoubleBinding(() -> 0.0);
            for (int col = 0; col < vector.size() ; col++) {
                var pairProduct = symmetricMatrix.get(row,col).multiply(vector.get(col));
                resultVector[row] = resultVector[row].add(pairProduct);
            }
        }
        return new ArrayExpressionVector(resultVector);
    }

    /**
     * Returns a primitive double matrix whose elements are the
     * current values of the symmetric matrix.
     *
     * @param symmetricMatrix the symmetric matrix to be transformed
     * @return primitive double matrix representation of the matrix's current state
     */
    public static double[][] toArray(ExpressionSquareMatrix symmetricMatrix){
        int size = symmetricMatrix.size();
        var primitiveMatrix = new double[size][];
        for (int i = 0; i < size; i++) {
            primitiveMatrix[i] = new double[size];
            for (int j = 0; j < size; j++) {
                primitiveMatrix[i][j] = symmetricMatrix.get(i,j).doubleValue();
            }
        }
        return primitiveMatrix;
    }

    /**
     * Returns a string representation of the current values of the symmetric matrix.
     *
     * @param symmetricMatrix the symmetric matrix to be transformed
     * @return string representation of the matrix's current state
     */
    public static String toString(ExpressionSquareMatrix symmetricMatrix){
        return "[" + IntStream.range(0,symmetricMatrix.size())
                .mapToObj(row-> "[" + IntStream.range(0,symmetricMatrix.size())
                    .mapToDouble(col->symmetricMatrix.get(row,col).doubleValue())
                    .mapToObj(num->Double.toString(num))
                    .collect(Collectors.joining(", ")) + "]")
                .collect(Collectors.joining(", ")) +"]";
    }
}
