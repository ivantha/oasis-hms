package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.SpecialistBranch;

import java.util.HashMap;

public class SpecialistBranchServices {
    public static SpecialistBranch getSpecialistBranchById(int id){
        return Session.specialistBranchCache.getItemHashMap().get(id);
    }
}
