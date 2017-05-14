package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.controller.main.DashboardController;
import com.oasis.factory.UIFactory;
import com.oasis.model.BloodGroup;
import com.oasis.model.Ethnicity;
import com.oasis.model.Gender;
import com.oasis.model.Patient;
import com.oasis.services.PatientServices;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PatientManagementController implements Controller{
    @FXML
    private TableView<Patient> patientTableView;
    @FXML
    private TableColumn<Patient, Integer> idTableColumn;
    @FXML
    private TableColumn<Patient, String> firstNameTableColumn;
    @FXML
    private TableColumn<Patient, String> middleNameTableColumn;
    @FXML
    private TableColumn<Patient, String> lastNameTableColumn;
    @FXML
    private TableColumn<Patient, String> nicTableColumn;
    @FXML
    private TableColumn<Patient, Gender> genderTableColumn;
    @FXML
    private TableColumn<Patient, LocalDate> dobTableColumn;
    @FXML
    private TableColumn<Patient, Date> addedDateTableColumn;

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
    private ComboBox<Ethnicity> ethnicityComboBox;
    @FXML
    private ComboBox<BloodGroup> bloodGroupComboBox;

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
        patientTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (patientTableView.getSelectionModel().getSelectedItem() != null) {
                if (oldValue != null) {
                    streetTextField.textProperty().unbindBidirectional(oldValue.getAddressArrayList().get(0).streetProperty());
                    townTextField.textProperty().unbindBidirectional(oldValue.getAddressArrayList().get(0).townProperty());
                    provinceTextField.textProperty().unbindBidirectional(oldValue.getAddressArrayList().get(0).provinceProperty());
                    postalCodeTextField.textProperty().unbindBidirectional(oldValue.getAddressArrayList().get(0).postalCodeProperty());

                    telephoneTextField.textProperty().unbindBidirectional(oldValue.getTelephoneArrayList().get(0).telephoneProperty());
                    emailTextField.textProperty().unbindBidirectional(oldValue.getEmailArrayList().get(0).emailProperty());

                    ethnicityComboBox.valueProperty().unbindBidirectional(oldValue.ethnicityObjectPropertyProperty());
                    bloodGroupComboBox.valueProperty().unbindBidirectional(oldValue.bloodGroupObjectPropertyProperty());

                    if(!oldValue.equals(tempPatientHashMap.get(oldValue.getId()))){
                        editedPatientHashMap.put(oldValue.getId(), oldValue);
                    }
                }

                streetTextField.textProperty().bindBidirectional(newValue.getAddressArrayList().get(0).streetProperty());
                townTextField.textProperty().bindBidirectional(newValue.getAddressArrayList().get(0).townProperty());
                provinceTextField.textProperty().bindBidirectional(newValue.getAddressArrayList().get(0).provinceProperty());
                postalCodeTextField.textProperty().bindBidirectional(newValue.getAddressArrayList().get(0).postalCodeProperty());

                telephoneTextField.textProperty().bindBidirectional(newValue.getTelephoneArrayList().get(0).telephoneProperty());
                emailTextField.textProperty().bindBidirectional(newValue.getEmailArrayList().get(0).emailProperty());

                ethnicityComboBox.valueProperty().bindBidirectional(newValue.ethnicityObjectPropertyProperty());
                bloodGroupComboBox.valueProperty().bindBidirectional(newValue.bloodGroupObjectPropertyProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempPatientHashMap.clear();
        editedPatientHashMap.clear();
        deletedPatientHashMap.clear();

        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : PatientServices.getPatientArrayList()) {
            patientArrayList.add(patient.clone());
            tempPatientHashMap.put(patient.getId(), patient.clone());
        }
        ObservableList<Patient> patientObservableList = FXCollections.observableList(patientArrayList);
        patientTableView.setItems(patientObservableList);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameTableColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());
        middleNameTableColumn.setCellValueFactory(param -> param.getValue().middleNameProperty());
        lastNameTableColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());
        nicTableColumn.setCellValueFactory(param -> param.getValue().nicProperty());
        genderTableColumn.setCellValueFactory(param -> param.getValue().genderProperty());
        dobTableColumn.setCellValueFactory(param -> param.getValue().dobProperty());
        addedDateTableColumn.setCellValueFactory(param -> param.getValue().addedDateObjectPropertyProperty());
        addedDateTableColumn.setCellFactory(new Callback<TableColumn<Patient, Date>, TableCell<Patient, Date>>() {
            @Override
            public TableCell<Patient, Date> call(TableColumn<Patient, Date> param) {
                return new TableCell<Patient, Date>(){
                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(item));
                        }
                    }
                };
            }
        });
    }

    public void editButtonOnAction(ActionEvent actionEvent) {
        Patient selectedPatient = patientTableView.getSelectionModel().getSelectedItem();
        if(selectedPatient != null) {
            UI ui = UIFactory.getUI(UIName.NEW_EDIT_PATIENT);
            Parent parent = ui.getParent();
            NewEditPatientController controller = (NewEditPatientController) ui.getController();
            controller.showPatient(selectedPatient);
            DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
            dashboardController.setWorkspace(parent);
        }
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Patient selectedPatient = patientTableView.getSelectionModel().getSelectedItem();
        if(selectedPatient != null){
            patientTableView.getItems().remove(selectedPatient);
            deletedPatientHashMap.put(selectedPatient.getId(), selectedPatient);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (patientTableView.getSelectionModel().getSelectedItem() != null) {
            Patient patient = patientTableView.getSelectionModel().getSelectedItem();

            if(!patient.equals(tempPatientHashMap.get(patient.getId()))){
                editedPatientHashMap.put(patient.getId(), patient);
            }
        }

        PatientServices.updatePatient(new ArrayList<>(editedPatientHashMap.values()));
        editedPatientHashMap.clear();

        PatientServices.deletePatient(new ArrayList<>(deletedPatientHashMap.values()));
        deletedPatientHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EDIT_PATIENT, true);
    }
}
