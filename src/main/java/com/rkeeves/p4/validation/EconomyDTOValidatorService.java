package com.rkeeves.p4.validation;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.DTOInvalidException;

/**
 * This entity represents a service which can validate an {@code EconomyDTO}.
 * A possible source of semantically invalid {@code EconomyDTO} can be due to
 * {@code ProductDTO} names not being unique (Aka a name is duplicated).
 * An other issue is a {@code ProductDTO} which has null or empty {@code String}
 * as its name field.
 * An other more convoluted issue is due to dependencies:
 * A {@code ProductDTO} can use only already defined {@code ProductDTO}s as ingredients.
 * Aka
 */
public interface EconomyDTOValidatorService {

    /**
     * Throws exception if and only if the user given dto is invalid.
     *
     * @param economyDTO user given dto to validate
     * @throws DTOInvalidException
     */
    void validate(EconomyDTO economyDTO) throws DTOInvalidException;
}
