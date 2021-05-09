package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyModelService;

/**
 * Default implementation of EconomyModel service
 */
public class DefaultEconomyModelService implements EconomyModelService {

    /**
     * This method constructs a JavaFX model object from the user supplied DTO.
     * The model implements all business rules of the economy.
     *
     * @param dto the Data Transfer Object representing the economy
     * @return model implementing the rules of the economy
     */
    public EconomyModel create(EconomyDTO dto){
        return DefaultEconomyModel.create(dto);
    }
}
