package com.rkeeves.p4.model;

import javafx.beans.binding.NumberExpression;

/**
 * This entity provides access to all derived properties related to finances for a given product.
 * These derived attributes are modelled using {@code JavaFX}'s {@code NumberExpression} based change listening mechanism.
 */
public interface ProductFinancialModel {

    /**
     * The trade revenue which can be generated if the goal market demand is fully met.
     *
     * @return trade revenue
     */
    NumberExpression getTradeRevenueProperty();

    /**
     * The sum of daily wages to all workers for a given product.
     *
     * @return sum of all workers daily wages
     */
    NumberExpression getWorkerWageProperty();

    /**
     * The sum of daily property taxes of all workshops for a given product.
     *
     * @return sum property taxes
     */
    NumberExpression getPropertyTaxProperty();

    /**
     * The sum of daily running costs of all workshops for a given product.
     *
     * @return sum running costs
     */
    NumberExpression getWorkshopRunningCostProperty();

    /**
     * The sum of all worker wages, property taxes and running costs.
     *
     * @return sum of all wages, taxes, running costs
     */
    NumberExpression getExpenseProperty();

    /**
     * The difference of trade revenue and expenses.
     *
     * @return profits
     */
    NumberExpression getProfitProperty();
}
