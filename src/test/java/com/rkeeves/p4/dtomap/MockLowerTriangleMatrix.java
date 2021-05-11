package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.model.LowerTriangleMatrix;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MockLowerTriangleMatrix implements LowerTriangleMatrix {

    private Map<Pair<Integer,Integer>, DoubleProperty> values = new HashMap<>();

    @Override
    public DoubleProperty getElementOfLowerTriangle(int row, int col) {
        return values.getOrDefault(new Pair<>(row,col), new SimpleDoubleProperty());
    }

    void set(int row, int col, double val){
        values.computeIfAbsent(new Pair<>(row,col),pair->new SimpleDoubleProperty())
                .set(val);
    }
}
