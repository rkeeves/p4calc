package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.ProductModel;

import java.util.HashMap;


/**
 * This entity is the default implementation of an product model to dto mapper.
 */
public class ProductDTOMapper implements DTOMapper<ProductModel, ProductDTO> {

    @Override
    public ProductDTO toDTO(ProductModel model) {
        var dto = new ProductDTO();
        dto.setName(model.getNameProperty().get());
        dto.setMarketDemandFulfillmentRatio(model.getMarketDemandFulfillmentRatioProperty().get());
        dto.setBaseDemandInKg(model.getBaseDemandInKgProperty().get());
        dto.setProductionPerWorkshop(model.getProductionPerWorkshopProperty().get());
        dto.setBasePrice(model.getBasePriceProperty().get());
        var ingredients = model.getIngredients();
        var dtoIngredients = new HashMap<String,Double>();
        for (var ingredientModelAndWeightPair: ingredients.entrySet()) {
            var name = ingredientModelAndWeightPair.getKey().getNameProperty().get();
            var weight = ingredientModelAndWeightPair.getValue().get();
            dtoIngredients.put(name,weight);
        }
        dto.setIngredients(dtoIngredients);
        return dto;
    }
}
