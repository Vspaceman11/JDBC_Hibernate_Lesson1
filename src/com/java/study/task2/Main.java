package com.java.study.task2;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/Test";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";
    private static final String NEW_INSERT = "INSERT INTO info(fName, lName, age, phone, address)" +
            "VALUES(?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM info";
    private static final String SELECT_NAMES = "SELECT fName, lName FROM info";

    public static void main(String[] args) {
        registerDriver();
        Connection connection = null;
        PreparedStatement statement = null;
        truncate();
        try {

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(NEW_INSERT);
            statement.setString(1, "Alex");
            statement.setString(2, "Kaufman");
            statement.setByte(3, (byte) 19);
            statement.setString(4, "(050)9123112");
            statement.setString(5, "Jeneva st. 13");
            statement.execute();

            statement.setString(1, "Sam");
            statement.setString(2, "Fisher");
            statement.setByte(3, (byte) 35);
            statement.setString(4, "(060)2131567");
            statement.setString(5, "Pamela av. 102");
            statement.execute();

            statement.setString(1, "Charlie");
            statement.setString(2, "Scene");
            statement.setByte(3, (byte) 40);
            statement.setString(4, "(050)7122789");
            statement.setString(5, "Denver st. 45");
            statement.execute();



            boolean closed = statement.isClosed();
            System.out.println(closed);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        setSelectAll();
        getNames();
    }

    private static void truncate() {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement("TRUNCATE TABLE info");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setSelectAll() {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(SELECT_ALL);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            System.out.println("All info");
            while (resultSet.next()) {
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                byte age = resultSet.getByte("age");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");

                System.out.println(fName + " " + lName + " " + age + " " + phone + " " + address);
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void getNames() {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(SELECT_NAMES);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            System.out.println("All info");
            while (resultSet.next()) {
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");

                System.out.println(fName + " " + lName);
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}