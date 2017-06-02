package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller._main.DashboardController;
import com.oasis.database.connector.*;
import com.oasis.factory.CacheFactory;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import com.rits.cloning.Cloner;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

public class SystemServices {
    public static void start() {
        SystemServices.initializeSession();
        UIFactory.initializeAllUIs();

        createDefaultDirectories();
    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    private static void initializeSession() {
        Session.APP_CONFIG = new ConfigurationFile(new ConfigurationHandler());

        Session.physicianConnector = new PhysicianConnector();
        Session.wardConnector = new WardConnector();
        Session.testConnector = new TestConnector();
        Session.specialityConnector = new SpecialityConnector();
        Session.ethnicityConnector = new EthnicityConnector();
        Session.employeeConnector = new EmployeeConnector();
        Session.employeeRoleConnector = new EmployeeRoleConnector();
        Session.degreeConnector = new DegreeConnector();
        Session.bloodGroupConnector = new BloodGroupConnector();
        Session.patientConnector = new PatientConnector();
        Session.admissionConnector = new AdmissionConnector();
        Session.chargeConnector = new ChargeConnector();
        Session.paymentConnector = new PaymentConnector();
        Session.treatmentConnector = new TreatmentConnector();
        Session.userConnector = new UserConnector();

        CacheFactory.initializeAllCaches();

        Session.cloner = new Cloner();
    }

    private static void createDefaultDirectories() {
        File tempDirectory = new File(System.getProperty("user.dir"), "temp");
        if (!tempDirectory.exists()) {
            tempDirectory.mkdir();
        }

        File profilePicturesDirectory = new File(System.getProperty("user.dir"), "profile_pictures");
        if (!profilePicturesDirectory.exists()) {
            profilePicturesDirectory.mkdir();
        }
    }

    public static void loadDynamicButton(AnchorPane tabButtonAnchorPane, String name, ObjectProperty<Button> lastPressedMainSideButton) {
        if (!name.equals("")) {
            Button sideButton = new Button();
            sideButton.setPrefWidth(200);
            sideButton.setPrefHeight(40);
            sideButton.getStyleClass().add("tabButton");

            UIName uiName = UIName.valueOf(name);
            sideButton.setText("                " + UIFactory.getUIName(uiName));
            sideButton.getStyleClass().add(uiName.name());
            sideButton.setOnAction(event1 -> {
                UIFactory.launchUI(uiName, true);
                lastPressedMainSideButton.setValue(sideButton);
            });

            tabButtonAnchorPane.getChildren().clear();
            tabButtonAnchorPane.getChildren().add(sideButton);
        }
    }

    public static void showLauncher() {
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.showLauncher();
    }

    public static void cleanTemp(String prefix) {
        File tempDirectory = new File(System.getProperty("user.dir"), "temp");
        for (File file : tempDirectory.listFiles()) {
            if (file.getName().startsWith(prefix)) {
                file.delete();
            }
        }
    }

    public static void loadLogin(Stage primaryStage, BooleanProperty ready) {
        Session.currentUser = null;

        UI ui = UIFactory.getNewUI(UIName.LOGIN);
        Parent parent = ui.getParent();
        Scene scene = new Scene(parent, 1300, 700);

        primaryStage.setScene(scene);

        ready.addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) {
                Platform.runLater(() -> primaryStage.show());
            }
        });
    }

    public static void loadDashboard(Stage primaryStage) {
        UI ui = UIFactory.getNewUI(UIName.DASHBOARD);
        ui.getController().refreshView();
        Scene scene = new Scene(ui.getParent(), 1300, 700);
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                SystemServices.showLauncher();
            } else if (event.getCode() == KeyCode.Q) {
                SystemServices.exit();
            }
        });

        primaryStage.setScene(scene);

        Platform.runLater(() -> primaryStage.show());
    }
}
