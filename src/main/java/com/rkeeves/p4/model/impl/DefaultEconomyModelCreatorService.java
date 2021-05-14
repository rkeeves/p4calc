package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.DTOInvalidException;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyModelCreatorService;
import com.rkeeves.p4.validation.EconomyDTOValidatorService;
import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@code EconomyModelService}.
 */
@RequiredArgsConstructor
public class DefaultEconomyModelCreatorService implements EconomyModelCreatorService {

    private final EconomyDTOValidatorService validatorService;
    /**
     * This method constructs a model object from the user supplied {@code Data Transfer Object}.
     * The model implements all business rules of the economy.
     *
     * @param economyDTO the {@code Data Transfer Object} representing the economy
     * @return model implementing the rules of the economy
     * @throws DTOInvalidException if the model is invalid
     */
    public EconomyModel create(EconomyDTO economyDTO) throws DTOInvalidException {
        validatorService.validate(economyDTO);
        return DefaultEconomyModel.create(economyDTO);
    }
}
