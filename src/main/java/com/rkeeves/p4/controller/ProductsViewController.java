package com.rkeeves.p4.controller;

import com.rkeeves.p4.javafx.MathBindings;
import com.rkeeves.p4.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.RequiredArgsConstructor;

import static com.rkeeves.p4.javafx.ui.TableColumns.*;

@RequiredArgsConstructor
public class ProductsViewController implements Wireable<EconomyModel>{

    @FXML
    private TableView<ProductModel> productModelsTable;

    @FXML
    private TableColumn<ProductModel,String> columnName;

    @FXML
    private TableColumn<ProductModel,Number> columnMarketDemandFulfillmentRatio;

    @FXML
    private TableColumn<ProductModel,Number> columnBasePrice;

    @FXML
    private TableColumn<ProductModel,Number> columnProductionPerWorkshop;

    @FXML
    private TableColumn<ProductModel,Number> columnBaseDemandInKg;

    @FXML
    private TableColumn<ProductModel,Number> columnMarketDemand;

    @FXML
    private TableColumn<ProductModel,Number> columnSumDemand;

    @FXML
    private TableColumn<ProductModel,Number> columnWorkshopCount;

    @FXML
    private TableColumn<ProductModel,Number> columnTradeRevenue;

    @FXML
    private TableColumn<ProductModel,Number> columnWorkerWage;

    @FXML
    private TableColumn<ProductModel,Number> columnPropertyTax;

    @FXML
    private TableColumn<ProductModel,Number> columnWorkshopRunningCost;

    @FXML
    private TableColumn<ProductModel,Number> columnExpense;

    @FXML
    private TableColumn<ProductModel,Number> columnProfit;

    @Override
    public void wireModel(EconomyModel model) {
        productModelsTable.itemsProperty().set(model.getProductModels());
        readonlyColumnForProperty(columnName, ProductBasicPropertiesModel::getNameProperty);
        editableColumnForInteger(columnMarketDemandFulfillmentRatio, ProductModel::getMarketDemandFulfillmentRatioProperty);
        editableColumnForDouble(columnBasePrice, ProductModel::getBasePriceProperty);
        editableColumnForDouble(columnProductionPerWorkshop, ProductModel::getProductionPerWorkshopProperty);
        editableColumnForDouble(columnBaseDemandInKg, ProductModel::getBaseDemandInKgProperty);
        readonlyColumnForNumberExpr(columnMarketDemand, product-> MathBindings.ceil(product.getMarketDemandProperty()));
        readonlyColumnForNumberExpr(columnSumDemand, product-> MathBindings.ceil(product.getSumDemandProperty()));
        readonlyColumnForNumberExpr(columnWorkshopCount,ProductAssetModel::getWorkshopCountProperty);
        readonlyColumnForNumberExpr(columnTradeRevenue, ProductFinancialModel::getTradeRevenueProperty);
        readonlyColumnForNumberExpr(columnWorkerWage, ProductFinancialModel::getWorkerWageProperty);
        readonlyColumnForNumberExpr(columnPropertyTax, ProductFinancialModel::getPropertyTaxProperty);
        readonlyColumnForNumberExpr(columnWorkshopRunningCost, ProductFinancialModel::getWorkshopRunningCostProperty);
        readonlyColumnForNumberExpr(columnExpense, ProductFinancialModel::getExpenseProperty);
        readonlyColumnForNumberExpr(columnProfit, ProductFinancialModel::getProfitProperty);
    }
}
