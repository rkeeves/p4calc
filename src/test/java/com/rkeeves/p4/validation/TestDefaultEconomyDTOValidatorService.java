package com.rkeeves.p4.validation;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import com.rkeeves.p4.model.DTOInvalidException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TestDefaultEconomyDTOValidatorService {
    private static JSONService jsonService;

    private static EconomyDTOValidatorService validatorService;

    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
        validatorService = new DefaultEconomyDTOValidatorService();
    }

    @Test
    void validate_WhenProductNameIsEmptyString_Throw(){
        try {
            var dto = jsonService.readFromResource("/economyTestData/error_empty_string_product_name.json", EconomyDTO.class);
            assertThrows(DTOInvalidException.class,()->validatorService.validate(dto));
        } catch (JSONReadFailedException e) {
            fail("Wasn't able to load test data",e);
        }
    }

    @Test
    void validate_WhenProductNamesNotUnique_Throw(){
        try {
            var dto = jsonService.readFromResource("/economyTestData/error_duplicate_names.json", EconomyDTO.class);
            assertThrows(DTOInvalidException.class,()->validatorService.validate(dto));
        } catch (JSONReadFailedException e) {
            fail("Wasn't able to load test data",e);
        }
    }

    @Test
    void validate_WhenProductIsIngredientOfItself_Throw(){
        try {
            var dto = jsonService.readFromResource("/economyTestData/error_ingredient_of_itself.json", EconomyDTO.class);
            assertThrows(DTOInvalidException.class,()->validatorService.validate(dto));
        } catch (JSONReadFailedException e) {
            fail("Wasn't able to load test data",e);
        }
    }

    @Test
    void validate_WhenIngredientIsNotYetDefined_Throw(){
        try {
            var dto = jsonService.readFromResource("/economyTestData/error_uses_not_yet_defined_as_ingredient.json", EconomyDTO.class);
            assertThrows(DTOInvalidException.class,()->validatorService.validate(dto));
        } catch (JSONReadFailedException e) {
            fail("Wasn't able to load test data",e);
        }
    }

    @Test
    void validate_WhenDTOIsValid_DoNotThrow(){
        try {
            var dto = jsonService.readFromResource("/economyTestData/valid.json", EconomyDTO.class);
            validatorService.validate(dto);
        } catch (JSONReadFailedException e) {
            fail("Wasn't able to load test data",e);
        } catch (DTOInvalidException e){
            fail("Should not throw",e);
        }
    }
}
