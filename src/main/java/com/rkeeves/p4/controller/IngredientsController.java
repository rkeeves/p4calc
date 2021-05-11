package com.rkeeves.p4.controller;

import com.rkeeves.p4.javafx.ui.TextFields;
import com.rkeeves.p4.model.EconomyModel;
import com.rkeeves.p4.model.LowerTriangleMatrix;
import com.rkeeves.p4.model.ProductModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class IngredientsController implements Wireable<EconomyModel>{

    @FXML
    private GridPane gridPaneIngredientMatrix;

    @Override
    public void wireModel(EconomyModel economyModel) {
        setupGrid(economyModel);
    }

    private void setupGrid(EconomyModel economyModel){
        gridPaneIngredientMatrix.getChildren().clear();
        var products = economyModel.listProductModels();
        var lowerTriangle = economyModel.getDependencyMatrixMutableLowerTriangle();
        setupRowLabels(products);
        setupColumnLabels(products);
        setupMatrixComponents(products.size(),lowerTriangle);
        var columnConstraints = gridPaneIngredientMatrix.getColumnConstraints();
        columnConstraints.addAll(IntStream.range(0,products.size())
                .mapToObj(i->new ColumnConstraints( 80))
                .collect(Collectors.toList()));
    }

    private void setupRowLabels(List<ProductModel> products){
        for (int i = 0; i < products.size(); i++) {
            var label = createLabelForProductName(products.get(i).getNameProperty());
            gridPaneIngredientMatrix.add(label, 0, i+1);
        }
    }

    private void setupColumnLabels(List<ProductModel> products){
        // We don't need the last column
        // because - by definition - a product can only depend on
        // products which were defined before itself
        for (int i = 0; i < products.size()-1; i++) {
            var label = createLabelForProductName(products.get(i).getNameProperty());
            gridPaneIngredientMatrix.add(label, i+1, 0);
        }
    }

    private Label createLabelForProductName(StringProperty productNameProperty){
        var label = new Label();
        label.textProperty().bind(productNameProperty);
        label.getStyleClass().add("ingredient-label");
        return label;
    }

    private void setupMatrixComponents(int size, LowerTriangleMatrix lowerTriangleMatrix){
        // Because we added a "name" column already
        var gridColumnOffset = 1;
        // Because we added a "name" row already
        var gridRowOffset = 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(row>col) {
                    // We place text fields only on elements of lower triangle without main diagonal
                    var textField = createTextFieldIngredient(lowerTriangleMatrix.getElementOfLowerTriangle(row,col));
                    gridPaneIngredientMatrix.add(textField, col+gridColumnOffset, row+gridRowOffset);
                }
            }
        }
    }

    private TextField createTextFieldIngredient(DoubleProperty ingredientQuantityProperty){
        var textField = new TextField();
        TextFields.bindBidirectional(textField, ingredientQuantityProperty);
        var cssClasses = textField.getStyleClass();
        cssClasses.add("modern-text-input");
        cssClasses.add("ingredient-cell");
        return textField;
    }

}
