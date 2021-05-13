package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;


/**
 * An {@code ExpressionVector} a vector whose elements are {@code NumberExpression}s.
 */
public interface ExpressionVector {

    /**
     * Returns the size of the {@code ExpressionVector}.
     *
     * @return size of the {@code ExpressionVector}
     */
    int size();

    /**
     * Returns the {@code NumberExpression} at the user given index.
     *
     * @param index the index of the queried element
     * @return {@code NumberExpression} at the user given index
     */
    NumberExpression get(int index);
}
