package com.rkeeves.p4.io;

import javafx.scene.Node;

/**
 * This service provides a generic way for loading fxml based views
 * with user supplied controllers
 */
public interface FXMLService {

    /**
     * Loads the fxml based view from the location given by the user.
     * The user supplied controller instance will be used as controller for FXMLLoader.
     * This method does NOT validate whether all FXML annotated fields of the controller
     * were injected by the FXMLLoader, so use this with caution.
     *
     * @param controller - the controller instance
     * @param fxmlResourcePath - fxml file's resource name string
     * @param <A> - controller's actual type
     * @param <T> - the view's root node's actual type
     * @return the view's root
     * @throws FXMLLoadFailedException - If any loading errors occured
     */
    <A, T extends Node> T load(A controller, String fxmlResourcePath) throws FXMLLoadFailedException;
}
