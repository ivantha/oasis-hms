package com.oasis.ui;

import org.jetbrains.annotations.Contract;

public enum UIName {
    DASHBOARD("/com/oasis/ui/view/main/Dashboard.fxml"),

    LAUNCHER("/com/oasis/ui/view/main/Launcher.fxml"),
    EMPLOYEE_SIDEBAR("/com/oasis/ui/view/sidebar/EmployeeSideBar.fxml"),
    PHYSICIAN_SIDEBAR("/com/oasis/ui/view/sidebar/PhysicianSideBar.fxml"),
    PATIENT_SIDEBAR("/com/oasis/ui/view/sidebar/PatientSideBar.fxml"),
    ADMISSION_SIDEBAR("/com/oasis/ui/view/sidebar/AdmissionSideBar.fxml"),
    PAYMENT_SIDEBAR("/com/oasis/ui/view/sidebar/PaymentSideBar.fxml"),
    MEDICAL_SIDEBAR("/com/oasis/ui/view/sidebar/MedicalSideBar.fxml"),
    ADMINISTRATION_SIDEBAR("/com/oasis/ui/view/sidebar/AdministrationSideBar.fxml"),
    REPORTS_SIDEBAR("/com/oasis/ui/view/sidebar/ReportsSideBar.fxml"),
    SETTINGS_SIDEBAR("/com/oasis/ui/view/sidebar/SettingsSideBar.fxml");

    private final String location;

    UIName(String location) {
        this.location = location;
    }

    @Contract(pure = true)
    public String getLocation() {
        return location;
    }
}
