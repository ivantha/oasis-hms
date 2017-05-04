package com.oasis.controller.administration;

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

public class AdministrationSideBarController implements Controller {
    @FXML
    private Button newWardButton;
    @FXML
    private Button wardManagementButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newWardButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_WARD));
        newWardButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        wardManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.WARD_MANAGEMENT));
        wardManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    public void newWardButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_WARD, true);
    }

    public void wardManagementButtonAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.WARD_MANAGEMENT, true);
    }
}
