package com.oasis.controller.admission;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Admission;
import com.oasis.model.Patient;
import com.oasis.model.Treatment;
import com.oasis.services.AdmissionServices;
import com.oasis.services.PatientServices;
import com.oasis.services.TreatmentServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.AutoCompleteFXC;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TreatmentManagementController implements Controller{
    @FXML
    private AnchorPane patientAnchorPane;

    @FXML
    private TableView<Admission> admissionTableView;
    @FXML
    private TableColumn<Admission, Integer> admissionIdTableColumn;
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
    private Button deleteButton;
    @FXML
    private Button newButton;

    private AutoCompleteFXC<Patient> patientAutoCompleteFXC;

    private ObjectProperty<Patient> currentPatientObjectProperty = new SimpleObjectProperty<>();
    private ChangeListener<Patient> currentPatientChangeListener;

    private ObjectProperty<Admission> currentAdmissionObjectProperty = new SimpleObjectProperty<>();
    private ChangeListener<Admission> currentAdmissionChangeListener;

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
        causeTextArea.setText("");

        if(null != currentPatientChangeListener){
            currentPatientObjectProperty.removeListener(currentPatientChangeListener);
        }
        currentPatientChangeListener = (observable, oldValue, newValue) -> {
            ArrayList<Admission> admissionArrayList = new ArrayList<>();
            for (Admission admission : AdmissionServices.getAdmissionArrayListByPatient(newValue)) {
                admissionArrayList.add(admission.clone());
            }
            ObservableList<Admission> admissionObservableList = FXCollections.observableList(admissionArrayList);
            admissionTableView.setItems(admissionObservableList);
        };
        currentPatientObjectProperty.addListener(currentPatientChangeListener);

        if(null != currentAdmissionChangeListener){
            currentAdmissionObjectProperty.removeListener(currentAdmissionChangeListener);
        }
        currentAdmissionChangeListener = (observable, oldValue, newValue) -> {
            updateTreatmentTable(newValue);
        };
        currentAdmissionObjectProperty.addListener(currentAdmissionChangeListener);

        admissionIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        admissionDateTableColumn.setCellValueFactory(param -> param.getValue().admissionDateObjectPropertyProperty());
        releaseDateTableColumn.setCellValueFactory(param -> param.getValue().releaseDateObjectPropertyProperty());

        treatmentIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTableColumn.setCellValueFactory(param -> param.getValue().descriptionProperty());
        givenDateTableColumn.setCellValueFactory(param -> param.getValue().giveDateProperty());
    }

    public void editButtonOnAction(ActionEvent actionEvent) {

    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {

    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EDIT_TREATMENT, true);
    }

    private void updateTreatmentTable(Admission admission){
        ArrayList<Treatment> treatmentArrayList = new ArrayList<>();
        for (Treatment treatment : TreatmentServices.getTreatmentArrayListByAdmission(admission)) {
            treatmentArrayList.add(treatment.clone());
        }
        ObservableList<Treatment> treatmentObservableList = FXCollections.observableList(treatmentArrayList);
        treatmentTableView.setItems(treatmentObservableList);
    }
}
