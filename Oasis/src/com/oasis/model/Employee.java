package com.oasis.model;

import java.sql.Time;
import java.util.Date;

public class Employee {
    private int id;
    private String nic;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date dob;
    private String profilePicture;
    private Date startDate;
    private Date endDate;
    private EmployeeRole employeeRole;
    private Time defaultShiftStart;
    private Time defaultShiftEnd;
    private String workingDays;

    public Employee(int id, String nic, String firstName, String middleName, String lastName, String gender, Date dob, String profilePicture, Date startDate, Date endDate, EmployeeRole employeeRole, Time defaultShiftStart, Time defaultShiftEnd, String workingDays) {
        this.id = id;
        this.nic = nic;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.profilePicture = profilePicture;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeRole = employeeRole;
        this.defaultShiftStart = defaultShiftStart;
        this.defaultShiftEnd = defaultShiftEnd;
        this.workingDays = workingDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Time getDefaultShiftStart() {
        return defaultShiftStart;
    }

    public void setDefaultShiftStart(Time defaultShiftStart) {
        this.defaultShiftStart = defaultShiftStart;
    }

    public Time getDefaultShiftEnd() {
        return defaultShiftEnd;
    }

    public void setDefaultShiftEnd(Time defaultShiftEnd) {
        this.defaultShiftEnd = defaultShiftEnd;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }
}
