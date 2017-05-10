package com.oasis.controller.employee;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
import com.oasis.services.EmployeeRoleServices;
import com.oasis.services.EmployeeServices;
import com.oasis.services.GenderServices;
import com.oasis.services.SpecialityServices;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewEditEmployeeController implements Controller{
    @FXML
    private TextField nicTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<Gender> genderComboBox;
    @FXML
    private JFXDatePicker dobDatePicker;
    @FXML
    private JFXDatePicker startDateDatePicker;
    @FXML
    private JFXDatePicker endDateDatePicker;
    @FXML
    private ComboBox<EmployeeRole> employeeRoleComboBox;
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
    private TextField telephoneTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField streetTextField;
    @FXML
    private TextField townTextField;
    @FXML
    private TextField provinceTextField;
    @FXML
    private TextField postalCodeTextField;

    @FXML
    private ComboBox<Speciality> specialityComboBox;

    @FXML
    public ImageView profilePictureImageView;
    @FXML
    private Button browseButton;

    @FXML
    private ListView<Degree> degreeListView;
    @FXML
    private Button addCircleButton;
    @FXML
    private Button removeCircleButton;

    @FXML
    private Button okButton;

    private Employee tempEmployee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempEmployee = new Employee();
        tempEmployee.setId(0);

        nicTextField.textProperty().bindBidirectional(tempEmployee.nicProperty());
        firstNameTextField.textProperty().bindBidirectional(tempEmployee.firstNameProperty());
        middleNameTextField.textProperty().bindBidirectional(tempEmployee.middleNameProperty());
        lastNameTextField.textProperty().bindBidirectional(tempEmployee.lastNameProperty());
        genderComboBox.valueProperty().bindBidirectional(tempEmployee.genderProperty());
        dobDatePicker.valueProperty().bindBidirectional(tempEmployee.dobProperty());
        startDateDatePicker.valueProperty().bindBidirectional(tempEmployee.startDateProperty());
        endDateDatePicker.valueProperty().bindBidirectional(tempEmployee.endDateProperty());
        employeeRoleComboBox.valueProperty().bindBidirectional(tempEmployee.employeeRoleProperty());
        shiftStartTimeTimePicker.valueProperty().bindBidirectional(tempEmployee.defaultShiftStartProperty());
        shiftEndTimeTimePicker.valueProperty().bindBidirectional(tempEmployee.defaultShiftEndProperty());

        mondayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().mondayProperty());
        tuesdayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().tuesdaysProperty());
        wednesdayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().wednesdayProperty());
        thursdayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().thursdayProperty());
        fridayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().fridayProperty());
        saturdayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().saturdayProperty());
        sundayCheckBox.selectedProperty().bindBidirectional(tempEmployee.getWorkingDays().sundayProperty());

        tempEmployee.getEmployeeTelephoneArrayList().add(new EmployeeTelephone());
        telephoneTextField.textProperty().bindBidirectional(tempEmployee.getEmployeeTelephoneArrayList().get(0).telephoneProperty());
        tempEmployee.getEmployeeEmailArrayList().add(new EmployeeEmail());
        emailTextField.textProperty().bindBidirectional(tempEmployee.getEmployeeEmailArrayList().get(0).emailProperty());

        tempEmployee.getEmployeeAddressArrayList().add(new EmployeeAddress());
        streetTextField.textProperty().bindBidirectional(tempEmployee.getEmployeeAddressArrayList().get(0).streetProperty());
        townTextField.textProperty().bindBidirectional(tempEmployee.getEmployeeAddressArrayList().get(0).townProperty());
        provinceTextField.textProperty().bindBidirectional(tempEmployee.getEmployeeAddressArrayList().get(0).provinceProperty());
        postalCodeTextField.textProperty().bindBidirectional(tempEmployee.getEmployeeAddressArrayList().get(0).postalCodeProperty());

        specialityComboBox.setDisable(true);

        ObservableList<Gender> genderObservableList = FXCollections
                .observableList(GenderServices.getGenderArrayList());
        genderComboBox.setItems(genderObservableList);

        ObservableList<EmployeeRole> employeeRoleObservableList = FXCollections
                .observableList(EmployeeRoleServices.getEmployeeRoleArrayList());
        employeeRoleComboBox.setItems(employeeRoleObservableList);

        ObservableList<Speciality> specialityObservableList = FXCollections
                .observableList(SpecialityServices.getSpecialityArrayList());
        specialityComboBox.setItems(specialityObservableList);

        degreeListView.itemsProperty().bindBidirectional(tempEmployee.degreeListPropertyProperty());
    }

    public void browseButtonOnAction(ActionEvent actionEvent) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an image");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));

            File source = fileChooser.showOpenDialog(null);

            File dest = new File(System.getProperty("user.dir"), "temp\\pp_new_" + tempEmployee.getId() + ".jpg");

        try {
            FileUtils.copyFile(source, dest);

            Image profilePictureImage = new Image(dest.toURI().toString());
            profilePictureImageView.setImage(profilePictureImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCircleButtonOnAction(ActionEvent actionEvent) {
        UI ui = UIFactory.getUI(UIName.DEGREE_LIST_POPOVER);

        DegreeListPopOverController degreeListPopOverController = (DegreeListPopOverController) ui.getController();
        degreeListPopOverController.refreshView();
        degreeListPopOverController.setAddedDegreeObservableList(tempEmployee.degreeListPropertyProperty());

        PopOver popOver = new PopOver();
//        URL cssURL = this.getClass().getClassLoader().getResource("com/oasis/resources/styles/list_popover.css");
//        popOver.getRoot().getStylesheets().add(cssURL.toExternalForm());

        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
        popOver.setContentNode(ui.getParent());

        popOver.show(addCircleButton, -5);

    }

    public void removeCircleButtonOnAction(ActionEvent actionEvent) {
        Degree selectedDegree = degreeListView.getSelectionModel().getSelectedItem();
        if(selectedDegree != null){
            degreeListView.getItems().remove(selectedDegree);
        }
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.add(tempEmployee);

        EmployeeServices.newEmployee(employeeArrayList);

        UIFactory.launchUI(UIName.EMPLOYEE_MANAGEMENT, true);
    }

    public void employeeRoleComboBoxOnAction(ActionEvent actionEvent) {
        EmployeeRole selectedEmployeeRole = employeeRoleComboBox.getSelectionModel().getSelectedItem();
        if(selectedEmployeeRole != null && selectedEmployeeRole.getRole().equals("Doctor")){
            specialityComboBox.setDisable(false);
        }else {
            specialityComboBox.setDisable(true);
        }
    }
}
