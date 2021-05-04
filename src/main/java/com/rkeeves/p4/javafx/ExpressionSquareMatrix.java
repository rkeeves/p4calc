package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;

public interface ExpressionSquareMatrix {

    int size();

    NumberExpression get(int row, int col);
}
