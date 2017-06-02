package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Ward;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class WardServices {
    public static Ward getWardById(UIName uiName, int id) {
        return CacheFactory.getWardCache().getItemHashMap().get(id);
    }

    public static ArrayList<Ward> getWardArrayList(UIName uiName) {
        ArrayList<Ward> wardArrayList = new ArrayList<>();
        wardArrayList.addAll(Session.wardConnector.getWardHashMap().values());
        return wardArrayList;
    }

    public static void newWard(UIName uiName, ArrayList<Ward> wardArrayList) {
        for (Ward ward : wardArrayList) {
            Session.wardConnector.newWard(ward);
        }
    }

    public static void updateWard(UIName uiName, ArrayList<Ward> wardArrayList) {
        for (Ward ward : wardArrayList) {
            Session.wardConnector.updateWard(ward);
        }
    }

    public static void deleteWard(UIName uiName, ArrayList<Ward> wardArrayList) {
        for (Ward ward : wardArrayList) {
            Session.wardConnector.deleteWard(ward);
        }
    }
}
