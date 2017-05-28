package com.oasis.controller.admission;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.oasis.adapter.SearchAdapter;
import com.oasis.controller.Controller;
import com.oasis.model.Patient;
import com.oasis.services.EmployeeServices;
import com.oasis.services.PatientServices;
import com.oasis.services.PhysicianServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.AutoCompleteFXC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewEditAdmissionController implements Controller {
    @FXML
    private AnchorPane patientAnchorPane;
    @FXML
    private AnchorPane physicianAnchorPane;
    @FXML
    private AnchorPane admissionConsultantAnchorPane;
    @FXML
    private AnchorPane leadingConsultantAnchorPane;
    @FXML
    private TextArea causeTextArea;

    @FXML
    private JFXDatePicker admissionDateDatePicker;
    @FXML
    private JFXTimePicker admissionTimeTimePicker;
    @FXML
    private Button todayButton;
    @FXML
    private Button nowButton;

    @FXML
    private Button okButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        patientAnchorPane.getChildren().add(new AutoCompleteFXC<>(searchParam -> PatientServices.getPatientLike(searchParam), UIName.PATIENT_DETAILS_POPOVER));
        physicianAnchorPane.getChildren().add(new AutoCompleteFXC<>(searchParam -> PhysicianServices.getPhysicianLike(searchParam), UIName.PHYSICIAN_DETAILS_POPOVER));
        admissionConsultantAnchorPane.getChildren().add(new AutoCompleteFXC<>(searchParam -> EmployeeServices.getEmployeeLike(searchParam), UIName.EMPLOYEE_DETAILS_POPOVER));
        leadingConsultantAnchorPane.getChildren().add(new AutoCompleteFXC<>(searchParam -> EmployeeServices.getEmployeeLike(searchParam), UIName.EMPLOYEE_DETAILS_POPOVER));

        patientAnchorPane.toBack();
        physicianAnchorPane.toBack();
        admissionConsultantAnchorPane.toBack();
        leadingConsultantAnchorPane.toBack();

        causeTextArea.toBack();
    }

    public void todayButtonOnAction(ActionEvent actionEvent) {
        admissionDateDatePicker.setValue(LocalDate.now());
    }

    public void nowButtonOnAction(ActionEvent actionEvent) {
        admissionTimeTimePicker.setValue(LocalTime.now());
    }

    public void okButtonOnAction(ActionEvent actionEvent) {

    }
}
