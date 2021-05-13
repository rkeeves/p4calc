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
     * Creates a new instance of {@code EconomyDTO}.
     */
    public EconomyDTO(){

    }

    /**
     * -- GETTER --
     * Returns the object describing the parameters of the economy.
     * @return {@code EconomyParametersDTO} object
     * -- SETTER --
     * Changes the object describing the parameters of the economy.
     * @param economyParameters new {@code EconomyParametersDTO} object
     */
    @Getter
    @Setter
    private EconomyParametersDTO economyParameters;

    /**
     * -- GETTER --
     * Returns a list of all {@code ProductDTO}s.
     * @return list of {@code ProductDTO}s
     * -- SETTER --
     * Changes all the products of an economy.
     * @param products a new list of {@code ProductDTO}s
     */
    @Getter
    @Setter
    private List<ProductDTO> products;
}
