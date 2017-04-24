package com.oasis.factory;

import com.oasis.controller.Controller;
import com.oasis.controller.main.DashboardController;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.HashMap;

public class UIFactory {
    private static final HashMap<UIName, UI> UI_HASH_MAP = new HashMap<>();

    public static UI getUI(UIName uiName) {
        if (UIFactory.UI_HASH_MAP.containsKey(uiName)) {
            return UIFactory.UI_HASH_MAP.get(uiName);
        } else {
            try {
                return loadUI(uiName, uiName.getLocation());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void initializeAllUIs() {
        for (UIName uiName : UIName.values()) {
            try {
                loadUI(uiName, uiName.getLocation());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static UI loadUI(UIName uiName, String location) throws IOException {
        System.out.println("Loading UI : " + uiName.name());
        FXMLLoader fxmlLoader = new FXMLLoader(UIFactory.class.getResource(location));
        Parent parent = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        UI ui = new UI(parent, controller);
        UIFactory.UI_HASH_MAP.put(uiName, ui);
        return ui;
    }

    public static void launchUI(UIName uiName) {
        Parent parent = UIFactory.getUI(uiName).getParent();
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.setWorkspace(parent);
    }
}
