package com.oasis.controller.administration;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.factory.ValidationFactory;
import com.oasis.model.Doctor;
import com.oasis.model.Gender;
import com.oasis.model.Ward;
import com.oasis.services.EmployeeServices;
import com.oasis.services.GenderServices;
import com.oasis.services.WardServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.AutoCompleteFXC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewWardController implements Controller {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField maxPatientCountTextField;
    @FXML
    private ComboBox<Gender> genderAcceptanceComboBox;
    @FXML
    private AnchorPane supervisorAnchorPane;

    @FXML
    private Button okButton;

    private Ward tempWard;

    private AutoCompleteFXC<Doctor> supervisorAutoCompleteFXC;
    private ValidationFactory validationFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supervisorAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                EmployeeServices.getDoctorLike(searchParam), UIName.EMPLOYEE_DETAILS_POPOVER);
        supervisorAnchorPane.getChildren().add(supervisorAutoCompleteFXC);
    }

    @Override
    public void refreshView() {
        tempWard = new Ward();
        nameTextField.textProperty().bindBidirectional(tempWard.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tempWard.descriptionProperty());
        maxPatientCountTextField.textProperty().bindBidirectional(tempWard.maxPatientCountProperty(), new NumberStringConverter());
        genderAcceptanceComboBox.valueProperty().bindBidirectional(tempWard.genderAcceptanceProperty());
        supervisorAutoCompleteFXC.bindList(tempWard.supervisorProperty());
        supervisorAutoCompleteFXC.updateText();

        ObservableList<Gender> genderObservableList = FXCollections
                .observableList(GenderServices.getGenderArrayList());
        genderAcceptanceComboBox.setItems(genderObservableList);

        addValidators();
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        if (validationFactory.isValid()) {
            ArrayList<Ward> wardArrayList = new ArrayList<>();
            wardArrayList.add(tempWard);

            WardServices.newWard(wardArrayList);

            UIFactory.launchUI(UIName.WARD_MANAGEMENT, true);
        } else {
            validationFactory.generateErrorNotifications();
        }
    }

    private void addValidators() {
        validationFactory = new ValidationFactory();
        validationFactory.addNameValidator(nameTextField);
        validationFactory.addIntegerValidator(maxPatientCountTextField);
        validationFactory.addComboBoxValidator(genderAcceptanceComboBox);
        validationFactory.addAutoCompleteFXCValidator(supervisorAutoCompleteFXC);
    }
}
