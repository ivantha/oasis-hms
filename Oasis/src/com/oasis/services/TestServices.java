package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Test;

import java.util.HashMap;

public class TestServices {
    public static Test getTestById(int id){
        return Session.testCache.getItemHashMap().get(id);
    }
}
