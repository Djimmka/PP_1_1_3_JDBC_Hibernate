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
        try(Connection connect = utilSet.getConnection()){
            Statement statement = connect.createStatement();
            statement.execute("CREATE TABLE if not exists `pp_1_1_3-4_jdbc_hibernate-master`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `surname` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));");
        } catch (SQLException e) {
            System.out.println("ошибка создания таблицы пользователей");
        }
    }

    public void dropUsersTable() {
        try(Connection connect = utilSet.getConnection()){
            Statement statement = connect.createStatement();
            statement.execute("drop table if exists users;");
        } catch (SQLException e) {
            System.out.println("ошибка удаления таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connect = utilSet.getConnection()){
            Statement statement = connect.createStatement();
            statement.execute("insert into users (name, surname, age)\n" +
                    "values (\"" + name + "\",\"" + lastName + "\",\"" + age + "\");");
            System.out.println("Пользователь " + name + " " + lastName + " " + age + " добавлен");
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен");
        }
    }

    public void removeUserById(long id) {
        try(Connection connect = utilSet.getConnection()){
            Statement statement = connect.createStatement();
            statement.execute("delete from users where id=" + id + ";");
        } catch (SQLException e) {
            System.out.println("Jшибка удаления cуществующей записи");
        }
    }

    public List<User> getAllUsers() {
        try(Connection connect = utilSet.getConnection()){
            Statement statement = connect.createStatement();
            List<User> res = new ArrayList<>();
            ResultSet result = statement.executeQuery("select * from users;");
            int i=0;
            while (result.next()) {
                res.add(i++, new User(result.getString(2), result.getString(3), result.getByte(4)));
            }
            System.out.println("Данные получены");
            result.close();
            connect.close();
            return res;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения команды вывода содержимого таблицы");
        }

        return null;
    }

    public void cleanUsersTable() {
        try(Connection connect = utilSet.getConnection()){
            Statement statement = connect.createStatement();
            statement.execute("truncate table users");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения команды удаления");
        }
    }
}
