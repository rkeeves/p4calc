package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.javafx.ExpressionVector;
import com.rkeeves.p4.javafx.ExpressionVectorAdapter;
import javafx.beans.binding.NumberExpression;

import java.util.List;

import static com.rkeeves.p4.javafx.ExpressionSquareMatrices.multiplyMatrixWithVector;

class SumDemandVectorFactory {

    private SumDemandVectorFactory(){

    }

    static ExpressionVector createSumDemandVector(DependencyMatrix dependencyMatrix, List<DefaultProductModel> productModels){
        var demandMatrix = new DemandMatrix(dependencyMatrix);
        var marketDemandsArray = new NumberExpression[productModels.size()];
        for (int i = 0; i < productModels.size(); i++) {
            marketDemandsArray[i] = productModels.get(i).getMarketDemandProperty();
        }
        var marketDemandsVector = new ExpressionVectorAdapter<>(marketDemandsArray);
        return multiplyMatrixWithVector(demandMatrix, marketDemandsVector);
    }
}
