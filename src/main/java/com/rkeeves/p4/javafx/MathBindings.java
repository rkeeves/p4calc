package com.rkeeves.p4.javafx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.NumberExpression;

public class MathBindings {

    private MathBindings(){

    }

    public static NumberBinding divideDefaultsIfDivisorZero(NumberExpression dividend,
                                                            NumberExpression divisor,
                                                            double defaultValue){
        return Bindings.createDoubleBinding(() -> divisor.doubleValue() == 0 ? defaultValue : dividend.doubleValue() / divisor.doubleValue(), dividend, divisor);
    }

    public static NumberBinding ceil(NumberExpression value){
        return Bindings.createDoubleBinding(() -> Math.ceil(value.doubleValue()), value);
    }

    public static NumberBinding floor(NumberExpression value){
        return Bindings.createDoubleBinding(() -> Math.floor(value.doubleValue()), value);
    }
}
