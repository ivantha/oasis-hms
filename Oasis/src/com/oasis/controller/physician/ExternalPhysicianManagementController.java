package com.oasis.controller.physician;

import com.oasis.controller.Controller;
import com.oasis.model.Physician;
import com.oasis.model.PhysicianDesignation;
import com.oasis.services.PhysicianServices;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExternalPhysicianManagementController implements Controller{
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
    private TableColumn<Physician, String> designationTableColumn;
    @FXML
    private TableColumn<Physician, String> telephoneTableColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Physician> physicianObservableList = FXCollections.observableList(
                PhysicianServices.getPhysicianArrayList());
        physicianTableView.setItems(physicianObservableList);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameTableColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());
        middleNameTableColumn.setCellValueFactory(param -> param.getValue().middleNameProperty());
        lastNameTableColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());
        designationTableColumn.setCellValueFactory(param -> param.getValue().getPhysicianDesignation().nameProperty());
        telephoneTableColumn.setCellValueFactory(param -> param.getValue().getPhysicianTelephoneArrayList().get(0).telephoneProperty());
    }

    @Override
    public void refreshView() {

    }
}
