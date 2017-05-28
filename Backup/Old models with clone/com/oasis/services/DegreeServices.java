package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Degree;

import java.util.ArrayList;

public class DegreeServices {
    public static Degree getDegreeById(int id){
        return Session.degreeCache.getItemHashMap().get(id);
    }

    public static ArrayList<Degree> getDegreeArrayList(){
        ArrayList<Degree> ethnicityArrayList = new ArrayList<>();
        ethnicityArrayList.addAll(Session.degreeCache.getItemHashMap().values());
        return ethnicityArrayList;
    }
}
