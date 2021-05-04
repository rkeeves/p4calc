package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;
import lombok.Data;

@Data
public class ArrayExpressionVector implements MutableExpressionVector{

    private final NumberExpression[] expressions;

    @Override
    public int size() {
        return expressions.length;
    }

    @Override
    public NumberExpression get(int index) {
        return expressions[index];
    }

    @Override
    public void set(int index, NumberExpression expr) {
        expressions[index] = expr;
    }
}
