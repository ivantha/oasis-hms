package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Ethnicity;

import java.util.HashMap;

public class EthnicityServices {
    public static Ethnicity getEthnicityById(int id){
        return Session.ethnicityCache.getItemHashMap().get(id);
    }
}
