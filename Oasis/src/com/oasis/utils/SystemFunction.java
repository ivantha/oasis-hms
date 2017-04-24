package com.oasis.utils;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.controller.main.DashboardController;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UIName;
import javafx.application.Platform;

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
    }

    public static void showLauncher() {
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.showLauncher();
    }
}
