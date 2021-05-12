package com.rkeeves.p4.dtomap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDefaultEconomyDTOService {

    private static EconomyDTOService economyDTOService;

    private static EconomyParametersDTOMapper economyParametersDTOMapper;

    private static ProductDTOMapper productDTOMapper;

    private MockEconomyModel economyModel;

    @BeforeAll
    static void init(){
        productDTOMapper = new ProductDTOMapper();
        economyParametersDTOMapper = new EconomyParametersDTOMapper();
        economyDTOService = new DefaultEconomyDTOService();
    }

    @BeforeEach
    void initInstance(){
        economyModel = new MockEconomyModel();
    }

    @Test
    void toDto_WhenGivenModel_MapsEconomyParametersModelCorrectly(){
        var actualEconomyDTO = economyDTOService.toDto(economyModel);
        var actualEconomyParametersDTO = actualEconomyDTO.getEconomyParameters();
        var expectedEconomyParametersDTO = economyParametersDTOMapper.toDTO(economyModel.getEconomyParametersModel());
        assertEquals(expectedEconomyParametersDTO, actualEconomyParametersDTO);
    }

    @Test
    void toDto_WhenGivenModel_MapsProductsCorrectly(){
        var productModels = economyModel.getProductModels();
        var productA = new MockProductModel();
        productA.getNameProperty().set("A");
        var productB = new MockProductModel();
        productB.getNameProperty().set("B");
        productModels.addAll(List.of(productA,productB));
        var actualEconomyDTO = economyDTOService.toDto(economyModel);
        var actualProducts = actualEconomyDTO.getProducts();
        var expectedProducts = economyModel.getProductModels()
                .stream()
                .map(productDTOMapper::toDTO)
                .collect(Collectors.toList());
        assertEquals(expectedProducts, actualProducts);
    }
}
