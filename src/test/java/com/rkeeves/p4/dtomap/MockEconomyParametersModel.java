package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.model.EconomyParametersModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;

@Data
class MockEconomyParametersModel implements EconomyParametersModel {

    private final DoubleProperty kgPerBarrelProperty = new SimpleDoubleProperty();

    private final DoubleProperty globalMarketDemandMultiplierProperty = new SimpleDoubleProperty();

    private final IntegerProperty consumerCountProperty = new SimpleIntegerProperty();

    private final DoubleProperty globalSellPriceMultiplierProperty = new SimpleDoubleProperty();

    private final IntegerProperty workersPerWorkshopProperty = new SimpleIntegerProperty();

    private final IntegerProperty wagePerWorkerProperty = new SimpleIntegerProperty();

    private final IntegerProperty fixedPropertyTaxProperty = new SimpleIntegerProperty();

    private final IntegerProperty fixedRunningCostProperty = new SimpleIntegerProperty();
}
