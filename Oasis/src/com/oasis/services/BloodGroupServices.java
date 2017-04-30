package com.oasis.services;

import com.oasis.database.connector.BloodGroupConnector;
import com.oasis.model.BloodGroup;

import java.util.HashMap;

public class BloodGroupServices {
    private static BloodGroupConnector bloodGroupConnector = new BloodGroupConnector();

    public static HashMap<Integer, BloodGroup> getBloodGroupHashMap(){
        return bloodGroupConnector.getBloodGroupHashMap();
    }
}
