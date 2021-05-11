package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.EconomyModel;

/**
 * This service interface represents an entity which can transform a valid model into a Data Transfer Object.
 */
public interface EconomyDTOService {

    /**
     * This method transforms a user given valid economy model into a valid dto.
     *
     * @param economyModel user given economy model object
     * @return data transfer object of economy
     */
    EconomyDTO toDto(EconomyModel economyModel);
}
