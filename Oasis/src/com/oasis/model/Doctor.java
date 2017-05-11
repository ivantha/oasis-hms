package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Doctor extends Employee{
    private ObjectProperty<Speciality> specialityObjectProperty = new SimpleObjectProperty();

    public Doctor(Employee employee) {
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
    public Employee clone() {
        return super.clone();
        //More code
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
