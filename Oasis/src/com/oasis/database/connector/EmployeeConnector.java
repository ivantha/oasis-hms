package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.*;
import com.oasis.services.EmployeeRoleServices;
import com.oasis.services.GenderServices;
import com.oasis.utils.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class EmployeeConnector extends Connect {
    public HashMap<Integer, Employee> getEmployeeHashMap() {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("employee.id");
                String nic = resultSet.getString("employee.nic");
                String firstName = resultSet.getString("employee.first_name");
                String middleName = resultSet.getString("employee.middle_name");
                String lastName = resultSet.getString("employee.last_name");
                Gender gender = GenderServices.getGenderByTag(resultSet.getString("employee.gender"));
                LocalDate dob = DateTimeFormatter.DATETIMEToLocalDate(resultSet.getString("employee.dob"));
                LocalDate startDate = DateTimeFormatter.DATETIMEToLocalDate(resultSet.getString("employee.start_date"));

                String endDateString = resultSet.getString("employee.end_date");
                LocalDate endDate = null;
                if(endDateString != null){
                    endDate = DateTimeFormatter.DATETIMEToLocalDate(endDateString);
                }

                EmployeeRole employeeRole = EmployeeRoleServices.getEmployeeRoleById(resultSet.getInt("employee.employee_role_id"));
                LocalTime defaultShiftStart = DateTimeFormatter.TIMEtoLocalTime(resultSet.getString("employee.default_shift_start"));
                LocalTime defaultShiftEnd = DateTimeFormatter.TIMEtoLocalTime(resultSet.getString("employee.default_shift_end"));
                WorkingDays workingDays = new WorkingDays(resultSet.getString("employee.working_days"));

                Employee employee = new Employee(id, nic, firstName, middleName, lastName, gender, dob, startDate, endDate,
                        employeeRole, defaultShiftStart, defaultShiftEnd, workingDays);
                employeeHashMap.put(id, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_telephone");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int employeeId = resultSet.getInt("employee_telephone.employee_id");
                int employeeTelephoneId = resultSet.getInt("employee_telephone.id");
                String employeeTelephoneTelephone = resultSet.getString("employee_telephone.telephone");
                EmployeeTelephone employeeTelephone = new EmployeeTelephone(employeeTelephoneId, employeeTelephoneTelephone);

                employeeHashMap.get(employeeId).getEmployeeTelephoneArrayList().add(employeeTelephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_address");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int employeeId = resultSet.getInt("employee_address.employee_id");
                int employeeAddressId = resultSet.getInt("employee_address.id");
                String employeeAddressStreet = resultSet.getString("employee_address.street");
                String employeeAddressTown = resultSet.getString("employee_address.town");
                String employeeAddressProvince = resultSet.getString("employee_address.province");
                String employeeAddressPostalCode = resultSet.getString("employee_address.postal_code");
                EmployeeAddress employeeAddress = new EmployeeAddress(employeeAddressId, employeeAddressStreet, employeeAddressTown, employeeAddressProvince, employeeAddressPostalCode);

                employeeHashMap.get(employeeId).getEmployeeAddressArrayList().add(employeeAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_email");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int employeeId = resultSet.getInt("employee_email.employee_id");
                int employeeEmailId = resultSet.getInt("employee_email.id");
                String employeeEmailEmail = resultSet.getString("employee_email.email");
                EmployeeEmail employeeEmail = new EmployeeEmail(employeeEmailId, employeeEmailEmail);

                employeeHashMap.get(employeeId).getEmployeeEmailArrayList().add(employeeEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT employee_has_degree.employee_id, degree.id, " +
                    "degree.name, degree.acronym FROM employee_has_degree " +
                    "INNER JOIN degree ON degree.id = employee_has_degree.degree_id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int employeeId = resultSet.getInt("employee_has_degree.employee_id");
                int degreeId = resultSet.getInt("degree.id");
                String name = resultSet.getString("degree.name");
                String acronym = resultSet.getString("degree.acronym");
                Degree degree = new Degree(degreeId, name, acronym);

                employeeHashMap.get(employeeId).getDegreeArrayListObjectProperty().add(degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }

    public void newEmployee(Employee employee) {

    }

    public void updateEmployee(Employee employee) {

    }

    public void deleteEmployee(Employee employee) {

    }
}
