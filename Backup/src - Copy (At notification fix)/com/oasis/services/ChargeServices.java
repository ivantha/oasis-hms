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

    public static void newCharge(ArrayList<Charge> chargeArrayList) {
        for(Charge charge: chargeArrayList){
            Session.chargeConnector.newCharge(charge);
        }
    }

    public static void updateCharge(ArrayList<Charge> chargeArrayList){
        for(Charge charge: chargeArrayList){
            Session.chargeConnector.updateCharge(charge);
        }
    }

    public static void deleteCharge(ArrayList<Charge> chargeArrayList){
        for(Charge charge: chargeArrayList){
            Session.chargeConnector.deleteCharge(charge);
        }
    }

    public static int newChargeWithReturid(Charge charge){
        return Session.chargeConnector.newChargeWithReturid(charge);
    }
}
