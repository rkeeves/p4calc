package com.rkeeves.p4.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PropertyArrays {

    private PropertyArrays(){

    }

    public static DoubleProperty[] doubles(int size){
        return doubles(size, 0.0);
    }

    public static DoubleProperty[] doubles(int size, double defaultValue){
        var array = new DoubleProperty[size];
        for (int i = 0; i < size; i++) {
            array[i] = new SimpleDoubleProperty(defaultValue);
        }
        return array;
    }

    public static IntegerProperty[] ints(int size){
        return ints(size,0);
    }

    public static IntegerProperty[] ints(int size, int defaultValue){
        var array = new IntegerProperty[size];
        for (int i = 0; i < size; i++) {
            array[i] = new SimpleIntegerProperty(defaultValue);
        }
        return array;
    }
}
