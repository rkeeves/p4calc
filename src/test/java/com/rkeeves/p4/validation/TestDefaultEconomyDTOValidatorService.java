package com.rkeeves.p4.validation;

import com.rkeeves.p4.io.JSONReadFailedException;
import com.rkeeves.p4.io.JSONService;
import com.rkeeves.p4.io.JacksonJSONService;
import com.rkeeves.p4.model.DTOInvalidException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestDefaultEconomyDTOValidatorService {

    private static final String INVALID_TEST_CASE_ARRAY_RESOURCE_NAME = "/validation/invalidEconomyDTOTestCasesArray.json";

    private static final String VALID_TEST_CASE_ARRAY_RESOURCE_NAME = "/validation/validEconomyDTOTestCasesArray.json";

    private static JSONService jsonService;

    private static EconomyDTOValidatorService validatorService;

    @BeforeAll
    static void init(){
        jsonService = new JacksonJSONService();
        validatorService = new DefaultEconomyDTOValidatorService();
    }

    @ParameterizedTest
    @MethodSource("validate_ProvideInvalidTestCases")
    void validate_GivenInvalidDTO_Throws(EconomyDTOTestCase dtoTestCase){
        assertThrows(DTOInvalidException.class,
                ()->validatorService.validate(dtoTestCase.getEconomyDTO()),
                dtoTestCase.getTestCaseName());
    }

    @ParameterizedTest
    @MethodSource("validate_ProvideValidTestCases")
    void validate_GivenValidDTO_DoesNotThrow(EconomyDTOTestCase dtoTestCase){
        assertDoesNotThrow(()->validatorService.validate(dtoTestCase.getEconomyDTO()),
                dtoTestCase.getTestCaseName());
    }

    private static Stream<Arguments> validate_ProvideInvalidTestCases() throws JSONReadFailedException {
        return provideTestCases(INVALID_TEST_CASE_ARRAY_RESOURCE_NAME);
    }

    private static Stream<Arguments> validate_ProvideValidTestCases() throws JSONReadFailedException {
        return provideTestCases(VALID_TEST_CASE_ARRAY_RESOURCE_NAME);
    }

    private static Stream<Arguments> provideTestCases(String resourceName) throws JSONReadFailedException {
        var dtoArray = jsonService.readFromResource(resourceName,
                EconomyDTOTestCase[].class);
        return Arrays.stream(dtoArray)
                .map(Arguments::of);
    }
}
