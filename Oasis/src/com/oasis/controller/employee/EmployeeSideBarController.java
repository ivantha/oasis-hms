package com.oasis.controller.employee;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.listener.DynamicButtonDragDetectedEventHandler;
import com.oasis.listener.DynamicButtonDragDoneEventHandler;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeSideBarController implements Controller{
    @FXML
    private Button newEmployeeButton;
    @FXML
    private Button employeeManagementButton;
    @FXML
    private Button submitAttendanceButton;
    @FXML
    private Button attendanceManagementButton;
    @FXML
    private Button specialitiesButton;
    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newEmployeeButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_EMPLOYEE));
        newEmployeeButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        employeeManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.EMPLOYEE_MANAGEMENT));
        employeeManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        submitAttendanceButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.SUBMIT_ATTENDANCE));
        submitAttendanceButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        attendanceManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.ATTENDANCE_MANAGEMENT));
        attendanceManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        specialitiesButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.SPECIALITY_MANAGEMENT));
        specialitiesButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newEmployeeButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EMPLOYEE, true);
    }

    @FXML
    public void employeeManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.EMPLOYEE_MANAGEMENT, true);
    }

    @FXML
    public void submitAttendanceButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.SUBMIT_ATTENDANCE, true);
    }

    @FXML
    public void attendanceManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.ATTENDANCE_MANAGEMENT, true);
    }

    @FXML
    public void specialitiesButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.SPECIALITY_MANAGEMENT, true);
    }
}
