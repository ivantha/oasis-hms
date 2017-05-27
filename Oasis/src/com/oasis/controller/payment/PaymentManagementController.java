package com.oasis.controller.payment;

import com.oasis.controller.Controller;
import com.oasis.model.*;
import com.oasis.services.AdmissionServices;
import com.oasis.services.ChargeServices;
import com.oasis.services.PatientServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.AutoCompleteFXC;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.HashMap;
import java.util.ResourceBundle;

public class PaymentManagementController implements Controller{
    @FXML
    private AnchorPane patientAnchorPane;

    @FXML
    private TableView<Admission> admissionTableView;
    @FXML
    private TableColumn<Admission, Integer> admissionIdTableColumn;
    @FXML
    private TableColumn<Admission, LocalDate> admissionDateTableColumn;
    @FXML
    private TableColumn<Admission, LocalDate> releaseDateTableColumn;

    @FXML
    private TableView<Charge> chargeTableView;
    @FXML
    private TableColumn<Charge, Double> chargeAmountTableColumn;
    @FXML
    private TableColumn<Charge, LocalDate> chargedDateTableColumn;
    @FXML
    private TableColumn<Charge, ChargeType> typeTableColumn;

    @FXML
    private TableView<Payment> paymentTableView;
    @FXML
    private TableColumn<Payment, Integer> paymentIdTableColumn;
    @FXML
    private TableColumn<Payment, Double> amountTableColumn;
    @FXML
    private TableColumn<Payment, Date> paymentDateTableColumn;

    @FXML
    private TextArea causeTextArea;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;

    private AutoCompleteFXC<Patient> patientAutoCompleteFXC;

    private HashMap<Integer, Payment> tempPaymentHashMap = new HashMap<>();
    private HashMap<Integer, Payment> editedPaymentHashMap = new HashMap<>();
    private HashMap<Integer, Payment> deletedPaymentHashMap = new HashMap<>();
    private HashMap<Integer, Admission> tempAdmissionHashMap = new HashMap<>();
    private HashMap<Integer, Charge> tempChargeHashMap = new HashMap<>();

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

        chargeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (chargeTableView.getSelectionModel().getSelectedItem() != null) {
                if (oldValue != null) {
                    descriptionTextArea.textProperty().unbindBidirectional(oldValue.descriptionProperty());
                }

                descriptionTextArea.textProperty().bindBidirectional(newValue.descriptionProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempPaymentHashMap.clear();
        editedPaymentHashMap.clear();
        deletedPaymentHashMap.clear();
        tempAdmissionHashMap.clear();

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
                tempAdmissionHashMap.put(admission.getId(), admission.clone());
            }
            ObservableList<Admission> admissionObservableList = FXCollections.observableList(admissionArrayList);
            admissionTableView.setItems(admissionObservableList);
        };
        currentPatientObjectProperty.addListener(currentPatientChangeListener);

        if(null != currentAdmissionChangeListener){
            currentAdmissionObjectProperty.removeListener(currentAdmissionChangeListener);
        }
        currentAdmissionChangeListener = (observable, oldValue, newValue) -> {
            ArrayList<Charge> chargeArrayList = new ArrayList<>();
            for (Charge charge : ChargeServices.getChargeArrayListByAdmission(newValue)) {
                chargeArrayList.add(charge.clone());
                tempChargeHashMap.put(charge.getId(), charge.clone());
            }
            ObservableList<Charge> chargeObservableList = FXCollections.observableList(chargeArrayList);
            chargeTableView.setItems(chargeObservableList);
        };
        currentAdmissionObjectProperty.addListener(currentAdmissionChangeListener);

        admissionIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        admissionDateTableColumn.setCellValueFactory(param -> param.getValue().admissionDateObjectPropertyProperty());
        releaseDateTableColumn.setCellValueFactory(param -> param.getValue().releaseDateObjectPropertyProperty());

        chargeAmountTableColumn.setCellValueFactory(param -> param.getValue().amountProperty().asObject());
        chargedDateTableColumn.setCellValueFactory(param -> param.getValue().chargedDateProperty());
        typeTableColumn.setCellValueFactory(param -> param.getValue().chargeTypeProperty());
    }

    public void newButtonOnAction(ActionEvent actionEvent) {

    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {

    }
}