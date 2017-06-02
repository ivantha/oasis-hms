package com.oasis.controller._A;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Physician;
import com.oasis.model.PhysicianDesignation;
import com.oasis.model.Telephone;
import com.oasis.services.PhysicianServices;
import com.oasis.ui.UIName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewPhysicianController implements Controller {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox designationComboBox;
    @FXML
    private TextField telephoneTextField;

    @FXML
    private Button okButton;

    private Physician tempPhysician;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempPhysician = new Physician();
        firstNameTextField.textProperty().bindBidirectional(tempPhysician.firstNameProperty());
        middleNameTextField.textProperty().bindBidirectional(tempPhysician.middleNameProperty());
        lastNameTextField.textProperty().bindBidirectional(tempPhysician.lastNameProperty());
        designationComboBox.valueProperty().bindBidirectional(tempPhysician.physicianDesignationObjectPropertyProperty());
        tempPhysician.getTelephoneArrayList().add(new Telephone());
        telephoneTextField.textProperty().bindBidirectional(tempPhysician.getTelephoneArrayList().get(0).telephoneProperty());

        ObservableList<PhysicianDesignation> physicianDesignationObservableList = FXCollections
                .observableList(PhysicianServices.getPhysicianDesignationArrayList());
        designationComboBox.setItems(physicianDesignationObservableList);
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.add(tempPhysician);

        PhysicianServices.newPhysician(physicianArrayList);
        UIFactory.launchUI(UIName.PHYSICIAN_MANAGEMENT, true);
    }
}
