package com.rkeeves.p4.io;

import java.io.File;

public interface JSONService {

    <T> T readFromResource(String resourceName, Class<T> cls) throws JSONReadFailedException;

    <T> T readFromFile(File file, Class<T> cls) throws JSONReadFailedException;

    <T> void writeToFile(File file, T object) throws JSONWriteFailedException;
}
