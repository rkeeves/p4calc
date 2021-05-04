package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import javafx.beans.property.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultProductBasicPropertiesModel implements ProductBasicPropertiesModel{

    private final StringProperty nameProperty;

    private final IntegerProperty marketDemandFulfillmentRatioProperty;

    private final DoubleProperty baseDemandInKgProperty;

    private final DoubleProperty productionPerWorkshopProperty;

    private final DoubleProperty basePriceProperty;

    public static ProductBasicPropertiesModel create(ProductDTO dto){
        return DefaultProductBasicPropertiesModel.builder()
                .nameProperty(new SimpleStringProperty(dto.getName()))
                .marketDemandFulfillmentRatioProperty(new SimpleIntegerProperty(dto.getMarketDemandFulfillmentRatio()))
                .baseDemandInKgProperty(new SimpleDoubleProperty(dto.getBaseDemandInKg()))
                .productionPerWorkshopProperty(new SimpleDoubleProperty(dto.getProductionPerWorkshop()))
                .basePriceProperty(new SimpleDoubleProperty(dto.getBasePrice()))
                .build();
    }
}
