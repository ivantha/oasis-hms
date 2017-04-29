package com.oasis.utils;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller.main.DashboardController;
import com.oasis.factory.UIFactory;
import com.oasis.model.*;
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
            public void itemUpdater(HashMap<Integer, BloodGroup> itemHashMap) {

            }
        };

        Session.degreeCache = new Cache<Degree>() {
            @Override
            public void itemUpdater(HashMap<Integer, Degree> itemHashMap) {

            }
        };

        Session.employeeCache = new Cache<Employee>() {
            @Override
            public void itemUpdater(HashMap<Integer, Employee> itemHashMap) {

            }
        };

        Session.employeeRoleCache = new Cache<EmployeeRole>() {
            @Override
            public void itemUpdater(HashMap<Integer, EmployeeRole> itemHashMap) {

            }
        };

        Session.ethnicityCache = new Cache<Ethnicity>() {
            @Override
            public void itemUpdater(HashMap<Integer, Ethnicity> itemHashMap) {

            }
        };

        Session.specialistBranchCache = new Cache<SpecialistBranch>() {
            @Override
            public void itemUpdater(HashMap<Integer, SpecialistBranch> itemHashMap) {

            }
        };

        Session.testCache = new Cache<Test>() {
            @Override
            public void itemUpdater(HashMap<Integer, Test> itemHashMap) {

            }
        };

        Session.wardCache = new Cache<Ward>() {
            @Override
            public void itemUpdater(HashMap<Integer, Ward> itemHashMap) {

            }
        };
    }

    public static void showLauncher() {
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.showLauncher();
    }
}
