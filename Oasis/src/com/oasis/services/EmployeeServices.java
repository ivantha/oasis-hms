package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Employee;

import java.util.HashMap;

public class EmployeeServices {
    public static Employee getEmployeeById(int id){
        return Session.employeeCache.getItemHashMap().get(id);
    }
}
