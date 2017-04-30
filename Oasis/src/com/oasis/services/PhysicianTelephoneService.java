package com.oasis.services;

import com.oasis.database.connector.PhysicianTelephoneConnector;
import com.oasis.model.PhysicianTelephone;

import java.util.HashMap;

public class PhysicianTelephoneService {
    private static PhysicianTelephoneConnector physicianTelephoneConnector = new PhysicianTelephoneConnector();

    public static HashMap<Integer, PhysicianTelephone> getPhysicianTelephoneHashMap(){
        return physicianTelephoneConnector.getPhysicianTelephoneHashMap();
    }
}
