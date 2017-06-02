package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Speciality;

import java.util.ArrayList;

public class SpecialityServices {
    public static Speciality getSpecialistBranchById(int id) {
        return CacheFactory.getSpecialityCache().getItemHashMap().get(id);
    }

    public static void newSpeciality(ArrayList<Speciality> specialityArrayList) {
        for (Speciality speciality : specialityArrayList) {
            Session.specialityConnector.newSpeciality(speciality);
        }
    }

    public static ArrayList<Speciality> getSpecialityArrayList() {
        ArrayList<Speciality> specialityArrayList = new ArrayList<>();
        specialityArrayList.addAll(Session.specialityConnector.getSpecialityHashMap().values());
        return specialityArrayList;
    }

    public static void updateSpeciality(ArrayList<Speciality> specialityArrayList) {
        for (Speciality speciality : specialityArrayList) {
            Session.specialityConnector.updateSpeciality(speciality);
        }
    }

    public static void deleteSpeciality(ArrayList<Speciality> specialityArrayList) {
        for (Speciality speciality : specialityArrayList) {
            Session.specialityConnector.deleteSpeciality(speciality);
        }
    }
}
