package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Charge;

import java.util.ArrayList;

public class ChargeServices {
    public static ArrayList<Charge> getChargeArrayListByAdmission(Admission admission){
        ArrayList<Charge> chargeArrayList = new ArrayList<>();
        chargeArrayList.addAll(Session.chargeConnector.getChargeHashMapByAdmission(admission).values());
        return chargeArrayList;
    }
}
