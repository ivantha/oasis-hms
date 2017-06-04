package com.oasis.validation;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;

public class ComboBoxValidator implements Validator{
    private final ComboBox comboBox;

    public ComboBoxValidator(ComboBox comboBox) {
        this.comboBox = comboBox;

        Platform.runLater(() -> comboBox.getStyleClass().remove("combo-box-invalid"));

        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null == newValue){
                comboBox.getStyleClass().add("combo-box-invalid");
            }else {
                comboBox.getStyleClass().remove("combo-box-invalid");
            }
        });
    }

    @Override
    public boolean isValid() {
        return null != comboBox.getSelectionModel().getSelectedItem();
    }

    @Override
    public void setStateForce() {
        comboBox.getStyleClass().add("combo-box-invalid");
    }
}
