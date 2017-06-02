package com.oasis.database.connector;

import com.mysql.jdbc.PreparedStatement;
import com.oasis.database.Connector;
import com.oasis.model.EmployeeRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EmployeeRoleConnector extends Connector {
    public HashMap<Integer, EmployeeRole> getEmployeeRoleHashMap() {
        HashMap<Integer, EmployeeRole> employeeRoleHashMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM employee_role");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("employee_role.id");
                String role = resultSet.getString("employee_role.role");

                EmployeeRole employeeRole = new EmployeeRole(id, role);
                employeeRoleHashMap.put(id, employeeRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeRoleHashMap;
    }
}
