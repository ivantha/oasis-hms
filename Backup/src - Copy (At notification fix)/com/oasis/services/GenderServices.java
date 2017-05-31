package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Gender;

import java.util.ArrayList;

public class GenderServices {
    public static ArrayList<Gender> getGenderArrayList(){
        ArrayList<Gender> genderArrayList = new ArrayList<>();
        genderArrayList.addAll(Session.genderCache.getItemHashMap().values());
        return genderArrayList;
    }

    public static Gender getGenderByTag(String tag){
        if(tag.equals("f")){
            return Session.genderCache.getItemHashMap().get(2);
        }else{
            return Session.genderCache.getItemHashMap().get(1);
        }
    }
}
