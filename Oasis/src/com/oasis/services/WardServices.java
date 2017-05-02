package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Test;
import com.oasis.model.Ward;

import java.util.ArrayList;
import java.util.HashMap;

public class WardServices {
    public static Ward getWardById(int id){
        return Session.wardCache.getItemHashMap().get(id);
    }

    public static ArrayList<Ward> getWardArrayList(){
        ArrayList<Ward> wardArrayList = new ArrayList<>();
        wardArrayList.addAll(Session.wardCache.getItemHashMap().values());
        return wardArrayList;
    }

    public static void newWard(ArrayList<Ward> wardArrayList) {
        for(Ward ward: wardArrayList){
            Session.wardConnector.newWard(ward);
        }
    }
}
