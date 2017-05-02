package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Race;
import com.oasis.services.RaceServices;
import com.oasis.ui.UIName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RaceManagementController implements Controller{
    @FXML
    private TableView<Race> raceTableView;
    @FXML
    private TableColumn<Race, Integer> idTableColumn;
    @FXML
    private TableColumn<Race, String> nameTableColumn;

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    private Button deleteButton;
    @FXML
    private Button newButton;
    @FXML
    private Button saveButton;

    private HashMap<Integer, Race> tempRaceHashMap = new HashMap<>();
    private HashMap<Integer, Race> editedRaceHashMap = new HashMap<>();
    private HashMap<Integer, Race> deletedRaceHashMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        raceTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (raceTableView.getSelectionModel().getSelectedItem() != null) {
                idTextField.setText(String.valueOf(newValue.getId()));

                if (oldValue != null) {
                    nameTextField.textProperty().unbindBidirectional(oldValue.nameProperty());

                    if(!oldValue.equals(tempRaceHashMap.get(oldValue.getId()))){
                        editedRaceHashMap.put(oldValue.getId(), oldValue);
                    }
                }
                nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempRaceHashMap.clear();
        editedRaceHashMap.clear();
        deletedRaceHashMap.clear();

        ArrayList<Race> raceArrayList = new ArrayList<>();
        for (Race race : RaceServices.getRaceArrayList()) {
            raceArrayList.add(race.clone());
            tempRaceHashMap.put(race.getId(), race.clone());
        }
        ObservableList<Race> raceObservableList = FXCollections.observableList(raceArrayList);
        raceTableView.setItems(raceObservableList);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(param -> param.getValue().nameProperty());
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Race selectedRace = raceTableView.getSelectionModel().getSelectedItem();
        if(selectedRace != null){
            raceTableView.getItems().remove(selectedRace);
            deletedRaceHashMap.put(selectedRace.getId(), selectedRace);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (raceTableView.getSelectionModel().getSelectedItem() != null) {
            Race race = raceTableView.getSelectionModel().getSelectedItem();

            if(!race.equals(tempRaceHashMap.get(race.getId()))){
                editedRaceHashMap.put(race.getId(), race);
            }
        }

        RaceServices.updateRace(new ArrayList<>(editedRaceHashMap.values()));
        editedRaceHashMap.clear();

        RaceServices.deleteRace(new ArrayList<>(deletedRaceHashMap.values()));
        deletedRaceHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_RACE, true);
    }
}
