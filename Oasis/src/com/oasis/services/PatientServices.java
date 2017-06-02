package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Patient;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class PatientServices {
    public static Patient getPatientById(UIName uiName, int id) {
        return Session.patientConnector.getPatientHashMap().get(id);
    }

    public static ArrayList<Patient> getPatientArrayList(UIName uiName) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientArrayList.addAll(Session.patientConnector.getPatientHashMap().values());
        return patientArrayList;
    }

    public static void newPatient(UIName uiName, ArrayList<Patient> patientArrayList) {
        for (Patient patient : patientArrayList) {
            PatientServices.removeEmptyAttributes(uiName, patient);
            Session.patientConnector.newPatient(patient);
        }
    }

    public static void updatePatient(UIName uiName, ArrayList<Patient> patientArrayList) {
        for (Patient patient : patientArrayList) {
            PatientServices.removeEmptyAttributes(uiName, patient);
            Session.patientConnector.updatePatient(patient);
        }
    }

    public static void deletePatient(UIName uiName, ArrayList<Patient> patientArrayList) {
        for (Patient patient : patientArrayList) {
            PatientServices.removeEmptyAttributes(uiName, patient);
            Session.patientConnector.deletePatient(patient);
        }
    }

    public static void removeEmptyAttributes(UIName uiName, Patient patient) {
        CommonServices.removeEmptyObjects(patient.getTelephoneArrayList());
        CommonServices.removeEmptyObjects(patient.getAddressArrayList());
        CommonServices.removeEmptyObjects(patient.getEmailArrayList());
        CommonServices.removeEmptyObjects(patient.getEmergencyContactArrayList());
    }

    public static ArrayList<Patient> getPatientLike(UIName uiName, String param) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientArrayList.addAll(Session.patientConnector.getPatientLike(param).values());
        return patientArrayList;
    }
}
