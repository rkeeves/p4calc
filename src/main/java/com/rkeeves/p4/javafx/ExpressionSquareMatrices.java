package com.rkeeves.p4.javafx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExpressionSquareMatrices {

    private ExpressionSquareMatrices(){

    }

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

    public static String toString(ExpressionSquareMatrix symmetricMatrix){
        return "[" + IntStream.range(0,symmetricMatrix.size())
                .mapToObj(row-> "[" + IntStream.range(0,symmetricMatrix.size())
                    .mapToDouble(col->symmetricMatrix.get(row,col).doubleValue())
                    .mapToObj(num->Double.toString(num))
                    .collect(Collectors.joining(", ")) + "]")
                .collect(Collectors.joining(", ")) +"]";
    }
}
