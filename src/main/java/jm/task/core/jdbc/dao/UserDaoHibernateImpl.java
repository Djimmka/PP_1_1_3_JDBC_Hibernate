package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;
import java.util.OptionalLong;
// Not 1.1.3 task

public class UserDaoHibernateImpl implements UserDao {
    private final Util utilSet = new Util();
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = utilSet.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE `pp_1_1_3-4_jdbc_hibernate-master`.`users` (\n" +
                    "  `id` INT NOT NULL,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));").executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println("Already created");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = utilSet.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table users;").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (RuntimeException e) {
        System.out.println("Already deleted");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = utilSet.getSession()) {
            session.beginTransaction();
            OptionalLong i = OptionalLong.of(1);
            try {
                List<User> users = getAllUsers();
                i = users.stream().mapToLong(User::getId).max();
            } catch (RuntimeException e) {}
            long j = (i.isPresent()) ? (i.getAsLong()+1) : (long) 1;
            User user = new User(name, lastName, age);
            user.setId(j);
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (RuntimeException e) {
            System.out.println("Already exist");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = utilSet.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (RuntimeException e) {
            System.out.println("Already removed");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = utilSet.getSession()) {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            users = criteria.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println("No users");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = utilSet.getSession()) {
            session.beginTransaction();
            List<User> users = getAllUsers();
            users.forEach(session::delete);
            session.getTransaction().commit();
            session.close();
        } catch (RuntimeException e) {
            System.out.println("Already cleaned");
        }
    }
}
