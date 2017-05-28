package com.oasis.model;

import com.oasis.common.Session;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Doctor extends Employee{
    private ObjectProperty<Speciality> specialityObjectProperty = new SimpleObjectProperty();

    public Doctor(Employee employee) {
        super(employee.getId(), employee.getNic(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),
                employee.getGender(), employee.getDob(), employee.getStartDate(), employee.getEndDate(), employee.getEmployeeRole(),
                employee.getDefaultShiftStart(), employee.getDefaultShiftEnd(), employee.getWorkingDays(),
                employee.getTelephoneArrayList().get(0), employee.getAddressArrayList().get(0), employee.getEmailArrayList().get(0));
    }

    public Doctor(int id, String nic, String firstName, String middleName, String lastName,
                  Gender gender, LocalDate dob, LocalDate startDate, LocalDate endDate, EmployeeRole employeeRole,
                  LocalTime defaultShiftStart, LocalTime defaultShiftEnd, WorkingDays workingDays ) {
        super(id, nic, firstName, middleName, lastName, gender, dob, startDate, endDate, employeeRole, defaultShiftStart, defaultShiftEnd, workingDays);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        //More code
    }

    @Override
    public Doctor clone() {
        Doctor clonedDoctor = new Doctor(getId(), getNic(), getFirstName(), getMiddleName(), getLastName(),
                getGender(), getDob(), getStartDate(), getEndDate(), getEmployeeRole(),
                getDefaultShiftStart(), getDefaultShiftEnd(), getWorkingDays().clone());

        for(Telephone telephone: getTelephoneArrayList()){
            clonedDoctor.getTelephoneArrayList().add(telephone.clone());
        }
        for(Address address: getAddressArrayList()){
            clonedDoctor.getAddressArrayList().add(address.clone());
        }
        for(Email email: getEmailArrayList()){
            clonedDoctor.getEmailArrayList().add(email.clone());
        }
        for(Degree degree: getDegreeListProperty()){
            clonedDoctor.getDegreeListProperty().add(degree);
        }

        clonedDoctor.setSpecialityObjectProperty(getSpecialityObjectProperty());

        return clonedDoctor;
    }

    public Speciality getSpecialityObjectProperty() {
        return specialityObjectProperty.get();
    }

    public ObjectProperty<Speciality> specialityObjectPropertyProperty() {
        return specialityObjectProperty;
    }

    public void setSpecialityObjectProperty(Speciality specialityObjectProperty) {
        this.specialityObjectProperty.set(specialityObjectProperty);
    }
}
