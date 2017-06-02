package com.oasis.services;

import com.oasis.factory.CacheFactory;
import com.oasis.model.EmployeeRole;

import java.util.ArrayList;

public class EmployeeRoleServices {
    public static EmployeeRole getEmployeeRoleById(int id) {
        return CacheFactory.getEmployeeRoleCache().getItemHashMap().get(id);
    }

    public static ArrayList<EmployeeRole> getEmployeeRoleArrayList() {
        ArrayList<EmployeeRole> employeeRoleArrayList = new ArrayList<>();
        employeeRoleArrayList.addAll(CacheFactory.getEmployeeRoleCache().getItemHashMap().values());
        return employeeRoleArrayList;
    }
}
