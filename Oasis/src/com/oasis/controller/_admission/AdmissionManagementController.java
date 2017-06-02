package com.oasis.controller._admission;

import com.oasis.controller.Controller;
import com.oasis.controller._main.DashboardController;
import com.oasis.factory.UIFactory;
import com.oasis.model.Admission;
import com.oasis.model.Doctor;
import com.oasis.model.Patient;
import com.oasis.model.Physician;
import com.oasis.services.AdmissionServices;
import com.oasis.services.PatientServices;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import com.oasis.ui.component.AutoCompleteFXC;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdmissionManagementController implements Controller {
    @FXML
    private AnchorPane patientAnchorPane;
    @FXML
    private Button clearButton;

    @FXML
    private TableView<Admission> admissionTableView;
    @FXML
    private TableColumn<Admission, Integer> idTableColumn;
    @FXML
    private TableColumn<Admission, Physician> physicianTableColumn;
    @FXML
    private TableColumn<Admission, Doctor> admissionConsultantTableColumn;
    @FXML
    private TableColumn<Admission, Doctor> leadingConsultantTableColumn;
    @FXML
    private TableColumn<Admission, Date> admissionDateTableColumn;
    @FXML
    private TableColumn<Admission, Date> releaseDateTableColumn;

    @FXML
    private TextArea causeTextArea;

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;

    private AutoCompleteFXC<Patient> patientAutoCompleteFXC;

    private HashMap<Integer, Admission> tempAdmissionHashMap = new HashMap<>();
    private HashMap<Integer, Admission> editedAdmissionHashMap = new HashMap<>();
    private HashMap<Integer, Admission> deletedAdmissionHashMap = new HashMap<>();

    private ObjectProperty<Patient> currentPatientObjectProperty = new SimpleObjectProperty<>();
    private ChangeListener<Patient> currentPatientChangeListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                PatientServices.getPatientLike(UIName.ADMISSION_MANAGEMENT, searchParam), UIName.PATIENT_DETAILS_POPOVER);

        patientAnchorPane.getChildren().add(patientAutoCompleteFXC);

        admissionTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (admissionTableView.getSelectionModel().getSelectedItem() != null) {
                if (oldValue != null) {
                    causeTextArea.textProperty().unbindBidirectional(oldValue.causeProperty());

                    if (!oldValue.equals(tempAdmissionHashMap.get(oldValue.getId()))) {
                        editedAdmissionHashMap.put(oldValue.getId(), oldValue);
                    }
                }

                causeTextArea.textProperty().bindBidirectional(newValue.causeProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempAdmissionHashMap.clear();
        editedAdmissionHashMap.clear();
        deletedAdmissionHashMap.clear();

        currentPatientObjectProperty = new SimpleObjectProperty<>();
        patientAutoCompleteFXC.unBindList();
        patientAutoCompleteFXC.bindList(currentPatientObjectProperty);

        patientAutoCompleteFXC.getTextField().setText("");
        admissionTableView.setItems(null);
        causeTextArea.setText("");

        if (null != currentPatientChangeListener) {
            currentPatientObjectProperty.removeListener(currentPatientChangeListener);
        }
        currentPatientChangeListener = (observable, oldValue, newValue) -> {
            ArrayList<Admission> admissionArrayList = new ArrayList<>();
            for (Admission admission : AdmissionServices.getAdmissionArrayListByPatient(UIName.ADMISSION_MANAGEMENT, newValue)) {
                admissionArrayList.add(admission.clone());
                tempAdmissionHashMap.put(admission.getId(), admission.clone());
            }
            ObservableList<Admission> admissionObservableList = FXCollections.observableList(admissionArrayList);
            admissionTableView.setItems(admissionObservableList);
        };
        currentPatientObjectProperty.addListener(currentPatientChangeListener);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        physicianTableColumn.setCellValueFactory(param -> param.getValue().physicianObjectPropertyProperty());
        admissionConsultantTableColumn.setCellValueFactory(param -> param.getValue().admissionConsultantObjectPropertyProperty());
        leadingConsultantTableColumn.setCellValueFactory(param -> param.getValue().leadingConsultantObjectPropertyProperty());
        admissionDateTableColumn.setCellValueFactory(param -> param.getValue().admissionDateObjectPropertyProperty());
        releaseDateTableColumn.setCellValueFactory(param -> param.getValue().releaseDateObjectPropertyProperty());
    }

    public void editButtonOnAction(ActionEvent actionEvent) {
        Admission selectedAdmission = admissionTableView.getSelectionModel().getSelectedItem();
        if (selectedAdmission != null) {
            UI ui = UIFactory.getUI(UIName.NEW_EDIT_ADMISSION);
            Parent parent = ui.getParent();
            NewEditAdmissionController controller = (NewEditAdmissionController) ui.getController();
            controller.showAdmission(selectedAdmission);
            DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
            dashboardController.setWorkspace(parent);
        }
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Admission selectedAdmission = admissionTableView.getSelectionModel().getSelectedItem();
        if (selectedAdmission != null) {
            admissionTableView.getItems().remove(selectedAdmission);
            deletedAdmissionHashMap.put(selectedAdmission.getId(), selectedAdmission);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (admissionTableView.getSelectionModel().getSelectedItem() != null) {
            Admission admission = admissionTableView.getSelectionModel().getSelectedItem();

            if (!admission.equals(tempAdmissionHashMap.get(admission.getId()))) {
                editedAdmissionHashMap.put(admission.getId(), admission);
            }
        }

        AdmissionServices.updateAdmission(UIName.ADMISSION_MANAGEMENT, new ArrayList<>(editedAdmissionHashMap.values()));
        editedAdmissionHashMap.clear();

        AdmissionServices.deleteAdmission(UIName.ADMISSION_MANAGEMENT, new ArrayList<>(deletedAdmissionHashMap.values()));
        deletedAdmissionHashMap.clear();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EDIT_ADMISSION, true);
    }
}
