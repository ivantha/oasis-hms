package com.oasis.ui;

import org.jetbrains.annotations.Contract;

public enum UIName {
    DASHBOARD("/com/oasis/ui/view/main/Dashboard.fxml"),

    LAUNCHER("/com/oasis/ui/view/main/Launcher.fxml"),
    EMPLOYEE_SIDEBAR("/com/oasis/ui/view/employee/EmployeeSideBar.fxml"),
    PHYSICIAN_SIDEBAR("/com/oasis/ui/view/physician/PhysicianSideBar.fxml"),
    PATIENT_SIDEBAR("/com/oasis/ui/view/patient/PatientSideBar.fxml"),
    ADMISSION_SIDEBAR("/com/oasis/ui/view/admission/AdmissionSideBar.fxml"),
    PAYMENT_SIDEBAR("/com/oasis/ui/view/payment/PaymentSideBar.fxml"),
    MEDICAL_SIDEBAR("/com/oasis/ui/view/medical/MedicalSideBar.fxml"),
    ADMINISTRATION_SIDEBAR("/com/oasis/ui/view/administration/AdministrationSideBar.fxml"),
    REPORTS_SIDEBAR("/com/oasis/ui/view/reports/ReportsSideBar.fxml"),
    SETTINGS_SIDEBAR("/com/oasis/ui/view/settings/SettingsSideBar.fxml");

    private final String location;

    UIName(String location) {
        this.location = location;
    }

    @Contract(pure = true)
    public String getLocation() {
        return location;
    }
}
