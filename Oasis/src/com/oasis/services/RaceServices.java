package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Race;

import java.util.ArrayList;

public class RaceServices {
    public static Race getRacebyId(int id){
        return Session.raceCache.getItemHashMap().get(id);
    }

    public static void newRace(ArrayList<Race> raceArrayList) {
        for(Race race: raceArrayList){
            Session.raceConnector.newRace(race);
        }
    }

    public static ArrayList<Race> getRaceArrayList(){
        ArrayList<Race> raceArrayList = new ArrayList<>();
        raceArrayList.addAll(Session.raceConnector.getRaceHashMap().values());
        return raceArrayList;
    }

    public static void updateRace(ArrayList<Race> raceArrayList){
        for(Race race: raceArrayList){
            Session.raceConnector.updateRace(race);
        }
    }

    public static void deleteRace(ArrayList<Race> raceArrayList){
        for(Race race: raceArrayList){
            Session.raceConnector.deleteRace(race);
        }
    }
}
