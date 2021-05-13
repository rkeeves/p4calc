package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyModelCreatorService;

/**
 * Default implementation of {@code EconomyModelService}.
 */
public class DefaultEconomyModelCreatorService implements EconomyModelCreatorService {

    /**
     * This method constructs a model object from the user supplied {@code Data Transfer Object}.
     * The model implements all business rules of the economy.
     *
     * @param dto the {@code Data Transfer Object} representing the economy
     * @return model implementing the rules of the economy
     */
    public EconomyModel create(EconomyDTO dto){
        return DefaultEconomyModel.create(dto);
    }
}
