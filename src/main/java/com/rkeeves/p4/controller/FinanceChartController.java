package com.rkeeves.p4.controller;

import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.ProductModel;
import javafx.beans.binding.NumberExpression;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.function.Function;

public class FinanceChartController implements Wireable<EconomyModel>{

    @FXML
    private BarChart<String,Number> barChartFinance;

    @Override
    public void wireModel(EconomyModel model) {
        addSeries(model,"Expense",ProductModel::getExpenseProperty);
        addSeries(model,"Trade Revenue",ProductModel::getTradeRevenueProperty);
        addSeries(model,"Profit",ProductModel::getProfitProperty);
    }

    private void addSeries(EconomyModel model,
                           String label,
                           Function<ProductModel, NumberExpression> propertyGetter){
        var series = makeSeries(model,label,propertyGetter);
        barChartFinance.getData().add(series);
    }

    private XYChart.Series<String,Number> makeSeries(EconomyModel model,
                                   String label,
                                   Function<ProductModel, NumberExpression> propertyGetter){
        var series = new XYChart.Series<String,Number>();
        series.setName(label);
        var dataList = series.getData();
        model.getProductModels()
                .stream()
                .map(product->{
                    var data = new XYChart.Data<String,Number>();
                    data.XValueProperty().bind(product.getNameProperty());
                    data.YValueProperty().bind(propertyGetter.apply(product));
                    return data;
                })
                .forEachOrdered(dataList::add);
        return series;
    }
}
