package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.MathBindings;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.ProductAssetModel;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import com.rkeeves.p4.model.ProductDemandModel;
import javafx.beans.binding.NumberExpression;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultProductAssetModel implements ProductAssetModel {

    private final NumberExpression workshopCountProperty;

    private final NumberExpression requiredWorkerCountProperty;

    public static DefaultProductAssetModel create(EconomyParametersModel economyParametersModel,
                                                  ProductBasicPropertiesModel productBasicPropertiesModel,
                                                  ProductDemandModel productDemandModel){
        var workshopCountRational = MathBindings.divideDefaultsIfDivisorZero(productDemandModel.getSumDemandProperty(),
                productBasicPropertiesModel.getProductionPerWorkshopProperty(),
                0.0);
        var workshopCount = MathBindings.ceil(workshopCountRational);
        var requiredWorkerCount = workshopCount.multiply(economyParametersModel.getWorkersPerWorkshopProperty());
        return DefaultProductAssetModel.builder()
                .workshopCountProperty(workshopCount)
                .requiredWorkerCountProperty(requiredWorkerCount)
                .build();
    }
}
