package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<User> fourUsers = new HashSet<User>(Set.of(new User("Vasya","Vasylich", (byte) 15),
                                                        new User("Petya", "", (byte) 18),
                                                        new User("Георгий", "Викторович" , (byte) 67),
                                                        new User("", "Иваныч", (byte) 45)));
        UserServiceImpl dataTransfer = new UserServiceImpl();
        dataTransfer.createUsersTable();
        fourUsers.forEach(a -> dataTransfer.saveUser(a.getName(), a.getLastName(), a.getAge()));
        dataTransfer.getAllUsers().forEach(System.out::println);
        dataTransfer.cleanUsersTable();
        dataTransfer.dropUsersTable();

    }
}
