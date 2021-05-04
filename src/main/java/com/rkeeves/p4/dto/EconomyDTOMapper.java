package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.EconomyModel;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EconomyDTOMapper implements DTOMapper<EconomyModel, EconomyDTO> {

    private final EconomyParametersDTOMapper economyParametersDTOMapper;

    private final ProductDTOMapper productDTOMapper;

    @Override
    public EconomyDTO toDTO(EconomyModel model) {
        var dto = new EconomyDTO();
        dto.setEconomyParameters(economyParametersDTOMapper.toDTO(model.getEconomyParametersModel()));
        var products = model.listProductModels()
                .stream()
                .map(productDTOMapper::toDTO)
                .collect(Collectors.toList());
        dto.setProducts(products);
        return dto;
    }
}
