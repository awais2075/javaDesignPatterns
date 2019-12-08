package database;

import java.sql.*;

public class DatabaseHandler {
    private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String DATABASE_NAME = "employee_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;

    private Connection connection;

    private static DatabaseHandler instance = new DatabaseHandler();

    private DatabaseHandler() {
        createConnection();
    }

    public static DatabaseHandler getInstance() {
        return instance;
    }

    private void createConnection() {
        try {
            Class.forName(JDBC_DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(
                    CONNECTION_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }


    public Connection getConnection() {
        if(connection == null) {
            createConnection();
        }
        return connection;
    }

    public int insertData(PreparedStatement statement) {
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    public int updateData(PreparedStatement statement) {
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    public int deleteData(PreparedStatement statement) {
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    public ResultSet viewData(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }


}
