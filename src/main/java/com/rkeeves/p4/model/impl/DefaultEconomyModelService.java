package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyModelService;

public class DefaultEconomyModelService implements EconomyModelService {

    public EconomyModel create(EconomyDTO dto){
        return DefaultEconomyModel.create(dto);
    }
}
