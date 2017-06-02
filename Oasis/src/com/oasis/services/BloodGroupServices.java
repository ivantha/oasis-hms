package com.oasis.services;

import com.oasis.factory.CacheFactory;
import com.oasis.model.BloodGroup;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class BloodGroupServices {
    public static BloodGroup getBloodGroupById(UIName uiName, int id) {
        return CacheFactory.getBloodGroupCache().getItemHashMap().get(id);
    }

    public static ArrayList<BloodGroup> getBloodGroupArrayList(UIName uiName) {
        ArrayList<BloodGroup> bloodGroupArrayList = new ArrayList<>();
        bloodGroupArrayList.addAll(CacheFactory.getBloodGroupCache().getItemHashMap().values());
        return bloodGroupArrayList;
    }
}
