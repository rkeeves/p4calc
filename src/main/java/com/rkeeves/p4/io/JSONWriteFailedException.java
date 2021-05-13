package com.rkeeves.p4.io;

/**
 * An {@code Exception} to signal JSON file write errors.
 */
public class JSONWriteFailedException extends Exception{

    /**
     * Constructs an instance with the user supplied error message, and cause.
     *
     * @param message user supplied error message
     * @param cause user supplied {@code Throwable} which caused this {@code Exception}
     */
    public JSONWriteFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
