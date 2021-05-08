package com.rkeeves.p4.io;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

public class JacksonOperations {

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public <T> void writeToFile(File file, T object) throws JacksonWriteFailedException {
        try (var writer = new FileWriter(file)){
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, object);
        }catch (IOException e){
            throw new JacksonWriteFailedException(String.format("Failed to write to %s",file.getAbsolutePath()), e);
        }
    }

    public <T> T readFromFile(File file, Class<T> cls) throws JacksonReadFailedException {
        try {
            return objectMapper.readValue(new FileReader(file), cls);
        } catch (FileNotFoundException e) {
            throw new JacksonReadFailedException(String.format("File '%s' not found",file.getAbsolutePath()),e);
        } catch (JsonParseException e) {
            throw new JacksonReadFailedException(String.format("Failed to parse json %s",file.getAbsolutePath()),e);
        } catch (JsonMappingException e) {
            throw new JacksonReadFailedException(String.format("Failed to map json %s",file.getAbsolutePath()),e);
        } catch (IOException e) {
            throw new JacksonReadFailedException(String.format("IO error during parse of json %s",file.getAbsolutePath()),e);
        }
    }

    public <T> T readFromResource(String resourceName, Class<T> cls) throws JacksonReadFailedException {
        return readFromInputStream(resourceName, getFileFromResourceAsStream(resourceName), cls);
    }

    public <T> T readFromInputStream(String resourceName, InputStream inputStream, Class<T> cls) throws JacksonReadFailedException {
        try {
            return objectMapper.readValue(inputStream, cls);
        } catch (JsonParseException e) {
            throw new JacksonReadFailedException(String.format("Failed to parse json %s",resourceName),e);
        } catch (JsonMappingException e) {
            throw new JacksonReadFailedException(String.format("Failed to map json %s",resourceName),e);
        } catch (IOException e) {
            throw new JacksonReadFailedException(String.format("IO error during parse of json %s",resourceName),e);
        }
    }

    private InputStream getFileFromResourceAsStream(String resourceName) throws JacksonReadFailedException {
        InputStream inputStream =  this.getClass().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new JacksonReadFailedException(String.format("Resource '%s' was not found!", resourceName));
        } else {
            return inputStream;
        }
    }
}
