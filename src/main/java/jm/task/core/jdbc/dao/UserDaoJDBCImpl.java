package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util utilSet = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connect = utilSet.getConnection(); Statement statement = connect.createStatement();) {
            statement.execute("CREATE TABLE if not exists `pp_1_1_3-4_jdbc_hibernate-master`.`users` (\n" + //
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `surname` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));");
        } catch (SQLException e) {
            System.out.println("ошибка создания таблицы пользователей");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connect = utilSet.getConnection(); Statement statement = connect.createStatement();) {
            statement.execute("drop table if exists users;");  //
        } catch (SQLException e) {
            System.out.println("ошибка удаления таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = utilSet.getConnection();
             PreparedStatement statement = connect.prepareStatement("insert into users (name, surname, age)\n" +
                     "values (?,?,?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("Пользователь " + name + " " + lastName + " " + age + " добавлен");
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connect = utilSet.getConnection();
             PreparedStatement statement = connect.prepareStatement("delete from users where id=?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления cуществующей записи");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Connection connect = utilSet.getConnection();
             Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery("select * from users;")) {
            List<User> res = new ArrayList<>();
            while (result.next()) {
                res.add(new User(result.getString(2), result.getString(3), result.getByte(4)));
            }
            System.out.println("Данные получены");
            return res;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения команды вывода содержимого таблицы");
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connect = utilSet.getConnection(); Statement statement = connect.createStatement();) {
            statement.execute("truncate table users");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения команды удаления");
            e.printStackTrace();
        }
    }
}
