package com.rkeeves.p4.javafx.component;

import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.util.function.Function;

public class TableColumns {

    private static final StringConverter<Number> nullableIntConverter = NullableStringConverter.convertToNumber(NullableStringConverter.forInteger());

    private static final StringConverter<Number> nullableDoubleConverter = NullableStringConverter.convertToNumber(NullableStringConverter.forDouble());

    public static <O, T> void readonlyColumnForProperty(TableColumn<O, T> column, Function<O, Property<T>> getter) {
        javafx.util.Callback<TableColumn.CellDataFeatures<O, T>,
                javafx.beans.value.ObservableValue<T>> factory = cellData -> getter.apply(cellData.getValue());
        column.setCellValueFactory(factory);
    }


    public static <O> void readonlyColumnForNumberExpr(TableColumn<O, Number> column, Function<O, NumberExpression> getter) {
        javafx.util.Callback<TableColumn.CellDataFeatures<O, Number>,
                javafx.beans.value.ObservableValue<Number>> factory = cellData -> getter.apply(cellData.getValue());
        column.setCellValueFactory(factory);
    }

    public static <O> void editableColumnForDouble(TableColumn<O, Number> column, Function<O, DoubleProperty> getter) {
        columnForNumber(column, getter.andThen(e -> e), nullableDoubleConverter);
    }

    public static <O> void editableColumnForInteger(TableColumn<O, Number> column,
                                                    Function<O, IntegerProperty> getter) {
        columnForNumber(column, getter.andThen(e -> e), nullableIntConverter);
    }

    private static <O> void columnForNumber(TableColumn<O, Number> column,
                                            Function<O, Property<Number>> getter,
                                            StringConverter<Number> converter) {
        column.setCellValueFactory(cellDataFeatures -> {
            var o = cellDataFeatures.getValue();
            var val = getter.apply(o);
            return val;
        });

        column.setCellFactory(col -> new TextFieldTableCell<>(converter) {
            @Override
            public void updateItem(Number item, boolean empty) {
                if (empty || item == null) {
                    item = getItem();
                }
                super.updateItem(item, empty);
            }
        });

        column.setOnEditCommit(cellEditEvent -> {
            var object = cellEditEvent.getTableView()
                    .getItems()
                    .get(cellEditEvent.getTablePosition().getRow());
            if (cellEditEvent.getNewValue() != null)
                getter.apply(object).setValue(cellEditEvent.getNewValue());
        });
    }
}
