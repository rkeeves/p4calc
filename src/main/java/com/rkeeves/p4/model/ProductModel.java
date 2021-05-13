package com.rkeeves.p4.model;

/**
 * This entity provides access to all properties of a given product.
 * This interface is simply a collection of other more fine-grained interfaces.
 */
public interface ProductModel extends ProductBasicPropertiesModel,
        ProductDemandModel,
        ProductAssetModel,
        ProductFinancialModel,
        ProductIngredientsModel{
}
