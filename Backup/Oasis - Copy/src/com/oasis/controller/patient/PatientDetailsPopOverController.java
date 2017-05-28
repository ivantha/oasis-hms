package com.oasis.controller.patient;

import com.oasis.controller.PopOverController;
import com.oasis.model.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientDetailsPopOverController implements PopOverController<Patient>{
    @FXML
    private Label idLabel;
    @FXML
    private Label nicLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label middleNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label dobLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    public void refreshView(Patient patient) {
        idLabel.setText(String.valueOf(patient.getId()));
        nicLabel.setText(patient.getNic());
        firstNameLabel.setText(patient.getFirstName());
        middleNameLabel.setText(patient.getMiddleName());
        lastNameLabel.setText(patient.getLastName());
        genderLabel.setText(patient.getGender().getName());
        dobLabel.setText(patient.getDob().toString());
    }
}
