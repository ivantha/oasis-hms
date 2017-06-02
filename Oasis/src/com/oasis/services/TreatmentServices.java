package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Treatment;

import java.util.ArrayList;

public class TreatmentServices {
    public static ArrayList<Treatment> getTreatmentArrayListByAdmission(Admission admission) {
        ArrayList<Treatment> treatmentArrayList = new ArrayList<>();
        treatmentArrayList.addAll(Session.treatmentConnector.getTreatmentArrayListByAdmission(admission).values());
        return treatmentArrayList;
    }

    public static void newTreatment(ArrayList<Treatment> treatmentArrayList, Admission admission) {
        for (Treatment treatment : treatmentArrayList) {
            Session.treatmentConnector.newTreatment(treatment, admission.getId());
        }
    }

    public static void updateTreatment(ArrayList<Treatment> treatmentArrayList) {
        for (Treatment treatment : treatmentArrayList) {
            Session.treatmentConnector.updateTreatment(treatment);
        }
    }

    public static void deleteTreatment(ArrayList<Treatment> treatmentArrayList) {
        for (Treatment treatment : treatmentArrayList) {
            Session.treatmentConnector.deleteTreatment(treatment);
        }
    }
}
