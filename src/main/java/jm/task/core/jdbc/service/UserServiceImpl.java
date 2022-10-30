package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.*;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl uDAO = new UserDaoJDBCImpl();

    public void createUsersTable() {
        uDAO.createUsersTable();
    }

    public void dropUsersTable() {
        uDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        uDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        uDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return uDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        uDAO.cleanUsersTable();
    }
}
