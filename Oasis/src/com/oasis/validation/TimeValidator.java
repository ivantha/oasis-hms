package com.oasis.validation;

import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;

public class TimeValidator implements Validator{
    private final JFXTimePicker timePicker;

    public TimeValidator(JFXTimePicker timePicker) {
        this.timePicker = timePicker;

        Platform.runLater(() -> timePicker.getStyleClass().remove("time-picker-invalid"));

        timePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(null == newValue){
                timePicker.getStyleClass().add("time-picker-invalid");
            }else {
                timePicker.getStyleClass().remove("time-picker-invalid");
            }
        });
    }

    @Override
    public boolean isValid() {
        return null != timePicker.valueProperty().get();
    }

    @Override
    public void setStateForce() {
        timePicker.getStyleClass().add("time-picker-invalid");
    }
}
