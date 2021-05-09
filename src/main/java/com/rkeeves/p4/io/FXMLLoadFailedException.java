package com.rkeeves.p4.io;

/**
 * An Exception to signal any errors during FXML loading operations.
 */
public class FXMLLoadFailedException extends Exception{

    /**
     * Constructs an instance based on user supplied message and cause.
     * 
     * @param message user supplied error message
     * @param cause the underlying exception which was thrown by FXMLLoader
     */
    public FXMLLoadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
