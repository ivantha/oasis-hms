package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.BloodGroup;

import java.util.ArrayList;

public class BloodGroupServices {
    public static BloodGroup getBloodGroupById(int id){
        return Session.bloodGroupCache.getItemHashMap().get(id);
    }

    public static ArrayList<BloodGroup> getBloodGroupArrayList(){
        ArrayList<BloodGroup> bloodGroupArrayList = new ArrayList<>();
        bloodGroupArrayList.addAll(Session.bloodGroupCache.getItemHashMap().values());
        return bloodGroupArrayList;
    }
}
