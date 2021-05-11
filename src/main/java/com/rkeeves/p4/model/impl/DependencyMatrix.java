package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionSquareMatrices;
import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;

class DependencyMatrix implements ExpressionSquareMatrix, LowerTriangleMatrix {

    private final DoubleProperty[][] squareMatrix;

    static DependencyMatrix createByLowerTriangleValues(double[][] valueMatrix){
        int size = valueMatrix.length;
        var dependencyMatrix =new DependencyMatrix(ExpressionSquareMatrices.doublePropertySquareMatrix(size));
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                dependencyMatrix.getElementOfLowerTriangle(i,j).set(valueMatrix[i][j]);
            }
        }
        return dependencyMatrix;
    }

    private DependencyMatrix(DoubleProperty[][] squareMatrix) {
        this.squareMatrix = squareMatrix;
        setupBindings();
    }

    private void setupBindings(){
        int size = squareMatrix.length;
        for (int diag = 2; diag <= size; diag++) {
            var maxRow = size-diag+1;
            for (int row = 0; row < maxRow; row++) {
                var col = row+diag-1;
                DoubleBinding value = squareMatrix[col][row].add(0);
                for (int p = col-1; p > row; p--) {
                    value = value.add(squareMatrix[p][row].multiply(squareMatrix[p][col]));
                }
                squareMatrix[row][col].bind(value);
            }
        }
    }

    @Override
    public int size() {
        return squareMatrix.length;
    }

    @Override
    public NumberExpression get(int row, int col) {
        return squareMatrix[row][col];
    }

    @Override
    public DoubleProperty getElementOfLowerTriangle(int row, int col) {
        if(row <= col)
            throw new IndexOutOfBoundsException("Tried to access element outside of lower triangle");
        return squareMatrix[row][col];
    }

    double[][] toArray(){
        return ExpressionSquareMatrices.toArray(this);
    }
}
