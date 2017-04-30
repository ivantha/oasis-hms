package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.TestConnector;
import com.oasis.model.Test;

import java.util.HashMap;

public class TestServices {
    private static TestConnector testConnector = new TestConnector();

    public static HashMap<Integer, Test> getTestHashMap(){
        return testConnector.getTestHashMap();
    }

    public static Test getTestById(int id){
        return Session.testCache.getItemHashMap().get(id);
    }
}
