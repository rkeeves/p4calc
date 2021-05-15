package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyParametersDTO;
import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultEconomyParametersModel {

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
        var testFolder = "/economyTestData";
        var resources = List.of(testFolder + "/economyParametersDTO_00.json",
                testFolder + "/economyParametersDTO_01.json",
                testFolder + "/economyParametersDTO_02.json",
                testFolder + "/economyParametersDTO_03.json");
        var dtoList = new ArrayList<Arguments>();
        for (var res : resources) {
            var dto = jsonService.readFromResource(res, EconomyParametersDTO.class);
            dtoList.add(Arguments.of(dto));
        }
        return dtoList.stream();
    }
}
