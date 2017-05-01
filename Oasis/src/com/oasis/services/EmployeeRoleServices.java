package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.EmployeeRole;

import java.util.HashMap;

public class EmployeeRoleServices {
    public static EmployeeRole getEmployeeRoleById(int id){
        return Session.employeeRoleCache.getItemHashMap().get(id);
    }
}
