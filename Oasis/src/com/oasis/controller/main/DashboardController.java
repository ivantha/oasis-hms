package com.oasis.controller.main;

import com.oasis.common.Session;
import com.oasis.controller.Controller;
import com.oasis.controller.physician.ExternalPhysicianManagementController;
import com.oasis.factory.UIFactory;
import com.oasis.model.Physician;
import com.oasis.services.PhysicianServices;
import com.oasis.ui.UIName;
import com.oasis.utils.Cache;
import com.oasis.utils.SystemFunction;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardController implements Controller {
    @FXML
    public Button category1Button;
    @FXML
    public Button category2Button;
    @FXML
    public Button category3Button;
    @FXML
    public Button category4Button;
    @FXML
    public Button category5Button;
    @FXML

    private boolean isLauncherVisible = false;
    private Parent launcherParent;

    @FXML
    private AnchorPane sideBarAnchorPane;
    @FXML
    private AnchorPane workspaceAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        category1Button.setOnAction(event -> {
            UIFactory.launchUI(UIName.PHYSICIAN_MANAGEMENT, true);
        });

        category2Button.setOnAction(event -> {
            UIFactory.launchUI(UIName.NEW_PHYSICIAN, true);
        });

        category3Button.setOnAction(event -> {
            Session.physicianDesignationCache.getItemHashMap().get(1).setName("car");
        });

        category4Button.setOnAction(event -> {

        });

        category5Button.setOnAction(event -> {

        });
    }

    @Override
    public void refreshView() {

    }

    public void closeButtonOnAction(ActionEvent actionEvent) {
        SystemFunction.exit();
    }

    public void minimizeButtonOnAction(ActionEvent actionEvent) {
        ((Stage) ((Button) (actionEvent.getSource())).getScene().getWindow()).setIconified(true);
    }

    public boolean isLauncherVisible() {
        return isLauncherVisible;
    }

    public void setLauncherVisible(boolean launcherVisible) {
        isLauncherVisible = launcherVisible;
    }

    public void showLauncher() {
        if (!isLauncherVisible()) {
            slideInLauncher();
        } else {
            slideOutLauncher(event -> {
                this.workspaceAnchorPane.getChildren().remove(launcherParent);
            });
        }
    }

    private void slideInLauncher() {
        launcherParent = UIFactory.getUI(UIName.LAUNCHER).getParent();
        this.workspaceAnchorPane.getChildren().add(launcherParent);

        final Duration TIME_SEC = Duration.millis(300);

        TranslateTransition translateTransition = new TranslateTransition(TIME_SEC);
        translateTransition.setFromX(0 - 450);
        translateTransition.setFromY(700 - 300);
        translateTransition.setToX(100);
        translateTransition.setToY(50);

        ScaleTransition scaleTransition = new ScaleTransition(TIME_SEC);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        ParallelTransition pt = new ParallelTransition(launcherParent, translateTransition, scaleTransition);
        pt.play();

        setLauncherVisible(true);
    }

    private void slideOutLauncher(EventHandler<ActionEvent> eventEventHandler) {
        final Duration TIME_SEC = Duration.millis(300);

        TranslateTransition translateTransition = new TranslateTransition(TIME_SEC);
        translateTransition.setFromX(100);
        translateTransition.setFromY(50);
        translateTransition.setToX(0 - 450);
        translateTransition.setToY(700 - 300);

        ScaleTransition scaleTransition = new ScaleTransition(TIME_SEC);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);

        ParallelTransition pt = new ParallelTransition(launcherParent, translateTransition, scaleTransition);
        pt.setOnFinished(eventEventHandler);
        pt.play();

        setLauncherVisible(false);
    }

    public void setWorkspace(Parent parent) {
        if (isLauncherVisible()) {
            EventHandler<ActionEvent> eventEventHandler = event -> {
                this.workspaceAnchorPane.getChildren().clear();
                this.workspaceAnchorPane.getChildren().add(parent);
            };
            slideOutLauncher(eventEventHandler);
        } else {
            this.workspaceAnchorPane.getChildren().clear();
            this.workspaceAnchorPane.getChildren().add(parent);
        }
    }
}