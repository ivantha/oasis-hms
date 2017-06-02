package com.oasis.services;

import com.oasis.factory.CacheFactory;
import com.oasis.model.BloodGroup;

import java.util.ArrayList;

public class BloodGroupServices {
    public static BloodGroup getBloodGroupById(int id) {
        return CacheFactory.getBloodGroupCache().getItemHashMap().get(id);
    }

    public static ArrayList<BloodGroup> getBloodGroupArrayList() {
        ArrayList<BloodGroup> bloodGroupArrayList = new ArrayList<>();
        bloodGroupArrayList.addAll(CacheFactory.getBloodGroupCache().getItemHashMap().values());
        return bloodGroupArrayList;
    }
}
