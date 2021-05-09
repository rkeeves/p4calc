package com.rkeeves.p4.io;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

/**
 * Jackson based JSONService implementation.
 * Provides method for read/write operations for JSON data.
 */
public class JacksonJSONService implements JSONService{

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * This method reads a resource file - given by the user supplied resource name -
     * and returns an object of the user given type populated with data based on the JSON file.
     *
     * @param resourceName - the name of the resource to process
     * @param cls - class object of the constructible object's type
     * @param <T> - the constructible object's type
     * @return the constructed and populated object
     * @throws JSONReadFailedException
     */
    @Override
    public <T> T readFromResource(String resourceName, Class<T> cls) throws JSONReadFailedException {
        return readFromInputStream(resourceName, getFileFromResourceAsStream(resourceName), cls);
    }

    /**
     * This method reads a file - given by the user supplied file object -
     * and returns an object of the user given type populated with data based on the JSON file.
     *
     * @param file - the file to process
     * @param cls - class object of the constructible object's type
     * @param <T> - the constructible object's type
     * @return the constructed and populated object
     * @throws JSONReadFailedException
     */
    @Override
    public <T> T readFromFile(File file, Class<T> cls) throws JSONReadFailedException {
        try {
            return objectMapper.readValue(new FileReader(file), cls);
        } catch (FileNotFoundException e) {
            throw new JSONReadFailedException(String.format("File '%s' not found",file.getAbsolutePath()),e);
        } catch (JsonParseException e) {
            throw new JSONReadFailedException(String.format("Failed to parse json %s",file.getAbsolutePath()),e);
        } catch (JsonMappingException e) {
            throw new JSONReadFailedException(String.format("Failed to map json %s",file.getAbsolutePath()),e);
        } catch (IOException e) {
            throw new JSONReadFailedException(String.format("IO error during parse of json %s",file.getAbsolutePath()),e);
        }
    }

    private <T> T readFromInputStream(String resourceName, InputStream inputStream, Class<T> cls) throws JSONReadFailedException {
        try {
            return objectMapper.readValue(inputStream, cls);
        } catch (JsonParseException e) {
            throw new JSONReadFailedException(String.format("Failed to parse json %s",resourceName),e);
        } catch (JsonMappingException e) {
            throw new JSONReadFailedException(String.format("Failed to map json %s",resourceName),e);
        } catch (IOException e) {
            throw new JSONReadFailedException(String.format("IO error during parse of json %s",resourceName),e);
        }
    }

    /**
     * Writes to a user given file JSON formatted data, based on the object given by the user.
     *
     * @param file - the file to write
     * @param object - the object to write
     * @param <T> - the object's type
     * @throws JSONWriteFailedException
     */
    @Override
    public <T> void writeToFile(File file, T object) throws JSONWriteFailedException {
        try (var writer = new FileWriter(file)){
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, object);
        }catch (IOException e){
            throw new JSONWriteFailedException(String.format("Failed to write to %s",file.getAbsolutePath()), e);
        }
    }

    private InputStream getFileFromResourceAsStream(String resourceName) throws JSONReadFailedException {
        InputStream inputStream =  this.getClass().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new JSONReadFailedException(String.format("Resource '%s' was not found!", resourceName));
        } else {
            return inputStream;
        }
    }
}
