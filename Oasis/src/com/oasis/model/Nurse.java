package com.oasis.model;

import com.oasis.common.Session;

public class Nurse extends Employee{
    public Nurse(Employee employee) {
        super(employee.getId(), employee.getNic(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),
                employee.getGender(), employee.getDob(), employee.getStartDate(), employee.getEndDate(), employee.getEmployeeRole(),
                employee.getDefaultShiftStart(), employee.getDefaultShiftEnd(), employee.getWorkingDays(),
                employee.getTelephoneArrayList().get(0), employee.getAddressArrayList().get(0), employee.getEmailArrayList().get(0));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        //More code
    }

    @Override
    public Nurse clone() {
        return Session.cloner.deepClone(this);
    }
}
