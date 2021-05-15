package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.ProductDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class DependencyMatrixFactoryTestCase {

    private String testCaseName;

    private List<ProductDTO> productDTOList = new ArrayList<>();

    private double[][] expectedLowerTriangleMatrix;

    private double[][] expectedDependencyMatrix;
}
