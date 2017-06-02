package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.factory.CacheFactory;
import com.oasis.model.Test;
import com.oasis.ui.UIName;

import java.util.ArrayList;

public class TestServices {
    public static Test getTestById(UIName uiName, int id) {
        return CacheFactory.getTestCache().getItemHashMap().get(id);
    }

    public static void newTest(UIName uiName, ArrayList<Test> testArrayList) {
        for (Test test : testArrayList) {
            Session.testConnector.newTest(test);
        }
    }

    public static ArrayList<Test> getTestArrayList(UIName uiName) {
        ArrayList<Test> testArrayList = new ArrayList<>();
        testArrayList.addAll(Session.testConnector.getTestHashMap().values());
        return testArrayList;
    }

    public static void updateTest(UIName uiName, ArrayList<Test> testArrayList) {
        for (Test test : testArrayList) {
            Session.testConnector.updateTest(test);
        }
    }

    public static void deleteTest(UIName uiName, ArrayList<Test> testArrayList) {
        for (Test test : testArrayList) {
            Session.testConnector.deleteTest(test);
        }
    }
}
