package com.oasis.controller._A_to_validate;

import com.oasis.controller.Controller;
import com.oasis.controller.admission.NewEditTreatmentController;
import com.oasis.controller.main.DashboardController;
import com.oasis.factory.UIFactory;
import com.oasis.model.Admission;
import com.oasis.model.Patient;
import com.oasis.model.Treatment;
import com.oasis.services.AdmissionServices;
import com.oasis.services.PatientServices;
import com.oasis.services.TreatmentServices;
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

public class TreatmentManagementController implements Controller {
    @FXML
    private AnchorPane patientAnchorPane;

    @FXML
    private TableView<Admission> admissionTableView;
    @FXML
    private TableColumn<Admission, Integer> admissioidTableColumn;
    @FXML
    private TableColumn<Admission, Date> admissionDateTableColumn;
    @FXML
    private TableColumn<Admission, Date> releaseDateTableColumn;

    @FXML
    private TableView<Treatment> treatmentTableView;
    @FXML
    private TableColumn<Treatment, Integer> treatmentIdTableColumn;
    @FXML
    private TableColumn<Treatment, String> descriptionTableColumn;
    @FXML
    private TableColumn<Treatment, Date> givenDateTableColumn;

    @FXML
    private TextArea causeTextArea;
    @FXML
    private TextArea resultTextArea;

    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button newButton;

    private AutoCompleteFXC<Patient> patientAutoCompleteFXC;

    private ObjectProperty<Patient> currentPatientObjectProperty = new SimpleObjectProperty<>();
    private ChangeListener<Patient> currentPatientChangeListener;

    private ObjectProperty<Admission> currentAdmissionObjectProperty = new SimpleObjectProperty<>();
    private ChangeListener<Admission> currentAdmissionChangeListener;

    private HashMap<Integer, Treatment> tempTreatmentHashMap = new HashMap<>();
    private HashMap<Integer, Treatment> editedTreatmentHashMap = new HashMap<>();
    private HashMap<Integer, Treatment> deletedTreatmentHashMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                PatientServices.getPatientLike(searchParam), UIName.PATIENT_DETAILS_POPOVER);

        patientAnchorPane.getChildren().add(patientAutoCompleteFXC);

        admissionTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (admissionTableView.getSelectionModel().getSelectedItem() != null) {
                if (oldValue != null) {
                    causeTextArea.textProperty().unbindBidirectional(oldValue.causeProperty());
                }

                currentAdmissionObjectProperty.set(newValue);
                causeTextArea.textProperty().bindBidirectional(newValue.causeProperty());
            }
        });

        treatmentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (treatmentTableView.getSelectionModel().getSelectedItem() != null) {
                if (oldValue != null) {
                    resultTextArea.textProperty().unbindBidirectional(oldValue.resultProperty());

                    if (!oldValue.equals(tempTreatmentHashMap.get(oldValue.getId()))) {
                        editedTreatmentHashMap.put(oldValue.getId(), oldValue);
                    }
                }

                resultTextArea.textProperty().bindBidirectional(newValue.resultProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        currentPatientObjectProperty = new SimpleObjectProperty<>();
        patientAutoCompleteFXC.unBindList();
        patientAutoCompleteFXC.bindList(currentPatientObjectProperty);

        patientAutoCompleteFXC.getTextField().setText("");
        admissionTableView.setItems(null);
        causeTextArea.setText(null);
        resultTextArea.setText(null);

        treatmentTableView.setItems(null);

        if (null != currentPatientChangeListener) {
            currentPatientObjectProperty.removeListener(currentPatientChangeListener);
        }
        currentPatientChangeListener = (observable, oldValue, newValue) -> {
            updateAdmissionTable(newValue);
        };
        currentPatientObjectProperty.addListener(currentPatientChangeListener);

        if (null != currentAdmissionChangeListener) {
            currentAdmissionObjectProperty.removeListener(currentAdmissionChangeListener);
        }
        currentAdmissionChangeListener = (observable, oldValue, newValue) -> {
            updateTreatmentTable(newValue);
        };
        currentAdmissionObjectProperty.addListener(currentAdmissionChangeListener);

        admissioidTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        admissionDateTableColumn.setCellValueFactory(param -> param.getValue().admissionDateObjectPropertyProperty());
        releaseDateTableColumn.setCellValueFactory(param -> param.getValue().releaseDateObjectPropertyProperty());

        treatmentIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTableColumn.setCellValueFactory(param -> param.getValue().descriptionProperty());
        givenDateTableColumn.setCellValueFactory(param -> param.getValue().givenDateObjectPropertyProperty());
    }

    public void editButtonOnAction(ActionEvent actionEvent) {
        Treatment selectedTreatment = treatmentTableView.getSelectionModel().getSelectedItem();
        if (selectedTreatment != null) {
            UI ui = UIFactory.getUI(UIName.NEW_EDIT_TREATMENT);
            Parent parent = ui.getParent();
            NewEditTreatmentController controller = (NewEditTreatmentController) ui.getController();
            controller.showTreatment(selectedTreatment);
            DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
            dashboardController.setWorkspace(parent);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (treatmentTableView.getSelectionModel().getSelectedItem() != null) {
            Treatment treatment = treatmentTableView.getSelectionModel().getSelectedItem();

            if (!treatment.equals(tempTreatmentHashMap.get(treatment.getId()))) {
                editedTreatmentHashMap.put(treatment.getId(), treatment);
            }
        }

        TreatmentServices.updateTreatment(new ArrayList<>(editedTreatmentHashMap.values()));
        editedTreatmentHashMap.clear();

        TreatmentServices.deleteTreatment(new ArrayList<>(deletedTreatmentHashMap.values()));
        deletedTreatmentHashMap.clear();

        refreshView();
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Treatment selectedTreatment = treatmentTableView.getSelectionModel().getSelectedItem();
        if (selectedTreatment != null) {
            treatmentTableView.getItems().remove(selectedTreatment);
            deletedTreatmentHashMap.put(selectedTreatment.getId(), selectedTreatment);
        }
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        Admission selectedAdmission = admissionTableView.getSelectionModel().getSelectedItem();
        if (selectedAdmission != null) {
            UI ui = UIFactory.getUI(UIName.NEW_EDIT_TREATMENT);
            Parent parent = ui.getParent();
            NewEditTreatmentController controller = (NewEditTreatmentController) ui.getController();
            controller.refreshView(selectedAdmission);
            DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
            dashboardController.setWorkspace(parent);
        }
    }

    private void updateAdmissionTable(Patient patient) {
        ArrayList<Admission> admissionArrayList = new ArrayList<>();
        for (Admission admission : AdmissionServices.getAdmissionArrayListByPatient(patient)) {
            admissionArrayList.add(admission.clone());
        }
        ObservableList<Admission> admissionObservableList = FXCollections.observableList(admissionArrayList);
        admissionTableView.setItems(admissionObservableList);
    }

    private void updateTreatmentTable(Admission admission) {
        ArrayList<Treatment> treatmentArrayList = new ArrayList<>();
        for (Treatment treatment : TreatmentServices.getTreatmentArrayListByAdmission(admission)) {
            treatmentArrayList.add(treatment.clone());
        }
        ObservableList<Treatment> treatmentObservableList = FXCollections.observableList(treatmentArrayList);
        treatmentTableView.setItems(treatmentObservableList);
    }
}
