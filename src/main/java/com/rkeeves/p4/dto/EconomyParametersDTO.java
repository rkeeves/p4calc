package com.rkeeves.p4.dto;

import lombok.Data;

@Data
public class EconomyParametersDTO {

    private Double kgPerBarrel = 1900.0;

    private Double globalMarketDemandMultiplier = 1.0;

    private Integer consumerCount = 1;

    private Double globalSellPriceFactor = 1.8;

    private Integer workersPerWorkshop  = 25;

    private Integer wagePerWorker = 6;

    private Integer fixedPropertyTax = 40;

    private Integer fixedRunningCost = 50;
}
