package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util util = new Util();
        util.getConnection();
        UserDao user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("Питер","Паркер", (byte) 14);
        user.saveUser("Бурдюк","Колесов", (byte) 32);
        user.saveUser("Владимир","Жмыхов", (byte) 28);
        user.saveUser("Холодец","Застывший", (byte) 15);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();



    }
}
