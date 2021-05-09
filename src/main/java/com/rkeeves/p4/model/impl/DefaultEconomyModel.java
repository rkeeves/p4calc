package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.javafx.ExpressionSquareMatrix;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import com.rkeeves.p4.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class DefaultEconomyModel implements EconomyModel {

    private final EconomyParametersModel economyParametersModel;

    private final DependencyMatrix dependencyMatrix;

    @Getter(AccessLevel.PRIVATE)
    private final ObservableList<ProductModel> productModels;

    @Override
    public int getProductCount() {
        return productModels.size();
    }

    @Override
    public ObservableList<ProductModel> listProductModels() {
        return productModels;
    }

    @Override
    public LowerTriangleMatrix getDependencyMatrixMutableLowerTriangle() {
        return dependencyMatrix;
    }

    @Override
    public ExpressionSquareMatrix getDependencyMatrix() {
        return dependencyMatrix;
    }

    static DefaultEconomyModel create(EconomyDTO dto){
        var economyConstantsModel = DefaultEconomyParametersModel.create(dto.getEconomyParameters());
        var result = new ProductModelsFactory().create(economyConstantsModel, dto.getProducts());
        var dependencyMatrix = result.getSome();
        var productList = result.getOther();
        return DefaultEconomyModel.builder()
                .economyParametersModel(economyConstantsModel)
                .dependencyMatrix(dependencyMatrix)
                .productModels(FXCollections.observableArrayList(productList))
                .build();
    }
}
