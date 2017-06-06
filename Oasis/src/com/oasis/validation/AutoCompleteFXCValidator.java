package com.oasis.validation;

import com.oasis.factory.NotificationFactory;
import com.oasis.ui.component.AutoCompleteFXC;
import com.oasis.ui.component.Notification;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

public class AutoCompleteFXCValidator<T> implements Validator {
    private final AutoCompleteFXC autoCompleteFXC;
    private ObjectProperty<T> valueProperty = new SimpleObjectProperty<>();
    private ChangeListener<T> valueChangeListener;
    private boolean valid = false;

    public AutoCompleteFXCValidator(AutoCompleteFXC<T> autoCompleteFXC) {
        this.autoCompleteFXC = autoCompleteFXC;
        valueProperty.bind(autoCompleteFXC.getListView().getSelectionModel().selectedItemProperty());

        Platform.runLater(() -> autoCompleteFXC.getTextField().getStyleClass().remove("text-field-invalid"));

        valueChangeListener = (observable, oldValue, newValue) -> {
            valid = true;
            autoCompleteFXC.getTextField().getStyleClass().remove("text-field-invalid");

            if (null != autoCompleteFXC.getObjectProperty()) {
                if (null == autoCompleteFXC.getTextField().getText()
                        || !autoCompleteFXC.objectPropertyProperty().get().toString().equals(autoCompleteFXC.getTextField().getText())) {
                    valid = false;
                    autoCompleteFXC.getTextField().getStyleClass().add("text-field-invalid");
                }
            } else {
                valid = false;
                autoCompleteFXC.getTextField().getStyleClass().add("text-field-invalid");
            }
        };
        valueProperty.addListener(valueChangeListener);
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void refreshState() {
        valueChangeListener.changed(null, null, (T) autoCompleteFXC.getObjectProperty());
    }

    @Override
    public Notification getInvalidArgumentNotification() {
        return NotificationFactory.defaultInvalidArguementNotification("Invalid value",
                "The search parameter \"" + autoCompleteFXC.getTextField().getText() + "\" is not correct. " +
                        "Please enter a correct search parameter and select a relevant value");
    }
}
