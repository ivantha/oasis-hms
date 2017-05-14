package com.oasis.controller.employee;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.main.Main;
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

public class NewEditEmployeeController implements Controller {
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
    private boolean isEditingMode = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        try {
            unBindFields(tempEmployee);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempEmployee = new Employee();
        tempEmployee.setId(0);

        isEditingMode = false;
        bindFields(tempEmployee);

        employeeRoleComboBox.setDisable(false);
    }

    public void showEmployee(Employee employee) {
        try {
            unBindFields(tempEmployee);
        } catch (Exception ex) {
            //This does not need to be handled
        }

        tempEmployee = employee;

        isEditingMode = true;
        bindFields(employee);

        employeeRoleComboBox.setDisable(true);
        loadProfilePicture(employee.getId());
    }

    private void unBindFields(Employee employee) {
        nicTextField.textProperty().unbindBidirectional(employee.nicProperty());
        firstNameTextField.textProperty().unbindBidirectional(employee.firstNameProperty());
        middleNameTextField.textProperty().unbindBidirectional(employee.middleNameProperty());
        lastNameTextField.textProperty().unbindBidirectional(employee.lastNameProperty());
        genderComboBox.valueProperty().unbindBidirectional(employee.genderProperty());
        dobDatePicker.valueProperty().unbindBidirectional(employee.dobProperty());
        startDateDatePicker.valueProperty().unbindBidirectional(employee.startDateProperty());
        endDateDatePicker.valueProperty().unbindBidirectional(employee.endDateProperty());
        employeeRoleComboBox.valueProperty().unbindBidirectional(employee.employeeRoleProperty());
        shiftStartTimeTimePicker.valueProperty().unbindBidirectional(employee.defaultShiftStartProperty());
        shiftEndTimeTimePicker.valueProperty().unbindBidirectional(employee.defaultShiftEndProperty());

        mondayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().mondayProperty());
        tuesdayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().tuesdaysProperty());
        wednesdayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().wednesdayProperty());
        thursdayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().thursdayProperty());
        fridayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().fridayProperty());
        saturdayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().saturdayProperty());
        sundayCheckBox.selectedProperty().unbindBidirectional(employee.getWorkingDays().sundayProperty());

        telephoneTextField.textProperty().unbindBidirectional(employee.getTelephoneArrayList().get(0).telephoneProperty());
        emailTextField.textProperty().unbindBidirectional(employee.getEmailArrayList().get(0).emailProperty());

        streetTextField.textProperty().unbindBidirectional(employee.getAddressArrayList().get(0).streetProperty());
        townTextField.textProperty().unbindBidirectional(employee.getAddressArrayList().get(0).townProperty());
        provinceTextField.textProperty().unbindBidirectional(employee.getAddressArrayList().get(0).provinceProperty());
        postalCodeTextField.textProperty().unbindBidirectional(employee.getAddressArrayList().get(0).postalCodeProperty());

        degreeListView.itemsProperty().unbindBidirectional(employee.degreeListPropertyProperty());
    }

    private void bindFields(Employee employee) {
        nicTextField.textProperty().bindBidirectional(employee.nicProperty());
        firstNameTextField.textProperty().bindBidirectional(employee.firstNameProperty());
        middleNameTextField.textProperty().bindBidirectional(employee.middleNameProperty());
        lastNameTextField.textProperty().bindBidirectional(employee.lastNameProperty());
        genderComboBox.valueProperty().bindBidirectional(employee.genderProperty());
        dobDatePicker.valueProperty().bindBidirectional(employee.dobProperty());
        startDateDatePicker.valueProperty().bindBidirectional(employee.startDateProperty());
        endDateDatePicker.valueProperty().bindBidirectional(employee.endDateProperty());
        employeeRoleComboBox.valueProperty().bindBidirectional(employee.employeeRoleProperty());
        shiftStartTimeTimePicker.valueProperty().bindBidirectional(employee.defaultShiftStartProperty());
        shiftEndTimeTimePicker.valueProperty().bindBidirectional(employee.defaultShiftEndProperty());

        mondayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().mondayProperty());
        tuesdayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().tuesdaysProperty());
        wednesdayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().wednesdayProperty());
        thursdayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().thursdayProperty());
        fridayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().fridayProperty());
        saturdayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().saturdayProperty());
        sundayCheckBox.selectedProperty().bindBidirectional(employee.getWorkingDays().sundayProperty());

        if(tempEmployee.getTelephoneArrayList().isEmpty()) {
            tempEmployee.getTelephoneArrayList().add(new Telephone());
        }
        if(tempEmployee.getEmailArrayList().isEmpty()) {
            tempEmployee.getEmailArrayList().add(new Email());
        }
        if(tempEmployee.getAddressArrayList().isEmpty()) {
            tempEmployee.getAddressArrayList().add(new Address());
        }

        telephoneTextField.textProperty().bindBidirectional(employee.getTelephoneArrayList().get(0).telephoneProperty());
        emailTextField.textProperty().bindBidirectional(employee.getEmailArrayList().get(0).emailProperty());

        streetTextField.textProperty().bindBidirectional(employee.getAddressArrayList().get(0).streetProperty());
        townTextField.textProperty().bindBidirectional(employee.getAddressArrayList().get(0).townProperty());
        provinceTextField.textProperty().bindBidirectional(employee.getAddressArrayList().get(0).provinceProperty());
        postalCodeTextField.textProperty().bindBidirectional(employee.getAddressArrayList().get(0).postalCodeProperty());

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

        degreeListView.itemsProperty().bindBidirectional(employee.degreeListPropertyProperty());
    }

    public void browseButtonOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));

        File source = fileChooser.showOpenDialog(null);

        if (source != null) {
            File dest;
            if (!isEditingMode) {
                dest = new File(System.getProperty("user.dir"), "temp\\pp_new_" + tempEmployee.getId() + ".jpg");
            } else {
                dest = new File(System.getProperty("user.dir"), "temp\\pp_changed_" + tempEmployee.getId() + ".jpg");
            }

            try {
                FileUtils.copyFile(source, dest);

                Image profilePictureImage = new Image(dest.toURI().toString());
                profilePictureImageView.setImage(profilePictureImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        if (selectedDegree != null) {
            degreeListView.getItems().remove(selectedDegree);
        }
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.add(tempEmployee);
        if (!isEditingMode) {
            EmployeeServices.newEmployee(employeeArrayList);
        } else {
            EmployeeServices.updateEmployee(employeeArrayList);
        }
        UIFactory.launchUI(UIName.EMPLOYEE_MANAGEMENT, true);
    }

    public void employeeRoleComboBoxOnAction(ActionEvent actionEvent) {
        EmployeeRole selectedEmployeeRole = employeeRoleComboBox.getSelectionModel().getSelectedItem();
        if (selectedEmployeeRole != null && selectedEmployeeRole.isDoctor()) {
            specialityComboBox.setDisable(false);
        } else {
            specialityComboBox.setDisable(true);
        }
    }

    private void loadProfilePicture(int employeeId) {
        File savedImage = new File(System.getProperty("user.dir") + "/profile_pictures/", "pp_" + employeeId + ".jpg");
        Image profilePictureImage = new Image(Main.class.getResourceAsStream("/com/oasis/resources/images/default_profile_picture.png"));
        if (savedImage.exists()) {
            profilePictureImage = new Image(savedImage.toURI().toString());
        }
        profilePictureImageView.setImage(profilePictureImage);
    }
}
