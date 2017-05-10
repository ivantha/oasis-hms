package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.oasis.database.Connect;
import com.oasis.model.*;
import com.oasis.services.EmployeeRoleServices;
import com.oasis.services.GenderServices;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
                LocalDate dob = resultSet.getDate("employee.dob").toLocalDate();
                LocalDate startDate = resultSet.getDate("employee.start_date").toLocalDate();

                Date endDateDate = resultSet.getDate("employee.end_date");
                LocalDate endDate = null;
                if(endDateDate != null){
                    endDate = endDateDate.toLocalDate();
                }

                EmployeeRole employeeRole = EmployeeRoleServices.getEmployeeRoleById(resultSet.getInt("employee.employee_role_id"));
                LocalTime defaultShiftStart = resultSet.getTime("employee.default_shift_start").toLocalTime();
                LocalTime defaultShiftEnd = resultSet.getTime("employee.default_shift_end").toLocalTime();
                WorkingDays workingDays = WorkingDays.valueOf(resultSet.getString("employee.working_days"));

                Employee employee = new Employee(id, nic, firstName, middleName, lastName, gender, dob, startDate, endDate,
                        employeeRole, defaultShiftStart, defaultShiftEnd, workingDays);
                employeeHashMap.put(id, employee);
            }

            PreparedStatement preparedStatement1 = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_telephone");
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()){
                int employeeId = resultSet1.getInt("employee_telephone.employee_id");
                int employeeTelephoneId = resultSet1.getInt("employee_telephone.id");
                String employeeTelephoneTelephone = resultSet1.getString("employee_telephone.telephone");
                EmployeeTelephone employeeTelephone = new EmployeeTelephone(employeeTelephoneId, employeeTelephoneTelephone);

                employeeHashMap.get(employeeId).getEmployeeTelephoneArrayList().add(employeeTelephone);
            }

            PreparedStatement preparedStatement2 = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_address");
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet2.next()){
                int employeeId = resultSet2.getInt("employee_address.employee_id");
                int employeeAddressId = resultSet2.getInt("employee_address.id");
                String employeeAddressStreet = resultSet2.getString("employee_address.street");
                String employeeAddressTown = resultSet2.getString("employee_address.town");
                String employeeAddressProvince = resultSet2.getString("employee_address.province");
                String employeeAddressPostalCode = resultSet2.getString("employee_address.postal_code");
                EmployeeAddress employeeAddress = new EmployeeAddress(employeeAddressId, employeeAddressStreet, employeeAddressTown, employeeAddressProvince, employeeAddressPostalCode);

                employeeHashMap.get(employeeId).getEmployeeAddressArrayList().add(employeeAddress);
            }

            PreparedStatement preparedStatement3 = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_email");
            ResultSet resultSet3 = preparedStatement3.executeQuery();

            while (resultSet3.next()){
                int employeeId = resultSet3.getInt("employee_email.employee_id");
                int employeeEmailId = resultSet3.getInt("employee_email.id");
                String employeeEmailEmail = resultSet3.getString("employee_email.email");
                EmployeeEmail employeeEmail = new EmployeeEmail(employeeEmailId, employeeEmailEmail);

                employeeHashMap.get(employeeId).getEmployeeEmailArrayList().add(employeeEmail);
            }

            PreparedStatement preparedStatement4 = (PreparedStatement) getConnection().prepareStatement("SELECT employee_has_degree.employee_id, degree.id, " +
                    "degree.name, degree.acronym FROM employee_has_degree " +
                    "INNER JOIN degree ON degree.id = employee_has_degree.degree_id");
            ResultSet resultSet4 = preparedStatement4.executeQuery();

            while (resultSet4.next()){
                int employeeId = resultSet4.getInt("employee_has_degree.employee_id");
                int degreeId = resultSet4.getInt("degree.id");
                String name = resultSet4.getString("degree.name");
                String acronym = resultSet4.getString("degree.acronym");
                Degree degree = new Degree(degreeId, name, acronym);

                employeeHashMap.get(employeeId).getDegreeListProperty().add(degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }

    public void newEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                    "employee(nic, first_name, middle_name, last_name, gender, dob, start_date, end_date, employee_role_id, " +
                    "default_shift_start, default_shift_end, working_days) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getNic());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setString(5, employee.getGender().getTag());
            preparedStatement.setDate(6, Date.valueOf(employee.getDob()));
            preparedStatement.setDate(7, Date.valueOf(employee.getStartDate()));
            preparedStatement.setDate(8, Date.valueOf(employee.getEndDate()));
            preparedStatement.setInt(9, employee.getEmployeeRole().getId());
            preparedStatement.setTime(10, Time.valueOf(employee.getDefaultShiftStart()));
            preparedStatement.setTime(11, Time.valueOf(employee.getDefaultShiftEnd()));
            preparedStatement.setString(12, employee.getWorkingDays().toString());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }

            for (EmployeeTelephone employeeTelephone : employee.getEmployeeTelephoneArrayList()) {
                PreparedStatement preparedStatement1 = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                        "employee_telephone(employee_id, telephone) " +
                        "VALUES(?, ?)");
                preparedStatement1.setInt(1, employee.getId());
                preparedStatement1.setString(2, employeeTelephone.getTelephone());

                preparedStatement1.execute();
            }

            for (EmployeeAddress employeeAddress : employee.getEmployeeAddressArrayList()) {
                PreparedStatement preparedStatement1 = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                        "employee_address(employee_id, street, town, province, postal_code) " +
                        "VALUES(?, ?, ?, ?, ?)");
                preparedStatement1.setInt(1, employee.getId());
                preparedStatement1.setString(2, employeeAddress.getStreet());
                preparedStatement1.setString(3, employeeAddress.getTown());
                preparedStatement1.setString(4, employeeAddress.getProvince());
                preparedStatement1.setString(5, employeeAddress.getPostalCode());

                preparedStatement1.execute();
            }

            for (EmployeeEmail employeeEmail : employee.getEmployeeEmailArrayList()) {
                PreparedStatement preparedStatement1 = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                        "employee_email(employee_id, email) " +
                        "VALUES(?, ?)");
                preparedStatement1.setInt(1, employee.getId());
                preparedStatement1.setString(2, employeeEmail.getEmail());

                preparedStatement1.execute();
            }

            for (Degree degree : employee.getDegreeListProperty()) {
                PreparedStatement preparedStatement1 = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                        "employee_has_degree(employee_id, degree_id) " +
                        "VALUES(?, ?)");
                preparedStatement1.setInt(1, employee.getId());
                preparedStatement1.setInt(2, degree.getId());

                preparedStatement1.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {

    }

    public void deleteEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM employee " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, employee.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
