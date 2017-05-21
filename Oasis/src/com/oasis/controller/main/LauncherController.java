package com.oasis.controller.main;


import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LauncherController implements Controller {
    @FXML
    private AnchorPane employeeTile;
    @FXML
    private AnchorPane physicianTile;
    @FXML
    private AnchorPane patientTile;
    @FXML
    private AnchorPane admissionTile;
    @FXML
    private AnchorPane paymentTile;
    @FXML
    private AnchorPane medicalTile;
    @FXML
    private AnchorPane administrationTile;
    @FXML
    private AnchorPane reportsTile;
    @FXML
    private AnchorPane settingsTile;
    @FXML
    private AnchorPane launcherSideBar;

    private AnchorPane lastPressedTile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastPressedTile = employeeTile;
        setTilePressed(employeeTile, "com/oasis/resources/icons/employee_black.png");
        setLauncherSideBar(UIName.EMPLOYEE_SIDEBAR);
    }

    @Override
    public void refreshView() {

    }

    @FXML
    public void employeeTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(employeeTile, "com/oasis/resources/icons/employee_black.png");
        setLauncherSideBar(UIName.EMPLOYEE_SIDEBAR);
    }

    @FXML
    public void physicianTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(physicianTile, "com/oasis/resources/icons/physician_black.png");
        setLauncherSideBar(UIName.PHYSICIAN_SIDEBAR);
    }

    @FXML
    public void patientTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(patientTile, "com/oasis/resources/icons/patient_black.png");
        setLauncherSideBar(UIName.PATIENT_SIDEBAR);
    }

    @FXML
    public void admissionTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(admissionTile, "com/oasis/resources/icons/admission_black.png");
        setLauncherSideBar(UIName.ADMISSION_SIDEBAR);
    }

    @FXML
    public void paymentTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(paymentTile, "com/oasis/resources/icons/payment_black.png");
        setLauncherSideBar(UIName.PAYMENT_SIDEBAR);
    }

    @FXML
    public void medicalTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(medicalTile, "com/oasis/resources/icons/medical_black.png");
        setLauncherSideBar(UIName.MEDICAL_SIDEBAR);
    }

    @FXML
    public void administrationTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(administrationTile, "com/oasis/resources/icons/administration_black.png");
        setLauncherSideBar(UIName.ADMINISTRATION_SIDEBAR);
    }

    @FXML
    public void reportsTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(reportsTile, "com/oasis/resources/icons/reports_black.png");
        setLauncherSideBar(UIName.REPORTS_SIDEBAR);
    }

    @FXML
    public void optionsTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(settingsTile, "com/oasis/resources/icons/settings_black.png");
        setLauncherSideBar(UIName.SETTINGS_SIDEBAR);
    }

    private void setTilePressed(AnchorPane tile, String blackIconPath) {
        lastPressedTile.setStyle(null);
        lastPressedTile.getChildren().get(0).setStyle(null);
        lastPressedTile.getChildren().get(1).setStyle(null);

        tile.setStyle("-fx-background-color: #d27d1e;");
        tile.getChildren().get(0).setStyle("-fx-background-image: url('" + blackIconPath + "');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 50% 50%;");
        tile.getChildren().get(1).setStyle("-fx-text-fill: #000000");
        lastPressedTile = tile;
    }

    private void setLauncherSideBar(UIName uiName) {
        UI ui = UIFactory.getUI(uiName);
        this.launcherSideBar.getChildren().clear();
        this.launcherSideBar.getChildren().add(ui.getParent());
    }
}