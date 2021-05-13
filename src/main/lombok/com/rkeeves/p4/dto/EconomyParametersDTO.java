package com.rkeeves.p4.dto;

import lombok.Data;

/**
 * This entity contains all global parameters related to economy calculations.
 */
@Data
public class EconomyParametersDTO {


    /**
     * Creates a new instance of {@code EconomyParametersDTO}.
     */
    public EconomyParametersDTO() {
    }

    /**
     * -- GETTER --
     * Returns the ratio of unit conversion between kilograms and barrels.
     * For example a value of 1900 means that 1900 kg fits into one barrel.
     *
     * @return ratio of kg to barrels
     * -- SETTER --
     * Changes the ratio of unit conversion between kilograms and barrels.
     *
     * @param kgPerBarrel new ratio
     */
    private Double kgPerBarrel = 1900.0;

    /**
     * -- GETTER --
     * Returns the multiplier which gets applies globally to market demands.
     * For example a value of 1.1 means that all market demands will be multiplied by 1.1.
     *
     * @return multiplier of market demands
     * -- SETTER --
     * Changes the multiplier which gets applies globally to market demands.
     *
     * @param globalMarketDemandMultiplier new multiplier
     */
    private Double globalMarketDemandMultiplier = 1.0;

    /**
     * -- GETTER --
     * Returns how many consumers are used for the calculations.
     *
     * @return consumer count
     * -- SETTER --
     * Changes how many consumers are used for the calculations.
     *
     * @param consumerCount new consumer count
     */
    private Integer consumerCount = 1;

    /**
     * -- GETTER --
     * Returns the price factor for calculating sell price.
     * The sell price is calculated for each product by multiplying their respective
     * base price values with this number.
     *
     * @return sell price factor
     * -- SETTER --
     * Changes the price factor for calculating sell price.
     *
     * @param globalSellPriceFactor new sell price factor
     */
    private Double globalSellPriceFactor = 1.8;

    /**
     * -- GETTER --
     * Returns how many workers does a workshop employ when it is fully staffed.
     *
     * @return worker count per workshop
     * -- SETTER --
     * Changes how many workers does a workshop employ when it is fully staffed.
     *
     * @param workersPerWorkshop new worker count per workshop
     */
    private Integer workersPerWorkshop  = 25;

    /**
     * -- GETTER --
     * Returns the daily wage of any worker working in any workshop.
     *
     * @return daily wage
     * -- SETTER --
     * Changes how many workers does a workshop employ when it is fully staffed.
     *
     * @param wagePerWorker new daily wage
     */
    private Integer wagePerWorker = 6;

    /**
     * -- GETTER --
     * Returns the fixed property tax which must be payed by the the player at the end of each day.
     * In this game property tax is a fixed value.
     * The player must pay this tax after all workshops (and houses).
     * Fixed in this context means that this tax is not a percentage based,
     * but rather a fixed amount.
     *
     * @return fixed property tax
     * -- SETTER --
     * Changes the fixed property tax which must be payed by the the player at the end of each day.
     *
     * @param fixedPropertyTax new fixed property tax
     */
    private Integer fixedPropertyTax = 40;

    /**
     * -- GETTER --
     * Returns the fixed running cost for a workshop per day.
     * Fixed in this context means that this cost doesn't depend on the utlization of a workshop.
     * This cost must be always payed by the player each day.
     *
     * @return fixed running cost of a workshop
     * -- SETTER --
     * Changes the fixed running cost for a workshop per day.
     *
     * @param fixedRunningCost new fixed running cost
     */
    private Integer fixedRunningCost = 50;
}
