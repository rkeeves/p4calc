package com.rkeeves.p4.model;

import javafx.beans.property.DoubleProperty;

import java.util.Map;
import java.util.Optional;

/**
 * This entity represents a product's ability to supply information about its ingredients.
 * The different ingredients' quantities were modelled using {@code JavaFX}'s {@code NumberExpression} based change listening mechanism.
 */
public interface ProductIngredientsModel {

    /**
     * Returns a map of all products which can be used as ingredients.
     * Keys of the map are the products (via ProductBasicProperties interface).
     * Values of the map are the actual required quantities.
     * If a product is a possible ingredient, but it is not used for the recipe,
     * the quantity described by the map entry's value will be zero.
     *
     * @return map of ingredient products and their current required quantity
     */
    Map<ProductBasicPropertiesModel, DoubleProperty> getIngredients();

    /**
     * Returns optionally the current quantity for an ingredient.
     * The optional is empty if and only if the product is not a possible ingredient.
     *
     * @param ingredient the ingredient to search for
     * @return quantity of the ingredient or empty if the product is not a possible ingredient
     */
    Optional<DoubleProperty> getIngredientQuantity(ProductBasicPropertiesModel ingredient);

    /**
     * Returns optionally the current quantity for an ingredient based on name.
     * The optional is empty if and only if the product is not a possible ingredient.
     *
     * @param ingredientName the ingredient's name
     * @return quantity of the ingredient or empty if the product is not a possible ingredient
     */
    Optional<DoubleProperty> getIngredientQuantity(String ingredientName);
}
