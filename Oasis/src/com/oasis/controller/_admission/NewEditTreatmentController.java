package com.oasis.controller._admission;

import com.jfoenix.controls.JFXDatePicker;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Admission;
import com.oasis.model.Charge;
import com.oasis.model.Treatment;
import com.oasis.services.TreatmentServices;
import com.oasis.ui.UIName;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewEditTreatmentController implements Controller {
    @FXML
    private TextField patientTextField;
    @FXML
    private TextField admissioidTextField;
    @FXML
    private TextArea treatmentDescriptionTextArea;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private JFXDatePicker givenDateDatePicker;

    @FXML
    private CheckBox chargedCheckBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextArea chargedDescriptionTextArea;
    @FXML
    private JFXDatePicker chargedDateDatePicker;

    @FXML
    private Button okButton;

    private Treatment tempTreatment;
    private Admission tempAdmission;
    private boolean isEditingMode = false;

    private ChangeListener<LocalDate> givenDateChangeListener;
    private ChangeListener<LocalDate> chargedDateChangeListener;
    private ChangeListener<Boolean> chargedCheckBoxChangeListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        throw new UnsupportedOperationException();
    }

    public void refreshView(Admission admission) {
        tempAdmission = admission;

        try {
            unBindFields(tempTreatment);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempTreatment = new Treatment();
        tempTreatment.setId(0);

        isEditingMode = false;
        bindFields(tempTreatment);

        patientTextField.setText(admission.getPatientObjectProperty().toString());
        admissioidTextField.setText(String.valueOf(admission.getId()));
        chargedCheckBox.setSelected(false);
    }

    public void showTreatment(Treatment treatment) {

    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Treatment> treatmentArrayList = new ArrayList<>();
        treatmentArrayList.add(tempTreatment);
        if (!isEditingMode) {
            TreatmentServices.newTreatment(UIName.NEW_EDIT_TREATMENT, treatmentArrayList, tempAdmission);
        } else {
            TreatmentServices.updateTreatment(UIName.NEW_EDIT_TREATMENT, treatmentArrayList);
        }
        UIFactory.launchUI(UIName.TREATMENT_MANAGEMENT, true);
    }

    private void unBindFields(Treatment treatment) {
        treatmentDescriptionTextArea.textProperty().unbindBidirectional(treatment.descriptionProperty());
        resultTextArea.textProperty().unbindBidirectional(treatment.resultProperty());
        givenDateDatePicker.valueProperty().removeListener(givenDateChangeListener);

        chargedCheckBox.selectedProperty().removeListener(chargedCheckBoxChangeListener);
    }

    private void bindFields(Treatment treatment) {
        treatmentDescriptionTextArea.textProperty().bindBidirectional(treatment.descriptionProperty());
        resultTextArea.textProperty().bindBidirectional(treatment.resultProperty());
        givenDateChangeListener = (observable, oldValue, newValue) -> {
            treatment.setGivenDateObjectProperty(java.sql.Date.valueOf(newValue));
        };
        givenDateDatePicker.valueProperty().addListener(givenDateChangeListener);

        chargedCheckBoxChangeListener = (observable, oldValue, newValue) -> {
            if (newValue) {
                if (null == treatment.getChargeObjectProperty()) {
                    treatment.setChargeObjectProperty(new Charge());
                }

                amountTextField.setDisable(false);
                chargedDescriptionTextArea.setDisable(false);
                chargedDateDatePicker.setDisable(false);

//                amountTextField.textProperty().bindBidirectional(treatment.getChargeObjectProperty().amountProperty(), new NumberStringConverter());
                Bindings.bindBidirectional(amountTextField.textProperty(), treatment.getChargeObjectProperty().amountProperty(),
                        new NumberStringConverter());
                chargedDescriptionTextArea.textProperty().bindBidirectional(treatment.getChargeObjectProperty().descriptionProperty());
                chargedDateChangeListener = (observable1, oldValue1, newValue1) -> {
                    treatment.getChargeObjectProperty().setChargedDate(java.sql.Date.valueOf(newValue1));
                };
                chargedDateDatePicker.valueProperty().addListener(chargedDateChangeListener);
            } else {
                amountTextField.setDisable(true);
                chargedDescriptionTextArea.setDisable(true);
                chargedDateDatePicker.setDisable(true);

                amountTextField.textProperty().unbindBidirectional(treatment.getChargeObjectProperty().amountProperty());
                chargedDescriptionTextArea.textProperty().unbindBidirectional(treatment.getChargeObjectProperty().descriptionProperty());
                chargedDateDatePicker.valueProperty().removeListener(chargedDateChangeListener);
            }
        };
        chargedCheckBox.selectedProperty().addListener(chargedCheckBoxChangeListener);
    }
}
