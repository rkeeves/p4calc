package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import com.rkeeves.p4.javafx.ExpressionSquareMatrices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TestDependencyMatrixFactory {

    private static final String TEST_CASE_ARRAY_RESOURCE_NAME = "/model/dependencyMatrixFactory/dependencyMatrixFactoryTestCaseArray.json";

    private static JSONService jsonService;
    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void createIngredientsLowerTriangleMatrix_WhenGivenValidProducts_ReturnsValidLowerTriangleMatrix(DependencyMatrixFactoryTestCase testCase){
        var actualLowerTriangleMatrix = DependencyMatrixFactory.createIngredientsLowerTriangleMatrix(testCase.getProductDTOList());
        assertArrayEquals(testCase.getExpectedLowerTriangleMatrix(), actualLowerTriangleMatrix);
    }


    @ParameterizedTest
    @MethodSource("provideTestCases")
    void createDependencyMatrix_WhenGivenValidProducts_ReturnsValidDependencyMatrix(DependencyMatrixFactoryTestCase testCase){
        var actualDependencyMatrix = DependencyMatrixFactory.createDependencyMatrix(testCase.getProductDTOList());
        assertArrayEquals(testCase.getExpectedDependencyMatrix(), ExpressionSquareMatrices.toArray(actualDependencyMatrix));
    }

    private static Stream<Arguments> provideTestCases() throws JSONReadFailedException {
        var dtoArray = jsonService.readFromResource(TEST_CASE_ARRAY_RESOURCE_NAME,
                DependencyMatrixFactoryTestCase[].class);
        return Arrays.stream(dtoArray)
                .map(Arguments::of);
    }
}
