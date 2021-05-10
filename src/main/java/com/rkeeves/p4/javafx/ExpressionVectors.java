package com.rkeeves.p4.javafx;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExpressionVectors {

    private ExpressionVectors(){

    }

    public static ArrayExpressionVector arrayExpressionVector(double[] defaultValues){
        NumberExpression[] array = new DoubleExpression[defaultValues.length];
        for (int i = 0; i < defaultValues.length; i++) {
            array[i] = new SimpleDoubleProperty(defaultValues[i]);
        }
        return new ArrayExpressionVector(array);
    }

    public static double[] toArray(ExpressionVector vector){
        double[] array = new double[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            array[i] = vector.get(i).doubleValue();
        }
        return array;
    }

    public static String toString(ExpressionVector vector){
        return IntStream.range(0,vector.size())
                .mapToObj(vector::get)
                .map(NumberExpression::doubleValue)
                .map(num->Double.toString(num))
                .collect(Collectors.joining(", "));
    }
}
