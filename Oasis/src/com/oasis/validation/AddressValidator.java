package com.oasis.validation;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator implements Validator{
    private final TextField streetTextField;
    private final TextField townTextField;
    private final TextField provinceTextField;
    private final TextField postalCodeTextField;

    private StringProperty streetValue = new SimpleStringProperty();
    private StringProperty townValue = new SimpleStringProperty();
    private StringProperty provinceValue = new SimpleStringProperty();
    private StringProperty postalCodeValue = new SimpleStringProperty();

    private ChangeListener<String> streetChangeListener;
    private ChangeListener<String> townChangeListener;
    private ChangeListener<String> provinceChangeListener;
    private ChangeListener<String> postalCodeListener;

    private boolean streetValid = false;
    private boolean townValid = false;
    private boolean provinceValid = false;
    private boolean postalCodeValid = false;

    public AddressValidator(TextField streetTextField, TextField townTextField, TextField provinceTextField,
                             TextField postalCodeTextField) {
        this.streetTextField = streetTextField;
        this.townTextField = townTextField;
        this.provinceTextField = provinceTextField;
        this.postalCodeTextField = postalCodeTextField;

        streetValue.bind(streetTextField.textProperty());
        townValue.bind(townTextField.textProperty());
        provinceValue.bind(provinceTextField.textProperty());
        postalCodeValue.bind(postalCodeTextField.textProperty());

        Platform.runLater(() -> {
            streetTextField.getStyleClass().remove( "text-field-invalid");
            townTextField.getStyleClass().remove("text-field-invalid");
            provinceTextField.getStyleClass().remove("text-field-invalid");
            postalCodeTextField.getStyleClass().remove("text-field-invalid");
        });

        streetChangeListener = (observable, oldValue, newValue) -> {
            if(null != newValue){
                Pattern pattern = Pattern.compile("\\S+");
                Matcher matcher = pattern.matcher(newValue);

                if (matcher.matches()){
                    streetValid = true;
                    streetTextField.getStyleClass().remove("text-field-invalid");
                }else{
                    streetValid = false;
                    streetTextField.getStyleClass().add("text-field-invalid");
                }
            }else {
                streetValid = false;
                streetTextField.getStyleClass().add("text-field-invalid");
            }
        };
        streetValue.addListener(streetChangeListener);

        townChangeListener = (observable, oldValue, newValue) -> {
            if(null != newValue){
                Pattern pattern = Pattern.compile("\\S+");
                Matcher matcher = pattern.matcher(newValue);

                if (matcher.matches()){
                    townValid = true;
                    townTextField.getStyleClass().remove("text-field-invalid");
                }else{
                    townValid = false;
                    townTextField.getStyleClass().add("text-field-invalid");
                }
            }else {
                townValid = false;
                townTextField.getStyleClass().add("text-field-invalid");
            }
        };
        townValue.addListener(townChangeListener);

        provinceChangeListener = (observable, oldValue, newValue) -> {
            if(null != newValue){
                Pattern pattern = Pattern.compile("\\S+");
                Matcher matcher = pattern.matcher(newValue);

                if (matcher.matches()){
                    provinceValid = true;
                    provinceTextField.getStyleClass().remove("text-field-invalid");
                }else{
                    provinceValid = false;
                    provinceTextField.getStyleClass().add("text-field-invalid");
                }
            }else{
                provinceValid = false;
                provinceTextField.getStyleClass().add("text-field-invalid");
            }
        };
        provinceValue.addListener(provinceChangeListener);

        postalCodeListener = (observable, oldValue, newValue) -> {
            if(null != newValue){
                Pattern pattern = Pattern.compile("\\d{5}");
                Matcher matcher = pattern.matcher(newValue);

                if (matcher.matches()){
                    postalCodeValid = true;
                    postalCodeTextField.getStyleClass().remove("text-field-invalid");
                }else {
                    postalCodeValid = false;
                    postalCodeTextField.getStyleClass().add("text-field-invalid");
                }
            }else{
                postalCodeValid = false;
                postalCodeTextField.getStyleClass().add("text-field-invalid");
            }
        };
        postalCodeValue.addListener(postalCodeListener);
    }

    @Override
    public boolean isValid() {
        return (streetValue.isEmpty().get() && townValue.isEmpty().get() && provinceValue.isEmpty().get() && postalCodeValue.isEmpty().get())
                || (streetValid && townValid && provinceValid && postalCodeValid);
    }

    @Override
    public void setStateForce() {
        streetChangeListener.changed(null, null, streetTextField.getText());
        townChangeListener.changed(null, null, townTextField.getText());
        provinceChangeListener.changed(null, null, provinceTextField.getText());
        postalCodeListener.changed(null, null, postalCodeTextField.getText());
    }
}
