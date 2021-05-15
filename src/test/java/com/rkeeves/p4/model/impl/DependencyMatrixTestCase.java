package com.rkeeves.p4.model.impl;

import lombok.Data;

@Data
class DependencyMatrixTestCase {

    private String testCaseName;

    private double[][] inputMatrix;

    private double[][] expectedMatrix;
}
