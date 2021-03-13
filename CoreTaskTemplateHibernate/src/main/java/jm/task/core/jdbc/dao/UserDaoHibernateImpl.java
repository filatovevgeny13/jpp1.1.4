package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS `users` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `lastName` varchar(45) NOT NULL,\n" +
                "  `age` int NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
        Util.getSessionFactory().close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "drop table if exists users";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
        Util.getSessionFactory().close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
        Util.getSessionFactory().close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        Util.getSessionFactory().close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        Util.getSessionFactory().close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
        session.close();
        Util.getSessionFactory().close();
    }
}
