package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Ethnicity;

import java.util.ArrayList;

public class EthnicityServices {
    public static Ethnicity getEthnicityById(int id) {
        return CacheFactory.getEthnicityCache().getItemHashMap().get(id);
    }

    public static void addEthnicity(ArrayList<Ethnicity> ethnicityArrayList) {
        for (Ethnicity ethnicity : ethnicityArrayList) {
            Session.ethnicityConnector.newEthnicity(ethnicity);
        }
    }

    public static ArrayList<Ethnicity> getEthnicityArrayList() {
        ArrayList<Ethnicity> ethnicityArrayList = new ArrayList<>();
        ethnicityArrayList.addAll(Session.ethnicityConnector.getEthnicityHashMap().values());
        return ethnicityArrayList;
    }

    public static void updateEthnicity(ArrayList<Ethnicity> ethnicityArrayList) {
        for (Ethnicity ethnicity : ethnicityArrayList) {
            Session.ethnicityConnector.updateEthnicity(ethnicity);
        }
    }

    public static void deleteEthnicity(ArrayList<Ethnicity> ethnicityArrayList) {
        for (Ethnicity ethnicity : ethnicityArrayList) {
            Session.ethnicityConnector.deleteEthnicity(ethnicity);
        }
    }
}
