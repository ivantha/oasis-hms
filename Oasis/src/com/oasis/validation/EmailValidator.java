package com.oasis.validation;

import javafx.scene.control.TextField;

public class EmailValidator implements Validator {
    private final TextField textField;

    public EmailValidator(TextField textField) {
        this.textField = textField;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
