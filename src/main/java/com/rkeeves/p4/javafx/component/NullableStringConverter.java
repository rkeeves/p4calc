package com.rkeeves.p4.javafx.component;

import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class NullableStringConverter<T> extends StringConverter<T> {

    private final Function<String,T> fromString;

    private final Function<T,String> toString;

    public static StringConverter<Integer> forInteger(){
        return new NullableStringConverter<>(Integer::parseInt,value->Integer.toString(value));
    }

    public static StringConverter<Double> forDouble(){
        return new NullableStringConverter<>(Double::parseDouble,value->Double.toString(value));
    }

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