package com.rkeeves.p4.dto;

import com.rkeeves.p4.model.ProductBasicPropertiesModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestProductDTOMapper {

    private static ProductDTOMapper mapper;

    private MockProductModel productModel;

    @BeforeAll
    static void init(){
        mapper = new ProductDTOMapper();
    }

    @BeforeEach
    void initInstance(){
        productModel = new MockProductModel();
    }

    @Test
    void toDTO_WhenGivenModel_MapsNameCorrectly(){
        String name = "foo";
        productModel.getNameProperty().set(name);
        var dto = mapper.toDTO(productModel);
        assertEquals(name, dto.getName());
    }

    @Test
    void toDTO_WhenGivenModel_MapsBaseDemandInKgCorrectly(){
        double value = 21.3;
        productModel.getBaseDemandInKgProperty().set(value);
        var dto = mapper.toDTO(productModel);
        assertEquals(value, dto.getBaseDemandInKg());
    }

    @Test
    void toDTO_WhenGivenModel_MapsBasePriceCorrectly(){
        double value = 21.3;
        productModel.getBasePriceProperty().set(value);
        var dto = mapper.toDTO(productModel);
        assertEquals(value, dto.getBasePrice());
    }

    @Test
    void toDTO_WhenGivenModel_MapsIngredientsCorrectly(){
        var ingredient0 = new MockProductModel();
        ingredient0.getNameProperty().set("A");
        var ingredient0weight = 3;
        var ingredient1 = new MockProductModel();
        ingredient1.getNameProperty().set("B");
        var ingredient1weight = 5;
        var ingredients = new HashMap<ProductBasicPropertiesModel, DoubleProperty>();
        ingredients.put(ingredient0, new SimpleDoubleProperty(ingredient0weight));
        ingredients.put(ingredient1, new SimpleDoubleProperty(ingredient1weight));
        productModel.setIngredients(ingredients);
        var dto = mapper.toDTO(productModel);
        assertEquals(ingredients.size(),dto.getIngredients().size());
        var actualIngredients = dto.getIngredients();
        for (var entry : ingredients.entrySet()) {
            var actualWeight = actualIngredients.get(entry.getKey().getNameProperty().get());
            assertEquals(entry.getValue().get(), actualWeight);
        }
    }

    @Test
    void toDTO_WhenGivenModel_MapsMarketDemandFulfillmentRatioCorrectly(){
        int value = 21;
        productModel.getMarketDemandFulfillmentRatioProperty().set(value);
        var dto = mapper.toDTO(productModel);
        assertEquals(value, dto.getMarketDemandFulfillmentRatio());
    }

    @Test
    void toDTO_WhenGivenModel_MapsProductionPerWorkshopCorrectly(){
        double value = 21.1;
        productModel.getProductionPerWorkshopProperty().set(value);
        var dto = mapper.toDTO(productModel);
        assertEquals(value, dto.getProductionPerWorkshop());
    }
}
