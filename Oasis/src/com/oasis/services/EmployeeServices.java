package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Doctor;
import com.oasis.model.Employee;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
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
            EmployeeServices.removeEmptyAttributes(employee);
            Session.employeeConnector.newEmployee(employee);
            EmployeeServices.uploadImage("new_", employee.getId());
        }
    }

    public static void updateEmployee(ArrayList<Employee> employeeArrayList){
        for(Employee employee: employeeArrayList){
            EmployeeServices.removeEmptyAttributes(employee);
            Session.employeeConnector.updateEmployee(employee);
            EmployeeServices.uploadImage("changed_", employee.getId());
        }
    }

    public static void deleteEmployee(ArrayList<Employee> employeeArrayList){
        for(Employee employee: employeeArrayList){
            EmployeeServices.removeEmptyAttributes(employee);
            Session.employeeConnector.deleteEmployee(employee);
        }
    }

    private static void uploadImage(String prefix, int employeeID){
        File source = new File(System.getProperty("user.dir"), "temp\\pp_" + prefix + employeeID + ".jpg");
        if(source.exists()){
            File dest = new File(System.getProperty("user.dir"), "profile_pictures\\pp_" + employeeID + ".jpg");
            try {
                FileUtils.copyFile(source, dest);
                source.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void removeEmptyAttributes(Employee employee){
        CommonServices.removeEmptyObjects(employee.getTelephoneArrayList());
        CommonServices.removeEmptyObjects(employee.getAddressArrayList());
        CommonServices.removeEmptyObjects(employee.getEmailArrayList());
    }

    public static ArrayList<Employee> getEmployeeLike(String param){
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.addAll(Session.employeeConnector.getEmployeeLike(param).values());
        return employeeArrayList;
    }

    public static ArrayList<Doctor> getDoctorLike(String param){
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for(Employee employee: Session.employeeConnector.getDoctorLike(param).values()){
            doctorArrayList.add(new Doctor(employee));
        }
        return doctorArrayList;
    }
}
