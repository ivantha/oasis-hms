package com.oasis.controller._patient;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Ethnicity;
import com.oasis.services.EthnicityServices;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewEthnicityController implements Controller {
    @FXML
    private TextField ethnicityTextField;

    @FXML
    private Button okButton;

    private Ethnicity tempEthnicity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempEthnicity = new Ethnicity();
        ethnicityTextField.textProperty().bindBidirectional(tempEthnicity.nameProperty());
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Ethnicity> ethnicityArrayList = new ArrayList<>();
        ethnicityArrayList.add(tempEthnicity);

        EthnicityServices.addEthnicity(UIName.NEW_ETHNICITY, ethnicityArrayList);

        UIFactory.launchUI(UIName.ETHNICITY_MANAGEMENT, true);
    }
}
