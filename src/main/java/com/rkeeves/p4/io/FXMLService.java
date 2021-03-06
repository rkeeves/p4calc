package com.rkeeves.p4.io;

import javafx.scene.Node;

/**
 * This service provides a generic way for loading {@code FXML} based views with user supplied controllers.
 */
public interface FXMLService {

    /**
     * Loads the {@code FXML} based view from the location given by the user.
     * The user supplied controller instance will be used as controller for {@code FXMLLoader}.
     * This method does NOT validate whether all {@code FXML} annotated fields of the controller
     * were injected by the {@code FXMLLoader}, so use this with caution.
     *
     * @param controller the controller instance
     * @param fxmlResourcePath fxml file's resource name string
     * @param <A> controller's actual type
     * @param <T> the view's root node's actual type
     * @return the view's root
     * @throws FXMLLoadFailedException
     */
    <A, T extends Node> T load(A controller, String fxmlResourcePath) throws FXMLLoadFailedException;
}
