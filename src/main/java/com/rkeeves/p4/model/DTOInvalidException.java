package com.rkeeves.p4.model;

/**
 * This Exception represents the invalidity of Data Transfer Objects.
 */
public class DTOInvalidException extends Exception{

    /**
     * Constructs and instance based on the user supplied error message.
     * @param message error message
     */
    public DTOInvalidException(String message) {
        super(message);
    }
}
