package com.oasis.utils;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller.main.DashboardController;
import com.oasis.database.connector.*;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
import com.oasis.ui.UIName;
import javafx.application.Platform;

import java.util.HashMap;

public class SystemFunction {
    public static void start() {
        SystemFunction.initializeSession();

        UIFactory.initializeAllUIs();
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
        Session.specialistBranchConnector = new SpecialistBranchConnector();
        Session.raceConnector = new RaceConnector();
        Session.physicianTelephoneConnector = new PhysicianTelephoneConnector();
        Session.physicianDesignationConnector = new PhysicianDesignationConnector();
        Session.ethnicityConnector = new EthnicityConnector();
        Session.employeeConnector = new EmployeeConnector();
        Session.employeeRoleConnector = new EmployeeRoleConnector();
        Session.degreeConnector = new DegreeConnector();
        Session.bloodGroupConnector = new BloodGroupConnector();

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

        Session.physicianDesignationCache = new Cache<PhysicianDesignation>() {
            @Override
            public void itemAdder(HashMap<Integer, PhysicianDesignation> itemHashMap) {
                itemHashMap.putAll(Session.physicianDesignationConnector.getPhysicianDesignationhashMap());
            }
        };

        Session.raceCache = new Cache<Race>() {
            @Override
            public void itemAdder(HashMap<Integer, Race> itemHashMap) {
                itemHashMap.putAll(Session.raceConnector.getRaceHashMap());
            }
        };

        Session.specialistBranchCache = new Cache<SpecialistBranch>() {
            @Override
            public void itemAdder(HashMap<Integer, SpecialistBranch> itemHashMap) {
                itemHashMap.putAll(Session.specialistBranchConnector.getSpecialistBranchHashMap());
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
    }

    public static void showLauncher() {
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.showLauncher();
    }
}
