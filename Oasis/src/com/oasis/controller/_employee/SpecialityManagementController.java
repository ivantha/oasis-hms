package com.oasis.controller._employee;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Speciality;
import com.oasis.services.SpecialityServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.SearchFXC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SpecialityManagementController implements Controller {
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Speciality> specialityTableView;
    @FXML
    private TableColumn<Speciality, Integer> idTableColumn;
    @FXML
    private TableColumn<Speciality, String> nameTableColumn;
    @FXML
    private TableColumn<Speciality, String> descriptionTableColumn;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;

    private HashMap<Integer, Speciality> tempSpecialityHashMap = new HashMap<>();
    private HashMap<Integer, Speciality> editedSpecialityHashMap = new HashMap<>();
    private HashMap<Integer, Speciality> deletedSpecialityHashMap = new HashMap<>();

    private SearchFXC<Speciality> specialitySearchFXC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        specialityTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (specialityTableView.getSelectionModel().getSelectedItem() != null) {
                idTextField.setText(String.valueOf(newValue.getId()));

                if (oldValue != null) {
                    nameTextField.textProperty().unbindBidirectional(oldValue.nameProperty());
                    descriptionTextArea.textProperty().unbindBidirectional(oldValue.descriptionProperty());

                    if (!oldValue.equals(tempSpecialityHashMap.get(oldValue.getId()))) {
                        editedSpecialityHashMap.put(oldValue.getId(), oldValue);
                    }
                }
                nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
                descriptionTextArea.textProperty().bindBidirectional(newValue.descriptionProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempSpecialityHashMap.clear();
        editedSpecialityHashMap.clear();
        deletedSpecialityHashMap.clear();

        ArrayList<Speciality> specialityArrayList = new ArrayList<>();
        for (Speciality speciality : SpecialityServices.getSpecialityArrayList(UIName.SPECIALITY_MANAGEMENT)) {
            specialityArrayList.add(speciality.clone());
            tempSpecialityHashMap.put(speciality.getId(), speciality.clone());
        }

        searchTextField.setText(null);
        ObservableList<Speciality> specialityObservableList = FXCollections.observableList(specialityArrayList);
        specialitySearchFXC = new SearchFXC<>(searchTextField, specialityTableView, specialityObservableList,
                "getName");

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(param -> param.getValue().nameProperty());
        descriptionTableColumn.setCellValueFactory(param -> param.getValue().descriptionProperty());
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Speciality selectedSpeciality = specialityTableView.getSelectionModel().getSelectedItem();
        if (selectedSpeciality != null) {
            specialityTableView.getItems().remove(selectedSpeciality);
            deletedSpecialityHashMap.put(selectedSpeciality.getId(), selectedSpeciality);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (specialityTableView.getSelectionModel().getSelectedItem() != null) {
            Speciality speciality = specialityTableView.getSelectionModel().getSelectedItem();

            if (!speciality.equals(tempSpecialityHashMap.get(speciality.getId()))) {
                editedSpecialityHashMap.put(speciality.getId(), speciality);
            }
        }

        SpecialityServices.updateSpeciality(UIName.SPECIALITY_MANAGEMENT, new ArrayList<>(editedSpecialityHashMap.values()));
        editedSpecialityHashMap.clear();

        SpecialityServices.deleteSpeciality(UIName.SPECIALITY_MANAGEMENT, new ArrayList<>(deletedSpecialityHashMap.values()));
        deletedSpecialityHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_SPECIALITY, true);
    }
}
