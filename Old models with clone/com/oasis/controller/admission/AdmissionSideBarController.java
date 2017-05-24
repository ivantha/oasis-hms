package com.oasis.controller.admission;

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

public class AdmissionSideBarController implements Controller{
    @FXML
    private Button newAdmissionButton;
    @FXML
    private Button admissionManagementButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newAdmissionButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.NEW_EDIT_ADMISSION));
        newAdmissionButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
        admissionManagementButton.setOnDragDetected(new DynamicButtonDragDetectedEventHandler(UIName.ADMISSION_MANAGEMENT));
        admissionManagementButton.setOnDragDone(new DynamicButtonDragDoneEventHandler());
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newAdmissionButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_EDIT_ADMISSION, true);
    }

    @FXML
    public void admissionManagementButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.ADMISSION_MANAGEMENT, true);
    }
}
