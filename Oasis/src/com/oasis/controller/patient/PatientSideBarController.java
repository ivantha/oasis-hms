package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.listener.DynamicButtonDragDetectedEventHandler;
import com.oasis.listener.DynamicButtonDragDoneEventHandler;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientSideBarController implements Controller {
    @FXML
    private Button newPatientButton;
    @FXML
    private Button patientManagementButton;
    @FXML
    private Button ethnicityButton;

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newPatientButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_EDIT_PATIENT));
        newPatientButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        patientManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.PATIENT_MANAGEMENT));
        patientManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        ethnicityButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.ETHNICITY_MANAGEMENT));
        ethnicityButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newPatientButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EDIT_PATIENT, true);
    }

    @FXML
    public void patientManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.PATIENT_MANAGEMENT, true);
    }

    @FXML
    public void ethnicityButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.ETHNICITY_MANAGEMENT, true);
    }
}
