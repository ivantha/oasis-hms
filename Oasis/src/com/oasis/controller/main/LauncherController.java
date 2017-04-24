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
        setTilePressed(employeeTile);
        setLauncherSideBar(UIName.EMPLOYEE_SIDEBAR);
    }

    @FXML
    public void employeeTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(employeeTile);
        setLauncherSideBar(UIName.EMPLOYEE_SIDEBAR);
    }

    @FXML
    public void physicianTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(physicianTile);
        setLauncherSideBar(UIName.PHYSICIAN_SIDEBAR);
    }

    @FXML
    public void patientTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(patientTile);
        setLauncherSideBar(UIName.PATIENT_SIDEBAR);
    }

    @FXML
    public void admissionTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(admissionTile);
        setLauncherSideBar(UIName.ADMISSION_SIDEBAR);
    }

    @FXML
    public void paymentTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(paymentTile);
        setLauncherSideBar(UIName.PAYMENT_SIDEBAR);
    }

    @FXML
    public void medicalTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(medicalTile);
        setLauncherSideBar(UIName.MEDICAL_SIDEBAR);
    }

    @FXML
    public void administrationTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(administrationTile);
        setLauncherSideBar(UIName.ADMINISTRATION_SIDEBAR);
    }

    @FXML
    public void reportsTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(reportsTile);
        setLauncherSideBar(UIName.REPORTS_SIDEBAR);
    }

    @FXML
    public void optionsTileOnMouseClicked(MouseEvent mouseEvent) {
        setTilePressed(settingsTile);
        setLauncherSideBar(UIName.SETTINGS_SIDEBAR);
    }

    private void setTilePressed(AnchorPane tile) {
        lastPressedTile.setStyle("-fx-background-color: transparent;");

        tile.setStyle("-fx-background-color: #16232c;");
        lastPressedTile = tile;
    }

    private void setLauncherSideBar(UIName uiName){
        UI ui = UIFactory.getUI(uiName);
        this.launcherSideBar.getChildren().clear();
        this.launcherSideBar.getChildren().add(ui.getParent());
    }
}
