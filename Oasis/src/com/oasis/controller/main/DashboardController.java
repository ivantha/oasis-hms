package com.oasis.controller.main;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UIName;
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
import java.util.ResourceBundle;

public class DashboardController implements Controller {
    private boolean isLauncherVisible = false;
    private Parent launcherParent;

    @FXML
    private AnchorPane sideBarAnchorPane;
    @FXML
    private AnchorPane workspaceAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
            setLauncherVisible(true);
        } else {
            slideOutLauncher();
            setLauncherVisible(false);
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
    }

    private void slideOutLauncher() {
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
        pt.setOnFinished(event -> {
            this.workspaceAnchorPane.getChildren().remove(launcherParent);
        });
        pt.play();
    }

    public void setWorkspace(Parent parent) {
        this.workspaceAnchorPane.getChildren().clear();
        this.workspaceAnchorPane.getChildren().add(parent);
    }
}