package com.oasis.factory;

import com.oasis.validation.EmailValidator;
import com.oasis.validation.Validator;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ValidationFactory {
    private final ArrayList<Validator> validatorArrayList = new ArrayList<>();

    public ValidationFactory() {
    }

    public boolean isValid() {
        for (Validator validator : validatorArrayList) {
            if (!validator.isValid()) {
                return false;
            }
        }

        return true;
    }

    public void addEmailValidator(TextField textField) {
        EmailValidator emailValidator = new EmailValidator(textField);
        validatorArrayList.add(emailValidator);
    }
}
