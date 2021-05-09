package com.rkeeves.p4.io;

import java.io.File;

/**
 * This service provides a generic way for reading/writing objects to JSON.
 */
public interface JSONService {

    /**
     * This method reads a resource file - given by the user supplied resource name -
     * and returns an object of the user given type populated with data based on the JSON file.
     *
     * @param resourceName the name of the resource to process
     * @param cls class object of the constructible object's type
     * @param <T> the constructible object's type
     * @return the constructed and populated object
     * @throws JSONReadFailedException
     */
    <T> T readFromResource(String resourceName, Class<T> cls) throws JSONReadFailedException;

    /**
     * This method reads a file - given by the user supplied file object -
     * and returns an object of the user given type populated with data based on the JSON file.
     *
     * @param file the file to process
     * @param cls class object of the constructible object's type
     * @param <T> the constructible object's type
     * @return the constructed and populated object
     * @throws JSONReadFailedException
     */
    <T> T readFromFile(File file, Class<T> cls) throws JSONReadFailedException;

    /**
     * Writes to a user given file JSON formatted data, based on the object given by the user.
     *
     * @param file the file to write
     * @param object the object to write
     * @param <T> the object's type
     * @throws JSONWriteFailedException
     */
    <T> void writeToFile(File file, T object) throws JSONWriteFailedException;
}
