package com.rkeeves.p4.io;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

public class JacksonJSONService implements JSONService{

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public <T> T readFromResource(String resourceName, Class<T> cls) throws JSONReadFailedException {
        return readFromInputStream(resourceName, getFileFromResourceAsStream(resourceName), cls);
    }

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

    public <T> T readFromInputStream(String resourceName, InputStream inputStream, Class<T> cls) throws JSONReadFailedException {
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
