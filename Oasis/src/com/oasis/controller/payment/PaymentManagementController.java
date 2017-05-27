package com.oasis.controller.payment;

import com.oasis.controller.Controller;
import com.oasis.model.*;
import com.oasis.services.AdmissionServices;
import com.oasis.services.ChargeServices;
import com.oasis.services.PatientServices;
import com.oasis.services.PaymentServices;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

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
    private TextField amountTextField;

    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;

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
            updateChargeTable(newValue);
            updatePaymentTable(newValue);
        };
        currentAdmissionObjectProperty.addListener(currentAdmissionChangeListener);

        admissionIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        admissionDateTableColumn.setCellValueFactory(param -> param.getValue().admissionDateObjectPropertyProperty());
        releaseDateTableColumn.setCellValueFactory(param -> param.getValue().releaseDateObjectPropertyProperty());

        chargeAmountTableColumn.setCellValueFactory(param -> param.getValue().amountProperty().asObject());
        chargedDateTableColumn.setCellValueFactory(param -> param.getValue().chargedDateProperty());
        typeTableColumn.setCellValueFactory(param -> param.getValue().chargeTypeProperty());

        paymentIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        amountTableColumn.setCellValueFactory(param -> param.getValue().amountProperty().asObject());
        paymentDateTableColumn.setCellValueFactory(param -> param.getValue().paymentDateProperty());
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        Admission selectedAdmission = admissionTableView.getSelectionModel().getSelectedItem();
        if(null != selectedAdmission){
            double amount = Double.valueOf(amountTextField.getText());
            Payment payment = new Payment(selectedAdmission.getId(), amount, new Date());

            ArrayList<Payment> newPaymentArrayList = new ArrayList<>();
            newPaymentArrayList.add(payment);
            PaymentServices.newPayment(newPaymentArrayList);

            amountTextField.setText(null);
            updatePaymentTable(currentAdmissionObjectProperty.get());
        }
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Payment selectedPayment = paymentTableView.getSelectionModel().getSelectedItem();
        if(selectedPayment != null){
            paymentTableView.getItems().remove(selectedPayment);

            ArrayList<Payment> deletePaymentArrayList = new ArrayList<>();
            deletePaymentArrayList.add(selectedPayment);
            PaymentServices.deletePayment(deletePaymentArrayList);
        }
    }

    private void updateChargeTable(Admission admission){
        ArrayList<Charge> chargeArrayList = new ArrayList<>();
        for (Charge charge : ChargeServices.getChargeArrayListByAdmission(admission)) {
            chargeArrayList.add(charge.clone());
        }
        ObservableList<Charge> chargeObservableList = FXCollections.observableList(chargeArrayList);
        chargeTableView.setItems(chargeObservableList);
    }

    private void updatePaymentTable(Admission admission){
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        for (Payment payment : PaymentServices.getPaymentArrayListByAdmission(admission)) {
            paymentArrayList.add(payment.clone());
        }
        ObservableList<Payment> paymentObservableList = FXCollections.observableList(paymentArrayList);
        paymentTableView.setItems(paymentObservableList);
    }
}