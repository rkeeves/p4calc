package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

/**
 * This entity represents a square matrix whose elements are {@code NumberExpression}s.
 */
public interface ExpressionSquareMatrix {

    /**
     * Returns the size of the square matrix.
     *
     * @return size of the square matrix
     */
    int size();

    /**
     * Returns the {@code NumberExpression} from at the user given row and column.
     *
     * @param row row of the selected element
     * @param col column of the selected element
     * @return expression at the user given coordinates
     */
    NumberExpression get(int row, int col);
}
