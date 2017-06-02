package com.oasis.controller._employee;

import com.oasis.controller.PopOverController;
import com.oasis.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDetailsPopOverController implements PopOverController<Employee> {
    @FXML
    private Label idLabel;
    @FXML
    private Label nicLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label middleNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label roleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    @Override
    public void refreshView(Employee employee) {
        idLabel.setText(String.valueOf(employee.getId()));
        nicLabel.setText(employee.getNic());
        firstNameLabel.setText(employee.getFirstName());
        middleNameLabel.setText(employee.getMiddleName());
        lastNameLabel.setText(employee.getLastName());
        genderLabel.setText(employee.getGender().getName());
        roleLabel.setText(employee.getEmployeeRole().getRole());
    }
}
