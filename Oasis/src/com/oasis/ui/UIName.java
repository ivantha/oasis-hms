package com.oasis.ui;

import org.jetbrains.annotations.Contract;

public enum UIName {
    LOGIN("/com/oasis/ui/view/main/Login.fxml", "login"),
    DASHBOARD("/com/oasis/ui/view/main/Dashboard.fxml", "dashboard"),

    //Sidebar
    LAUNCHER("/com/oasis/ui/view/main/Launcher.fxml", "launcher"),
    EMPLOYEE_SIDEBAR("/com/oasis/ui/view/employee/EmployeeSideBar.fxml", "launcher_sidebar"),
    PHYSICIAN_SIDEBAR("/com/oasis/ui/view/physician/PhysicianSideBar.fxml", "launcher_sidebar"),
    PATIENT_SIDEBAR("/com/oasis/ui/view/patient/PatientSideBar.fxml", "launcher_sidebar"),
    ADMISSION_SIDEBAR("/com/oasis/ui/view/admission/AdmissionSideBar.fxml", "launcher_sidebar"),
    PAYMENT_SIDEBAR("/com/oasis/ui/view/payment/PaymentSideBar.fxml", "launcher_sidebar"),
    MEDICAL_SIDEBAR("/com/oasis/ui/view/medical/MedicalSideBar.fxml", "launcher_sidebar"),
    ADMINISTRATION_SIDEBAR("/com/oasis/ui/view/administration/AdministrationSideBar.fxml", "launcher_sidebar"),
    REPORTS_SIDEBAR("/com/oasis/ui/view/reports/ReportsSideBar.fxml", "launcher_sidebar"),
    SETTINGS_SIDEBAR("/com/oasis/ui/view/settings/SettingsSideBar.fxml", "launcher_sidebar"),

    //Physician
    NEW_PHYSICIAN("/com/oasis/ui/view/physician/NewPhysician.fxml", "new_edit_window"),
    PHYSICIAN_MANAGEMENT("/com/oasis/ui/view/physician/PhysicianManagement.fxml", "management_edit_window"),
    PHYSICIAN_DETAILS_POPOVER("/com/oasis/ui/view/physician/PhysicianDetailsPopOver.fxml", "details_popover"),

    //Admission
    NEW_EDIT_ADMISSION("/com/oasis/ui/view/admission/NewEditAdmission.fxml", "new_edit_window"),
    ADMISSION_MANAGEMENT("/com/oasis/ui/view/admission/AdmissionManagement.fxml", "management_edit_window"),
    NEW_EDIT_TREATMENT("/com/oasis/ui/view/admission/NewEditTreatment.fxml", "new_edit_window"),
    TREATMENT_MANAGEMENT("/com/oasis/ui/view/admission/TreatmentManagement.fxml", "management_edit_window"),

    //Payment
    PAYMENT_MANAGEMENT("/com/oasis/ui/view/payment/PaymentManagement.fxml", "management_edit_window"),

    //Patient
    NEW_ETHNICITY("/com/oasis/ui/view/patient/NewEthnicity.fxml", "new_edit_window"),
    ETHNICITY_MANAGEMENT("/com/oasis/ui/view/patient/EthnicityManagement.fxml", "management_edit_window"),
    NEW_EDIT_PATIENT("/com/oasis/ui/view/patient/NewEditPatient.fxml", "new_edit_window"),
    PATIENT_MANAGEMENT("/com/oasis/ui/view/patient/PatientManagement.fxml", "management_edit_window"),
    PATIENT_DETAILS_POPOVER("/com/oasis/ui/view/patient/PatientDetailsPopOver.fxml", "details_popover"),

    //Employee
    NEW_SPECIALITY("/com/oasis/ui/view/employee/NewSpeciality.fxml", "new_edit_window"),
    SPECIALITY_MANAGEMENT("/com/oasis/ui/view/employee/SpecialityManagement.fxml", "management_edit_window"),
    NEW_EDIT_EMPLOYEE("/com/oasis/ui/view/employee/NewEditEmployee.fxml", "new_edit_window"),
    EMPLOYEE_MANAGEMENT("/com/oasis/ui/view/employee/EmployeeManagement.fxml", "management_edit_window"),
    SUBMIT_ATTENDANCE("/com/oasis/ui/view/employee/SubmitAttendance.fxml", "submit_attendance"),
    ATTENDANCE_MANAGEMENT("/com/oasis/ui/view/employee/AttendanceManagement.fxml", "attendance_management"),
    DEGREE_LIST_POPOVER("/com/oasis/ui/view/employee/DegreeListPopOver.fxml", "list_popover"),
    EMPLOYEE_DETAILS_POPOVER("/com/oasis/ui/view/employee/EmployeeDetailsPopOver.fxml", "details_popover"),

    //Medical
    NEW_TEST("/com/oasis/ui/view/medical/NewTest.fxml", "new_edit_window"),
    TEST_MANAGEMENT("/com/oasis/ui/view/medical/TestManagement.fxml", "management_edit_window"),

    //Administration
    NEW_WARD("/com/oasis/ui/view/administration/NewWard.fxml", "new_edit_window"),
    WARD_MANAGEMENT("/com/oasis/ui/view/administration/WardManagement.fxml", "management_edit_window");

    private final String location;
    private final String style;

    UIName(String location, String style) {
        this.location = location;
        this.style = style;
    }

    @Contract(pure = true)
    public String getLocation() {
        return location;
    }

    @Contract(pure = true)
    public String getStyle() {
        return style;
    }
}
