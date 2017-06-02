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

    public static UI getNewUI(UIName uiName) {
        try {
            return loadUI(uiName, uiName.getLocation());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
        if (isRefreshed) {
            Controller controller = ui.getController();
            controller.refreshView();
        }
        DashboardController dashboardController = ((DashboardController) (UIFactory.getUI(UIName.DASHBOARD).getController()));
        dashboardController.setWorkspace(parent);
    }

    public static String getUIName(UIName uiName) {
        String name = "";
        switch (uiName) {
            //Physician
            case NEW_PHYSICIAN:
                name = "New physician";
                break;
            case PHYSICIAN_MANAGEMENT:
                name = "Physician";
                break;

            //Admission
            case NEW_EDIT_ADMISSION:
                name = "New admission";
                break;
            case ADMISSION_MANAGEMENT:
                name = "Admission";
                break;
            case NEW_EDIT_TREATMENT:
                name = "New treatment";
                break;
            case TREATMENT_MANAGEMENT:
                name = "Treatments";
                break;

            //Payment
            case PAYMENT_MANAGEMENT:
                name = "Payments";
                break;

            //Patient
            case NEW_ETHNICITY:
                name = "New ethnicity";
                break;
            case ETHNICITY_MANAGEMENT:
                name = "Ethnicity";
                break;
            case NEW_EDIT_PATIENT:
                name = "New patient";
                break;
            case PATIENT_MANAGEMENT:
                name = "Patients";
                break;

            //Employee
            case NEW_SPECIALITY:
                name = "New speciality";
                break;
            case SPECIALITY_MANAGEMENT:
                name = "Specialities";
                break;
            case NEW_EDIT_EMPLOYEE:
                name = "New employee";
                break;
            case EMPLOYEE_MANAGEMENT:
                name = "Employees";
                break;
            case SUBMIT_ATTENDANCE:
                name = "Submit attendance";
                break;
            case ATTENDANCE_MANAGEMENT:
                name = "Attendance";
                break;

            //Medical
            case NEW_TEST:
                name = "New test";
                break;
            case TEST_MANAGEMENT:
                name = "Tests";
                break;

            //Administration
            case NEW_WARD:
                name = "New ward";
                break;
            case WARD_MANAGEMENT:
                name = "Wards";
                break;
        }

        return name;
    }
}
