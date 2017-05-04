package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.listener.DynamicButtonDragDetectedEventHandler;
import com.oasis.listener.DynamicButtonDragDoneEventHandler;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientSideBarController implements Controller{
    @FXML
    private Button newPatientButton;
    @FXML
    private Button patientManagementButton;
    @FXML
    private Button newTreatmentButton;
    @FXML
    private Button treatmentManagementButton;
    @FXML
    private Button raceButton;
    @FXML
    private Button ethnicityButton;
    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newPatientButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_PATIENT));
        newPatientButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        patientManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.PATIENT_MANAGEMENT));
        patientManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        newTreatmentButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_TREATMENT));
        newTreatmentButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        treatmentManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.TREATMENT_MANAGEMENT));
        treatmentManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        raceButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.RACE_MANAGEMENT));
        raceButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        ethnicityButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.ETHNICITY_MANAGEMENT));
        ethnicityButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newPatientButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_PATIENT, true);
    }

    @FXML
    public void patientManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.PATIENT_MANAGEMENT, true);
    }

    @FXML
    public void newTreatmentButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_TREATMENT, true);
    }

    @FXML
    public void treatmentManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.TREATMENT_MANAGEMENT, true);
    }

    @FXML
    public void raceButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.RACE_MANAGEMENT, true);
    }

    @FXML
    public void ethnicityButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.ETHNICITY_MANAGEMENT, true);
    }
}
