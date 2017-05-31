package com.oasis.database;

import com.mysql.jdbc.Connection;
import com.oasis.common.Session;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Connect {
    private Connection connection;
    private String url;
    private String userName;
    private String password;
    private String dbName;

    public Connect() {
        url = "jdbc:mysql://" + Session.APP_CONFIG.getServerIP() + ":3306/";
        userName = Session.APP_CONFIG.getUserName();
        password = Session.APP_CONFIG.getPassword();
        dbName = Session.APP_CONFIG.getDbName();

        try {
            connection = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
