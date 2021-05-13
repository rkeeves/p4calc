package com.rkeeves.p4.model;

import javafx.beans.binding.NumberExpression;

/**
 * This entity provides access to all derived attributes related to owned assets for a given product.
 * These derived attributes are modelled using {@code JavaFX}'s {@code NumberExpression} based change listening mechanism.
 */
public interface ProductAssetModel {

    /**
     * The required number of required workshops to fulfill the market demand after all multipliers are applied.
     * This number represents the gross number of workshops.
     * Aka not just the number of workshops which are needed to produce the market demand, but also to fulfill demand
     * coming from other workshops if this product is an ingredient of an other needed good.
     *
     * @return number of required workshops
     */
    NumberExpression getWorkshopCountProperty();

    /**
     * The required number of workers to fully staff all required workshops (for a given product).
     *
     * @return the number of workers
     */
    NumberExpression getRequiredWorkerCountProperty();
}
