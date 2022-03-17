package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://localhost:3306/first_task";
    private static String dbUsername = "root";
    private static String dbPassword = "Root123";
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
