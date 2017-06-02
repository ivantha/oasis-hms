package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Speciality;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class SpecialityServices {
    public static Speciality getSpecialistBranchById(UIName uiName, int id) {
        return CacheFactory.getSpecialityCache().getItemHashMap().get(id);
    }

    public static void newSpeciality(UIName uiName, ArrayList<Speciality> specialityArrayList) {
        for (Speciality speciality : specialityArrayList) {
            Session.specialityConnector.newSpeciality(speciality);
        }
    }

    public static ArrayList<Speciality> getSpecialityArrayList(UIName uiName) {
        ArrayList<Speciality> specialityArrayList = new ArrayList<>();
        specialityArrayList.addAll(Session.specialityConnector.getSpecialityHashMap().values());
        return specialityArrayList;
    }

    public static void updateSpeciality(UIName uiName, ArrayList<Speciality> specialityArrayList) {
        for (Speciality speciality : specialityArrayList) {
            Session.specialityConnector.updateSpeciality(speciality);
        }
    }

    public static void deleteSpeciality(UIName uiName, ArrayList<Speciality> specialityArrayList) {
        for (Speciality speciality : specialityArrayList) {
            Session.specialityConnector.deleteSpeciality(speciality);
        }
    }
}
