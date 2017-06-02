package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Treatment;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class TreatmentServices {
    public static ArrayList<Treatment> getTreatmentArrayListByAdmission(UIName uiName, Admission admission) {
        ArrayList<Treatment> treatmentArrayList = new ArrayList<>();
        treatmentArrayList.addAll(Session.treatmentConnector.getTreatmentArrayListByAdmission(admission).values());
        return treatmentArrayList;
    }

    public static void newTreatment(UIName uiName, ArrayList<Treatment> treatmentArrayList, Admission admission) {
        for (Treatment treatment : treatmentArrayList) {
            Session.treatmentConnector.newTreatment(treatment, admission.getId());
        }
    }

    public static void updateTreatment(UIName uiName, ArrayList<Treatment> treatmentArrayList) {
        for (Treatment treatment : treatmentArrayList) {
            Session.treatmentConnector.updateTreatment(treatment);
        }
    }

    public static void deleteTreatment(UIName uiName, ArrayList<Treatment> treatmentArrayList) {
        for (Treatment treatment : treatmentArrayList) {
            Session.treatmentConnector.deleteTreatment(treatment);
        }
    }
}
