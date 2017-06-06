package com.oasis.validation;

import com.oasis.factory.NotificationFactory;
import com.oasis.ui.component.Notification;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextArea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionValidator implements Validator {
    private final TextArea textArea;
    private StringProperty value = new SimpleStringProperty();
    private ChangeListener<String> valueChangeListener;
    private boolean valid = false;

    public DescriptionValidator(TextArea textArea) {
        this.textArea = textArea;
        value.bind(textArea.textProperty());

        Platform.runLater(() -> textArea.getStyleClass().remove("text-field-invalid"));

        valueChangeListener = (observable, oldValue, newValue) -> {
            valid = true;
            textArea.getStyleClass().remove("text-field-invalid");

            if (null != newValue) {
                Pattern pattern = Pattern.compile(".*\\S.*");
                Matcher matcher = pattern.matcher(newValue);

                if (!matcher.matches()) {
                    valid = false;
                    textArea.getStyleClass().add("text-field-invalid");
                }
            } else {
                valid = false;
                textArea.getStyleClass().add("text-field-invalid");
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
        valueChangeListener.changed(null, null, textArea.getText());
    }

    @Override
    public Notification getInvalidArgumentNotification() {
        return NotificationFactory.defaultInvalidArguementNotification("Invalid description",
                "The description \"" + textArea.getText() + "\" is not correct. " +
                        "Please enter a correct description");
    }
}
