package com.rkeeves.p4.model;

import com.rkeeves.p4.dto.EconomyDTO;

/**
 * This service is responsible for providing {@code EconomyModel} for the user.
 */
public interface EconomyModelCreatorService {

    /**
     * Based on the economy model dto given by a user, provides a {@code Property}-based model of the economy.
     *
     * @param dto the {@code Data Transfer Object} representing the economy
     * @return model of the economy
     */
    EconomyModel create(EconomyDTO dto) throws DTOInvalidException;
}
