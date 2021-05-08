package com.rkeeves.p4.io;

public class JSONReadFailedException extends Exception{

    public JSONReadFailedException(String message) {
        super(message);
    }

    public JSONReadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
