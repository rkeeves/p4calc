package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.model.EconomyParametersModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;

@Data
public class MockEconomyParametersModel implements EconomyParametersModel {

    private final DoubleProperty kgPerBarrelProperty = new SimpleDoubleProperty(1900);

    private final DoubleProperty globalMarketDemandMultiplierProperty = new SimpleDoubleProperty(1.0);

    private final IntegerProperty consumerCountProperty = new SimpleIntegerProperty(10000);

    private final DoubleProperty globalSellPriceMultiplierProperty = new SimpleDoubleProperty(1.75);

    private final IntegerProperty workersPerWorkshopProperty = new SimpleIntegerProperty(25);

    private final IntegerProperty wagePerWorkerProperty = new SimpleIntegerProperty(6);

    private final IntegerProperty fixedPropertyTaxProperty = new SimpleIntegerProperty(40);

    private final IntegerProperty fixedRunningCostProperty = new SimpleIntegerProperty(50);
}
