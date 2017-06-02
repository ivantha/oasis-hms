package com.oasis.services;

import com.oasis.factory.CacheFactory;
import com.oasis.model.Degree;

import java.util.ArrayList;

public class DegreeServices {
    public static Degree getDegreeById(int id) {
        return CacheFactory.getDegreeCache().getItemHashMap().get(id);
    }

    public static ArrayList<Degree> getDegreeArrayList() {
        ArrayList<Degree> ethnicityArrayList = new ArrayList<>();
        ethnicityArrayList.addAll(CacheFactory.getDegreeCache().getItemHashMap().values());
        return ethnicityArrayList;
    }
}
