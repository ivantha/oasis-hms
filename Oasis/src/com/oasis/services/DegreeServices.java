package com.oasis.services;

import com.oasis.factory.CacheFactory;
import com.oasis.model.Degree;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class DegreeServices {
    public static Degree getDegreeById(UIName uiName, int id) {
        return CacheFactory.getDegreeCache().getItemHashMap().get(id);
    }

    public static ArrayList<Degree> getDegreeArrayList(UIName uiName) {
        ArrayList<Degree> ethnicityArrayList = new ArrayList<>();
        ethnicityArrayList.addAll(CacheFactory.getDegreeCache().getItemHashMap().values());
        return ethnicityArrayList;
    }
}
