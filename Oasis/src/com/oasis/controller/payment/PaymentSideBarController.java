package com.oasis.controller.payment;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UIName;
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
        UIFactory.launchUI(UIName.NEW_PAYMENT, true);
    }

    @FXML
    public void paymentManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.PAYMENT_MANAGEMENT, true);
    }
}
