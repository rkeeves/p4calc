package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.EconomyModel;

/**
 * This entity is a default implementation of a service which transforms economy models into {@code Data Transfer Object}s.
 */
public class DefaultEconomyDTOService implements EconomyDTOService{

    private final EconomyDTOMapper economyDTOMapper = new EconomyDTOMapper(new EconomyParametersDTOMapper(), new ProductDTOMapper());

    @Override
    public EconomyDTO toDto(EconomyModel economyModel) {
        return economyDTOMapper.toDTO(economyModel);
    }
}
