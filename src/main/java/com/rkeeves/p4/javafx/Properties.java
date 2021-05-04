package com.rkeeves.p4.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Properties {

    private Properties(){

    }

    public static IntegerProperty intProp(Integer defaultValue){
        return new SimpleIntegerProperty(defaultValue);
    }

    public static DoubleProperty doubleProp(Double defaultValue){
        return new SimpleDoubleProperty(defaultValue);
    }
}
