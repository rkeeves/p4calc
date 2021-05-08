package com.rkeeves.p4.javafx;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Both<A,B>{

    private final A some;

    private final B other;

    public static <A,B> Both<A,B> of(A some, B other){
        return new Both<>(some, other);
    }
}
