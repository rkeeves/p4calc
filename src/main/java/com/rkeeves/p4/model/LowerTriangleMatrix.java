package com.rkeeves.p4.model;

import javafx.beans.property.DoubleProperty;

public interface LowerTriangleMatrix {

    DoubleProperty getElementOfLowerTriangle(int row, int col);
}
