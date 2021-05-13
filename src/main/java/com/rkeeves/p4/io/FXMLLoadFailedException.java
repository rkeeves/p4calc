package com.rkeeves.p4.io;

/**
 * An {@code Exception} to signal any errors during {@code FXML} loading operations.
 */
public class FXMLLoadFailedException extends Exception{

    /**
     * Constructs an instance based on user supplied message and cause.
     * 
     * @param message user supplied error message
     * @param cause the underlying {@code Exception} which was thrown by {@code FXMLLoader}
     */
    public FXMLLoadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
