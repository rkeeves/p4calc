package com.rkeeves.p4.io;

import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJacksonJSONService {

    private static JSONService jsonService;

    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
    }

    @Data
    static class TestJSONPOJO{
        private String name;
        private Integer value;
    }

    @Test
    void readFromResource_WhenResourceExistsAndIsValid_ReturnMappedObject(){
        try {
            var pojo = jsonService.readFromResource("/json/valid_pojo.json", TestJSONPOJO.class);
            assertEquals("foo",pojo.name);
            assertEquals(1,pojo.value);
        } catch (JSONReadFailedException e) {
            fail("Exception should not be thrown",e);
        }
    }

    @Test
    void readFromResource_WhenResourceDoesNotExists_Throw(){
        try {
            var pojo = jsonService.readFromResource("/json/not_existing.json", TestJSONPOJO.class);
            fail("Exception should be thrown");
        } catch (JSONReadFailedException e) {

        }
    }

    @Test
    void readFromResource_WhenResourceExistsButIsEmpty_Throw(){
        try {
            var pojo = jsonService.readFromResource("/json/empty.json", TestJSONPOJO.class);
            fail("Exception should be thrown");
        } catch (JSONReadFailedException e) {

        }
    }

    @Test
    void readFromResource_WhenResourceExistsButIsInvalid_Throw(){
        try {
            var pojo = jsonService.readFromResource("/json/syntactic_error.json", TestJSONPOJO.class);
            fail("Exception should be thrown");
        } catch (JSONReadFailedException e) {

        }
    }
}
