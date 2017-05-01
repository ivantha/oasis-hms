package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Race;

import java.util.HashMap;

public class RaceServices {
    public static Race getRacebyId(int id){
        return Session.raceCache.getItemHashMap().get(id);
    }
}
