package com.rkeeves.p4.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * This entity provides access to all non-derived properties of a given product.
 */
public interface ProductBasicPropertiesModel {

    /**
     * The name of a given product.
     *
     * @return the name of a given product
     */
    StringProperty getNameProperty();

    /**
     * Represents the percentage of market demand (for a given product) which should be met.
     * For example, if market demand is 500 and this is set to 200, then 1000 products will be produced at the end
     * ('over supply').
     * But if this is set to 50, then only 250 will be produced at the end ('under supply'.).
     * This property is necessary, because - by default - due to the game's economy parameters not all demands can be
     * met, so at least one product's fulfillment ratio should be less than 100.
     *
     * @return the fulfillment of market demand in range of [0,+Inf]
     */
    IntegerProperty getMarketDemandFulfillmentRatioProperty();

    /**
     * The demand per capita of a given product in kilograms.
     * The game's ini files define this and it uses kilograms.
     * The unit conversion ratio from kilogram to barrels can be modified via economy parameters.
     *
     * @return demand per capita of a given product in kilograms
     *
     * @see com.rkeeves.p4.model.EconomyParametersModel
     */
    DoubleProperty getBaseDemandInKgProperty();

    /**
     * Production of a given product by one workshop per day in barrels.
     *
     * @return daily production per workshop
     */
    DoubleProperty getProductionPerWorkshopProperty();

    /**
     * The base price of a given product which is fixed.
     * The economy model of the game relies on a fixed price model.
     * Each product has an underlying base price and if it is under/over supplied
     * the market calculates the increased/decreased price by simply applying a multiplier
     * on this base price.
     *
     * @return base price of a given product
     */
    DoubleProperty getBasePriceProperty();
}
