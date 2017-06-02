package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Admission;
import com.oasis.model.Charge;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class ChargeServices {
    public static ArrayList<Charge> getChargeArrayListByAdmission(UIName uiName, Admission admission) {
        ArrayList<Charge> chargeArrayList = new ArrayList<>();
        chargeArrayList.addAll(Session.chargeConnector.getChargeHashMapByAdmission(admission).values());
        return chargeArrayList;
    }

    public static void newCharge(UIName uiName, ArrayList<Charge> chargeArrayList) {
        for (Charge charge : chargeArrayList) {
            Session.chargeConnector.newCharge(charge);
        }
    }

    public static void updateCharge(UIName uiName, ArrayList<Charge> chargeArrayList) {
        for (Charge charge : chargeArrayList) {
            Session.chargeConnector.updateCharge(charge);
        }
    }

    public static void deleteCharge(UIName uiName, ArrayList<Charge> chargeArrayList) {
        for (Charge charge : chargeArrayList) {
            Session.chargeConnector.deleteCharge(charge);
        }
    }

    public static int newChargeWithReturid(UIName uiName, Charge charge) {
        return Session.chargeConnector.newChargeWithReturid(charge);
    }
}
