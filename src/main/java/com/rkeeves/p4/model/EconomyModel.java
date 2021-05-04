package com.rkeeves.p4.model;

import java.util.List;

/**
 * This entity provides access to all data related to the economy.
 */
public interface EconomyModel {

    /**
     * Returns an entity which provides access to all "global" parameters of the economy.
     *
     * @return model of the parameters
     */
    EconomyParametersModel getEconomyParametersModel();

    /**
     * Returns a list of all products' models.
     *
     * @return list of product models
     */
    List<ProductModel> listProductModels();
}
