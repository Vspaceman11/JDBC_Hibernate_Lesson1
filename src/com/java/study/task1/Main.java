package com.java.study.task1;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    private static final String CONTACT_DETAILS = "SELECT name, phone, adress FROM person\n" +
            "INNER JOIN\n" +
            "personalData\n" +
            "ON person.id = personalData.id";

    private static final String DIVORCED_PERSONS = "SELECT name, birthday, phone FROM personalData\n" +
            "INNER JOIN\n" +
            "person\n" +
            "ON personalData.id = person.id\n" +
            "WHERE personalData.familyStatus = 'Divorced'";

    private static final String MANAGERS = "SELECT name, birthday, phone FROM salary\n" +
            "INNER JOIN\n" +
            "personalData\n" +
            "ON salary.id = personalData.id\n" +
            "INNER JOIN\n" +
            "person\n" +
            "ON salary.id = person.id\n" +
            "WHERE salary.position = 'Manager'";

    public static void main(String[] args) {
        registerDriver();
        contactDetails(CONTACT_DETAILS);
        divorcedPersons(DIVORCED_PERSONS);
        managers(MANAGERS);


    }

    private static void contactDetails(String contactDetails) {
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            preparedStatement = connection.prepareStatement(contactDetails);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("ContactDetails table");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String adress = resultSet.getNString("adress");

                System.out.println(name + " " + phone + " " + adress);
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void divorcedPersons(String divorcedPersons) {
        Connection connection;
        PreparedStatement preparedStatement;

        System.out.println("DivorcedPersons table");

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            preparedStatement = connection.prepareStatement(divorcedPersons);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Date birthday = resultSet.getDate("birthday");
                String phone = resultSet.getNString("phone");

                System.out.println(name + " " + birthday + " " + phone);
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        private static void managers (String managers){
            Connection connection;
            PreparedStatement preparedStatement;

            System.out.println("Managers table");

            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                preparedStatement = connection.prepareStatement(managers);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    Date birthday = resultSet.getDate("birthday");
                    String phone = resultSet.getNString("phone");

                    System.out.println(name + " " + birthday + " " + phone);
                }
                System.out.println();
            } catch (SQLException e) {
                throw new RuntimeException(e);
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

