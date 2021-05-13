package com.rkeeves.p4.model;

import javafx.beans.property.DoubleProperty;

/**
 * This entity represents the lower triangle of a matrix without main diagonal elements.
 * This describes product dependencies.
 *
 * Example:
 * Assume we have products A, B and C.
 * A requires nothing.
 * B requires 1 A.
 * C requires 1 A and 2 B.
 * In this case the matrix will look like [[0, 0, 0] [1, 0, 0] [1, 2, 0]]
 */
public interface LowerTriangleMatrix {

    /**
     * Returns the {@code DoubleProperty} representing how many ingredients are needed.
     *
     * @param row - row index
     * @param col - column index
     * @return double property representing the needed amount of the ingredient
     */
    DoubleProperty getElementOfLowerTriangle(int row, int col);
}
