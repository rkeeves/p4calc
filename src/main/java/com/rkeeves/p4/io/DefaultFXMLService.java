package com.rkeeves.p4.io;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class DefaultFXMLService implements FXMLService {

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
