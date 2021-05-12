package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestDefaultEconomyModel {

    private static JSONService jsonService;


    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
    }

    @Test
    void f(){
        try {
            var testData = jsonService.readFromResource("/economyTestData/economyTest1.json",EconomyTestDTO.class);
            System.out.println(testData);
        } catch (JSONReadFailedException e) {
            fail("Wasn't able to load test data",e);
        }

    }
}
