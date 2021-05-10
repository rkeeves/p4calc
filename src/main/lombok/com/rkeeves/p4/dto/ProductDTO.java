package com.rkeeves.p4.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * This entity represents data for one product in the economy.
 */
@Data
public class ProductDTO {

    /**
     * -- GETTER --
     * Returns the name of the product.
     *
     * @return the product's name
     * -- SETTER --
     * Changes the name of the product.
     *
     * @param name new name
     */
    private String name;

    /**
     * -- GETTER --
     * Returns the map of ingredients used by this product.
     * Keys are the ingredient products' names, while values are the needed amount.
     *
     * @return ingredients map
     * -- SETTER --
     * Changes the map of ingredients used by this product.
     *
     * @param ingredients new ingredients map
     */
    private Map<String, Double> ingredients = new HashMap<>();

    /**
     * -- GETTER --
     * Returns a percentage ratio of market demand fulfillment, which is provided output divided by actual demand.
     * For example, if this value is 100 then the calculation will exactly meet the demands.
     * If it is less than 100 then the calculation will under produce compared to demands.
     * If it is more than 100 then the calculation will over produce compared to demands.
     *
     * @return market demand fulfillment ratio
     * -- SETTER --
     * Changes the percentage ratio of market demand fulfillment
     *
     * @param marketDemandFulfillmentRatio new market demand fulfillment ratio
     */
    private Integer marketDemandFulfillmentRatio = 100;

    /**
     * -- GETTER --
     * Returns the base daily demand per capita for this product.
     *
     * @return base demand in kg
     * -- SETTER --
     * Changes the base daily demand per capita for this product.
     *
     * @param baseDemandInKg new base demand in kg
     */
    private Double baseDemandInKg;

    /**
     * -- GETTER --
     * Returns the base daily output in barrels for a given workshop which produces this product.
     *
     * @return production per workshop in barrels
     * -- SETTER --
     * Changes the base daily output in barrels for a given workshop which produces this product.
     *
     * @param productionPerWorkshop new production per workshop in barrels
     */
    private Double productionPerWorkshop;

    /**
     * -- GETTER --
     * Returns the base price this product.
     *
     * @return base price
     * -- SETTER --
     * Changes the base price this product.
     *
     * @param basePrice new base price
     */
    private Double basePrice;


    /**
     * Returns how many ingredients (in barrel) are used for the particular product,
     * which has the user given name.
     * If the product by the user given name is not an actual ingredient it returns zero.
     *
     * @param ingredient
     * @return the ingredient's used amount, or zero if the ingredient is unused for this product
     */
    public Double getIngredientWeightOrZero(String ingredient){
        return ingredients.getOrDefault(ingredient,0.0);
    }
}
