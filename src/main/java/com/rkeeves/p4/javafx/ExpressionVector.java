package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;


/**
 * Represents an expression vector whose elements can be read.
 */
public interface ExpressionVector {

    /**
     * Returns the size of the expression vector.
     *
     * @return size of the expression vector
     */
    int size();

    /**
     * Returns the expression at the user given index.
     *
     * @param index the index of the queried element
     * @return expression at the user given index
     */
    NumberExpression get(int index);
}
