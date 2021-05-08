package com.rkeeves.p4.model;

import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import javafx.collections.ObservableList;

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


    int getProductCount();
    /**
     * Returns a list of all products' models.
     *
     * @return list of product models
     */
    ObservableList<ProductModel> listProductModels();

    LowerTriangleMatrix getDependencyMatrixMutableLowerTriangle();

    ExpressionSquareMatrix getDependencyMatrix();
}
