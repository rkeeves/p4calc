package com.rkeeves.p4.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This entity represents all data which fully describes a working economy.
 */
@Data
public class EconomyDTO {

    /**
     * -- GETTER --
     * Returns the object describing the parameters of the economy.
     * * @return economy parameters object
     * -- SETTER --
     * Changes the object describing the parameters of the economy.
     * @param economyParameters new economy parameters object
     */
    @Getter
    @Setter
    private EconomyParametersDTO economyParameters;

    /**
     * -- GETTER --
     * Returns a list of all products.
     * @return list of products
     * -- SETTER --
     * Changes all the products of an economy.
     * @param products new list of products
     */
    @Getter
    @Setter
    private List<ProductDTO> products;
}
