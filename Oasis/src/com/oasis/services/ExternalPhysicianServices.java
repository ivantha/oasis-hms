package com.oasis.services;

import com.oasis.database.ExternalPhysicianConnector;

public class ExternalPhysicianServices {
    private static ExternalPhysicianConnector externalPhysicianConnector = new ExternalPhysicianConnector();

    public static void getPhysicianList(){
        externalPhysicianConnector.getPhysicianHashMap();
    }
}
