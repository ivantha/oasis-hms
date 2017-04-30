package com.oasis.controller.payment;

import com.oasis.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentSideBarController implements Controller{
    @FXML
    private Button newPaymentButton;
    @FXML
    private Button paymentManagementButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newPaymentButtonOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void paymentManagementButtonOnAction(ActionEvent actionEvent) {

    }
}
