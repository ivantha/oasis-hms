package com.oasis.controller.main;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oasis.common.Session;
import com.oasis.controller.Controller;
import com.oasis.model.Employee;
import com.oasis.services.UserServices;
import com.oasis.services.SystemServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller{
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField passwordPasswordField;
    @FXML
    private Label messageLabel;

    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button signInButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setVisible(false);

        usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> messageLabel.setVisible(false));
        passwordPasswordField.textProperty().addListener((observable, oldValue, newValue) -> messageLabel.setVisible(false));
    }

    @Override
    public void refreshView() {

    }

    public void closeButtonOnAction(ActionEvent actionEvent) {
        SystemServices.exit();
    }

    public void minimizeButtonOnAction(ActionEvent actionEvent) {
        ((Stage) ((Button) (actionEvent.getSource())).getScene().getWindow()).setIconified(true);
    }

    public void signInButtonOnAction(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        Employee employee = UserServices.getUser(username);

        if(null == employee){
            messageLabel.setText("Incorrect username");
            messageLabel.setVisible(true);
        }else if(!employee.getPassword().equals(password)){
            messageLabel.setText("Incorrect password");
            messageLabel.setVisible(true);
        }else{
            Session.currentUser = employee;

            Stage primaryStage = (Stage) signInButton.getScene().getWindow();
            SystemServices.loadDashboard(primaryStage);
        }
    }
}
