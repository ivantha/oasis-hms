package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.EthnicityConnector;
import com.oasis.model.Ethnicity;

import java.util.HashMap;

public class EthnicityServices {
    private static EthnicityConnector ethnicityConnector = new EthnicityConnector();

    public static HashMap<Integer, Ethnicity> getEthnicityHashMap(){
        return ethnicityConnector.getEthnicityHashMap();
    }

    public static Ethnicity getEthnicityById(int id){
        return Session.ethnicityCache.getItemHashMap().get(id);
    }
}
