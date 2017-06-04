package com.oasis.validation;

import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Platform;

public class DateValidator implements Validator{
    private final JFXDatePicker datePicker;

    public DateValidator(JFXDatePicker datePicker) {
        this.datePicker = datePicker;

        Platform.runLater(() ->  datePicker.getStyleClass().remove("date-picker-invalid"));

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(null == newValue){
                datePicker.getStyleClass().add("date-picker-invalid");
            }else {
                datePicker.getStyleClass().remove("date-picker-invalid");
            }
        });
    }

    @Override
    public boolean isValid() {
        return null != datePicker.valueProperty().get();
    }

    @Override
    public void setStateForce() {
        datePicker.getStyleClass().add("date-picker-invalid");
    }
}
