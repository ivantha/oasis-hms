package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connect;
import com.oasis.model.Employee;
import com.oasis.services.EmployeeServices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserConnector extends Connect{
    public Employee getUser(String username){
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_login " +
                    "WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int employeeId = resultSet.getInt("employee_login.employee_id");
                String password = resultSet.getString("employee_login.password");

                employee = EmployeeServices.getEmployeeById(employeeId);
                employee.setUsername(username);
                employee.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
