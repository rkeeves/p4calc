package com.rkeeves.p4.model.impl;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
class ExpectedDerivedProductAttribute {

    private String name;

    private Map<String, Double> ingredients = new HashMap<>();

    private Double baseDemandInKg;

    private Double productionPerWorkshop;

    private Double basePrice;

    private Double baseDemand;

    private Double marketDemand;

    private Double sumDemand;

    private Integer workShopCount;

    private Integer requiredWorkerCount;

    private Double tradeRevenue;

    private Integer workerWage;

    private Integer propertyTax;

    private Integer workshopRunningCost;

    private Integer expense;

    private Integer profit;
}
