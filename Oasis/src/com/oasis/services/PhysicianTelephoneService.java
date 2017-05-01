package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.PhysicianTelephone;

public class PhysicianTelephoneService {
    public static PhysicianTelephone getPhysicianTelephoneById(int id){
        return Session.physicianTelephoneConnector.getPhysicianTelephoneHashMap().get(id);
    }
}
