package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.UserDAO;
import com.dimdron.InternetProviders.dbconnection.model.User;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 31.05.14
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public void insert(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        Session session = null;
        List<User> list;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "from User";
            list = (List<User>)session.createQuery(query).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public User getByLogin(String login) {
        Session session = null;
        User user;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select u from User u where u.login=:login";
            user = (User)session.createQuery(query).setString("login", login).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public Integer getPerformedById(Integer id) {
        Session session = null;
        Integer res;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT COUNT(id) FROM t_applications WHERE repairman_id = :id";
            res = (Integer)session.createSQLQuery(query).addEntity(Integer.class).setInteger("id", id).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public List<User> getByType(Byte t) {
        Session session = null;
        List<User> users;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select u from User u where u.level = :t";
            users = (List<User>)session.createQuery(query).setByte("t", t).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

}
