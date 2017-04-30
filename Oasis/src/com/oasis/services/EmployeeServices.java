package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.database.connector.EmployeeConnector;
import com.oasis.model.Employee;

import java.util.HashMap;

public class EmployeeServices {
    private static EmployeeConnector employeeConnector = new EmployeeConnector();

    public static HashMap<Integer, Employee> getEmployeeHashMap(){
        return employeeConnector.getEmployeeHashMap();
    }

    public static Employee getEmployeeById(int id){
        return Session.employeeCache.getItemHashMap().get(id);
    }
}
