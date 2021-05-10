package com.rkeeves.p4.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductDTO {

    private String name;

    private Map<String, Double> ingredients = new HashMap<>();

    private Integer marketDemandFulfillmentRatio = 100;

    private Double baseDemandInKg;

    private Double productionPerWorkshop;

    private Double basePrice;

    public Double getIngredientWeightOrZero(String ingredient){
        return ingredients.getOrDefault(ingredient,0.0);
    }
}
