package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller.main.DashboardController;
import com.oasis.database.connector.*;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import com.oasis.ui.component.Notification;
import com.oasis.ui.component.NotificationType;
import com.oasis.ui.utils.UIUtils;
import com.oasis.utils.Cache;
import com.rits.cloning.Cloner;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexedCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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

        Session.bloodGroupCache = new Cache<BloodGroup>() {
            @Override
            public void itemAdder(HashMap<Integer, BloodGroup> itemHashMap) {
                itemHashMap.putAll(Session.bloodGroupConnector.getBloodGroupHashMap());
            }
        };

        Session.degreeCache = new Cache<Degree>() {
            @Override
            public void itemAdder(HashMap<Integer, Degree> itemHashMap) {
                itemHashMap.putAll(Session.degreeConnector.getDegreeHashMap());
            }
        };

        Session.employeeCache = new Cache<Employee>() {
            @Override
            public void itemAdder(HashMap<Integer, Employee> itemHashMap) {
                itemHashMap.putAll(Session.employeeConnector.getEmployeeHashMap());
            }
        };

        Session.employeeRoleCache = new Cache<EmployeeRole>() {
            @Override
            public void itemAdder(HashMap<Integer, EmployeeRole> itemHashMap) {
                itemHashMap.putAll(Session.employeeRoleConnector.getEmployeeRoleHashMap());
            }
        };

        Session.ethnicityCache = new Cache<Ethnicity>() {
            @Override
            public void itemAdder(HashMap<Integer, Ethnicity> itemHashMap) {
                itemHashMap.putAll(Session.ethnicityConnector.getEthnicityHashMap());
            }
        };

        Session.genderCache = new Cache<Gender>() {
            @Override
            public void itemAdder(HashMap<Integer, Gender> itemHashMap) {
            }
        };
        Session.genderCache.getItemHashMap().put(1, new Gender(1, "m"));
        Session.genderCache.getItemHashMap().put(2, new Gender(2, "f"));

        Session.physicianDesignationCache = new Cache<PhysicianDesignation>() {
            @Override
            public void itemAdder(HashMap<Integer, PhysicianDesignation> itemHashMap) {
                itemHashMap.putAll(Session.physicianConnector.getPhysicianDesignationhashMap());
            }
        };

        Session.specialityCache = new Cache<Speciality>() {
            @Override
            public void itemAdder(HashMap<Integer, Speciality> itemHashMap) {
                itemHashMap.putAll(Session.specialityConnector.getSpecialityHashMap());
            }
        };

        Session.testCache = new Cache<Test>() {
            @Override
            public void itemAdder(HashMap<Integer, Test> itemHashMap) {
                itemHashMap.putAll(Session.testConnector.getTestHashMap());
            }
        };

        Session.wardCache = new Cache<Ward>() {
            @Override
            public void itemAdder(HashMap<Integer, Ward> itemHashMap) {
                itemHashMap.putAll(Session.wardConnector.getWardHashMap());
            }
        };

        Session.cloner = new Cloner();

        Session.notificationObservableList = FXCollections.observableList(new ArrayList<>());
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
            sideButton.setText("                " + UIUtils.getUIName(uiName));
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

    public static void loadLogin(Stage primaryStage, BooleanProperty ready){
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

    public static void loadDashboard(Stage primaryStage){
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

    public static void addNotification(String heading, String content, NotificationType notificationType){

    }

    public static void removeNotification(Notification notification){
        int cellIndex = Session.notificationObservableList.indexOf(notification);

        DashboardController controller = (DashboardController) UIFactory.getUI(UIName.DASHBOARD).getController();
        IndexedCell<Notification> indexedCell = controller.getNotificationCell(cellIndex);

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(300));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setNode(indexedCell);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300));
        translateTransition.setFromX(0);
        translateTransition.setToX(175);
        translateTransition.setNode(indexedCell);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
        parallelTransition.setOnFinished(event -> {
            Session.notificationObservableList.remove(notification);
            indexedCell.setOpacity(1.0);
            indexedCell.setTranslateX(0.0);
        });
        parallelTransition.play();
    }
}
