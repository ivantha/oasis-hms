package com.oasis.controller.employee;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Degree;
import com.oasis.model.Employee;
import com.oasis.model.EmployeeRole;
import com.oasis.model.Gender;
import com.oasis.services.EmployeeServices;
import com.oasis.ui.UIName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Controller{
    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, Integer> idTableColumn;
    @FXML
    private TableColumn<Employee, String> firstNameTableColumn;
    @FXML
    private TableColumn<Employee, String> middleNameTableColumn;
    @FXML
    private TableColumn<Employee, String> lastNameTableColumn;
    @FXML
    private TableColumn<Employee, String> nicTableColumn;
    @FXML
    private TableColumn<Employee, Gender> genderTableColumn;
    @FXML
    private TableColumn<Employee, LocalDate> dobTableColumn;
    @FXML
    private TableColumn<Employee, EmployeeRole> roleTableColumn;

    @FXML
    private ImageView profilePictureImageView;
    @FXML
    private Button browseButton;

    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    public Label degreeLabel;

    @FXML
    private JFXDatePicker startDateDatePicker;
    @FXML
    private JFXDatePicker endDateDatePicker;
    @FXML
    private JFXTimePicker shiftStartTimeTimePicker;
    @FXML
    private JFXTimePicker shiftEndTimeTimePicker;

    @FXML
    private CheckBox mondayCheckBox;
    @FXML
    private CheckBox tuesdayCheckBox;
    @FXML
    private CheckBox wednesdayCheckBox;
    @FXML
    private CheckBox thursdayCheckBox;
    @FXML
    private CheckBox fridayCheckBox;
    @FXML
    private CheckBox saturdayCheckBox;
    @FXML
    private CheckBox sundayCheckBox;

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;


    private HashMap<Integer, Employee> tempEmployeeHashMap = new HashMap<>();
    private HashMap<Integer, Employee> editedEmployeeHashMap = new HashMap<>();
    private HashMap<Integer, Employee> deletedEmployeeHashMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (employeeTableView.getSelectionModel().getSelectedItem() != null) {
                if (oldValue != null) {
                    telephoneTextField.textProperty().unbindBidirectional(oldValue.getEmployeeTelephoneArrayList().get(0).telephoneProperty());
                    emailTextField.textProperty().unbindBidirectional(oldValue.getEmployeeEmailArrayList().get(0).emailProperty());
                    degreeLabel.textProperty().unbindBidirectional(oldValue.degreeArrayListObjectPropertyProperty());

                    startDateDatePicker.valueProperty().unbindBidirectional(oldValue.startDateProperty());
                    endDateDatePicker.valueProperty().unbindBidirectional(oldValue.endDateProperty());
                    shiftStartTimeTimePicker.valueProperty().unbindBidirectional(oldValue.defaultShiftStartProperty());
                    shiftEndTimeTimePicker.valueProperty().unbindBidirectional(oldValue.defaultShiftEndProperty());

                    mondayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().mondayProperty());
                    tuesdayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().tuesdaysProperty());
                    wednesdayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().wednesdayProperty());
                    thursdayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().thursdayProperty());
                    fridayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().fridayProperty());
                    saturdayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().saturdayProperty());
                    sundayCheckBox.selectedProperty().unbindBidirectional(oldValue.getWorkingDays().sundayProperty());

                    if(!oldValue.equals(tempEmployeeHashMap.get(oldValue.getId()))){
                        editedEmployeeHashMap.put(oldValue.getId(), oldValue);
                    }
                }

                telephoneTextField.textProperty().bindBidirectional(newValue.getEmployeeTelephoneArrayList().get(0).telephoneProperty());
                emailTextField.textProperty().bindBidirectional(newValue.getEmployeeEmailArrayList().get(0).emailProperty());
                degreeLabel.textProperty().bindBidirectional(newValue.degreeArrayListObjectPropertyProperty(), new StringConverter<ArrayList<Degree>>() {
                    @Override
                    public String toString(ArrayList<Degree> object) {
                        String acronymChain = "";
                        if(object.isEmpty()){
                            acronymChain = "[..]";
                        }else{
                            for(Degree degree: object){
                                if(acronymChain.equals("")){
                                    acronymChain += degree.getAcronym();
                                }else{
                                    acronymChain += ", " + degree.getAcronym();
                                }
                            }
                        }

                        return acronymChain;
                    }

                    @Override
                    public ArrayList<Degree> fromString(String string) {
                        return null;
                    }
                });

                startDateDatePicker.valueProperty().bindBidirectional(newValue.startDateProperty());
                endDateDatePicker.valueProperty().bindBidirectional(newValue.endDateProperty());
                shiftStartTimeTimePicker.valueProperty().bindBidirectional(newValue.defaultShiftStartProperty());
                shiftEndTimeTimePicker.valueProperty().bindBidirectional(newValue.defaultShiftEndProperty());

                mondayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().mondayProperty());
                tuesdayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().tuesdaysProperty());
                wednesdayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().wednesdayProperty());
                thursdayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().thursdayProperty());
                fridayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().fridayProperty());
                saturdayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().saturdayProperty());
                sundayCheckBox.selectedProperty().bindBidirectional(newValue.getWorkingDays().sundayProperty());
            }
        });
    }

    @Override
    public void refreshView() {
        tempEmployeeHashMap.clear();
        editedEmployeeHashMap.clear();
        deletedEmployeeHashMap.clear();

        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        for (Employee employee : EmployeeServices.getEmployeeArrayList()) {
            employeeArrayList.add(employee.clone());
            tempEmployeeHashMap.put(employee.getId(), employee.clone());
        }
        ObservableList<Employee> employeeObservableList = FXCollections.observableList(employeeArrayList);
        employeeTableView.setItems(employeeObservableList);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameTableColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());
        middleNameTableColumn.setCellValueFactory(param -> param.getValue().middleNameProperty());
        lastNameTableColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());
        nicTableColumn.setCellValueFactory(param -> param.getValue().nicProperty());
        genderTableColumn.setCellValueFactory(param -> param.getValue().genderProperty());
        dobTableColumn.setCellValueFactory(param -> param.getValue().dobProperty());
        roleTableColumn.setCellValueFactory(param -> param.getValue().employeeRoleProperty());
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {

    }

    public void editButtonOnAction(ActionEvent actionEvent) {

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {

    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EMPLOYEE, true);
    }

    public void browseButtonOnAction(ActionEvent actionEvent) {

    }
}
