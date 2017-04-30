package com.oasis.services;

import com.oasis.database.connector.DegreeConnector;
import com.oasis.model.Degree;

import java.util.HashMap;

public class DegreeServices {
    private static DegreeConnector degreeConnector = new DegreeConnector();

    public static HashMap<Integer, Degree> getDegreeHashMap(){
        return degreeConnector.getDegreeHashMap();
    }
}
