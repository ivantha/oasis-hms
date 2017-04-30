package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.RaceConnector;
import com.oasis.model.Race;

import java.util.HashMap;

public class RaceServices {
    private static RaceConnector raceConnector = new RaceConnector();

    public static HashMap<Integer, Race> getRaceHashMap(){
        return raceConnector.getRaceHashMap();
    }

    public static Race getRacebyId(int id){
        return Session.raceCache.getItemHashMap().get(id);
    }
}
