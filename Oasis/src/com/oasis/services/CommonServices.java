package com.oasis.services;

import com.oasis.model.Model;

import java.util.ArrayList;

public class CommonServices {
    public static void removeEmptyObjects(ArrayList objectList) {
        for (Object o : objectList) {
            Model m = (Model) o;
            if (m.isEmpty()) {
                objectList.remove(m);
            }
        }
    }
}
