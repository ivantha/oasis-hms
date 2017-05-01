package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.PhysicianDesignation;

import java.util.ArrayList;

public class PhysicianDesignationServices {
    public static PhysicianDesignation getPhysicianDesignationById(int id){
        return Session.physicianDesignationCache.getItemHashMap().get(id);
    }

    public static ArrayList<PhysicianDesignation> getPhysicianDesignationArrayList(){
        ArrayList<PhysicianDesignation> physicianDesignationArrayList = new ArrayList<>();
        physicianDesignationArrayList.addAll(Session.physicianDesignationCache.getItemHashMap().values());
        return physicianDesignationArrayList;
    }
}
