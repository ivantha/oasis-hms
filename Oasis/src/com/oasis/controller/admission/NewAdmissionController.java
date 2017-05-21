package com.oasis.controller.admission;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.oasis.controller.Controller;
import com.oasis.model.Patient;
import com.oasis.ui.component.AutoCompleteFXC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewAdmissionController implements Controller{
    @FXML
    private AnchorPane patientAnchorPane;
    @FXML
    private CustomTextField physicianCFXTextField;
    @FXML
    private CustomTextField admissionConsultantCFXTextField;
    @FXML
    private CustomTextField leadingConsultantCFXTextField;
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
        patientAnchorPane.getChildren().add(new AutoCompleteFXC<Patient>(250, 25, 150));
    }

    public void todayButtonOnAction(ActionEvent actionEvent) {

    }

    public void nowButtonOnAction(ActionEvent actionEvent) {

    }

    public void okButtonOnAction(ActionEvent actionEvent) {

    }
}
