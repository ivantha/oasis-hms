package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Employee;
import com.oasis.model.EmployeeRole;
import com.oasis.utils.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

public class EmployeeConnector extends Connect {
    public HashMap<Integer, Employee> getEmployeeHashMap() {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM blood_group");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("employee.id");
                String nic = resultSet.getString("employee.nic");
                String firstName = resultSet.getString("employee.first_name");
                String middleName = resultSet.getString("employee.middle_name");
                String lastName = resultSet.getString("employee.last_name");
                String gender = resultSet.getString("employee.gender");
                Date dob = DateTimeFormatter.DATETIMEToDate(resultSet.getString("employee.dob"));
                String profilePicture = resultSet.getString("employee.profile_picture");
                Date startDate = DateTimeFormatter.DATETIMEToDate(resultSet.getString("employee.start_date"));
                Date endDate = DateTimeFormatter.DATETIMEToDate(resultSet.getString("employee.end_date"));
                EmployeeRole employeeRole;
                Time defaultShiftStart;
                Time defaultShiftEnd;
                String workingDays = resultSet.getString("employee.working_days");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return employeeHashMap;
    }
}
