package com.oasis.factory;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.oasis.services.NotificationServices;
import com.oasis.ui.component.Notification;
import com.oasis.validation.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ValidationFactory {
    private final ArrayList<Validator> validatorArrayList = new ArrayList<>();

    public ValidationFactory() {
    }

    public boolean isValid() {
        boolean valid = true;
        for (Validator validator : validatorArrayList) {
            validator.refreshState();
            if (!validator.isValid()) {
                valid = false;
                System.out.println("cat" + validator.getClass().getName());
            }
        }

        return valid;
    }

    public void generateErrorNotifications() {
        for (Validator validator : validatorArrayList) {
            if (!validator.isValid()) {
                Notification notification = validator.getInvalidArgumentNotification();
                NotificationServices.addNotification(notification);
            }
        }
    }

    public void addEmailValidator(TextField textField) {
        EmailValidator emailValidator = new EmailValidator(textField);
        validatorArrayList.add(emailValidator);
    }

    public void addNameValidator(TextField textField) {
        NameValidator nameValidator = new NameValidator(textField);
        validatorArrayList.add(nameValidator);
    }

    public void addEthnicityValidator(TextField textField) {
        EthnicityValidator ethnicityValidator = new EthnicityValidator(textField);
        validatorArrayList.add(ethnicityValidator);
    }

    public void addAddressValidator(TextField nameTextField, TextField relationshipTextField, TextField telephoneTextField,
                                    TextField addressTextField) {
        AddressValidator addressValidator = new AddressValidator(nameTextField, relationshipTextField, telephoneTextField,
                addressTextField);
        validatorArrayList.add(addressValidator);
    }

    public void addEmergencyContactValidator(TextField streetTextField, TextField townTextField, TextField provinceTextField,
                                             TextField postalCodeTextField) {
        EmergencyContactValidator emergencyContactValidator = new EmergencyContactValidator(streetTextField, townTextField,
                provinceTextField, postalCodeTextField);
        validatorArrayList.add(emergencyContactValidator);
    }

    public void addCurrencyValidator(TextField textField) {
        CurrencyValidator currencyValidator = new CurrencyValidator(textField);
        validatorArrayList.add(currencyValidator);
    }

    public void addTelephoneValidator(TextField textField) {
        TelephoneValidator telephoneValidator = new TelephoneValidator(textField);
        validatorArrayList.add(telephoneValidator);
    }

    public void addNICValidator(TextField textField) {
        NICValidator nicValidator = new NICValidator(textField);
        validatorArrayList.add(nicValidator);
    }

    public void addComboBoxValidator(ComboBox comboBox) {
        ComboBoxValidator comboBoxValidator = new ComboBoxValidator(comboBox);
        validatorArrayList.add(comboBoxValidator);
    }

    public void addDatePickerValidator(JFXDatePicker datePicker) {
        DateValidator dateValidator = new DateValidator(datePicker);
        validatorArrayList.add(dateValidator);
    }

    public void addTimePickerValidator(JFXTimePicker timePicker) {
        TimeValidator timeValidator = new TimeValidator(timePicker);
        validatorArrayList.add(timeValidator);
    }
}
