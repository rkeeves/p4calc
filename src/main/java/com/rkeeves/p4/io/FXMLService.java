package com.rkeeves.p4.io;

import javafx.scene.Node;

public interface FXMLService {

    <A, T extends Node> T load(A controller, String fxmlResourcePath) throws FXMLLoadFailedException;
}
