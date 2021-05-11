package com.rkeeves.p4.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestProductDTO {

    @ParameterizedTest
    @MethodSource("getIngredientWeightOrZero_provideTestCasesForExistingIngredientQueried")
    void getIngredientWeightOrZero_WhenExistingIngredientQueried_ReturnsCorrectValue(ProductDTO dto,
                                                                                     String queriedName,
                                                                                     Double expectedValue){
        assertEquals(expectedValue, dto.getIngredientWeightOrZero(queriedName));
    }

    private static Stream<Arguments> getIngredientWeightOrZero_provideTestCasesForExistingIngredientQueried(){
        var dtoA = new ProductDTO();
        dtoA.setName("A");
        var dtoB = new ProductDTO();
        dtoB.setName("B");
        dtoB.getIngredients().put("A",1.2);
        var dtoC = new ProductDTO();
        dtoC.setName("C");
        dtoC.getIngredients().put("A",3.4);
        dtoC.getIngredients().put("B",5.6);
        return Stream.of(
                Arguments.of(dtoB, "A", 1.2),
                Arguments.of(dtoC, "A", 3.4),
                Arguments.of(dtoC, "B", 5.6)
        );
    }

    @ParameterizedTest
    @MethodSource("getIngredientWeightOrZero_provideTestCasesForNonExistingIngredientQueried")
    void getIngredientWeightOrZero_WhenNonExistingIngredientQueried_ReturnsZeroe(ProductDTO dto,
                                                                                     String queriedName){
        assertEquals(0.0, dto.getIngredientWeightOrZero(queriedName));
    }

    private static Stream<Arguments> getIngredientWeightOrZero_provideTestCasesForNonExistingIngredientQueried(){
        var dtoA = new ProductDTO();
        dtoA.setName("A");
        var dtoB = new ProductDTO();
        dtoB.setName("B");
        dtoB.getIngredients().put("A",1.2);
        var dtoC = new ProductDTO();
        dtoC.setName("C");
        dtoC.getIngredients().put("A",3.4);
        dtoC.getIngredients().put("B",5.6);
        return Stream.of(
                Arguments.of(dtoA, "A"),
                Arguments.of(dtoB, "B"),
                Arguments.of(dtoB, "C"),
                Arguments.of(dtoC, "C")
                );
    }
}
