package com.rkeeves.p4.model;

import javafx.beans.binding.NumberExpression;

public interface MutableProductDemandModel extends ProductDemandModel{

    /**
     * Binds the sum demand to a given expression.
     * Due to dependency issues the sum must be set after all product objects are constructed.
     * The products are not independent of each other
     * because a workshop of one product type can use other products as ingredients.
     *
     * @param sumDemandExpression
     */
    void bindSumDemandExpression(NumberExpression sumDemandExpression);
}
