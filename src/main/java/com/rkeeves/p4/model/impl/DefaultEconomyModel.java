package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.ProductModel;
import lombok.*;

import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultEconomyModel implements EconomyModel {

    private final EconomyParametersModel economyParametersModel;

    @Getter(AccessLevel.PRIVATE)
    private final List<ProductModel> productModels;

    @Override
    public List<ProductModel> listProductModels() {
        return productModels;
    }

    public static DefaultEconomyModel create(EconomyDTO dto){
        var economyConstantsModel = DefaultEconomyParametersModel.create(dto.getEconomyParameters());
        var products = new ProductModelsFactory().create(economyConstantsModel, dto.getProducts());
        return DefaultEconomyModel.builder()
                .economyParametersModel(economyConstantsModel)
                .productModels(products)
                .build();
    }
}
