package com.oasis.services;

import com.oasis.database.connector.PhysicianConnector;
import com.oasis.model.Physician;

import java.util.ArrayList;
import java.util.HashMap;

public class PhysicianServices {
    private static PhysicianConnector physicianConnector = new PhysicianConnector();

    public static HashMap<Integer, Physician> getPhysicianHashMap(){
        return physicianConnector.getPhysicianHashMap();
    }

    public static ArrayList<Physician> getPhysicianArrayList(){
        ArrayList<Physician> physicianArrayList = new ArrayList<>();
        physicianArrayList.addAll(PhysicianServices.getPhysicianHashMap().values());
        return physicianArrayList;
    }
}
