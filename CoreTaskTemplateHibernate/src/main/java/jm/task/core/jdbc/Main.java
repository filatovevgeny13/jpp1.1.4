package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Sokolov", (byte) 20);
        userService.saveUser("Vladimir", "Ivanov", (byte) 25);
        userService.saveUser("Roman", "Petrov", (byte) 30);
        userService.saveUser("Nikita", "Orlov", (byte) 35);
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.getInstance().getSessionFactory().close();
    }
}
