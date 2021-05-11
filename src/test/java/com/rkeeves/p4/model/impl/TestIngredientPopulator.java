package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dtomap.MockProductModel;
import com.rkeeves.p4.javafx.ExpressionVector;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import com.rkeeves.p4.model.MutableProductModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestIngredientPopulator {


    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenGivenValidInput_ReturnsProductsWithBoundSumDemandFields(LowerTriangleMatrix lowerTriangleMatrix,
                                                                            ExpressionVector sumDemands,
                                                                            List<? extends MutableProductModel> productModels,
                                                                            List<Map<ProductBasicPropertiesModel, DoubleProperty>> ingredientMaps){
        var boundedProductModels = IngredientPopulator.create(lowerTriangleMatrix,sumDemands,productModels);
        assertEquals(sumDemands.size(), boundedProductModels.size());
        for (int i = 0; i < boundedProductModels.size(); i++) {
            assertEquals(sumDemands.get(i).doubleValue(),boundedProductModels.get(i).getSumDemandProperty().doubleValue());
        }
    }

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_WhenGivenValidInput_ReturnsProductsWithPopulatedIngredientMaps(LowerTriangleMatrix lowerTriangleMatrix,
                                                                               ExpressionVector sumDemands,
                                                                               List<? extends MutableProductModel> productModels,
                                                                               List<Map<ProductBasicPropertiesModel, DoubleProperty>> expectedIngredientMaps){
        var boundedProductModels = IngredientPopulator.create(lowerTriangleMatrix,sumDemands,productModels);
        assertEquals(sumDemands.size(), boundedProductModels.size());
        assertEquals(sumDemands.size(), expectedIngredientMaps.size());
        var names = productModels.stream()
                .map(ProductModel::getNameProperty)
                .map(StringProperty::get)
                .collect(Collectors.toList());
        for (int i = 0; i < boundedProductModels.size(); i++) {
            var boundedProductModel = boundedProductModels.get(i);
            var actualIngredientMap = boundedProductModel.getIngredients();
            var expectedIngredientMap = expectedIngredientMaps.get(i);
            for (var name : names) {
                assertEquals(expectedIngredientMap.get(name),actualIngredientMap.get(name));
            }
        }
    }

    private static Stream<Arguments> create_provideTestCases() {
        return Stream.of(
                caseZero(),
                caseOne(),
                caseTwo(),
                caseThree(),
                caseFour()
        );
    }

    private static Arguments caseZero() {
        var lowerTriangle = new double[][]{};
        var sumDemands = new double[]{};
        List<? extends MutableProductModel> productModels = List.of();
        List<Map<ProductBasicPropertiesModel, DoubleProperty>> ingredientMaps = List.of();
        return Arguments.of(makeLowerTriangle(lowerTriangle),
                makeSumDemandVector(sumDemands),
                productModels,
                ingredientMaps);
    }

    private static Arguments caseOne() {
        var lowerTriangle = new double[][]{{}};
        var sumDemands = new double[]{1.0};
        var productA = new MockProductModel();
        productA.getNameProperty().set("A");
        List<? extends MutableProductModel> productModels = List.of(productA);
        List<Map<ProductBasicPropertiesModel, DoubleProperty>> ingredientMaps = List.of(
                map()
        );
        return Arguments.of(makeLowerTriangle(lowerTriangle),
                makeSumDemandVector(sumDemands),
                productModels,
                ingredientMaps);
    }

    private static Arguments caseTwo() {
        var lowerTriangle = new double[][]{
                {},
                {1.0}
        };
        var sumDemands = new double[]{1.0, 10.0};
        var productA = new MockProductModel();
        productA.getNameProperty().set("A");
        var productB = new MockProductModel();
        productB.getNameProperty().set("B");
        List<? extends MutableProductModel> productModels = List.of(productA, productB);
        List<Map<ProductBasicPropertiesModel, DoubleProperty>> ingredientMaps = List.of(
                map(),
                map(pair(productA,1.0))
        );
        return Arguments.of(makeLowerTriangle(lowerTriangle),
                makeSumDemandVector(sumDemands),
                productModels,
                ingredientMaps);
    }

    private static Arguments caseThree() {
        var lowerTriangle = new double[][]{
                {},
                {1.0},
                {2.0, 3.0}
        };
        var sumDemands = new double[]{1.0, 10.0, 100.0};
        var productA = new MockProductModel();
        productA.getNameProperty().set("A");
        var productB = new MockProductModel();
        productB.getNameProperty().set("B");
        var productC = new MockProductModel();
        productC.getNameProperty().set("C");
        List<? extends MutableProductModel> productModels = List.of(productA, productB, productC);
        List<Map<ProductBasicPropertiesModel, DoubleProperty>> ingredientMaps = List.of(
                map(),
                map(pair(productA,1.0)),
                map(pair(productA,2.0),pair(productB,3.0))
        );
        return Arguments.of(makeLowerTriangle(lowerTriangle),
                makeSumDemandVector(sumDemands),
                productModels,
                ingredientMaps);
    }

    private static Arguments caseFour() {
        var lowerTriangle = new double[][]{
                {},
                {1.0},
                {2.0, 3.0},
                {4.0, 5.0, 6.0}
        };
        var sumDemands = new double[]{1.0, 10.0, 100.0, 1000.0};
        var productA = new MockProductModel();
        productA.getNameProperty().set("A");
        var productB = new MockProductModel();
        productB.getNameProperty().set("B");
        var productC = new MockProductModel();
        productC.getNameProperty().set("C");
        var productD = new MockProductModel();
        productD.getNameProperty().set("D");
        List<? extends MutableProductModel> productModels = List.of(productA, productB, productC, productD);
        List<Map<ProductBasicPropertiesModel, DoubleProperty>> ingredientMaps = List.of(
                map(),
                map(pair(productA,1.0)),
                map(pair(productA,2.0),pair(productB,3.0)),
                map(pair(productA,4.0),pair(productB,5.0),pair(productC,6.0))
        );
        return Arguments.of(makeLowerTriangle(lowerTriangle),
                makeSumDemandVector(sumDemands),
                productModels,
                ingredientMaps);
    }

    private static LowerTriangleMatrix makeLowerTriangle(double[][] values){
        return new MockLowerTriangleMatrix(values);
    }

    static class MockLowerTriangleMatrix implements LowerTriangleMatrix{

        private SimpleDoubleProperty[][] doubleProperties;

        MockLowerTriangleMatrix(double[][] values){
            int size = values.length;
            doubleProperties = new SimpleDoubleProperty[size][];
            for (int i = 0; i < size; i++) {
                doubleProperties[i] = new SimpleDoubleProperty[size];
                for (int j = 0; j < i; j++) {
                    doubleProperties[i][j] = new SimpleDoubleProperty(values[i][j]);
                }
            }
        }

        @Override
        public DoubleProperty getElementOfLowerTriangle(int row, int col) {
            return doubleProperties[row][col];
        }
    }

    private static ExpressionVector makeSumDemandVector(double[] array){
        return new ExpressionVector(){

            @Override
            public int size() {
                return array.length;
            }

            @Override
            public NumberExpression get(int index) {
                return Bindings.createDoubleBinding(()->array[index]);
            }
        };
    }

    private static Map<ProductBasicPropertiesModel, DoubleProperty> map(Pair<ProductModel, Double>... pairs){
        var map = new HashMap<ProductBasicPropertiesModel, DoubleProperty>();
        for (var entry: pairs) {
            map.put(entry.getKey(), new SimpleDoubleProperty(entry.getValue()));
        }
        return map;
    }


    private static Pair<ProductModel, Double> pair(ProductModel model, Double value){
        return new Pair<>(model,value);
    }

}
