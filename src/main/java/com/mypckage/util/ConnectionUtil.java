package com.mypackage.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Util class to connecto to DB
 */
public class ConnectionUtil {

//    public static void main(String... args) {
//        try {
//            getConnection();
//            System.out.println("Connected successfully");
//        } catch (SQLException e) {
//            System.out.println("Connection failed ");
//            e.printStackTrace();
//        }
//    }


    //this will connect to our DB
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        //String url = "jdbc:postgresql://javafs220103.cludzulbnky0.us-east-1.rds.amazonaws.com:5432/ersdb";
        String url = System.getenv("RDBS_URL");
        //String url = "jdbc:postgresql://javafs220103.c8iqszwihdjq.us-east-1.rds.amazonaws.com:5432/ersdb";

        String username = System.getenv("SQL_USERNAME");
        String password = System.getenv("SQL_PASSWORD");
        //String username = "postgres";
        //String password = "password";
        return DriverManager.getConnection(url, username, password);
    }
}
