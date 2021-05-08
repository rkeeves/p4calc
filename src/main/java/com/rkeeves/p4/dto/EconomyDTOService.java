package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.EconomyModel;

public interface EconomyDTOService {

    EconomyDTO toDto(EconomyModel economyModel);
}
