package com.oasis.controller.patient;

import com.jfoenix.controls.JFXDatePicker;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
import com.oasis.services.BloodGroupServices;
import com.oasis.services.EthnicityServices;
import com.oasis.services.GenderServices;
import com.oasis.services.PatientServices;
import com.oasis.ui.UIName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewEditPatientController implements Controller{
    @FXML
    private TextField nicTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox genderComboBox;
    @FXML
    private JFXDatePicker dobDatePicker;
    @FXML
    private ComboBox<BloodGroup> bloodGroupComboBox;
    @FXML
    private ComboBox<Ethnicity> ethnicityComboBox;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField streetTextField;
    @FXML
    private TextField townTextField;
    @FXML
    private TextField provinceTextField;
    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField ecNameTextField;
    @FXML
    private TextField ecRelationshipTextField;
    @FXML
    private TextField ecTelephoneTextField;
    @FXML
    private TextField ecAddressTextField;

    @FXML
    private Button okButton;

    private Patient tempPatient;
    private boolean isEditingMode = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        try {
            unBindFields(tempPatient);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempPatient = new Patient();
        tempPatient.setId(0);

        isEditingMode = false;
        bindFields(tempPatient);
    }

    public void showPatient(Patient patient) {
        try {
            unBindFields(tempPatient);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempPatient = patient;

        isEditingMode = true;
        bindFields(tempPatient);
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientArrayList.add(tempPatient);
        if (!isEditingMode) {
            PatientServices.newPatient(patientArrayList);
        } else {
            PatientServices.updatePatient(patientArrayList);
        }
        UIFactory.launchUI(UIName.PATIENT_MANAGEMENT, true);
    }

    private void unBindFields(Patient patient) {
        nicTextField.textProperty().unbindBidirectional(patient.nicProperty());
        firstNameTextField.textProperty().unbindBidirectional(patient.firstNameProperty());
        middleNameTextField.textProperty().unbindBidirectional(patient.middleNameProperty());
        lastNameTextField.textProperty().unbindBidirectional(patient.lastNameProperty());
        genderComboBox.valueProperty().unbindBidirectional(patient.genderProperty());
        dobDatePicker.valueProperty().unbindBidirectional(patient.dobProperty());
        bloodGroupComboBox.valueProperty().unbindBidirectional(patient.bloodGroupObjectPropertyProperty());
        ethnicityComboBox.valueProperty().unbindBidirectional(patient.ethnicityObjectPropertyProperty());
        descriptionTextArea.textProperty().unbindBidirectional(patient.descriptionProperty());

        telephoneTextField.textProperty().unbindBidirectional(patient.getTelephoneArrayList().get(0).telephoneProperty());
        emailTextField.textProperty().unbindBidirectional(patient.getEmailArrayList().get(0).emailProperty());

        streetTextField.textProperty().unbindBidirectional(patient.getAddressArrayList().get(0).streetProperty());
        townTextField.textProperty().unbindBidirectional(patient.getAddressArrayList().get(0).townProperty());
        provinceTextField.textProperty().unbindBidirectional(patient.getAddressArrayList().get(0).provinceProperty());
        postalCodeTextField.textProperty().unbindBidirectional(patient.getAddressArrayList().get(0).postalCodeProperty());

        ecNameTextField.textProperty().unbindBidirectional(patient.getEmergencyContactArrayList().get(0).nameProperty());
        ecRelationshipTextField.textProperty().unbindBidirectional(patient.getEmergencyContactArrayList().get(0).relationshipProperty());
        ecTelephoneTextField.textProperty().unbindBidirectional(patient.getEmergencyContactArrayList().get(0).telephoneProperty());
        ecAddressTextField.textProperty().unbindBidirectional(patient.getEmergencyContactArrayList().get(0).addressProperty());
    }

    private void bindFields(Patient patient) {
        nicTextField.textProperty().bindBidirectional(patient.nicProperty());
        firstNameTextField.textProperty().bindBidirectional(patient.firstNameProperty());
        middleNameTextField.textProperty().bindBidirectional(patient.middleNameProperty());
        lastNameTextField.textProperty().bindBidirectional(patient.lastNameProperty());
        genderComboBox.valueProperty().bindBidirectional(patient.genderProperty());
        dobDatePicker.valueProperty().bindBidirectional(patient.dobProperty());
        bloodGroupComboBox.valueProperty().bindBidirectional(patient.bloodGroupObjectPropertyProperty());
        ethnicityComboBox.valueProperty().bindBidirectional(patient.ethnicityObjectPropertyProperty());
        descriptionTextArea.textProperty().bindBidirectional(patient.descriptionProperty());

        if(tempPatient.getTelephoneArrayList().isEmpty()) {
            tempPatient.getTelephoneArrayList().add(new Telephone());
        }
        if(tempPatient.getEmailArrayList().isEmpty()) {
            tempPatient.getEmailArrayList().add(new Email());
        }
        if(tempPatient.getAddressArrayList().isEmpty()) {
            tempPatient.getAddressArrayList().add(new Address());
        }
        if(tempPatient.getEmergencyContactArrayList().isEmpty()) {
            tempPatient.getEmergencyContactArrayList().add(new EmergencyContact());
        }

        telephoneTextField.textProperty().bindBidirectional(patient.getTelephoneArrayList().get(0).telephoneProperty());
        emailTextField.textProperty().bindBidirectional(patient.getEmailArrayList().get(0).emailProperty());

        streetTextField.textProperty().bindBidirectional(patient.getAddressArrayList().get(0).streetProperty());
        townTextField.textProperty().bindBidirectional(patient.getAddressArrayList().get(0).townProperty());
        provinceTextField.textProperty().bindBidirectional(patient.getAddressArrayList().get(0).provinceProperty());
        postalCodeTextField.textProperty().bindBidirectional(patient.getAddressArrayList().get(0).postalCodeProperty());

        ecNameTextField.textProperty().bindBidirectional(patient.getEmergencyContactArrayList().get(0).nameProperty());
        ecRelationshipTextField.textProperty().bindBidirectional(patient.getEmergencyContactArrayList().get(0).relationshipProperty());
        ecTelephoneTextField.textProperty().bindBidirectional(patient.getEmergencyContactArrayList().get(0).telephoneProperty());
        ecAddressTextField.textProperty().bindBidirectional(patient.getEmergencyContactArrayList().get(0).addressProperty());

        ObservableList<Gender> genderObservableList = FXCollections
                .observableList(GenderServices.getGenderArrayList());
        genderComboBox.setItems(genderObservableList);

        ObservableList<BloodGroup> bloodGroupObservableList = FXCollections
                .observableList(BloodGroupServices.getBloodGroupArrayList());
        bloodGroupComboBox.setItems(bloodGroupObservableList);

        ObservableList<Ethnicity> ethnicityObservableList = FXCollections
                .observableList(EthnicityServices.getEthnicityArrayList());
        ethnicityComboBox.setItems(ethnicityObservableList);
    }
}
