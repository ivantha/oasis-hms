package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Race;
import com.oasis.model.Speciality;

import java.util.ArrayList;

public class SpecialityServices {
    public static Speciality getSpecialistBranchById(int id){
        return Session.specialistBranchCache.getItemHashMap().get(id);
    }

    public static void newSpeciality(ArrayList<Speciality> specialityArrayList) {
        for(Speciality speciality: specialityArrayList){
            Session.specialityConnector.newSpeciality(speciality);
        }
    }
}
