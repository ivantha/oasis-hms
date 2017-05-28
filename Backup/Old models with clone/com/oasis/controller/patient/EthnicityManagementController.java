package com.oasis.controller.patient;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Ethnicity;
import com.oasis.services.EthnicityServices;
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

public class EthnicityManagementController implements Controller{
    @FXML
    private TableView<Ethnicity> ethnicityTableView;
    @FXML
    private TableColumn<Ethnicity, Integer> idTableColumn;
    @FXML
    private TableColumn<Ethnicity, String> nameTableColumn;

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

    private HashMap<Integer, Ethnicity> tempEthnicityHashMap = new HashMap<>();
    private HashMap<Integer, Ethnicity> editedEthnicityHashMap = new HashMap<>();
    private HashMap<Integer, Ethnicity> deletedEthnicityHashMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ethnicityTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (ethnicityTableView.getSelectionModel().getSelectedItem() != null) {
                idTextField.setText(String.valueOf(newValue.getId()));

                if (oldValue != null) {
                    nameTextField.textProperty().unbindBidirectional(oldValue.nameProperty());

                    if(!oldValue.equals(tempEthnicityHashMap.get(oldValue.getId()))){
                        editedEthnicityHashMap.put(oldValue.getId(), oldValue);
                    }
                }
                nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempEthnicityHashMap.clear();
        editedEthnicityHashMap.clear();
        deletedEthnicityHashMap.clear();

        ArrayList<Ethnicity> ethnicityArrayList = new ArrayList<>();
        for (Ethnicity ethnicity : EthnicityServices.getEthnicityArrayList()) {
            ethnicityArrayList.add(ethnicity.clone());
            tempEthnicityHashMap.put(ethnicity.getId(), ethnicity.clone());
        }
        ObservableList<Ethnicity> ethnicityObservableList = FXCollections.observableList(ethnicityArrayList);
        ethnicityTableView.setItems(ethnicityObservableList);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(param -> param.getValue().nameProperty());
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Ethnicity selectedEthnicity = ethnicityTableView.getSelectionModel().getSelectedItem();
        if(selectedEthnicity != null){
            ethnicityTableView.getItems().remove(selectedEthnicity);
            deletedEthnicityHashMap.put(selectedEthnicity.getId(), selectedEthnicity);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (ethnicityTableView.getSelectionModel().getSelectedItem() != null) {
            Ethnicity ethnicity = ethnicityTableView.getSelectionModel().getSelectedItem();

            if(!ethnicity.equals(tempEthnicityHashMap.get(ethnicity.getId()))){
                editedEthnicityHashMap.put(ethnicity.getId(), ethnicity);
            }
        }

        EthnicityServices.updateEthnicity(new ArrayList<>(editedEthnicityHashMap.values()));
        editedEthnicityHashMap.clear();

        EthnicityServices.deleteEthnicity(new ArrayList<>(deletedEthnicityHashMap.values()));
        deletedEthnicityHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_ETHNICITY, true);
    }
}
