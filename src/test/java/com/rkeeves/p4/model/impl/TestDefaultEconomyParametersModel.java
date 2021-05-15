package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyParametersDTO;
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

class TestDefaultEconomyParametersModel {

    private static final String TEST_CASE_ARRAY_RESOURCE_NAME = "/model/economyParameters/validEconomyParametersDTOArray.json";

    private static JSONService jsonService;

    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
    }

    @ParameterizedTest
    @MethodSource("create_provideTestCases")
    void create_GivenValidDTO_ReturnsValidModel(EconomyParametersDTO dto){
        var model = DefaultEconomyParametersModel.create(dto);
        assertEquals(dto.getConsumerCount(), model.getConsumerCountProperty().get());
        assertEquals(dto.getFixedPropertyTax(), model.getFixedPropertyTaxProperty().get());
        assertEquals(dto.getFixedRunningCost(), model.getFixedRunningCostProperty().get());
        assertEquals(dto.getGlobalMarketDemandMultiplier(), model.getGlobalMarketDemandMultiplierProperty().get());
        assertEquals(dto.getGlobalSellPriceFactor(), model.getGlobalSellPriceMultiplierProperty().get());
        assertEquals(dto.getKgPerBarrel(), model.getKgPerBarrelProperty().get());
        assertEquals(dto.getWagePerWorker(), model.getWagePerWorkerProperty().get());
        assertEquals(dto.getWorkersPerWorkshop(), model.getWorkersPerWorkshopProperty().get());
    }

    private static Stream<Arguments> create_provideTestCases() throws JSONReadFailedException {
        var dtoArray = jsonService.readFromResource(TEST_CASE_ARRAY_RESOURCE_NAME,
                EconomyParametersDTO[].class);
        return Arrays.stream(dtoArray)
                .map(Arguments::of);
    }
}
