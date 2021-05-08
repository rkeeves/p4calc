package com.rkeeves.p4.javafx.component;

import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class TextFields {

    private TextFields(){

    }

    private static final UnaryOperator<TextFormatter.Change> intFilter = change -> {
        if (change.getControlNewText().matches("[+-]?\\d*")) {
            return change;
        } else {
            return null;
        }
    };

    private static final UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
        if (change.getControlNewText().matches("[+-]?\\d*\\.?\\d*")) {
            return change;
        } else {
            return null;
        }
    };

    private static final StringConverter<? extends Number> intConverter = new IntegerStringConverter();

    private static final StringConverter<? extends Number> doubleConverter = new DoubleStringConverter();

    public static void bindExpression(TextField textField, NumberExpression expression){
        textField.textProperty().bind(expression.asString());
    }

    public static void bindBidirectional(TextField textField, IntegerProperty integerProperty){
        textField.setTextFormatter(new TextFormatter<>(intFilter));
        textField.textProperty().bindBidirectional(integerProperty,(StringConverter<Number>) intConverter);
    }

    public static void bindBidirectional(TextField textField, DoubleProperty doubleProperty){
        textField.setTextFormatter(new TextFormatter<>(doubleFilter));
        textField.textProperty().bindBidirectional(doubleProperty,(StringConverter<Number>) doubleConverter);
    }
}
