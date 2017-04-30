package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.PhysicianDesignationConnector;
import com.oasis.model.PhysicianDesignation;

import java.util.HashMap;

public class PhysicianDesignationServices {
    private static PhysicianDesignationConnector physicianDesignationConnector = new PhysicianDesignationConnector();

    public static HashMap<Integer, PhysicianDesignation> getPhysicianDesignationhashMap(){
        return physicianDesignationConnector.getPhysicianDesignationhashMap();
    }

    public static PhysicianDesignation getPhysicianDesignationById(int id){
        return Session.physicianDesignationCache.getItemHashMap().get(id);
    }
}
