package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionSquareMatrices;
import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;

public class DependencyMatrix implements ExpressionSquareMatrix {

    private final DoubleProperty[][] squareMatrix;

    public static DependencyMatrix createByLowerTriangleValues(double[][] valueMatrix){
        var dependencyMatrix = DependencyMatrix.create(valueMatrix.length);
        for (int i = 1; i < valueMatrix.length; i++) {
            for (int j = 0; j < i; j++) {
                dependencyMatrix.getElementOfLowerTriangle(i,j).set(valueMatrix[i][j]);
            }
        }
        return dependencyMatrix;
    }

    public static DependencyMatrix create(int productCount){
        return new DependencyMatrix(ExpressionSquareMatrices.doublePropertySquareMatrix(productCount));
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

    public DoubleProperty getElementOfLowerTriangle(int row, int col) {
        if(row <= col)
            throw new IndexOutOfBoundsException("Tried to access element outside of lower triangle");
        return squareMatrix[row][col];
    }

    public double[][] toArray(){
        return ExpressionSquareMatrices.toArray(this);
    }

    @Override
    public String toString() {
        return"DependencyMatrix{" + ExpressionSquareMatrices.toString(this) + "}";
    }
}