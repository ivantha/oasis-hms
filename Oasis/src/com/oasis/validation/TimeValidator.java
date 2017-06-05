package com.oasis.validation;

import com.jfoenix.controls.JFXTimePicker;
import com.oasis.factory.NotificationFactory;
import com.oasis.ui.component.Notification;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;

import java.time.LocalTime;

public class TimeValidator implements Validator {
    private final JFXTimePicker timePicker;
    private ChangeListener<LocalTime> valueChangeListener;

    public TimeValidator(JFXTimePicker timePicker) {
        this.timePicker = timePicker;

        Platform.runLater(() -> timePicker.getStyleClass().remove("time-picker-invalid"));

        valueChangeListener = (observable, oldValue, newValue) -> {
            timePicker.getStyleClass().remove("time-picker-invalid");
            if (null == newValue) {
                timePicker.getStyleClass().add("time-picker-invalid");
            }
        };
        timePicker.valueProperty().addListener(valueChangeListener);
    }

    @Override
    public boolean isValid() {
        return null != timePicker.valueProperty().get();
    }

    @Override
    public void refreshState() {
        valueChangeListener.changed(null, null, timePicker.getValue());
    }

    @Override
    public Notification getInvalidArgumentNotification() {
        return NotificationFactory.defaultInvalidArguementNotification("Invalid time",
                "Please choose a valid time");
    }
}
