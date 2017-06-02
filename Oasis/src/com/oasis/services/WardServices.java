package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Ward;

import java.util.ArrayList;

public class WardServices {
    public static Ward getWardById(int id) {
        return CacheFactory.getWardCache().getItemHashMap().get(id);
    }

    public static ArrayList<Ward> getWardArrayList() {
        ArrayList<Ward> wardArrayList = new ArrayList<>();
        wardArrayList.addAll(Session.wardConnector.getWardHashMap().values());
        return wardArrayList;
    }

    public static void newWard(ArrayList<Ward> wardArrayList) {
        for (Ward ward : wardArrayList) {
            Session.wardConnector.newWard(ward);
        }
    }

    public static void updateWard(ArrayList<Ward> wardArrayList) {
        for (Ward ward : wardArrayList) {
            Session.wardConnector.updateWard(ward);
        }
    }

    public static void deleteWard(ArrayList<Ward> wardArrayList) {
        for (Ward ward : wardArrayList) {
            Session.wardConnector.deleteWard(ward);
        }
    }
}
