package com.oasis.ui.utils;

import com.oasis.ui.UIName;

public class UIUtils {
    public static String getUIName(UIName uiName){
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
