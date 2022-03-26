package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
private SessionFactory sessionFactory = Util.getSessionFactory();
private Transaction transaction = null;
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class);
            transaction.commit();
            sessionFactory.close();
        } catch (HibernateException e ) {
            transaction.rollback();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
            transaction.commit();
            sessionFactory.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        transaction.commit();
            sessionFactory.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        transaction.commit();
            sessionFactory.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> userList = session.createSQLQuery("FROM users").list();
            transaction.commit();
            sessionFactory.close();

        } catch (HibernateException e ) {
            transaction.rollback();
    }
        return null;
    }
    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE users");
        transaction.commit();
            sessionFactory.close();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
    }

