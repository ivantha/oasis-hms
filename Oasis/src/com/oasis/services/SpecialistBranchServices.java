package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.SpecialistBranchConnector;
import com.oasis.model.SpecialistBranch;

import java.util.HashMap;

public class SpecialistBranchServices {
    private static SpecialistBranchConnector specialistBranchConnector = new SpecialistBranchConnector();

    public static HashMap<Integer, SpecialistBranch> getSpecialistBranchHashMap(){
        return specialistBranchConnector.getSpecialistBranchHashMap();
    }

    public static SpecialistBranch getSpecialistBranchById(int id){
        return Session.specialistBranchCache.getItemHashMap().get(id);
    }
}
