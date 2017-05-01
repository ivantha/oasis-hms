package com.oasis.controller.employee;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Race;
import com.oasis.model.Speciality;
import com.oasis.services.RaceServices;
import com.oasis.services.SpecialityServices;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewSpecialityController implements Controller{
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button okButton;

    private Speciality tempSpeciality;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempSpeciality = new Speciality();
        nameTextField.textProperty().bindBidirectional(tempSpeciality.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tempSpeciality.descriptionProperty());
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Speciality> specialityArrayList = new ArrayList<>();
        specialityArrayList.add(tempSpeciality);

        SpecialityServices.newSpeciality(specialityArrayList);

        UIFactory.launchUI(UIName.SPECIALITY_MANAGEMENT, true);
    }
}
