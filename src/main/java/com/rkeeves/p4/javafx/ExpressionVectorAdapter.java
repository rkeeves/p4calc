package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

/**
 * This generic entity adapts an array to be able to act as a read only expression vector.
 *
 * @param <T> the type of the array elements
 */
public class ExpressionVectorAdapter<T extends NumberExpression> implements ExpressionVector{

    private final T[] array;

    /**
     * Constructs a read-only expression vector adapter based on the given array.
     *
     * @param array user given array
     */
    public ExpressionVectorAdapter(T[] array){
        this.array = array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public NumberExpression get(int index) {
        return array[index];
    }
}
