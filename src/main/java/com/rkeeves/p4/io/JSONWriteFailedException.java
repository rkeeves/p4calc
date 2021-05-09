package com.rkeeves.p4.io;

/**
 * An Exception to signal JSON file write errors.
 */
public class JSONWriteFailedException extends Exception{

    /**
     * Constructs an instance with the user supplied error message, and cause.
     *
     * @param message - user supplied error message
     * @param cause - user supplied Throwable which caused this Exception
     */
    public JSONWriteFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
