package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Patient;

import java.util.ArrayList;

public class PatientServices {
    public static Patient getPatientById(int id){
        return Session.patientConnector.getPatientHashMap().get(id);
    }

    public static ArrayList<Patient> getPatientArrayList(){
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientArrayList.addAll(Session.patientConnector.getPatientHashMap().values());
        return patientArrayList;
    }

    public static void newPatient(ArrayList<Patient> patientArrayList){
        for(Patient patient: patientArrayList){
            PatientServices.removeEmptyAttributes(patient);
            Session.patientConnector.newPatient(patient);
        }
    }

    public static void updatePatient(ArrayList<Patient> patientArrayList){
        for(Patient patient: patientArrayList){
            PatientServices.removeEmptyAttributes(patient);
            Session.patientConnector.updatePatient(patient);
        }
    }

    public static void deletePatient(ArrayList<Patient> patientArrayList){
        for(Patient patient: patientArrayList){
            PatientServices.removeEmptyAttributes(patient);
            Session.patientConnector.deletePatient(patient);
        }
    }

    private static void removeEmptyAttributes(Patient patient){
        CommonServices.removeEmptyObjects(patient.getTelephoneArrayList());
        CommonServices.removeEmptyObjects(patient.getAddressArrayList());
        CommonServices.removeEmptyObjects(patient.getEmailArrayList());
        CommonServices.removeEmptyObjects(patient.getEmergencyContactArrayList());
    }
}
