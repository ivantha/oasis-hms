package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.EmployeeRole;

import java.util.ArrayList;

public class EmployeeRoleServices {
    public static EmployeeRole getEmployeeRoleById(int id){
        return Session.employeeRoleCache.getItemHashMap().get(id);
    }

    public static ArrayList<EmployeeRole> getEmployeeRoleArrayList(){
        ArrayList<EmployeeRole> employeeRoleArrayList = new ArrayList<>();
        employeeRoleArrayList.addAll(Session.employeeRoleCache.getItemHashMap().values());
        return employeeRoleArrayList;
    }
}
