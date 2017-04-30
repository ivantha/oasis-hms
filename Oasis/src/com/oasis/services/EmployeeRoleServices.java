package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.EmployeeRoleConnector;
import com.oasis.model.EmployeeRole;

import java.util.HashMap;

public class EmployeeRoleServices {
    private static EmployeeRoleConnector employeeRoleConnector = new EmployeeRoleConnector();

    public static HashMap<Integer, EmployeeRole> getEmployeeRoleHashMap(){
        return employeeRoleConnector.getEmployeeRoleHashMap();
    }

    public static EmployeeRole getEmployeeRoleById(int id){
        return Session.employeeRoleCache.getItemHashMap().get(id);
    }
}
