package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Employee;

public class UserServices {
    public static Employee getUser(String username){
        return Session.userConnector.getUser(username);
    }
}
