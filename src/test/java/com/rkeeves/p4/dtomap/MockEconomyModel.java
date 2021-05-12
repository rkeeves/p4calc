package com.rkeeves.p4.dtomap;

import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import com.rkeeves.p4.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MockEconomyModel implements EconomyModel {

    private final MockEconomyParametersModel economyParametersModel = new MockEconomyParametersModel();

    private final List<MockProductModel> productModels = new ArrayList<>();

    private final MockLowerTriangleMatrix lowerTriangleMatrix = new MockLowerTriangleMatrix();

    @Override
    public EconomyParametersModel getEconomyParametersModel() {
        return economyParametersModel;
    }

    @Override
    public ObservableList<ProductModel> getProductModels() {
        return FXCollections.observableArrayList(productModels);
    }

    @Override
    public LowerTriangleMatrix getDependencyMatrixMutableLowerTriangle() {
        return lowerTriangleMatrix;
    }
}
