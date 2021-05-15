package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestDependencyMatrix {

    private static final String TEST_CASE_ARRAY_RESOURCE_NAME = "/model/dependencyMatrix/dependencyMatrixTestCaseArray.json";

    private static JSONService jsonService;

    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void createByLowerTriangleValues_WhenGivenLowerTriangleMatrix_ReturnsCorrectMatrix(DependencyMatrixTestCase matrixTestCase){
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(matrixTestCase.getInputMatrix());
        assertArrayEquals(matrixTestCase.getExpectedMatrix(), dependencyMatrix.toArray());
    }

    private static Stream<Arguments> provideTestCases() throws JSONReadFailedException {
        var dtoArray = jsonService.readFromResource(TEST_CASE_ARRAY_RESOURCE_NAME,
                DependencyMatrixTestCase[].class);
        return Arrays.stream(dtoArray)
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void size_Always_ReturnsMatrixSize(DependencyMatrixTestCase matrixTestCase){
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(matrixTestCase.getInputMatrix());
        assertEquals(matrixTestCase.getExpectedMatrix().length, dependencyMatrix.toArray().length);
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void getElementOfLowerTriangle_WhenCoordinatesInsideLowerTriangle_ReturnsElement(DependencyMatrixTestCase matrixTestCase){
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(matrixTestCase.getInputMatrix());
        var expected = matrixTestCase.getExpectedMatrix();
        int size = expected.length;
        assertEquals(size, dependencyMatrix.toArray().length);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                assertEquals(expected[i][j],dependencyMatrix.getElementOfLowerTriangle(i,j).doubleValue());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void getElementOfLowerTriangle_WhenCoordinatesOutsideLowerTriangle_Throws(DependencyMatrixTestCase matrixTestCase){
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(matrixTestCase.getInputMatrix());
        var expected = matrixTestCase.getExpectedMatrix();
        int size = expected.length;
        assertEquals(size, dependencyMatrix.toArray().length);
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                // due to lambda's context not being immutable
                int row = i;
                int col = j;
                assertThrows(IndexOutOfBoundsException.class, ()->dependencyMatrix.getElementOfLowerTriangle(row,col));
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void get_WhenCoordinatesInsideMatrix_ReturnsElement(DependencyMatrixTestCase matrixTestCase){
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(matrixTestCase.getInputMatrix());
        var expected = matrixTestCase.getExpectedMatrix();
        int size = expected.length;
        assertEquals(size, dependencyMatrix.toArray().length);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                assertEquals(expected[i][j], dependencyMatrix.get(i,j).doubleValue());
            }
        }
    }
}
