package com.oasis.controller.administration;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
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

    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void newWardButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_WARD, true);
    }

    @FXML
    public void wardManagementButtonAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.WARD_MANAGEMENT, true);
    }
}
