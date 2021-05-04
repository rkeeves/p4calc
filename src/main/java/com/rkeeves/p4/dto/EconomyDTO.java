package com.rkeeves.p4.dto;

import lombok.Data;

import java.util.List;

@Data
public class EconomyDTO {

    private EconomyParametersDTO economyParameters;

    private List<ProductDTO> products;
}
