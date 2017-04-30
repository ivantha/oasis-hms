package com.oasis.utils;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller.main.DashboardController;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
import com.oasis.services.*;
import com.oasis.ui.UIName;
import javafx.application.Platform;

import java.util.HashMap;

public class SystemFunction {
    public static void start() {
        UIFactory.initializeAllUIs();

        SystemFunction.initializeSession();
    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    private static void initializeSession() {
        Session.APP_CONFIG = new ConfigurationFile(new ConfigurationHandler());

        Session.bloodGroupCache = new Cache<BloodGroup>() {
            @Override
            public void itemAdder(HashMap<Integer, BloodGroup> itemHashMap) {
                itemHashMap.putAll(BloodGroupServices.getBloodGroupHashMap());
            }
        };

        Session.degreeCache = new Cache<Degree>() {
            @Override
            public void itemAdder(HashMap<Integer, Degree> itemHashMap) {
                itemHashMap.putAll(DegreeServices.getDegreeHashMap());
            }
        };

        Session.employeeCache = new Cache<Employee>() {
            @Override
            public void itemAdder(HashMap<Integer, Employee> itemHashMap) {
                itemHashMap.putAll(EmployeeServices.getEmployeeHashMap());
            }
        };

        Session.employeeRoleCache = new Cache<EmployeeRole>() {
            @Override
            public void itemAdder(HashMap<Integer, EmployeeRole> itemHashMap) {
                itemHashMap.putAll(EmployeeRoleServices.getEmployeeRoleHashMap());
            }
        };

        Session.ethnicityCache = new Cache<Ethnicity>() {
            @Override
            public void itemAdder(HashMap<Integer, Ethnicity> itemHashMap) {
                itemHashMap.putAll(EthnicityServices.getEthnicityHashMap());
            }
        };

        Session.physicianDesignationCache = new Cache<PhysicianDesignation>() {
            @Override
            public void itemAdder(HashMap<Integer, PhysicianDesignation> itemHashMap) {
                itemHashMap.putAll(PhysicianDesignationServices.getPhysicianDesignationhashMap());
            }
        };

        Session.raceCache = new Cache<Race>() {
            @Override
            public void itemAdder(HashMap<Integer, Race> itemHashMap) {
                itemHashMap.putAll(RaceServices.getRaceHashMap());
            }
        };

        Session.specialistBranchCache = new Cache<SpecialistBranch>() {
            @Override
            public void itemAdder(HashMap<Integer, SpecialistBranch> itemHashMap) {
                itemHashMap.putAll(SpecialistBranchServices.getSpecialistBranchHashMap());
            }
        };

        Session.testCache = new Cache<Test>() {
            @Override
            public void itemAdder(HashMap<Integer, Test> itemHashMap) {
                itemHashMap.putAll(TestServices.getTestHashMap());
            }
        };

        Session.wardCache = new Cache<Ward>() {
            @Override
            public void itemAdder(HashMap<Integer, Ward> itemHashMap) {
                itemHashMap.putAll(WardServices.getWardHashMap());
            }
        };
    }

    public static void showLauncher() {
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.showLauncher();
    }
}
