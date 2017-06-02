package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Physician;
import com.oasis.model.PhysicianDesignation;

import java.util.ArrayList;

public class PhysicianServices {
    public static Physician getPhysicianById(int id) {
        return Session.physicianConnector.getPhysicianHashMap().get(id);
    }

    public static ArrayList<Physician> getPhysicianArrayList() {
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.addAll(Session.physicianConnector.getPhysicianHashMap().values());
        return physicianArrayList;
    }

    public static void newPhysician(ArrayList<Physician> physicianArrayList) {
        for (Physician physician : physicianArrayList) {
            removeEmptyAttributes(physician);
            Session.physicianConnector.newPhysician(physician);
        }
    }

    public static void updatePhysician(ArrayList<Physician> physicianArrayList) {
        for (Physician physician : physicianArrayList) {
            removeEmptyAttributes(physician);
            Session.physicianConnector.updatePhysician(physician);
        }
    }

    public static void deletePhysician(ArrayList<Physician> physicianArrayList) {
        for (Physician physician : physicianArrayList) {
            removeEmptyAttributes(physician);
        }

        for (Physician physician : physicianArrayList) {
            Session.physicianConnector.deletePhysician(physician);
        }
    }

    public static PhysicianDesignation getPhysicianDesignationById(int id) {
        return CacheFactory.getPhysicianDesignationCache().getItemHashMap().get(id);
    }

    public static ArrayList<PhysicianDesignation> getPhysicianDesignationArrayList() {
        ArrayList<PhysicianDesignation> physicianDesignationArrayList = new ArrayList<>();
        physicianDesignationArrayList.addAll(CacheFactory.getPhysicianDesignationCache().getItemHashMap().values());
        return physicianDesignationArrayList;
    }

    public static ArrayList<Physician> getPhysicianLike(String param) {
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.addAll(Session.physicianConnector.getPhysicianLike(param).values());
        return physicianArrayList;
    }

    private static void removeEmptyAttributes(Physician physician) {
        CommonServices.removeEmptyObjects(physician.getTelephoneArrayList());
    }
}
