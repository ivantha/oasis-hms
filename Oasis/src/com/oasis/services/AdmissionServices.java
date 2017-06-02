package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Patient;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class AdmissionServices {
    public static ArrayList<Admission> getAdmissionArrayListByPatient(UIName uiName, Patient patient) {
        ArrayList<Admission> admissionArrayList = new ArrayList<>();
        admissionArrayList.addAll(Session.admissionConnector.getAdmissionHashMapByPatient(patient).values());
        return admissionArrayList;
    }

    public static void newAdmission(UIName uiName, ArrayList<Admission> admissionArrayList) {
        for (Admission admission : admissionArrayList) {
            Session.admissionConnector.newAdmission(admission);
        }
    }

    public static void updateAdmission(UIName uiName, ArrayList<Admission> admissionArrayList) {
        for (Admission admission : admissionArrayList) {
            Session.admissionConnector.updateAdmission(admission);
        }
    }

    public static void deleteAdmission(UIName uiName, ArrayList<Admission> admissionArrayList) {
        for (Admission admission : admissionArrayList) {
            Session.admissionConnector.deleteAdmission(admission);
        }
    }
}
