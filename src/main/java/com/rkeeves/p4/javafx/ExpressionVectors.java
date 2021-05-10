package com.rkeeves.p4.javafx;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A helper class for expression vector related methods.
 */
public class ExpressionVectors {

    private ExpressionVectors(){

    }

    /**
     * Creates an array expression vector based on the double array given by the user.
     *
     * @param defaultValues user given array
     * @return array expression vector
     */
    public static ArrayExpressionVector arrayExpressionVector(double[] defaultValues){
        NumberExpression[] array = new DoubleExpression[defaultValues.length];
        for (int i = 0; i < defaultValues.length; i++) {
            array[i] = new SimpleDoubleProperty(defaultValues[i]);
        }
        return new ArrayExpressionVector(array);
    }

    /**
     * Returns a primitive double array whose elements are the
     * current values of the expression vector.
     *
     * @param vector the expression vector to be transformed
     * @return primitive double array
     */
    public static double[] toArray(ExpressionVector vector){
        double[] array = new double[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            array[i] = vector.get(i).doubleValue();
        }
        return array;
    }

    /**
     * Returns a string representation of the current values of the expression vector.
     *
     * @param vector the expression vector to be transformed
     * @return string representation
     */
    public static String toString(ExpressionVector vector){
        return IntStream.range(0,vector.size())
                .mapToObj(vector::get)
                .map(NumberExpression::doubleValue)
                .map(num->Double.toString(num))
                .collect(Collectors.joining(", "));
    }
}
