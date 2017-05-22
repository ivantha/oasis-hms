package com.oasis.utils;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller.main.DashboardController;
import com.oasis.database.connector.*;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
import com.oasis.ui.UIName;
import com.oasis.ui.utils.UIUtils;
import com.rits.cloning.Cloner;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.HashMap;

public class SystemFunction {
    public static void start() {
        SystemFunction.initializeSession();
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
            sideButton.setText(UIUtils.getUIName(uiName));
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

}
