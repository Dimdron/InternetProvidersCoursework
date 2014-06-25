package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.ConnectionTypeDAO;
import com.dimdron.InternetProviders.dbconnection.model.ConnectionType;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class ConnectionTypeDAOImpl implements ConnectionTypeDAO {

    @Override
    public boolean insertUnique(ConnectionType type) {
        ConnectionType d = getByName(type.getType());
        if (d != null)
            return false;
        insert(type);
        return true;
    }
    @Override
    public void insert(ConnectionType type) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(type);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Add connection type " + type);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(ConnectionType type) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(type);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Delete connection type " + type);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(ConnectionType type) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(type);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Update connection type " + type);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<ConnectionType> getAll() {
        Session session = null;
        List<ConnectionType> list;// = new ArrayList<ConnectionType>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (ArrayList<ConnectionType>)session.createQuery("from ConnectionType").list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public ConnectionType getById(int id) {
        Session session = null;
        ConnectionType res;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select c from ConnectionType c where c.id = :id";
            res = (ConnectionType)session.createQuery(query).setInteger("id", id).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public ConnectionType getByName(String type) {
        Session session = null;
        ConnectionType connectionType;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select t from ConnectionType t where t.type = :type";
            connectionType = (ConnectionType)session.createQuery(query).setString("type", type).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }
}
