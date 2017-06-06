package com.oasis.controller._A_to_validate;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Doctor;
import com.oasis.model.Employee;
import com.oasis.model.Gender;
import com.oasis.model.Ward;
import com.oasis.services.EmployeeServices;
import com.oasis.services.GenderServices;
import com.oasis.services.WardServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.AutoCompleteFXC;
import com.oasis.ui.component.SearchFXC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class WardManagementController implements Controller {
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Ward> wardTableView;
    @FXML
    private TableColumn<Ward, Integer> idTableColumn;
    @FXML
    private TableColumn<Ward, String> nameTableColumn;
    @FXML
    private TableColumn<Ward, String> descriptionTableColumn;
    @FXML
    private TableColumn<Ward, Integer> maxPatientCountTableColumn;
    @FXML
    private TableColumn<Ward, Integer> currentPatientCountTableColumn;
    @FXML
    private TableColumn<Ward, Gender> genderTableColumn;
    @FXML
    private TableColumn<Ward, Employee> supervisorTableColumn;

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField maxPatientCountTextField;
    @FXML
    private TextField currentPatientCountTextField;
    @FXML
    private ComboBox<Gender> genderComboBox;
    @FXML
    private AnchorPane supervisorAnchorPane;

    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;

    private HashMap<Integer, Ward> tempWardHashMap = new HashMap<>();
    private HashMap<Integer, Ward> editedWardHashMap = new HashMap<>();
    private HashMap<Integer, Ward> deletedWardHashMap = new HashMap<>();

    private SearchFXC<Ward> wardSearchFXC;
    private AutoCompleteFXC<Doctor> supervisorAutoCompleteFXC;
    private ObservableList<Ward> wardObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supervisorAutoCompleteFXC = new AutoCompleteFXC<>(searchParam ->
                EmployeeServices.getDoctorLike(searchParam), UIName.EMPLOYEE_DETAILS_POPOVER);
        supervisorAnchorPane.getChildren().add(supervisorAutoCompleteFXC);

        wardTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (wardTableView.getSelectionModel().getSelectedItem() != null) {
                idTextField.setText(String.valueOf(newValue.getId()));

                if (oldValue != null) {
                    nameTextField.textProperty().unbindBidirectional(oldValue.nameProperty());
                    descriptionTextArea.textProperty().unbindBidirectional(oldValue.descriptionProperty());
                    maxPatientCountTextField.textProperty().unbindBidirectional(oldValue.maxPatientCountProperty());
                    currentPatientCountTextField.textProperty().unbindBidirectional(oldValue.currentPatientCountProperty());
                    genderComboBox.valueProperty().unbindBidirectional(oldValue.genderAcceptanceProperty());
                    supervisorAutoCompleteFXC.unBindList();

                    if (!oldValue.equals(tempWardHashMap.get(oldValue.getId()))) {
                        editedWardHashMap.put(oldValue.getId(), oldValue);
                    }
                }
                nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
                descriptionTextArea.textProperty().bindBidirectional(newValue.descriptionProperty());
                maxPatientCountTextField.textProperty().bindBidirectional(newValue.maxPatientCountProperty(), new NumberStringConverter());
                currentPatientCountTextField.textProperty().bindBidirectional(newValue.currentPatientCountProperty(), new NumberStringConverter());
                genderComboBox.valueProperty().bindBidirectional(newValue.genderAcceptanceProperty());
                supervisorAutoCompleteFXC.bindList(newValue.supervisorProperty());

                supervisorAutoCompleteFXC.updateText();
            }
        });
    }

    @Override
    public void refreshView() {
        tempWardHashMap.clear();
        editedWardHashMap.clear();
        deletedWardHashMap.clear();

        ArrayList<Ward> wardArrayList = new ArrayList<>();
        for (Ward ward : WardServices.getWardArrayList()) {
            wardArrayList.add(ward.clone());
            tempWardHashMap.put(ward.getId(), ward.clone());
        }

        searchTextField.setText(null);
        wardObservableList = FXCollections.observableList(wardArrayList);
        wardSearchFXC = new SearchFXC<>(searchTextField, wardTableView, wardObservableList,
                "getName");

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(param -> param.getValue().nameProperty());
        descriptionTableColumn.setCellValueFactory(param -> param.getValue().descriptionProperty());
        maxPatientCountTableColumn.setCellValueFactory(param -> param.getValue().maxPatientCountProperty().asObject());
        currentPatientCountTableColumn.setCellValueFactory(param -> param.getValue().currentPatientCountProperty().asObject());
        genderTableColumn.setCellValueFactory(param -> param.getValue().genderAcceptanceProperty());
        supervisorTableColumn.setCellValueFactory(param -> param.getValue().supervisorProperty());

        ObservableList<Gender> genderObservableList = FXCollections
                .observableList(GenderServices.getGenderArrayList());
        genderComboBox.setItems(genderObservableList);
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Ward selectedWard = wardTableView.getSelectionModel().getSelectedItem();
        if (selectedWard != null) {
            wardObservableList.remove(selectedWard);
            deletedWardHashMap.put(selectedWard.getId(), selectedWard);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (wardTableView.getSelectionModel().getSelectedItem() != null) {
            Ward ward = wardTableView.getSelectionModel().getSelectedItem();

            if (!ward.equals(tempWardHashMap.get(ward.getId()))) {
                editedWardHashMap.put(ward.getId(), ward);
            }
        }

        WardServices.updateWard(new ArrayList<>(editedWardHashMap.values()));
        editedWardHashMap.clear();

        WardServices.deleteWard(new ArrayList<>(deletedWardHashMap.values()));
        deletedWardHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_WARD, true);
    }
}
