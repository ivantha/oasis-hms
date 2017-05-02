package com.oasis.controller.administration;

import com.oasis.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class WardManagementController implements Controller{
    @FXML
    private TableView wardTableView;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableColumn descriptionTableColumn;
    @FXML
    private TableColumn maxPatientCountTableColumn;
    @FXML
    private TableColumn currentPatientCountTableColumn;
    @FXML
    private TableColumn genderTableColumn;
    @FXML
    private TableColumn supervisorIdTableColumn;

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
    private ComboBox genderTextField;
    @FXML
    private TextField supervisorIdTextField;

    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {

    }

    public void newButtonOnAction(ActionEvent actionEvent) {

    }
}
