package com.rkeeves.p4.model;

/**
 * This entity provides access to all properties of a given product.
 */
public interface ProductModel extends ProductBasicPropertiesModel,
        ProductDemandModel,
        ProductAssetModel,
        ProductFinancialModel,
        ProductIngredientsModel{
}
