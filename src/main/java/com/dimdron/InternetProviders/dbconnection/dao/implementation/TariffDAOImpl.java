package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.TariffDAO;
import com.dimdron.InternetProviders.dbconnection.model.Tariff;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class TariffDAOImpl implements TariffDAO {
    public void insert(Tariff tariff) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(tariff);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Add tariff");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(Tariff tariff) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tariff);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Update tariff");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Tariff tariff) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tariff);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Delete tariff");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Tariff> getAll() {
        Session session = null;
        List<Tariff> list = new ArrayList<Tariff>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "from Tariff";
            list = (List<Tariff>)session.createQuery(query).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public Double getMaxPrice() {  //SELECT MAX(column_name) FROM table_name
        Session session = null;
        Double speed;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT MAX(price) FROM t_tariffs";
            speed = (Double)session.createSQLQuery(query).uniqueResult() ;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return speed;
    }

    @Override
    public Double getMaxSpeed() {
        Session session = null;
        Double speed;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT MAX(speed) FROM t_tariffs";
            speed = (Double)session.createSQLQuery(query).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return speed;
    }

    @Override
    public Double getMinSpeed() {
        Session session = null;
        Double speed;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT MIN(speed) FROM t_tariffs";
            speed = (Double)session.createSQLQuery(query).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return speed;
    }
}
