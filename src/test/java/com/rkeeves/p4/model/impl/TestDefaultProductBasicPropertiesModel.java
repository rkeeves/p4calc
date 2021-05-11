package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultProductBasicPropertiesModel {

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_GivenValidDTO_ReturnsValidModel(ProductDTO dto){
        var model = DefaultProductBasicPropertiesModel.create(dto);
        assertEquals(dto.getName(), model.getNameProperty().get());
        assertEquals(dto.getBaseDemandInKg(), model.getBaseDemandInKgProperty().get());
        assertEquals(dto.getBasePrice(), model.getBasePriceProperty().get());
        assertEquals(dto.getMarketDemandFulfillmentRatio(), model.getMarketDemandFulfillmentRatioProperty().get());
        assertEquals(dto.getProductionPerWorkshop(), model.getProductionPerWorkshopProperty().get());
    }

    private static Stream<Arguments> create_provideTestCases(){
        var dtoA = new ProductDTO();
        dtoA.setName("A");
        dtoA.setBaseDemandInKg(10.1);
        dtoA.setBasePrice(10.6);
        dtoA.setProductionPerWorkshop(65.5);
        dtoA.setMarketDemandFulfillmentRatio(100);
        dtoA.getIngredients().put("B",10.0);
        var dtoB = new ProductDTO();
        dtoA.setName("B");
        dtoA.setBaseDemandInKg(1.1);
        dtoA.setBasePrice(50.6);
        dtoA.setProductionPerWorkshop(665.5);
        dtoA.setMarketDemandFulfillmentRatio(70);
        dtoA.getIngredients().put("C",16.0);
        dtoA.getIngredients().put("D",17.0);
        return Stream.of(
                Arguments.of(dtoA),
                Arguments.of(dtoB)
        );
    }
}
