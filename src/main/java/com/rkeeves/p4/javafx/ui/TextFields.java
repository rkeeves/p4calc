package com.rkeeves.p4.javafx.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

/**
 * A static helper class to modify JavaFX text fields to facilitate binding to properties.
 */
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

    /**
     * Binds an integer property bidirectionally to a text field.
     *
     * @param textField whose string property is about to be bound to
     * @param integerProperty the property to base the binding on
     */
    public static void bindBidirectional(TextField textField, IntegerProperty integerProperty){
        textField.setTextFormatter(new TextFormatter<>(intFilter));
        textField.textProperty().bindBidirectional(integerProperty,(StringConverter<Number>) intConverter);
    }

    /**
     * Binds a double property bidirectionally to a text field.
     *
     * @param textField whose string property is about to be bound to
     * @param doubleProperty the property to base the binding on
     */
    public static void bindBidirectional(TextField textField, DoubleProperty doubleProperty){
        textField.setTextFormatter(new TextFormatter<>(doubleFilter));
        textField.textProperty().bindBidirectional(doubleProperty,(StringConverter<Number>) doubleConverter);
    }
}
