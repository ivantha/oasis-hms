package com.oasis.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Janitor extends Employee{
    public Janitor(Employee employee) {
        super(employee.getId(), employee.getNic(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),
                employee.getGender(), employee.getDob(), employee.getStartDate(), employee.getEndDate(), employee.getEmployeeRole(),
                employee.getDefaultShiftStart(), employee.getDefaultShiftEnd(), employee.getWorkingDays(),
                employee.getEmployeeTelephoneArrayList().get(0), employee.getEmployeeAddressArrayList().get(0), employee.getEmployeeEmailArrayList().get(0));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        //More code
    }

    @Override
    public Employee clone() {
        return super.clone();
        //More code
    }
}
