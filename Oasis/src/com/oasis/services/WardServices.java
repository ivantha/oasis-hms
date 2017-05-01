package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Ward;

import java.util.HashMap;

public class WardServices {
    public static Ward getWardById(int id){
        return Session.wardCache.getItemHashMap().get(id);
    }
}
