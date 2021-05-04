package com.rkeeves.p4;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.io.JacksonOperations;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.impl.DefaultEconomyModelService;

import java.io.IOException;

public class Entry {

    public static void main(String[] args) {
        var jackson = new JacksonOperations();
        try {
            var economyDTO = jackson.readFromResource("./data/defaultEconomyData.json", EconomyDTO.class);
            var economyModel = new DefaultEconomyModelService().create(economyDTO);
            economyModel.getEconomyParametersModel().getConsumerCountProperty().set(10000);
            System.out.println("Set consumer count to 10000");
            debug_print(economyModel);
            // We increase the number of consumers and we expect that all numbers will change, due to Bindings.
            economyModel.getEconomyParametersModel().getConsumerCountProperty().set(100000);
            System.out.println("Set consumer count to 100000, the model should 'recalculate' itself");
            debug_print(economyModel);
            // We change the ingredients for a product and we expect that all numbers will change, due to Bindings.
            // The API - as you can see from the overly complicated access - for this is not yet final.
            economyModel.listProductModels()
                    .stream()
                    .filter(productModel -> "meat".equals(productModel.getNameProperty().get()))
                    .findFirst()
                    .ifPresent(meatProduct->{
                        System.out.println("Found meat");
                        meatProduct.getIngredientQuantity("salt")
                                .ifPresent(saltQuantity->{
                                    System.out.println("Found salt ingredient");
                                    saltQuantity.set(saltQuantity.get()+1);
                                    System.out.println("Change: Meat now needs more salt, which will increase the sum demand for salt but also its ingredient's (wood) sum demand.");
                                });
                    });
            debug_print(economyModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void debug_print(EconomyModel economyModel){
        System.out.println("=====   DEBUG_PRINT   =====");
        System.out.println(String.format("Economy with %d consumers", economyModel.getEconomyParametersModel().getConsumerCountProperty().get()));
        System.out.println(String.format("%12s | %12s | %12s | %12s | %12s | %12s", "Name", "MarketDemand", "SumDemand", "Workshops", "TradeRevenue", "Profit"));
        economyModel.listProductModels()
                .stream()
                .forEachOrdered(productModel->{
                    System.out.println(String.format("%12s | %12f | %12f | %12f | %12f | %12f",productModel.getNameProperty().getValue(),
                            productModel.getMarketDemandProperty().getValue(),
                            productModel.getSumDemandProperty().getValue(),
                            productModel.getWorkshopCountProperty().getValue(),
                            productModel.getTradeRevenueProperty().getValue(),
                            productModel.getProfitProperty().getValue()));
                });
        System.out.println("===== EOF DEBUG_PRINT =====");
    }
}
