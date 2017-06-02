package com.oasis.services;

import com.oasis.factory.CacheFactory;
import com.oasis.model.Gender;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class GenderServices {
    public static ArrayList<Gender> getGenderArrayList(UIName uiName) {
        ArrayList<Gender> genderArrayList = new ArrayList<>();
        genderArrayList.addAll(CacheFactory.getGenderCache().getItemHashMap().values());
        return genderArrayList;
    }

    public static Gender getGenderByTag(UIName uiName, String tag) {
        if (tag.equals("f")) {
            return CacheFactory.getGenderCache().getItemHashMap().get(2);
        } else {
            return CacheFactory.getGenderCache().getItemHashMap().get(1);
        }
    }
}
