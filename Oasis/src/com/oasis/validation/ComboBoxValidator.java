package com.oasis.validation;

import com.oasis.factory.NotificationFactory;
import com.oasis.ui.component.Notification;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;

public class ComboBoxValidator<T> implements Validator {
    private final ComboBox<T> comboBox;
    private ChangeListener<T> valueChangeListener;

    public ComboBoxValidator(ComboBox comboBox) {
        this.comboBox = comboBox;

        Platform.runLater(() -> comboBox.getStyleClass().remove("combo-box-invalid"));

        valueChangeListener = (observable, oldValue, newValue) -> {
            comboBox.getStyleClass().remove("combo-box-invalid");
            if (null == newValue) {
                comboBox.getStyleClass().add("combo-box-invalid");
            }
        };
        comboBox.getSelectionModel().selectedItemProperty().addListener(valueChangeListener);
    }

    @Override
    public boolean isValid() {
        return null != comboBox.getSelectionModel().getSelectedItem();
    }

    @Override
    public void refreshState() {
        valueChangeListener.changed(null, null, comboBox.getSelectionModel().selectedItemProperty().get());
    }

    @Override
    public Notification getInvalidArgumentNotification() {
        return NotificationFactory.defaultInvalidArguementNotification("Value not selected",
                "Please choose a valid value from the drop down list");
    }
}
