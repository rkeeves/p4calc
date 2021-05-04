package com.rkeeves.p4.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class JacksonOperations {

    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public <T> void writeToFile(String fileName, T object) throws IOException {
        try (var writer = new FileWriter(fileName)){
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, object);
        }
    }

    public <T> T readFromFile(String fileName, Class<T> cls) throws IOException {
        return objectMapper.readValue(new FileReader(fileName), cls);
    }

    public <T> T readFromResource(String fileName, Class<T> cls) throws IOException {
        return readFromInputStream(getFileFromResourceAsStream(fileName), cls);
    }

    public <T> T readFromInputStream(InputStream inputStream, Class<T> cls) throws IOException {
        return objectMapper.readValue(inputStream, cls);
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException(String.format("File '%s' was not found!", fileName));
        } else {
            return inputStream;
        }
    }
}
