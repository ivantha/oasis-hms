package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Patient;

import java.util.ArrayList;

public class AdmissionServices {
    public static ArrayList<Admission> getAdmissionArrayListByPatient(Patient patient) {
        ArrayList<Admission> admissionArrayList = new ArrayList<>();
        admissionArrayList.addAll(Session.admissionConnector.getAdmissionHashMapByPatient(patient).values());
        return admissionArrayList;
    }

    public static void newAdmission(ArrayList<Admission> admissionArrayList) {
        for (Admission admission : admissionArrayList) {
            Session.admissionConnector.newAdmission(admission);
        }
    }

    public static void updateAdmission(ArrayList<Admission> admissionArrayList) {
        for (Admission admission : admissionArrayList) {
            Session.admissionConnector.updateAdmission(admission);
        }
    }

    public static void deleteAdmission(ArrayList<Admission> admissionArrayList) {
        for (Admission admission : admissionArrayList) {
            Session.admissionConnector.deleteAdmission(admission);
        }
    }
}
