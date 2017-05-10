package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getNic() {
        return super.getNic();
    }

    @Override
    public StringProperty nicProperty() {
        return super.nicProperty();
    }

    @Override
    public void setNic(String nic) {
        super.setNic(nic);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public StringProperty firstNameProperty() {
        return super.firstNameProperty();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getMiddleName() {
        return super.getMiddleName();
    }

    @Override
    public StringProperty middleNameProperty() {
        return super.middleNameProperty();
    }

    @Override
    public void setMiddleName(String middleName) {
        super.setMiddleName(middleName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public StringProperty lastNameProperty() {
        return super.lastNameProperty();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public Gender getGender() {
        return super.getGender();
    }

    @Override
    public ObjectProperty<Gender> genderProperty() {
        return super.genderProperty();
    }

    @Override
    public void setGender(Gender gender) {
        super.setGender(gender);
    }

    @Override
    public LocalDate getDob() {
        return super.getDob();
    }

    @Override
    public ObjectProperty<LocalDate> dobProperty() {
        return super.dobProperty();
    }

    @Override
    public void setDob(LocalDate dob) {
        super.setDob(dob);
    }

    @Override
    public LocalDate getStartDate() {
        return super.getStartDate();
    }

    @Override
    public ObjectProperty<LocalDate> startDateProperty() {
        return super.startDateProperty();
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        super.setStartDate(startDate);
    }

    @Override
    public LocalDate getEndDate() {
        return super.getEndDate();
    }

    @Override
    public ObjectProperty<LocalDate> endDateProperty() {
        return super.endDateProperty();
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        super.setEndDate(endDate);
    }

    @Override
    public EmployeeRole getEmployeeRole() {
        return super.getEmployeeRole();
    }

    @Override
    public ObjectProperty<EmployeeRole> employeeRoleProperty() {
        return super.employeeRoleProperty();
    }

    @Override
    public void setEmployeeRole(EmployeeRole employeeRole) {
        super.setEmployeeRole(employeeRole);
    }

    @Override
    public LocalTime getDefaultShiftStart() {
        return super.getDefaultShiftStart();
    }

    @Override
    public ObjectProperty<LocalTime> defaultShiftStartProperty() {
        return super.defaultShiftStartProperty();
    }

    @Override
    public void setDefaultShiftStart(LocalTime defaultShiftStart) {
        super.setDefaultShiftStart(defaultShiftStart);
    }

    @Override
    public LocalTime getDefaultShiftEnd() {
        return super.getDefaultShiftEnd();
    }

    @Override
    public ObjectProperty<LocalTime> defaultShiftEndProperty() {
        return super.defaultShiftEndProperty();
    }

    @Override
    public void setDefaultShiftEnd(LocalTime defaultShiftEnd) {
        super.setDefaultShiftEnd(defaultShiftEnd);
    }

    @Override
    public WorkingDays getWorkingDays() {
        return super.getWorkingDays();
    }

    @Override
    public void setWorkingDays(WorkingDays workingDays) {
        super.setWorkingDays(workingDays);
    }

    @Override
    public ArrayList<EmployeeTelephone> getEmployeeTelephoneArrayList() {
        return super.getEmployeeTelephoneArrayList();
    }

    @Override
    public void setEmployeeTelephoneArrayList(ArrayList<EmployeeTelephone> employeeTelephoneArrayList) {
        super.setEmployeeTelephoneArrayList(employeeTelephoneArrayList);
    }

    @Override
    public ArrayList<EmployeeAddress> getEmployeeAddressArrayList() {
        return super.getEmployeeAddressArrayList();
    }

    @Override
    public void setEmployeeAddressArrayList(ArrayList<EmployeeAddress> employeeAddressArrayList) {
        super.setEmployeeAddressArrayList(employeeAddressArrayList);
    }

    @Override
    public ArrayList<EmployeeEmail> getEmployeeEmailArrayList() {
        return super.getEmployeeEmailArrayList();
    }

    @Override
    public void setEmployeeEmailArrayList(ArrayList<EmployeeEmail> employeeEmailArrayList) {
        super.setEmployeeEmailArrayList(employeeEmailArrayList);
    }

    @Override
    public ArrayList<Degree> getDegreeArrayListObjectProperty() {
        return super.getDegreeArrayListObjectProperty();
    }

    @Override
    public ObjectProperty<ArrayList<Degree>> degreeArrayListObjectPropertyProperty() {
        return super.degreeArrayListObjectPropertyProperty();
    }

    @Override
    public void setDegreeArrayListObjectProperty(ArrayList<Degree> degreeArrayListObjectProperty) {
        super.setDegreeArrayListObjectProperty(degreeArrayListObjectProperty);
    }
}
