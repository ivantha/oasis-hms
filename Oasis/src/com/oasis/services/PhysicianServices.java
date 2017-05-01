package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Physician;

import java.util.ArrayList;

public class PhysicianServices {
    public static Physician getPhysicianById(int id){
        return Session.physicianConnector.getPhysicianHashMap().get(id);
    }

    public static ArrayList<Physician> getPhysicianArrayList(){
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.addAll(Session.physicianConnector.getPhysicianHashMap().values());
        return physicianArrayList;
    }

    public static void newPhysician(ArrayList<Physician> physicianArrayList){
        for(Physician physician: physicianArrayList){
            Session.physicianConnector.newPhysician(physician);
        }
    }

    public static void updatePhysician(ArrayList<Physician> physicianArrayList){
        for(Physician physician: physicianArrayList){
            Session.physicianConnector.updatePhysician(physician);
        }
    }

    public static void deletePhysician(ArrayList<Physician> physicianArrayList){
        for(Physician physician: physicianArrayList){
            Session.physicianConnector.deletePhysician(physician);
        }
    }
}
