package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.*;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl uDAO = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl uDAOH = new UserDaoHibernateImpl();
    public static boolean hibernateActive = false;
    public void createUsersTable() {
        if (hibernateActive) {
            uDAOH.createUsersTable();
        } else { uDAO.createUsersTable();}
    }

    public void dropUsersTable() {
        if (hibernateActive) {
            uDAOH.dropUsersTable();
        } else { uDAO.dropUsersTable();}
    }

    public void saveUser(String name, String lastName, byte age) {
        if (hibernateActive) {
            uDAOH.saveUser(name, lastName, age);
        } else { uDAO.saveUser(name, lastName, age);}
    }

    public void removeUserById(long id) {
        if (hibernateActive) {
            uDAOH.removeUserById(id);
        } else { uDAO.removeUserById(id);}
    }

    public List<User> getAllUsers() {
        return hibernateActive ? uDAOH.getAllUsers() : uDAO.getAllUsers();
    }

    public void cleanUsersTable() {
            if (hibernateActive) {
                uDAOH.cleanUsersTable();
            } else { uDAO.cleanUsersTable();}
    }
}
