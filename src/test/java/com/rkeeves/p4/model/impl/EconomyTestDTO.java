package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import lombok.Data;

import java.util.Map;

@Data
class EconomyTestDTO {

    private EconomyDTO economyDTO;

    private Map<String, ExpectedDerivedProductAttribute> expectedDerivedProductAttributes;
}
