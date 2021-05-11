package com.rkeeves.p4.model.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestDependencyMatrix {


    private DependencyMatrix makeDependencyMatrix(double[][] inputLowerTriangleMatrix){
        return DependencyMatrix.createByLowerTriangleValues(inputLowerTriangleMatrix);
    }

    @ParameterizedTest
    @MethodSource("createByLowerTriangleValues_provideTestCasesForUserGivesLowerTriangleMatrix")
    void createByLowerTriangleValues_WhenGivenLowerTriangleMatrix_ReturnsCorrectMatrix(double[][] inputMatrix,
                                                                                       double[][] expectedMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertArrayEquals(expectedMatrix, dependencyMatrix.toArray());
    }

    private static Stream<Arguments> createByLowerTriangleValues_provideTestCasesForUserGivesLowerTriangleMatrix(){
        return Stream.of(
                Arguments.of(new double[][]{}, new double[][]{}),
                Arguments.of(
                        new double[][]{
                                {}
                        },
                        new double[][]{
                                {0}
                        }),
                Arguments.of(
                        new double[][]{
                                {},
                                {0}
                        },
                        new double[][]{
                                {0, 0},
                                {0, 0}
                        }
                ),
                Arguments.of(
                        new double[][]{
                                {},
                                {3}
                        },
                        new double[][]{
                                {0, 3},
                                {3, 0}
                        }),
                Arguments.of(
                        new double[][]{
                                {},
                                {1},
                                {2, 3}
                        },
                        new double[][]{
                                {0, 1, 5},
                                {1, 0, 3},
                                {2, 3, 0}
                        }),
                Arguments.of(
                        new double[][]{
                                {},
                                {1},
                                {2, 3},
                                {4, 5, 6}
                        },
                        new double[][]{
                                {0, 1, 5, 39},
                                {1, 0, 3, 23},
                                {2, 3, 0, 6},
                                {4, 5, 6, 0}
                        })
        );
    }

    @ParameterizedTest
    @MethodSource("createByLowerTriangleValues_provideTestCasesForUserGivesFullMatrix")
    void createByLowerTriangleValues_WhenGivenFullMatrix_IgnoresMainDiagonalAndUpperTriangleValuesAndReturnsCorrectMatrix(double[][] inputMatrix,
                                                                                       double[][] expectedMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertArrayEquals(expectedMatrix, dependencyMatrix.toArray());
    }

    private static Stream<Arguments> createByLowerTriangleValues_provideTestCasesForUserGivesFullMatrix(){
        return Stream.of(
                Arguments.of(new double[][]{}, new double[][]{}),
                Arguments.of(
                        new double[][]{
                                {0}
                        },
                        new double[][]{
                                {0}
                        }),
                Arguments.of(
                        new double[][]{
                                {2, 3},
                                {0, 5}
                        },
                        new double[][]{
                                {0, 0},
                                {0, 0}
                        }
                ),
                Arguments.of(
                        new double[][]{
                                {7, 8},
                                {3, 9}
                        },
                        new double[][]{
                                {0, 3},
                                {3, 0}
                        }),
                Arguments.of(
                        new double[][]{
                                {2, 3, 4},
                                {1, 7, 8},
                                {2, 3, 9}
                        },
                        new double[][]{
                                {0, 1, 5},
                                {1, 0, 3},
                                {2, 3, 0}
                        }),
                Arguments.of(
                        new double[][]{
                                {2, 3, 7, 8},
                                {1, 7, 8, 9},
                                {2, 3, 6, 7},
                                {4, 5, 6, 10}
                        },
                        new double[][]{
                                {0, 1, 5, 39},
                                {1, 0, 3, 23},
                                {2, 3, 0, 6},
                                {4, 5, 6, 0}
                        }),
                Arguments.of(
                        new double[][]{
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0.25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0.5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0.25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 1, 0, 0, 0, 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0.5, 0, 0, 0, 0, 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 1.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        },
                        new double[][]{
                                { 0, 0, 0, 0, 0, 0, 0, 0.25, 0.5, 0, 0, 0, 0.125, 0, 0, 0.5, 0.25, 0.375, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.25, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0.5, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0.25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0, 0, 0, 1.5, 0, 0},
                                { 0.5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0.25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 1, 0, 0, 0, 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0.5, 0, 0, 0, 0, 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 1.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        })
        );
    }

    @ParameterizedTest
    @MethodSource("size_provideTestCases")
    void size_Always_ReturnsMatrixSize(int expectedSize, double[][] inputMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertEquals(expectedSize,dependencyMatrix.size());
    }

    private static Stream<Arguments> size_provideTestCases(){
        return Stream.of(
                Arguments.of(0, new double[][]{}),
                Arguments.of(1, new double[][]{{0}}),
                Arguments.of(2, new double[][]{{}, {0}}),
                Arguments.of(2, new double[][]{{0}, {0}}),
                Arguments.of(3, new double[][]{{}, {0}, {0, 0}}),
                Arguments.of(3, new double[][]{{0}, {0}, {0, 0}}),
                Arguments.of(4, new double[][]{{0}, {0}, {0, 0}, {0, 0, 0}})
        );
    }

    @ParameterizedTest
    @MethodSource("getElementOfLowerTriangle_provideTestCasesForCoordinatesInsideLowerTriangle")
    void getElementOfLowerTriangle_WhenCoordinatesInsideLowerTriangle_ReturnsElement(int row,
                                                                                     int col,
                                                                                     double expectedDoubleValue,
                                                                                     double[][] inputMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertEquals(expectedDoubleValue,dependencyMatrix.getElementOfLowerTriangle(row,col).doubleValue());
    }

    private static Stream<Arguments> getElementOfLowerTriangle_provideTestCasesForCoordinatesInsideLowerTriangle(){
        var inputMatrix4By4 = new double[][]{
                {},
                {1.1},
                {2.2, 3.3},
                {4.4, 5.5, 6.6}
        };
        int size = inputMatrix4By4.length;
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int row = 0; row < size; row++)
            for (int col = 0; col < row; col++)
                listOfArguments.add(Arguments.of(row, col, inputMatrix4By4[row][col], inputMatrix4By4));
        return listOfArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("getElementOfLowerTriangle_provideTestCasesForCoordinatesOutsideLowerTriangle")
    void getElementOfLowerTriangle_WhenCoordinatesOutsideLowerTriangle_Throws(int row, int col, double[][] inputMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertThrows(IndexOutOfBoundsException.class, ()->dependencyMatrix.getElementOfLowerTriangle(row,col));
    }

    private static Stream<Arguments> getElementOfLowerTriangle_provideTestCasesForCoordinatesOutsideLowerTriangle(){
        var inputMatrix4By4 = new double[][]{
                {},
                {1},
                {2, 3},
                {4, 5, 6}
        };
        int size = inputMatrix4By4.length;
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int row = 0; row < size; row++)
            for (int col = row; col < size; col++)
                listOfArguments.add(Arguments.of(row, col, inputMatrix4By4));
        return listOfArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("get_provideTestCasesForCoordinatesInsideMatrix")
    void get_WhenCoordinatesInsideMatrix_ReturnsElement(int row,
                                                                                     int col,
                                                                                     double expectedDoubleValue,
                                                                                     double[][] inputMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertEquals(expectedDoubleValue,dependencyMatrix.get(row,col).doubleValue());
    }

    private static Stream<Arguments> get_provideTestCasesForCoordinatesInsideMatrix(){
        var inputMatrix4By4 = new double[][]{
                {},
                {1},
                {2, 3},
                {4, 5, 6}
        };
        var expectedMatrix = new double[][]{
                {0, 1, 5, 39},
                {1, 0, 3, 23},
                {2, 3, 0, 6},
                {4, 5, 6, 0}
        };
        int size = inputMatrix4By4.length;
        List<Arguments> listOfArguments = new ArrayList<>();
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                listOfArguments.add(Arguments.of(row, col, expectedMatrix[row][col], inputMatrix4By4));
        return listOfArguments.stream();
    }

    @ParameterizedTest
    @MethodSource("get_provideTestCasesForCoordinatesOutsideMatrix")
    void get_WhenCoordinatesOutsideRange_Throws(int row, int col, double[][] inputMatrix){
        var dependencyMatrix = makeDependencyMatrix(inputMatrix);
        assertThrows(IndexOutOfBoundsException.class, ()->dependencyMatrix.get(row,col));
    }

    private static Stream<Arguments> get_provideTestCasesForCoordinatesOutsideMatrix(){
        var inputMatrix4By4 = new double[][]{
                {},
                {1},
                {2, 3},
                {4, 5, 6}
        };
        return Stream.of(
                Arguments.of(4, 0, inputMatrix4By4),
                Arguments.of(0, 4, inputMatrix4By4),
                Arguments.of(4, 1, inputMatrix4By4),
                Arguments.of(1, 4, inputMatrix4By4),
                Arguments.of(10, 10, inputMatrix4By4),
                Arguments.of(100, 200, inputMatrix4By4)
        );
    }
}
