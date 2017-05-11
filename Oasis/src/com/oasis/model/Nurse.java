package com.oasis.model;

public class Nurse extends Employee{
    public Nurse(Employee employee) {
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
