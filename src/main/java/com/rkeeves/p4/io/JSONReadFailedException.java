package com.rkeeves.p4.io;

/**
 * An Exception to signal JSON file read, parse and dto mapping errors
 */
public class JSONReadFailedException extends Exception{

    /**
     * Constructs an instance with the user supplied error message
     * @param message - user supplied error message
     */
    public JSONReadFailedException(String message) {
        super(message);
    }

    /**
     * Constructs an instance with the user supplied error message,
     * and the
     * @param message - user supplied error message
     * @param cause - user supplied Throwable which caused this Exception
     */
    public JSONReadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
