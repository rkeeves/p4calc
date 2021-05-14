package com.rkeeves.p4.validation;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.model.DTOInvalidException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the default implementation of a {@code EconomyDTOValidatorService}.
 */
public class DefaultEconomyDTOValidatorService implements EconomyDTOValidatorService {

    @Override
    public void validate(EconomyDTO economyDTO) throws DTOInvalidException {
        var orderedProductNames = economyDTO.getProducts()
                .stream()
                .map(ProductDTO::getName)
                .collect(Collectors.toList());
        checkProductNamesNoEmptyOrNull(orderedProductNames);
        checkProductNamesUnique(orderedProductNames);
        checkIngredientsPrecedeTheProductWhichUsesThem(orderedProductNames, economyDTO.getProducts());

    }

    private void checkProductNamesNoEmptyOrNull(List<String> orderedProductNames) throws DTOInvalidException{
        var emptyNameExists = orderedProductNames.stream()
                .anyMatch(name->name==null||"".equals(name));
        if(emptyNameExists){
            throw new DTOInvalidException("At least one product didn't have a name, or it was empty!");
        }
    }

    private void checkProductNamesUnique(List<String> orderedProductNames) throws DTOInvalidException{
        var uniqueNames = new HashSet<>(orderedProductNames);
        if(orderedProductNames.size() != uniqueNames.size()){
            throw new DTOInvalidException("There are multiple products with the same name!");
        }
    }

    private void checkIngredientsPrecedeTheProductWhichUsesThem(List<String> orderedProductNames, List<ProductDTO> unorderedProductDTOList) throws DTOInvalidException {
        for (var productDto : unorderedProductDTOList) {
            var productName = productDto.getName();
            int currentIndex = orderedProductNames.indexOf(productName);
            var ingredientNames = productDto.getIngredients().keySet();
            for (var ingredientName: ingredientNames) {
                int ingredientIndex = orderedProductNames.indexOf(ingredientName);
                if(ingredientIndex == currentIndex){
                    throw new DTOInvalidException(String.format("Product '%s' was used as an ingredient for itself!", ingredientName, productName));
                }else if(ingredientIndex > currentIndex){
                    throw new DTOInvalidException(String.format("Product '%s' was used as ingredient for '%s', but '%s' was not yet defined!", ingredientName, productName, ingredientName));
                }
            }
        }
    }
}
