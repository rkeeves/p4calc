package com.rkeeves.p4.javafx;

import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleDoubleProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExpressionVectorAdapter {

    private static SimpleDoubleProperty prop(double v){
        return new SimpleDoubleProperty(v);
    }

    @ParameterizedTest
    @MethodSource("size_provideTestCases")
    void size_Always_ReturnsSizeOfArray(NumberExpression[] expressions, int expectedSize){
        var vector = new ExpressionVectorAdapter<>(expressions);
        assertEquals(expectedSize,vector.size());
    }

    private static Stream<Arguments> size_provideTestCases(){
        return Stream.of(
                Arguments.of(new NumberExpression[]{}, 0),
                Arguments.of(new NumberExpression[]{prop(6)}, 1),
                Arguments.of(new NumberExpression[]{prop(6),prop(6)}, 2),
                Arguments.of(new NumberExpression[]{prop(6),prop(6),prop(6)}, 3),
                Arguments.of(new NumberExpression[]{prop(6),prop(6),prop(6),prop(6)}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("get_provideTestCases")
    void get_Always_ReturnsElement(NumberExpression[] expressions, int size){
        var vector = new ExpressionVectorAdapter<>(expressions);
        for (int i = 0; i < size; i++) {
            assertEquals(expressions[i],vector.get(i));
        }
    }

    private static Stream<Arguments> get_provideTestCases(){
        return Stream.of(
                Arguments.of(new NumberExpression[]{}, 0),
                Arguments.of(new NumberExpression[]{prop(6)}, 1),
                Arguments.of(new NumberExpression[]{prop(6),prop(6)}, 2),
                Arguments.of(new NumberExpression[]{prop(6),prop(6),prop(6)}, 3),
                Arguments.of(new NumberExpression[]{prop(6),prop(6),prop(6),prop(6)}, 4)
        );
    }
}
