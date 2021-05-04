package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

public interface ExpressionVector {

    int size();

    NumberExpression get(int index);
}
