package com.rkeeves.p4.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

/**
 * This entity provides access to all global parameters related to economy calculations.
 * These parameters are modelled using {@code JavaFX}'s {@code Property} based change listening mechanism.
 */
public interface EconomyParametersModel {

    /**
     * The conversion ratio between kilograms and barrels.
     * The basic needs for different goods are configurable in kilograms (via ini files),
     * but the actual game presents all data in barrels.
     * The conversion of these two units uses this conversion ratio.
     * By default it is '1900 kilograms per barrel', in which case this property should be 1900.
     * Basically: "How many kilograms are represented by one barrel".
     *
     * @return kilogram to barrel conversion ratio in range of ]0,+Infinity[.
     */
    DoubleProperty getKgPerBarrelProperty();

    /**
     * Demand gets multiplied by this number.
     * This applies to each and every good, hence the 'global' prefix.
     * This is basically a way to globally increase demand of all products.
     *
     * @return globally applied demand multiplier in range of ]0,+Infinity[
     */
    DoubleProperty getGlobalMarketDemandMultiplierProperty();

    /**
     * Represents the number of expected consumers.
     * This is just an estimation :Your actual consumer count can be less or greater than this.
     * Example: if you crank up the global market demand multiplier,
     * then this might not match the actual consumer count.
     * This is due to the fact that your workers ARE the consumers.
     * So if you need 2 workers to fulfill the demand of 1 consumer,
     * then you can meet the demand only 1 of the 2 workers.
     *
     * @return the expected consumer count
     */
    IntegerProperty getConsumerCountProperty();

    /**
     * This is a global multiplier which is applied on all products' base price to get the sell price.
     * Each product has a base price, but - depending on the scarcity of a product in a market -
     * you can charge more for that product. This act of 'charging more' is represented by this value.
     * For example, if you set this to 1.75 that means you are planning on charging 1.75*'base price',
     * instead of 'base price' for any product.
     *
     * @return globally applied sell price multiplier of ]0,+Infinity[
     */
    DoubleProperty getGlobalSellPriceMultiplierProperty();

    /**
     * The number of workers per a workshop of ANY type.
     * The game doesn't differentiate between workshops producing different product.
     * For example one building of a vineyard and one building of a hunter's lodge need the same amount of workers.
     *
     * @return number of workers per workshop
     */
    IntegerProperty getWorkersPerWorkshopProperty();

    /**
     * The wage of each worker per day.
     * The game doesn't differentiate between workshops producing different product.
     * For example a vineyard worker and a hunter's lodge worker require the same amount of wage.
     *
     * @return the wage of each worker per day
     */
    IntegerProperty getWagePerWorkerProperty();

    /**
     * The property tax in this game is fixed.
     * Each property no matter its type costs the same amount each day (in property taxes).
     * This value represents this fixed tax property.
     *
     * @return daily fixed property tax of any building
     */
    IntegerProperty getFixedPropertyTaxProperty();

    /**
     * This represents the cost of keeping any building running each day.
     * This has nothing to do with actual production.
     * This is a fixed cost which you must pay if you want to keep that building operational.
     *
     * @return daily fixed cost of an active building
     */
    IntegerProperty getFixedRunningCostProperty();
}
