package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

public class ExpressionVectorAdapter<T extends NumberExpression> implements ExpressionVector{

    private final T[] array;

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
