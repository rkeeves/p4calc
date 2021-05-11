package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMathBindings {

    @ParameterizedTest
    @MethodSource("ceil_provideTestCases")
    void ceil_Always_ReturnsCeiling(NumberExpression expression, double expectedValue){
        var result = MathBindings.ceil(expression);
        assertEquals(expectedValue,result.doubleValue());
    }

    private static SimpleDoubleProperty prop(double v){
        return new SimpleDoubleProperty(v);
    }

    private static Stream<Arguments> ceil_provideTestCases(){
        return Stream.of(
                Arguments.of(prop(0.4), 1.0),
                Arguments.of(prop(0.5), 1.0),
                Arguments.of(prop(1), 1.0),
                Arguments.of(prop(1.1), 2.0),
                Arguments.of(prop(1234.5), 1235.0)
                );
    }

    @ParameterizedTest
    @MethodSource("floor_provideTestCases")
    void floor_Always_ReturnsCeiling(NumberExpression expression, double expectedValue){
        var result = MathBindings.floor(expression);
        assertEquals(expectedValue,result.doubleValue());
    }

    private static Stream<Arguments> floor_provideTestCases(){
        return Stream.of(
                Arguments.of(prop(0.4), 0.0),
                Arguments.of(prop(0.5), 0.0),
                Arguments.of(prop(1), 1.0),
                Arguments.of(prop(1.1), 1.0),
                Arguments.of(prop(1234.5), 1234.0)
        );
    }

    @ParameterizedTest
    @MethodSource("divideDefaultsIfDivisorZero_provideTestCasesForNonZeroDivisor")
    void divideDefaultsIfDivisorZero_WhenGivenNonZeroDivisor_ReturnsDivided(NumberExpression dividend,
                                                                            NumberExpression divisor,
                                                                            double defaultValue,
                                                                            double expectedValue){
        var result = MathBindings.divideDefaultsIfDivisorZero(dividend, divisor,defaultValue);
        assertEquals(expectedValue,result.doubleValue());
    }

    private static Stream<Arguments> divideDefaultsIfDivisorZero_provideTestCasesForNonZeroDivisor(){
        return Stream.of(
                Arguments.of(prop(6),prop(2), 0.0, 3.0),
                Arguments.of(prop(100),prop(10), 0.0, 10.0),
                Arguments.of(prop(-100),prop(10), 0.0, -10.0),
                Arguments.of(prop(100),prop(-10), 0.0, -10.0),
                Arguments.of(prop(-100),prop(-10), 0.0, 10.0)
        );
    }

    @ParameterizedTest
    @MethodSource("divideDefaultsIfDivisorZero_provideTestCasesForZeroDivisor")
    void divideDefaultsIfDivisorZero_WhenGivenZeroDivisor_ReturnsDefaultValue(NumberExpression dividend,
                                                                            NumberExpression divisor,
                                                                            double defaultValue){
        var result = MathBindings.divideDefaultsIfDivisorZero(dividend, divisor,defaultValue);
        assertEquals(defaultValue,result.doubleValue());
    }

    private static Stream<Arguments> divideDefaultsIfDivisorZero_provideTestCasesForZeroDivisor(){
        return Stream.of(
                Arguments.of(prop(6),prop(0), 12.45),
                Arguments.of(prop(100),prop(0), 72.24),
                Arguments.of(prop(-100),prop(0), 64.04),
                Arguments.of(prop(100),prop(0), 10.65),
                Arguments.of(prop(-100),prop(0), 0.68)
        );
    }
}
