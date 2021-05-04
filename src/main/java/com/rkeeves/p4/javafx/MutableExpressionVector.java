package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

public interface MutableExpressionVector extends ExpressionVector{

    void set(int index, NumberExpression expr);
}
