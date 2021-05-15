package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultProductBasicPropertiesModel {

    private static final String TEST_CASE_ARRAY_RESOURCE_NAME = "/model/productBasicProperties/productDTOArray.json";

    private static JSONService jsonService;

    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
    }

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

    private static Stream<Arguments> create_provideTestCases() throws JSONReadFailedException {
        var dtoArray = jsonService.readFromResource(TEST_CASE_ARRAY_RESOURCE_NAME,
                ProductDTO[].class);
        return Arrays.stream(dtoArray)
                .map(Arguments::of);
    }
}
