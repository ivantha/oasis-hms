package com.oasis.controller.settings;

import com.oasis.common.Session;
import com.oasis.controller.Controller;
import com.oasis.main.OasisApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsSideBarController implements Controller {
    public Button resetDefaultButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {

    }

    public void resetDefaultButtonOnAction(ActionEvent actionEvent) {
        Session.APP_CONFIG.setDefaultConfig();

        Stage primaryStage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
        primaryStage.close();
        Platform.runLater(() -> {
            try {
                new OasisApplication().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
