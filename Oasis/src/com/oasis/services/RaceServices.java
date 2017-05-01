package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Physician;
import com.oasis.model.Race;

import java.util.ArrayList;
import java.util.HashMap;

public class RaceServices {
    public static Race getRacebyId(int id){
        return Session.raceCache.getItemHashMap().get(id);
    }

    public static void newRace(ArrayList<Race> raceArrayList) {
        for(Race race: raceArrayList){
            Session.raceConnector.newRace(race);
        }
    }
}
