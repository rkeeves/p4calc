package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionSquareMatrices;
import com.rkeeves.p4.javafx.ExpressionVectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DependencyMatrixTest {

    @Test
    void dependencyMatrix_WhenGivenDependency_CreatesValidMatrix(){
        var lowerTriangleMatrix = new double[][]{
                {0, 0, 0},
                {0, 0, 0},
                {1, 0, 0}
        };
        var expectedMatrix = new double[][]{
                {0, 0, 1},
                {0, 0, 0},
                {1, 0, 0}
        };
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(lowerTriangleMatrix);
        assertArrayEquals(expectedMatrix,dependencyMatrix.toArray());
    }

    @Test
    void dependencyMatrix_WhenGivenProductWithMultipleDependencies_CreatesValidMatrix(){
        var lowerTriangleMatrix = new double[][]{
                {0, 0, 0},
                {1, 0, 0},
                {2, 3, 0}
        };
        var expectedMatrix = new double[][]{
                {0, 1, 5},
                {1, 0, 3},
                {2, 3, 0}
        };
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(lowerTriangleMatrix);
        assertArrayEquals(expectedMatrix,dependencyMatrix.toArray());
    }

    @Test
    void dependencyMatrix_WhenGivenLongerThanTwoDependencyChains_CreatesValidMatrix(){
        var lowerTriangleMatrix = new double[][]{
                {0, 0, 0, 0},
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0}
        };
        var expectedMatrix = new double[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(lowerTriangleMatrix);
        assertArrayEquals(expectedMatrix,dependencyMatrix.toArray());
    }

    @Test
    void dependencyMatrix_WhenGivenRealExampleBasedOnGameDefaultSettings_CreatesValidMatrix(){
        var lowerTriangleMatrix = new double[][]{
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
        };
        var expectedMatrix = new double[][]{
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
        };
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(lowerTriangleMatrix);
        assertArrayEquals(expectedMatrix,dependencyMatrix.toArray());
    }

    @Test
    void dependencyMatrix_WhenMultipliedByVector_ReturnsCorrectResult(){
        var lowerTriangleMatrix = new double[][]{
                {0, 0, 0},
                {1, 0, 0},
                {2, 3, 0}
        };
        var inputVector = ExpressionVectors.arrayExpressionVector(new double[]{1, 1, 1});
        var expectedVector = new double[]{7, 4, 1};
        var dependencyMatrix = DependencyMatrix.createByLowerTriangleValues(lowerTriangleMatrix);
        DemandMatrix lowerTriangleBlockerMatrix = new DemandMatrix(dependencyMatrix);
        var actualVector = ExpressionSquareMatrices.multiplyMatrixWithVector(lowerTriangleBlockerMatrix,inputVector);
        assertArrayEquals(expectedVector, ExpressionVectors.toArray(actualVector));
    }
}
