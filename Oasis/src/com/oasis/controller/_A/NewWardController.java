package com.oasis.controller._A;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Gender;
import com.oasis.model.Ward;
import com.oasis.services.GenderServices;
import com.oasis.services.WardServices;
import com.oasis.ui.UIName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField supervisorTextField;

    @FXML
    private Button okButton;

    private Ward tempWard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempWard = new Ward();
        nameTextField.textProperty().bindBidirectional(tempWard.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tempWard.descriptionProperty());
        maxPatientCountTextField.textProperty().bindBidirectional(tempWard.maxPatientCountProperty(), new NumberStringConverter());
        genderAcceptanceComboBox.valueProperty().bindBidirectional(tempWard.genderAcceptanceProperty());
        supervisorTextField.textProperty().bindBidirectional(tempWard.supervisorIdProperty(), new NumberStringConverter());

        ObservableList<Gender> genderObservableList = FXCollections
                .observableList(GenderServices.getGenderArrayList());
        genderAcceptanceComboBox.setItems(genderObservableList);
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Ward> wardArrayList = new ArrayList<>();
        wardArrayList.add(tempWard);

        WardServices.newWard(wardArrayList);

        UIFactory.launchUI(UIName.WARD_MANAGEMENT, true);
    }
}
