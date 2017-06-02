package com.oasis.controller.physician;

import com.oasis.controller.PopOverController;
import com.oasis.model.Physician;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PhysicianDetailsPopOverController implements PopOverController<Physician> {
    @FXML
    private Label idLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label middleNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label designationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    @Override
    public void refreshView(Physician physician) {
        idLabel.setText(String.valueOf(physician.getId()));
        firstNameLabel.setText(physician.getFirstName());
        middleNameLabel.setText(physician.getMiddleName());
        lastNameLabel.setText(physician.getLastName());
        designationLabel.setText(physician.getPhysicianDesignationObjectProperty().getName());
    }
}
