package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Employee;

import java.util.ArrayList;

public class EmployeeServices {
    public static Employee getEmployeeById(int id){
        return Session.employeeCache.getItemHashMap().get(id);
    }

    public static ArrayList<Employee> getEmployeeArrayList(){
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.addAll(Session.employeeConnector.getEmployeeHashMap().values());
        return employeeArrayList;
    }

    public static void newEmployee(ArrayList<Employee> employeeArrayList){
        for(Employee employee: employeeArrayList){
            Session.employeeConnector.newEmployee(employee);
        }
    }

    public static void updateEmployee(ArrayList<Employee> employeeArrayList){
        for(Employee employee: employeeArrayList){
            Session.employeeConnector.updateEmployee(employee);
        }
    }

    public static void deleteEmployee(ArrayList<Employee> employeeArrayList){
        for(Employee employee: employeeArrayList){
            Session.employeeConnector.deleteEmployee(employee);
        }
    }
}
