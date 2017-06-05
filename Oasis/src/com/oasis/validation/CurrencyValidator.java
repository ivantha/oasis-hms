package com.oasis.validation;

import com.oasis.factory.NotificationFactory;
import com.oasis.ui.component.Notification;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyValidator implements Validator {
    private final TextField textField;
    private StringProperty value = new SimpleStringProperty();
    private ChangeListener<String> valueChangeListener;
    private boolean valid = false;

    public CurrencyValidator(TextField textField) {
        this.textField = textField;
        value.bind(textField.textProperty());

        Platform.runLater(() -> textField.getStyleClass().remove("text-field-invalid"));

        valueChangeListener = (observable, oldValue, newValue) -> {
            valid = true;
            textField.getStyleClass().remove("text-field-invalid");

            if (null != newValue) {
                Pattern pattern = Pattern.compile("\\d+(\\.\\d)?\\d*");
                Matcher matcher = pattern.matcher(newValue);

                if (!matcher.matches()) {
                    valid = false;
                    textField.getStyleClass().add("text-field-invalid");
                }
            } else {
                valid = false;
                textField.getStyleClass().add("text-field-invalid");
            }
        };
        value.addListener(valueChangeListener);
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void refreshState() {
        valueChangeListener.changed(null, null, textField.getText());
    }

    @Override
    public Notification getInvalidArgumentNotification() {
        return NotificationFactory.defaultInvalidArguementNotification("Invalid currency amount",
                "The currency amount \"" + textField.getText() + "\" is not correct. " +
                        "Please enter a valid currency amount");
    }
}
