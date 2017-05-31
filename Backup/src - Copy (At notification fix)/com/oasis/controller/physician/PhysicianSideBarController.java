package com.oasis.controller.physician;

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

public class PhysicianSideBarController implements Controller {
    @FXML
    private Button newPhysicianButton;
    @FXML
    private Button physicianManagementButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newPhysicianButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_PHYSICIAN));
        newPhysicianButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        physicianManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.PHYSICIAN_MANAGEMENT));
        physicianManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newPhysicianButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_PHYSICIAN, true);
    }

    @FXML
    public void physicianManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.PHYSICIAN_MANAGEMENT, true);
    }
}
