package com.rkeeves.p4.dtomap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEconomyParametersDTOMapper {

    private static EconomyParametersDTOMapper mapper;

    private MockEconomyParametersModel economyParametersModel;

    @BeforeAll
    static void init(){
        mapper = new EconomyParametersDTOMapper();
    }

    @BeforeEach
    void initInstance(){
        economyParametersModel = new MockEconomyParametersModel();
    }

    @Test
    void toDTO_WhenGivenModel_MapsKgPerBarrelCorrectly(){
        Double value = 1025.5;
        economyParametersModel.getKgPerBarrelProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getKgPerBarrel());
    }

    @Test
    void toDTO_WhenGivenModel_MapsGlobalMarketDemandMultiplierCorrectly(){
        Double value = 1025.5;
        economyParametersModel.getGlobalMarketDemandMultiplierProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getGlobalMarketDemandMultiplier());
    }

    @Test
    void toDTO_WhenGivenModel_MapsConsumerCountCorrectly(){
        Integer value = 102;
        economyParametersModel.getConsumerCountProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getConsumerCount());
    }

    @Test
    void toDTO_WhenGivenModel_MapsGlobalSellPriceFactorCorrectly(){
        Double value = 1025.5;
        economyParametersModel.getGlobalSellPriceMultiplierProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getGlobalSellPriceFactor());
    }

    @Test
    void toDTO_WhenGivenModel_MapsWorkersPerWorkshopCorrectly(){
        Integer value = 102;
        economyParametersModel.getWorkersPerWorkshopProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getWorkersPerWorkshop());
    }

    @Test
    void toDTO_WhenGivenModel_MapsWagePerWorkerCorrectly(){
        Integer value = 102;
        economyParametersModel.getWagePerWorkerProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getWagePerWorker());
    }
    
    @Test
    void toDTO_WhenGivenModel_MapsFixedPropertyTaxCorrectly(){
        Integer value = 102;
        economyParametersModel.getFixedPropertyTaxProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getFixedPropertyTax());
    }

    @Test
    void toDTO_WhenGivenModel_MapsFixedRunningCostCorrectly(){
        Integer value = 102;
        economyParametersModel.getFixedRunningCostProperty().set(value);
        var dto = mapper.toDTO(economyParametersModel);
        assertEquals(value, dto.getFixedRunningCost());
    }
}
