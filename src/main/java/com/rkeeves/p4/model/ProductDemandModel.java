package com.rkeeves.p4.model;

import javafx.beans.binding.NumberExpression;

/**
 * This entity provides access to all derived properties related to demand for a given product.
 */
public interface ProductDemandModel {

    /**
     * The base demand in barrels for a given product.
     * This represents demand ONLY by consumers, NOT by industries using this product as ingredient.
     *
     * @return base demand
     */
    NumberExpression getBaseDemandProperty();

    /**
     * The demand after every multiplier and modifier is applied to base demand (for a given product).
     * This represents demand ONLY by consumers, NOT by industries using this product as ingredient.
     *
     * @return market demand
     */
    NumberExpression getMarketDemandProperty();

    /**
     * This represents demand by consumers AND by industries using this product as ingredient.
     *
     * @return sum of market demand and industry demand
     */
    NumberExpression getSumDemandProperty();
}
