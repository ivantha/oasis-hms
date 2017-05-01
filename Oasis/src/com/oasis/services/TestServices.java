package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Race;
import com.oasis.model.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestServices {
    public static Test getTestById(int id){
        return Session.testCache.getItemHashMap().get(id);
    }

    public static void newTest(ArrayList<Test> testArrayList) {
        for(Test test: testArrayList){
            Session.testConnector.newTest(test);
        }
    }
}
