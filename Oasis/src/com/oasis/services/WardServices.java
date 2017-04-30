package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.WardConnector;
import com.oasis.model.Ward;

import java.util.HashMap;

public class WardServices {
    private static WardConnector wardConnector = new WardConnector();

    public static HashMap<Integer, Ward> getWardHashMap(){
        return wardConnector.getWardHashMap();
    }

    public static Ward getWardById(int id){
        return Session.wardCache.getItemHashMap().get(id);
    }
}
