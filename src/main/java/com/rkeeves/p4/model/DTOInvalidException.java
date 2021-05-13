package com.rkeeves.p4.model;

/**
 * This {@code Data Exception} represents the invalidity of {@code Data Transfer Object}s.
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
