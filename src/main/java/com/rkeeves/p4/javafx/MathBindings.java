package com.rkeeves.p4.javafx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.NumberExpression;

/**
 * Static helper class which contains utility functions for applying java.lang.Math methods on bindings.
 */
public class MathBindings {

    private MathBindings(){

    }

    /**
     * Returns a binding which divides dividend by divisor.
     * If divisor is zero the method doesn't throw,
     * but rather returns the defaultValue.
     *
     * @param dividend expression to divide
     * @param divisor expression to divide with
     * @param defaultValue value to return if divisor is zero
     * @return binding of the safe division
     */
    public static NumberBinding divideDefaultsIfDivisorZero(NumberExpression dividend,
                                                            NumberExpression divisor,
                                                            double defaultValue){
        return Bindings.createDoubleBinding(() -> divisor.doubleValue() == 0 ? defaultValue : dividend.doubleValue() / divisor.doubleValue(), dividend, divisor);
    }

    /**
     * A simple binding fot java.lang.Math.ceil.
     * @param value
     * @return
     */
    public static NumberBinding ceil(NumberExpression value){
        return Bindings.createDoubleBinding(() -> Math.ceil(value.doubleValue()), value);
    }

    /**
     * A simple binding fot java.lang.Math.floor.
     * @param value
     * @return
     */
    public static NumberBinding floor(NumberExpression value){
        return Bindings.createDoubleBinding(() -> Math.floor(value.doubleValue()), value);
    }
}
