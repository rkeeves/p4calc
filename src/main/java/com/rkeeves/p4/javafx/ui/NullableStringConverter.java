package com.rkeeves.p4.javafx.ui;

import javafx.util.StringConverter;

import java.util.function.Function;

/**
 * A generic type from/to string converter to work with JavaFX's API.
 *
 * @param <T> type to be converted from/to String
 */
public class NullableStringConverter<T> extends StringConverter<T> {

    private final Function<String,T> fromString;

    private final Function<T,String> toString;

    /**
     * Cosntructs a string converter object given to mapping functions.
     *
     * @param fromString mapper which can create instances of user given based on a string
     * @param toString mapper which can create string based on user given type's instances
     */
    public NullableStringConverter(Function<String, T> fromString, Function<T, String> toString) {
        this.fromString = fromString;
        this.toString = toString;
    }

    /**
     * Creates a converter instance for integers.
     *
     * @return converter
     */
    public static StringConverter<Integer> forInteger(){
        return new NullableStringConverter<>(Integer::parseInt,value->Integer.toString(value));
    }

    /**
     * Creates a converter instance for doubles.
     *
     * @return converter
     */
    public static StringConverter<Double> forDouble(){
        return new NullableStringConverter<>(Double::parseDouble,value->Double.toString(value));
    }

    /**
     * Given any converter whose generic type parameter extends number returns the same converter,
     * but casted to use Number as the generic type parameter.
     * Due to JavaFX's API, sometimes it is necessary to cast.
     * At least this way it is centralized where the bad C-style casting happens.
     *
     * @param baseConverter converter to cast
     * @return converter
     */
    public static StringConverter<Number> convertToNumber(StringConverter<? extends Number> baseConverter){
        return (StringConverter<Number>) baseConverter;
    }

    @Override
    public T fromString(String s) {
        if (s == null) {
            return null;
        } else {
            s = s.trim();
            try{
                return s.length() < 1 ? null : fromString.apply(s);
            }catch (Exception e){
                return null;
            }
        }

    }

    @Override
    public String toString(T integer) {
        try{
            return toString.apply(integer);
        }catch (Exception e){
            return null;
        }
    }
}