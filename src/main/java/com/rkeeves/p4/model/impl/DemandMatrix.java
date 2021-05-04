package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// The goal of this class is to "hide" the lower triangle's elements (including main diagonal) from the client.
// When the client accesses these elements this wrapper returns instead predefined "virtual" elements.
public class DemandMatrix implements ExpressionSquareMatrix {

    private final ExpressionSquareMatrix underlyingMatrix;

    private final DoubleProperty lowerTriangleVirtualElement = new SimpleDoubleProperty(0.0);

    private final DoubleProperty mainDiagonalVirtualElement  = new SimpleDoubleProperty(1.0);

    public DemandMatrix(ExpressionSquareMatrix underlyingMatrix) {
        this.underlyingMatrix = underlyingMatrix;
    }

    @Override
    public int size() {
        return underlyingMatrix.size();
    }

    @Override
    public NumberExpression get(int row, int col) {
        if(row == col)
            return mainDiagonalVirtualElement;
        if(row > col)
            return lowerTriangleVirtualElement;
        return underlyingMatrix.get(row,col);
    }
}
