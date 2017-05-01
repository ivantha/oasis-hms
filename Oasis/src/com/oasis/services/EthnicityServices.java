package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Ethnicity;
import com.oasis.model.Physician;

import java.util.ArrayList;
import java.util.HashMap;

public class EthnicityServices {
    public static Ethnicity getEthnicityById(int id){
        return Session.ethnicityCache.getItemHashMap().get(id);
    }

    public static void addEthnicity(ArrayList<Ethnicity> ethnicityArrayList) {
        for(Ethnicity ethnicity: ethnicityArrayList){
            Session.ethnicityConnector.newEthnicity(ethnicity);
        }
    }
}
