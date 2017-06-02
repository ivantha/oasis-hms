package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.oasis.database.Connector;
import com.oasis.model.*;
import com.oasis.services.EmployeeRoleServices;
import com.oasis.services.EmployeeServices;
import com.oasis.services.GenderServices;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class EmployeeConnector extends Connector {
    public HashMap<Integer, Employee> getEmployeeHashMap() {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee");
            getEmployeeGeneralDetails(preparedStatement, employeeHashMap);

            getEmployeeTelephoneDetails(employeeHashMap);
            getEmployeeAddressDetails(employeeHashMap);
            getEmployeeEmailDetails(employeeHashMap);
            getEmployeeDegreeDetails(employeeHashMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }

    private void getEmployeeGeneralDetails(PreparedStatement preparedStatement, HashMap<Integer, Employee> employeeHashMap) {
        try {
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
                if (endDateDate != null) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Expected when all the employees are not in the hash map
        }
    }

    private void getEmployeeTelephoneDetails(HashMap<Integer, Employee> employeeHashMap) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_telephone");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_telephone.employee_id");
                int employeeTelephoneId = resultSet.getInt("employee_telephone.id");
                String employeeTelephoneTelephone = resultSet.getString("employee_telephone.telephone");
                Telephone telephone = new Telephone(employeeTelephoneId, employeeTelephoneTelephone);

                employeeHashMap.get(employeeId).getTelephoneArrayList().add(telephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Expected when all the employees are not in the hash map
        }
    }

    private void getEmployeeAddressDetails(HashMap<Integer, Employee> employeeHashMap) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_address");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_address.employee_id");
                int employeeAddressId = resultSet.getInt("employee_address.id");
                String employeeAddressStreet = resultSet.getString("employee_address.street");
                String employeeAddressTown = resultSet.getString("employee_address.town");
                String employeeAddressProvince = resultSet.getString("employee_address.province");
                String employeeAddressPostalCode = resultSet.getString("employee_address.postal_code");
                Address address = new Address(employeeAddressId, employeeAddressStreet, employeeAddressTown, employeeAddressProvince, employeeAddressPostalCode);

                employeeHashMap.get(employeeId).getAddressArrayList().add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Expected when all the employees are not in the hash map
        }
    }

    private void getEmployeeEmailDetails(HashMap<Integer, Employee> employeeHashMap) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_email");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_email.employee_id");
                int employeeEmailId = resultSet.getInt("employee_email.id");
                String employeeEmailEmail = resultSet.getString("employee_email.email");
                Email email = new Email(employeeEmailId, employeeEmailEmail);

                employeeHashMap.get(employeeId).getEmailArrayList().add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Expected when all the employees are not in the hash map
        }
    }

    private void getEmployeeDegreeDetails(HashMap<Integer, Employee> employeeHashMap) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT employee_has_degree.employee_id, degree.id, " +
                    "degree.name, degree.acronym FROM employee_has_degree " +
                    "INNER JOIN degree ON degree.id = employee_has_degree.degree_id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_has_degree.employee_id");
                int degreeId = resultSet.getInt("degree.id");
                String name = resultSet.getString("degree.name");
                String acronym = resultSet.getString("degree.acronym");
                Degree degree = new Degree(degreeId, name, acronym);

                employeeHashMap.get(employeeId).getDegreeListProperty().add(degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Expected when all the employees are not in the hash map
        }
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
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }

            for (Telephone telephone : employee.getTelephoneArrayList()) {
                newEmployeeTelephone(employee.getId(), telephone);
            }

            for (Address address : employee.getAddressArrayList()) {
                newEmployeeAddress(employee.getId(), address);
            }

            for (Email email : employee.getEmailArrayList()) {
                newEmployeeEmail(employee.getId(), email);
            }

            for (Degree degree : employee.getDegreeListProperty()) {
                replaceEmployeeDegree(employee.getId(), degree);
            }

            if (employee.isDoctor()) {
                newDoctorSpeciality(employee.getId(), ((Doctor) employee).getSpecialityObjectProperty());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE employee SET " +
                    "nic = ?, " +
                    "first_name = ?, " +
                    "middle_name = ?, " +
                    "last_name = ?, " +
                    "gender = ?, " +
                    "dob = ?, " +
                    "start_date = ?, " +
                    "end_date = ?, " +
                    "employee_role_id = ?, " +
                    "default_shift_start = ?, " +
                    "default_shift_end = ?, " +
                    "working_days = ? " +
                    "WHERE id = ?");
            preparedStatement.setString(1, employee.getNic());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getFirstName());
            preparedStatement.setString(5, employee.getGender().getTag());
            preparedStatement.setDate(6, Date.valueOf(employee.getDob()));
            preparedStatement.setDate(7, Date.valueOf(employee.getStartDate()));
            preparedStatement.setDate(8, employee.getEndDate() != null ? Date.valueOf(employee.getEndDate()) : null);
            preparedStatement.setInt(9, employee.getEmployeeRole().getId());
            preparedStatement.setTime(10, Time.valueOf(employee.getDefaultShiftStart()));
            preparedStatement.setTime(11, Time.valueOf(employee.getDefaultShiftEnd()));
            preparedStatement.setString(12, employee.getWorkingDays().toString());
            preparedStatement.setInt(13, employee.getId());

            preparedStatement.execute();

            for (Telephone telephone : employee.getTelephoneArrayList()) {
                if (telephone.getId() == 0) {
                    newEmployeeTelephone(employee.getId(), telephone);
                } else {
                    updateEmployeeTelephone(telephone);
                }
            }

            for (Address address : employee.getAddressArrayList()) {
                if (address.getId() == 0) {
                    newEmployeeAddress(employee.getId(), address);
                } else {
                    updateEmployeeAddress(address);
                }
            }

            for (Email email : employee.getEmailArrayList()) {
                if (email.getId() == 0) {
                    newEmployeeEmail(employee.getId(), email);
                } else {
                    updateEmployeeEmail(email);
                }
            }

            Employee originalEmployee = EmployeeServices.getEmployeeById(employee.getId());
            for (Degree degree : employee.getDegreeListProperty()) {
                replaceEmployeeDegree(employee.getId(), degree);
            }
            for (Degree degree : originalEmployee.getDegreeListProperty()) {
                if (!employee.getDegreeListProperty().contains(degree)) {
                    deleteEmployeeDegree(employee.getId(), degree);
                }
            }

            if (employee.isDoctor()) {
                replaceDoctorSpeciality(employee.getId(), ((Doctor) employee).getSpecialityObjectProperty());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void newEmployeeTelephone(int employeeId, Telephone telephone) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "employee_telephone(employee_id, telephone) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setString(2, telephone.getTelephone());

        preparedStatement.execute();
    }

    private void updateEmployeeTelephone(Telephone telephone) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE employee_telephone SET " +
                "telephone = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, telephone.getTelephone());
        preparedStatement.setInt(2, telephone.getId());

        preparedStatement.execute();
    }

    //Delete employee telephone

    private void newEmployeeAddress(int employeeId, Address address) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "employee_address(employee_id, street, town, province, postal_code) " +
                "VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setString(2, address.getStreet());
        preparedStatement.setString(3, address.getTown());
        preparedStatement.setString(4, address.getProvince());
        preparedStatement.setString(5, address.getPostalCode());

        preparedStatement.execute();
    }

    private void updateEmployeeAddress(Address address) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE employee_address SET " +
                "street = ?, " +
                "town = ?, " +
                "province = ?, " +
                "postal_code = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, address.getStreet());
        preparedStatement.setString(2, address.getTown());
        preparedStatement.setString(3, address.getProvince());
        preparedStatement.setString(4, address.getPostalCode());
        preparedStatement.setInt(5, address.getId());

        preparedStatement.execute();
    }

    //Delete employee address

    private void newEmployeeEmail(int employeeId, Email email) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "employee_email(employee_id, email) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setString(2, email.getEmail());

        preparedStatement.execute();
    }

    private void updateEmployeeEmail(Email email) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("UPDATE employee_email SET " +
                "email = ? " +
                "WHERE id = ?");

        preparedStatement.setString(1, email.getEmail());
        preparedStatement.setInt(2, email.getId());

        preparedStatement.execute();
    }

    //Delete employee email

    private void replaceEmployeeDegree(int employeeId, Degree degree) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("REPLACE INTO " +
                "employee_has_degree(employee_id, degree_id) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, degree.getId());

        preparedStatement.execute();
    }

    private void deleteEmployeeDegree(int employeeId, Degree degree) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("DELETE FROM " +
                "employee_has_degree WHERE " +
                "employee_id = ? AND degree_id = ?");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, degree.getId());

        preparedStatement.execute();
    }

    private void newDoctorSpeciality(int employeeId, Speciality speciality) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("INSERT INTO " +
                "doctor(employee_id, speciality_id) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, speciality.getId());

        preparedStatement.execute();
    }

    private void replaceDoctorSpeciality(int employeeId, Speciality speciality) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("REPLACE INTO " +
                "doctor(employee_id, speciality_id) " +
                "VALUES(?, ?)");
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, speciality.getId());

        preparedStatement.execute();
    }

    //Delete doctor speciality

    public HashMap<Integer, Employee> getEmployeeLike(String param) {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee WHERE " +
                    "CONCAT_WS(' ', first_name, middle_name, last_name) LIKE '%" + param + "%'");
            getEmployeeGeneralDetails(preparedStatement, employeeHashMap);

            getEmployeeTelephoneDetails(employeeHashMap);
            getEmployeeAddressDetails(employeeHashMap);
            getEmployeeEmailDetails(employeeHashMap);
            getEmployeeDegreeDetails(employeeHashMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }

    public HashMap<Integer, Employee> getDoctorLike(String param) {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee " +
                    "INNER JOIN doctor ON employee.id = doctor.employee_id " +
                    "WHERE CONCAT_WS(' ', first_name, middle_name, last_name) LIKE '%" + param + "%' ");
            getEmployeeGeneralDetails(preparedStatement, employeeHashMap);

            getEmployeeTelephoneDetails(employeeHashMap);
            getEmployeeAddressDetails(employeeHashMap);
            getEmployeeEmailDetails(employeeHashMap);
            getEmployeeDegreeDetails(employeeHashMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }
}
