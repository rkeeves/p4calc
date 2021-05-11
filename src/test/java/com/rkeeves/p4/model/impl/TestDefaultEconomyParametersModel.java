package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyParametersDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDefaultEconomyParametersModel {

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

    private static Stream<Arguments> create_provideTestCases(){
        var dtoA = new EconomyParametersDTO();
        var dtoB = new EconomyParametersDTO();
        dtoB.setConsumerCount(107);
        dtoB.setFixedPropertyTax(1110);
        dtoB.setFixedRunningCost(506);
        dtoB.setGlobalMarketDemandMultiplier(665.5);
        dtoB.setGlobalSellPriceFactor(70.8);
        dtoB.setKgPerBarrel(11.2);
        dtoB.setWagePerWorker(11);
        dtoB.setWorkersPerWorkshop(56);
        return Stream.of(
                Arguments.of(dtoA),
                Arguments.of(dtoB)
        );
    }
}
