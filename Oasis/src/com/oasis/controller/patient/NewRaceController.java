package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Race;
import com.oasis.services.RaceServices;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewRaceController implements Controller{
    @FXML
    private TextField raceTextField;

    @FXML
    private Button okButton;

    private Race tempRace;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempRace = new Race();
        raceTextField.textProperty().bindBidirectional(tempRace.nameProperty());
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Race> raceArrayList = new ArrayList<>();
        raceArrayList.add(tempRace);

        RaceServices.newRace(raceArrayList);

        UIFactory.launchUI(UIName.RACE_MANAGEMENT, true);
    }
}
