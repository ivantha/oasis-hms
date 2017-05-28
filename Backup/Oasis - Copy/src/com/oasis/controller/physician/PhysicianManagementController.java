package com.oasis.controller.physician;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Physician;
import com.oasis.model.PhysicianDesignation;
import com.oasis.services.PhysicianServices;
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

public class PhysicianManagementController implements Controller {
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Physician> physicianTableView;
    @FXML
    private TableColumn<Physician, Integer> idTableColumn;
    @FXML
    private TableColumn<Physician, String> firstNameTableColumn;
    @FXML
    private TableColumn<Physician, String> middleNameTableColumn;
    @FXML
    private TableColumn<Physician, String> lastNameTableColumn;
    @FXML
    private TableColumn<Physician, PhysicianDesignation> designationTableColumn;
    @FXML
    private TableColumn<Physician, String> telephoneTableColumn;

    @FXML
    private TextField idTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<PhysicianDesignation> designationComboBox;
    @FXML
    private TextField telephoneTextField;

    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;

    private HashMap<Integer, Physician> tempPhysicianHashMap = new HashMap<>();
    private HashMap<Integer, Physician> editedPhysicianHashMap = new HashMap<>();
    private HashMap<Integer, Physician> deletedPhysicianHashMap = new HashMap<>();

    private SearchFXC<Physician> physicianSearchFXC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        physicianTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (physicianTableView.getSelectionModel().getSelectedItem() != null) {
                idTextField.setText(String.valueOf(newValue.getId()));

                if (oldValue != null) {
                    firstNameTextField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                    middleNameTextField.textProperty().unbindBidirectional(oldValue.middleNameProperty());
                    lastNameTextField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                    designationComboBox.valueProperty().unbindBidirectional(oldValue.physicianDesignationObjectPropertyProperty());
                    telephoneTextField.textProperty().unbindBidirectional(oldValue.getTelephoneArrayList()
                            .get(0).telephoneProperty());

                    if(!oldValue.equals(tempPhysicianHashMap.get(oldValue.getId()))){
                        editedPhysicianHashMap.put(oldValue.getId(), oldValue);
                    }
                }
                firstNameTextField.textProperty().bindBidirectional(newValue.firstNameProperty());
                middleNameTextField.textProperty().bindBidirectional(newValue.middleNameProperty());
                lastNameTextField.textProperty().bindBidirectional(newValue.lastNameProperty());
                designationComboBox.valueProperty().bindBidirectional(newValue.physicianDesignationObjectPropertyProperty());
                telephoneTextField.textProperty().bindBidirectional(newValue.getTelephoneArrayList()
                        .get(0).telephoneProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempPhysicianHashMap.clear();
        editedPhysicianHashMap.clear();
        deletedPhysicianHashMap.clear();

        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        for (Physician physician : PhysicianServices.getPhysicianArrayList()) {
            physicianArrayList.add(physician.clone());
            tempPhysicianHashMap.put(physician.getId(), physician.clone());
        }

        searchTextField.setText(null);
        ObservableList<Physician> physicianObservableList = FXCollections.observableList(physicianArrayList);
        physicianSearchFXC = new SearchFXC<>(searchTextField, physicianTableView, physicianObservableList,
                "getFirstName", "getMiddleName", "getLastName");

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameTableColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());
        middleNameTableColumn.setCellValueFactory(param -> param.getValue().middleNameProperty());
        lastNameTableColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());
        designationTableColumn.setCellValueFactory(param -> param.getValue().physicianDesignationObjectPropertyProperty());
        telephoneTableColumn.setCellValueFactory(param -> param.getValue().getTelephoneArrayList().get(0).telephoneProperty());

        ObservableList<PhysicianDesignation> physicianDesignationObservableList = FXCollections
                .observableList(PhysicianServices.getPhysicianDesignationArrayList());
        designationComboBox.setItems(physicianDesignationObservableList);
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (physicianTableView.getSelectionModel().getSelectedItem() != null) {
            Physician physician = physicianTableView.getSelectionModel().getSelectedItem();

            if(!physician.equals(tempPhysicianHashMap.get(physician.getId()))){
                editedPhysicianHashMap.put(physician.getId(), physician);
            }
        }

        PhysicianServices.updatePhysician(new ArrayList<>(editedPhysicianHashMap.values()));
        editedPhysicianHashMap.clear();

        PhysicianServices.deletePhysician(new ArrayList<>(deletedPhysicianHashMap.values()));
        deletedPhysicianHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_PHYSICIAN, true);
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Physician selectedPhysician = physicianTableView.getSelectionModel().getSelectedItem();
        if(selectedPhysician != null){
            physicianTableView.getItems().remove(selectedPhysician);
            deletedPhysicianHashMap.put(selectedPhysician.getId(), selectedPhysician);
        }
    }
}