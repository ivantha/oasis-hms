package com.oasis.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Employee implements Model<Employee> {
    private int id;
    private StringProperty nic = new SimpleStringProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dob = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private ObjectProperty<EmployeeRole> employeeRole = new SimpleObjectProperty<>();
    private ObjectProperty<LocalTime> defaultShiftStart = new SimpleObjectProperty<>();
    private ObjectProperty<LocalTime> defaultShiftEnd = new SimpleObjectProperty<>();
    private WorkingDays workingDays = new WorkingDays();
    private ArrayList<EmployeeTelephone> employeeTelephoneArrayList = new ArrayList<>();
    private ArrayList<EmployeeAddress> employeeAddressArrayList = new ArrayList<>();
    private ArrayList<EmployeeEmail> employeeEmailArrayList = new ArrayList<>();
    private ObjectProperty<ArrayList<Degree>> degreeArrayListObjectProperty = new SimpleObjectProperty<>(new ArrayList<>());

    public Employee(int id, String nic, String firstName, String middleName, String lastName, Gender gender, LocalDate dob,
                    LocalDate startDate, LocalDate endDate, EmployeeRole employeeRole,
                    LocalTime defaultShiftStart, LocalTime defaultShiftEnd, WorkingDays workingDays) {
        this.id = id;
        this.nic.setValue(nic);
        this.firstName.setValue(firstName);
        this.middleName.setValue(middleName);
        this.lastName.setValue(lastName);
        this.gender.setValue(gender);
        this.dob.setValue(dob);
        this.startDate.setValue(startDate);
        this.endDate.setValue(endDate);
        this.employeeRole.setValue(employeeRole);
        this.defaultShiftStart.setValue(defaultShiftStart);
        this.defaultShiftEnd.setValue(defaultShiftEnd);
        this.workingDays = workingDays;
    }

    public Employee(int id, String nic, String firstName, String middleName, String lastName, Gender gender, LocalDate dob,
                    LocalDate startDate, LocalDate endDate, EmployeeRole employeeRole,
                    LocalTime defaultShiftStart, LocalTime defaultShiftEnd, WorkingDays workingDays,
                    EmployeeTelephone employeeTelephone, EmployeeAddress employeeAddress, EmployeeEmail employeeEmail) {
        this.id = id;
        this.nic.setValue(nic);
        this.firstName.setValue(firstName);
        this.middleName.setValue(middleName);
        this.lastName.setValue(lastName);
        this.gender.setValue(gender);
        this.dob.setValue(dob);
        this.startDate.setValue(startDate);
        this.endDate.setValue(endDate);
        this.employeeRole.setValue(employeeRole);
        this.defaultShiftStart.setValue(defaultShiftStart);
        this.defaultShiftEnd.setValue(defaultShiftEnd);
        this.workingDays = workingDays;
        this.employeeTelephoneArrayList.add(employeeTelephone);
        this.employeeAddressArrayList.add(employeeAddress);
        this.employeeEmailArrayList.add(employeeEmail);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Employee.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Employee e = (Employee) obj;
        if (e.getId() != getId()) {
            return false;
        }
        if (e.getNic() != getNic()) {
            return false;
        }
        if (e.getFirstName() != getFirstName()) {
            return false;
        }
        if (e.getMiddleName() != getMiddleName()) {
            return false;
        }
        if (e.getLastName() != getLastName()) {
            return false;
        }
        if (!e.getGender().equals(getGender())) {
            return false;
        }
        if (!e.getDob().equals(getDob())) {
            return false;
        }
        if (!e.getStartDate().equals(getStartDate())) {
            return false;
        }
        if (!(e.getEndDate() == null ? getEndDate() == null : !e.getEndDate().equals(getEndDate()))) {
            return false;
        }
        if (!e.getEmployeeRole().equals(getEmployeeRole())) {
            return false;
        }
        if (!e.getDefaultShiftStart().equals(getDefaultShiftStart())) {
            return false;
        }
        if (!e.getDefaultShiftEnd().equals(getDefaultShiftEnd())) {
            return false;
        }
        if (!e.getWorkingDays().equals(getWorkingDays())) {
            return false;
        }
        if (!e.getEmployeeTelephoneArrayList().get(0).equals(getEmployeeTelephoneArrayList().get(0))) {
            return false;
        }
        if (!e.getEmployeeAddressArrayList().get(0).equals(getEmployeeAddressArrayList().get(0))) {
            return false;
        }
        if (!e.getEmployeeEmailArrayList().get(0).equals(getEmployeeEmailArrayList().get(0))) {
            return false;
        }
        if (!e.getDegreeArrayListObjectProperty().equals(getDegreeArrayListObjectProperty())) {
            return false;
        }

        return true;
    }

    @Override
    public Employee clone() {
        Employee clonedEmployee =  new Employee(getId(), getNic(), getFirstName(), getMiddleName(), getLastName(), getGender(), getDob(),
                getStartDate(), getEndDate(), getEmployeeRole(), getDefaultShiftStart(), getDefaultShiftEnd(), getWorkingDays(),
                getEmployeeTelephoneArrayList().get(0).clone(), getEmployeeAddressArrayList().get(0).clone(), getEmployeeEmailArrayList().get(0).clone());

        clonedEmployee.getDegreeArrayListObjectProperty().addAll(getDegreeArrayListObjectProperty());

        return clonedEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic.get();
    }

    public StringProperty nicProperty() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic.set(nic);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public Gender getGender() {
        return gender.get();
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public LocalDate getDob() {
        return dob.get();
    }

    public ObjectProperty<LocalDate> dobProperty() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob.set(dob);
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole.get();
    }

    public ObjectProperty<EmployeeRole> employeeRoleProperty() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole.set(employeeRole);
    }

    public LocalTime getDefaultShiftStart() {
        return defaultShiftStart.get();
    }

    public ObjectProperty<LocalTime> defaultShiftStartProperty() {
        return defaultShiftStart;
    }

    public void setDefaultShiftStart(LocalTime defaultShiftStart) {
        this.defaultShiftStart.set(defaultShiftStart);
    }

    public LocalTime getDefaultShiftEnd() {
        return defaultShiftEnd.get();
    }

    public ObjectProperty<LocalTime> defaultShiftEndProperty() {
        return defaultShiftEnd;
    }

    public void setDefaultShiftEnd(LocalTime defaultShiftEnd) {
        this.defaultShiftEnd.set(defaultShiftEnd);
    }

    public WorkingDays getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(WorkingDays workingDays) {
        this.workingDays = workingDays;
    }

    public ArrayList<EmployeeTelephone> getEmployeeTelephoneArrayList() {
        return employeeTelephoneArrayList;
    }

    public void setEmployeeTelephoneArrayList(ArrayList<EmployeeTelephone> employeeTelephoneArrayList) {
        this.employeeTelephoneArrayList = employeeTelephoneArrayList;
    }

    public ArrayList<EmployeeAddress> getEmployeeAddressArrayList() {
        return employeeAddressArrayList;
    }

    public void setEmployeeAddressArrayList(ArrayList<EmployeeAddress> employeeAddressArrayList) {
        this.employeeAddressArrayList = employeeAddressArrayList;
    }

    public ArrayList<EmployeeEmail> getEmployeeEmailArrayList() {
        return employeeEmailArrayList;
    }

    public void setEmployeeEmailArrayList(ArrayList<EmployeeEmail> employeeEmailArrayList) {
        this.employeeEmailArrayList = employeeEmailArrayList;
    }

    public ArrayList<Degree> getDegreeArrayListObjectProperty() {
        return degreeArrayListObjectProperty.get();
    }

    public ObjectProperty<ArrayList<Degree>> degreeArrayListObjectPropertyProperty() {
        return degreeArrayListObjectProperty;
    }

    public void setDegreeArrayListObjectProperty(ArrayList<Degree> degreeArrayListObjectProperty) {
        this.degreeArrayListObjectProperty.set(degreeArrayListObjectProperty);
    }
}
