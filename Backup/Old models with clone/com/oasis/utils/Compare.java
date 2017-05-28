package com.oasis.utils;

import javafx.collections.ObservableList;

public class Compare {
    public static boolean isEqual(ObservableList observableList1, ObservableList observableList2){
        for(Object o: observableList1){
            if(!observableList2.contains(o)){
                return false;
            }
        }
        return true;
    }
}
