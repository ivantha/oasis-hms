package com.oasis.factory;

import com.oasis.common.Session;
import com.oasis.controller.Controller;
import com.oasis.controller.main.DashboardController;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
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
                System.out.println("UI initialization error : " + uiName.name());
                e.printStackTrace();
            }
        }
    }

    private static UI loadUI(UIName uiName, String location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UIFactory.class.getResource(location));
        Parent parent = fxmlLoader.load();
        URL cssURL = UIFactory.class.getClassLoader().getResource("com/oasis/resources/styles/" +
                Session.APP_CONFIG.getTheme() + "/" + uiName.getStyle() + ".css");
        parent.setStyle(cssURL.toExternalForm());
        Controller controller = fxmlLoader.getController();

        UI ui = new UI(parent, controller);
        UIFactory.UI_HASH_MAP.put(uiName, ui);

        return ui;
    }

    public static void launchUI(UIName uiName, boolean isRefreshed) {
        UI ui = UIFactory.getUI(uiName);
        Parent parent = ui.getParent();
        if(isRefreshed) {
            Controller controller = ui.getController();
            controller.refreshView();
        }
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.setWorkspace(parent);
    }
}
