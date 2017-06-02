package com.oasis.services;

import com.oasis.model.Model;

import java.util.ArrayList;
import java.util.Iterator;

public class CommonServices {
    public static void removeEmptyObjects(ArrayList objectList) {
        Iterator iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Model m = (Model) iterator.next();
            if (m.isEmpty()) {
                iterator.remove();
            }
        }
    }
}
