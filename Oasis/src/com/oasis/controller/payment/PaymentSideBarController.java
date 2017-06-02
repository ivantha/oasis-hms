package com.oasis.controller.payment;

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

public class PaymentSideBarController implements Controller {
    @FXML
    private Button paymentManagementButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.PAYMENT_MANAGEMENT));
        paymentManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void paymentManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.PAYMENT_MANAGEMENT, true);
    }
}
