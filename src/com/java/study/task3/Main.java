package com.java.study.task3;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/Test";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        File file = new File("C:\\Users\\user\\IdeaProjects\\JDBC_Hibernate_Lesson1\\src\\com\\java\\study\\task3\\test.txt");
        registerDriver();

        readFile(file, "UTF-8");


    }
    private static void readFile(File file, String charset){
        StringBuilder sqlStatement;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                sqlStatement = new StringBuilder(scanner.nextLine());
                statement.execute(String.valueOf(sqlStatement));

            }
            scanner.close();
        } catch (FileNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
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
