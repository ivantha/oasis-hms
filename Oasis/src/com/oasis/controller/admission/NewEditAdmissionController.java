package com.oasis.controller.admission;

import com.jfoenix.controls.JFXDatePicker;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.factory.ValidationFactory;
import com.oasis.model.Admission;
import com.oasis.model.Doctor;
import com.oasis.model.Patient;
import com.oasis.model.Physician;
import com.oasis.services.AdmissionServices;
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

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
    private Button todayButton;

    @FXML
    private Button okButton;

    private Admission tempAdmission;
    private boolean isEditingMode = false;

    private AutoCompleteFXC<Patient> patientAutoCompleteFXC;
    private AutoCompleteFXC<Physician> physicianAutoCompleteFXC;
    private AutoCompleteFXC<Doctor> admissionConsultantAutoCompleteFXC;
    private AutoCompleteFXC<Doctor> leadingConsultantAutoCompleteFXC;

    private ValidationFactory validationFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                PatientServices.getPatientLike(searchParam), UIName.PATIENT_DETAILS_POPOVER);
        physicianAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                PhysicianServices.getPhysicianLike(searchParam), UIName.PHYSICIAN_DETAILS_POPOVER);
        admissionConsultantAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                EmployeeServices.getDoctorLike(searchParam), UIName.EMPLOYEE_DETAILS_POPOVER);
        leadingConsultantAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                EmployeeServices.getDoctorLike(searchParam), UIName.EMPLOYEE_DETAILS_POPOVER);

        patientAnchorPane.getChildren().add(patientAutoCompleteFXC);
        physicianAnchorPane.getChildren().add(physicianAutoCompleteFXC);
        admissionConsultantAnchorPane.getChildren().add(admissionConsultantAutoCompleteFXC);
        leadingConsultantAnchorPane.getChildren().add(leadingConsultantAutoCompleteFXC);

        patientAnchorPane.toBack();
        physicianAnchorPane.toBack();
        admissionConsultantAnchorPane.toBack();
        leadingConsultantAnchorPane.toBack();
        causeTextArea.toBack();
    }

    @Override
    public void refreshView() {
        try {
            unBindFields(tempAdmission);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempAdmission = new Admission();
        tempAdmission.setId(0);

        isEditingMode = false;
        bindFields(tempAdmission);

        patientAutoCompleteFXC.updateText();
        physicianAutoCompleteFXC.updateText();
        admissionConsultantAutoCompleteFXC.updateText();
        leadingConsultantAutoCompleteFXC.updateText();

        addValidators();
    }

    public void showAdmission(Admission admission) {
        try {
            unBindFields(tempAdmission);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempAdmission = admission;

        isEditingMode = true;
        bindFields(admission);

        patientAutoCompleteFXC.updateText();
        physicianAutoCompleteFXC.updateText();
        admissionConsultantAutoCompleteFXC.updateText();
        leadingConsultantAutoCompleteFXC.updateText();

        addValidators();
    }

    public void unBindFields(Admission admission) {
        patientAutoCompleteFXC.unBindList();
        physicianAutoCompleteFXC.unBindList();
        admissionConsultantAutoCompleteFXC.unBindList();
        leadingConsultantAutoCompleteFXC.unBindList();

        causeTextArea.textProperty().unbindBidirectional(admission.causeProperty());
    }

    public void bindFields(Admission admission) {
        patientAutoCompleteFXC.bindList(admission.patientObjectPropertyProperty());
        physicianAutoCompleteFXC.bindList(admission.physicianObjectPropertyProperty());
        admissionConsultantAutoCompleteFXC.bindList(admission.admissionConsultantObjectPropertyProperty());
        leadingConsultantAutoCompleteFXC.bindList(admission.leadingConsultantObjectPropertyProperty());

        causeTextArea.textProperty().bindBidirectional(admission.causeProperty());
    }

    public void todayButtonOnAction(ActionEvent actionEvent) {
        admissionDateDatePicker.setValue(LocalDate.now());
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        if (validationFactory.isValid()) {
            tempAdmission.setAdmissionDateObjectProperty(Date.valueOf(admissionDateDatePicker.getValue()));

            ArrayList<Admission> admissionArrayList = new ArrayList<>();
            admissionArrayList.add(tempAdmission);
            if (!isEditingMode) {
                AdmissionServices.newAdmission(admissionArrayList);
            } else {
                AdmissionServices.updateAdmission(admissionArrayList);
            }
            UIFactory.launchUI(UIName.ADMISSION_MANAGEMENT, true);
        } else {
            validationFactory.generateErrorNotifications();
        }
    }

    private void addValidators() {
        validationFactory = new ValidationFactory();
        validationFactory.addAutoCompleteFXCValidator(patientAutoCompleteFXC);
        validationFactory.addAutoCompleteFXCValidator(physicianAutoCompleteFXC);
        validationFactory.addAutoCompleteFXCValidator(admissionConsultantAutoCompleteFXC);
        validationFactory.addAutoCompleteFXCValidator(leadingConsultantAutoCompleteFXC);
        validationFactory.addDescriptionValidator(causeTextArea);
        validationFactory.addDatePickerValidator(admissionDateDatePicker);
    }
}
