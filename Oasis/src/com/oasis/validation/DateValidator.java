package com.oasis.validation;

import com.jfoenix.controls.JFXDatePicker;
import com.oasis.factory.NotificationFactory;
import com.oasis.ui.component.Notification;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;

import java.time.LocalDate;

public class DateValidator implements Validator {
    private final JFXDatePicker datePicker;
    private ChangeListener<LocalDate> valueChangeListener;

    public DateValidator(JFXDatePicker datePicker) {
        this.datePicker = datePicker;

        Platform.runLater(() -> datePicker.getStyleClass().remove("date-picker-invalid"));

        valueChangeListener = (observable, oldValue, newValue) -> {
            datePicker.getStyleClass().remove("date-picker-invalid");
            if (null == newValue) {
                datePicker.getStyleClass().add("date-picker-invalid");
            }
        };
        datePicker.valueProperty().addListener(valueChangeListener);
    }

    @Override
    public boolean isValid() {
        return null != datePicker.valueProperty().get();
    }

    @Override
    public void refreshState() {
        valueChangeListener.changed(null, null, datePicker.getValue());
    }

    @Override
    public Notification getInvalidArgumentNotification() {
        return NotificationFactory.defaultInvalidArguementNotification("Invalid date",
                "Please choose a valid date");
    }
}
