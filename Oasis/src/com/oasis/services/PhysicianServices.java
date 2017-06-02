package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Physician;
import com.oasis.model.PhysicianDesignation;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class PhysicianServices {
    public static Physician getPhysicianById(UIName uiName, int id) {
        return Session.physicianConnector.getPhysicianHashMap().get(id);
    }

    public static ArrayList<Physician> getPhysicianArrayList(UIName uiName) {
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.addAll(Session.physicianConnector.getPhysicianHashMap().values());
        return physicianArrayList;
    }

    public static void newPhysician(UIName uiName, ArrayList<Physician> physicianArrayList) {
        for (Physician physician : physicianArrayList) {
            Session.physicianConnector.newPhysician(physician);
        }
    }

    public static void updatePhysician(UIName uiName, ArrayList<Physician> physicianArrayList) {
        for (Physician physician : physicianArrayList) {
            Session.physicianConnector.updatePhysician(physician);
        }
    }

    public static void deletePhysician(UIName uiName, ArrayList<Physician> physicianArrayList) {
        for (Physician physician : physicianArrayList) {
            Session.physicianConnector.deletePhysician(physician);
        }
    }

    public static PhysicianDesignation getPhysicianDesignationById(UIName uiName, int id) {
        return CacheFactory.getPhysicianDesignationCache().getItemHashMap().get(id);
    }

    public static ArrayList<PhysicianDesignation> getPhysicianDesignationArrayList(UIName uiName) {
        ArrayList<PhysicianDesignation> physicianDesignationArrayList = new ArrayList<>();
        physicianDesignationArrayList.addAll(CacheFactory.getPhysicianDesignationCache().getItemHashMap().values());
        return physicianDesignationArrayList;
    }

    public static ArrayList<Physician> getPhysicianLike(UIName uiName, String param) {
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.addAll(Session.physicianConnector.getPhysicianLike(param).values());
        return physicianArrayList;
    }
}
