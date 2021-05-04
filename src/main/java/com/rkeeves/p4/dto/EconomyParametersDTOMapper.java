package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.EconomyParametersModel;

public class EconomyParametersDTOMapper implements DTOMapper<EconomyParametersModel, EconomyParametersDTO> {

    @Override
    public EconomyParametersDTO toDTO(EconomyParametersModel model) {
        var dto = new EconomyParametersDTO();
        dto.setKgPerBarrel(model.getKgPerBarrelProperty().get());
        dto.setGlobalMarketDemandMultiplier(model.getGlobalMarketDemandMultiplierProperty().get());
        dto.setConsumerCount(model.getConsumerCountProperty().get());
        dto.setGlobalSellPriceFactor(model.getGlobalSellPriceMultiplierProperty().get());
        dto.setWorkersPerWorkshop(model.getWorkersPerWorkshopProperty().get());
        dto.setWagePerWorker(model.getWagePerWorkerProperty().get());
        dto.setFixedPropertyTax(model.getFixedPropertyTaxProperty().get());
        dto.setFixedRunningCost(model.getFixedRunningCostProperty().get());
        return dto;
    }
}
