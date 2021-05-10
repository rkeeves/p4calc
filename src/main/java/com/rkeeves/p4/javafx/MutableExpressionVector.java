package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

/**
 * Represents an expression vector whose elements can be changed.
 */
public interface MutableExpressionVector extends ExpressionVector{

    /**
     * Swaps the old number expression of the vector at the given index with the
     * new number expression given by the user.
     *
     * @param index the index of the element
     * @param expr the new element
     */
    void set(int index, NumberExpression expr);
}
