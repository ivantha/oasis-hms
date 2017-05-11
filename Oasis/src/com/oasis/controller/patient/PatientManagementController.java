package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PatientManagementController implements Controller{
    @FXML
    private TableView patientTableView;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn firstNameTableColumn;
    @FXML
    private TableColumn middleNameTableColumn;
    @FXML
    private TableColumn lastNameTableColumn;
    @FXML
    private TableColumn nicTableColumn;
    @FXML
    private TableColumn genderTableColumn;
    @FXML
    private TableColumn dobTableColumn;
    @FXML
    private TableColumn addedDateTableColumn;

    @FXML
    private TextField streetTextField;
    @FXML
    private TextField townTextField;
    @FXML
    private TextField provinceTextField;
    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private ComboBox ethnicityComboBox;
    @FXML
    private ComboBox bloodGroupComboBox;

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;

    private HashMap<Integer, Patient> tempPatientHashMap = new HashMap<>();
    private HashMap<Integer, Patient> editedPatientHashMap = new HashMap<>();
    private HashMap<Integer, Patient> deletedPatientHashMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    public void editButtonOnAction(ActionEvent actionEvent) {

    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {

    }

    public void newButtonOnAction(ActionEvent actionEvent) {

    }
}
