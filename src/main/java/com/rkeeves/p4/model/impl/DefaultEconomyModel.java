package com.rkeeves.p4.model.impl;

import com.rkeeves.p4.dto.EconomyDTO;
import com.rkeeves.p4.dto.ProductDTO;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.EconomyParametersModel;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import com.rkeeves.p4.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class DefaultEconomyModel implements EconomyModel {

    private final EconomyParametersModel economyParametersModel;

    private final DependencyMatrix dependencyMatrix;

    @Getter(AccessLevel.PRIVATE)
    private final ObservableList<ProductModel> productModels;

    @Override
    public ObservableList<ProductModel> listProductModels() {
        return productModels;
    }

    @Override
    public LowerTriangleMatrix getDependencyMatrixMutableLowerTriangle() {
        return dependencyMatrix;
    }

    static DefaultEconomyModel create(EconomyDTO dto){
        var economyConstantsModel = DefaultEconomyParametersModel.create(dto.getEconomyParameters());
        var productDTOs = dto.getProducts();
        var defaultProductModels = createProductModels(economyConstantsModel, productDTOs);
        var dependencyMatrix = DependencyMatrixFactory.createDependencyMatrix(productDTOs);
        var sumDemandVector = SumDemandVectorFactory.createSumDemandVector(dependencyMatrix,defaultProductModels);
        var productModels = IngredientPopulator.create(dependencyMatrix,sumDemandVector,defaultProductModels);
        return DefaultEconomyModel.builder()
                .economyParametersModel(economyConstantsModel)
                .dependencyMatrix(dependencyMatrix)
                .productModels(FXCollections.observableArrayList(productModels))
                .build();
    }

    private static List<DefaultProductModel> createProductModels(EconomyParametersModel parametersModel, List<ProductDTO> productDTOs){
        return productDTOs.stream()
                .sequential()
                .map(productDTO->DefaultProductModel.create(parametersModel, productDTO))
                .collect(Collectors.toList());
    }
}
