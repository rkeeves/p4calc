package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.EconomyModel;

public class DefaultEconomyDTOService implements EconomyDTOService{

    private final EconomyDTOMapper economyDTOMapper = new EconomyDTOMapper(new EconomyParametersDTOMapper(), new ProductDTOMapper());

    @Override
    public EconomyDTO toDto(EconomyModel economyModel) {
        return economyDTOMapper.toDTO(economyModel);
    }
}
