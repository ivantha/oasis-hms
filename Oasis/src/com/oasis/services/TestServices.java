package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Test;

import java.util.ArrayList;

public class TestServices {
    public static Test getTestById(int id) {
        return CacheFactory.getTestCache().getItemHashMap().get(id);
    }

    public static ArrayList<Test> getTestArrayList() {
        ArrayList<Test> testArrayList = new ArrayList<>();
        testArrayList.addAll(Session.testConnector.getTestHashMap().values());
        return testArrayList;
    }

    public static void newTest(ArrayList<Test> testArrayList) {
        for (Test test : testArrayList) {
            Session.testConnector.newTest(test);
        }
    }

    public static void updateTest(ArrayList<Test> testArrayList) {
        for (Test test : testArrayList) {
            Session.testConnector.updateTest(test);
        }
    }

    public static void deleteTest(ArrayList<Test> testArrayList) {
        for (Test test : testArrayList) {
            Session.testConnector.deleteTest(test);
        }
    }
}
