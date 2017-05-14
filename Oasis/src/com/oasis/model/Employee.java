package com.oasis.model;

import com.oasis.utils.Compare;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Employee implements Model<Employee> {
    private int id = 0;
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
    private ArrayList<Telephone> telephoneArrayList = new ArrayList<>();
    private ArrayList<Address> addressArrayList = new ArrayList<>();
    private ArrayList<Email> emailArrayList = new ArrayList<>();
    private ListProperty<Degree> degreeListProperty;

    public Employee() {
        ObservableList<Degree> degreeObservableList = FXCollections.observableList(new ArrayList<>());
        degreeListProperty = new SimpleListProperty<>(degreeObservableList);
    }

    public Employee(int id, String nic, String firstName, String middleName, String lastName, Gender gender, LocalDate dob,
                    LocalDate startDate, LocalDate endDate, EmployeeRole employeeRole,
                    LocalTime defaultShiftStart, LocalTime defaultShiftEnd, WorkingDays workingDays) {
        this();

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
                    Telephone telephone, Address address, Email email) {
        this();

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
        this.telephoneArrayList.add(telephone);
        this.addressArrayList.add(address);
        this.emailArrayList.add(email);
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
        if (!e.getTelephoneArrayList().get(0).equals(getTelephoneArrayList().get(0))) {
            return false;
        }
        if (!e.getAddressArrayList().get(0).equals(getAddressArrayList().get(0))) {
            return false;
        }
        if (!e.getEmailArrayList().get(0).equals(getEmailArrayList().get(0))) {
            return false;
        }
        if (!Compare.isEqual(e.getDegreeListProperty(), getDegreeListProperty())) {
            return false;
        }

        return true;
    }

    @Override
    public Employee clone() {
        Employee clonedEmployee =  new Employee(getId(), getNic(), getFirstName(), getMiddleName(), getLastName(), getGender(), getDob(),
                getStartDate(), getEndDate(), getEmployeeRole(), getDefaultShiftStart(), getDefaultShiftEnd(), getWorkingDays());

        for(Telephone telephone: getTelephoneArrayList()){
            clonedEmployee.getTelephoneArrayList().add(telephone.clone());
        }
        for(Address address: getAddressArrayList()){
            clonedEmployee.getAddressArrayList().add(address.clone());
        }
        for(Email email: getEmailArrayList()){
            clonedEmployee.getEmailArrayList().add(email.clone());
        }
        for(Degree degree: getDegreeListProperty()){
            clonedEmployee.getDegreeListProperty().add(degree);
        }
//        clonedEmployee.getDegreeListProperty().addAll(getDegreeListProperty());

        return clonedEmployee;
    }

    public boolean isEmpty(){
        return false;
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

    public ArrayList<Telephone> getTelephoneArrayList() {
        return telephoneArrayList;
    }

    public void setTelephoneArrayList(ArrayList<Telephone> telephoneArrayList) {
        this.telephoneArrayList = telephoneArrayList;
    }

    public ArrayList<Address> getAddressArrayList() {
        return addressArrayList;
    }

    public void setAddressArrayList(ArrayList<Address> addressArrayList) {
        this.addressArrayList = addressArrayList;
    }

    public ArrayList<Email> getEmailArrayList() {
        return emailArrayList;
    }

    public void setEmailArrayList(ArrayList<Email> emailArrayList) {
        this.emailArrayList = emailArrayList;
    }

    public ObservableList<Degree> getDegreeListProperty() {
        return degreeListProperty.get();
    }

    public ListProperty<Degree> degreeListPropertyProperty() {
        return degreeListProperty;
    }

    public void setDegreeListProperty(ObservableList<Degree> degreeListProperty) {
        this.degreeListProperty.set(degreeListProperty);
    }

    public boolean isDoctor(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Doctor");
        }else {
            return false;
        }
    }

    public boolean isNurse(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Nurse");
        }else {
            return false;
        }
    }
    public boolean isTech(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Tech");
        }else {
            return false;
        }
    }
    public boolean isTheraphist(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Theraphist");
        }else {
            return false;
        }
    }
    public boolean isPharmacist(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Pharmacist");
        }else {
            return false;
        }
    }
    public boolean isMedicalAssistant(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Medical Assistant");
        }else {
            return false;
        }
    }
    public boolean isMedicalLabTechnologist(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Medical Lab Technologist");
        }else {
            return false;
        }
    }
    public boolean isDiatician(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Diatician");
        }else {
            return false;
        }
    }
    public boolean isJanitor(){
        if(employeeRole != null){
            return  getEmployeeRole().getRole().equals("Janitor");
        }else {
            return false;
        }
    }

}
