package com.rkeeves.p4.io;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Default implementation of an {@code FXMLService}.
 */
public class DefaultFXMLService implements FXMLService {

    /**
     * This method loads the fxml based view from the location given by the user.
     * This implementation does not create a controller factory,
     * but just simply uses the user supplied controller.
     * If you use nested controllers, this method will behave in an undefined way.
     *
     * @param controller the controller instance
     * @param fxmlResourcePath fxml file's resource name string
     * @param <A> controller's actual type
     * @param <T> the view's root node's actual type
     * @return the view's root
     * @throws FXMLLoadFailedException
     */
    @Override
    public <A, T extends Node> T load(A controller, String fxmlResourcePath) throws FXMLLoadFailedException {
        var loader = new FXMLLoader();
        loader.setLocation(DefaultFXMLService.class.getResource(fxmlResourcePath));
        loader.setController(controller);
        try {
            return loader.load();
        } catch (IOException ioException) {
            throw new FXMLLoadFailedException(String.format("Failed to load fxml from %s",fxmlResourcePath),ioException);
        }
    }
}
