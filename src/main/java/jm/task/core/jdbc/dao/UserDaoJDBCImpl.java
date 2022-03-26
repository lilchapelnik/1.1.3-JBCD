package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (
            PreparedStatement preparedStatement =
                    connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTO_INCREMENT , name VARCHAR(45), lastname VARCHAR(45), age INT (3))")) {
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            }
        }

    public void dropUsersTable() {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }


    public void saveUser(String name, String lastName, byte age) {

            String sql = "INSERT INTO users (name, lastname, age) VALUES (?,?,?)";
            try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setAge(resultSet.getByte(4));
                    userList.add(user);
                    connection.commit();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users ")) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {

            throwables.printStackTrace();
            }
        }
    }
