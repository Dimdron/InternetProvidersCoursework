package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.AdditionalServiceDAO;
import com.dimdron.InternetProviders.dbconnection.model.AdditionalService;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 21.05.14
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class AdditionalServiceDAOImpl implements AdditionalServiceDAO {
    @Override
    public void insert(AdditionalService additionalService) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(additionalService);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Add service");
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(AdditionalService additionalService) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(additionalService);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Update service");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(AdditionalService additionalService) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(additionalService);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Delete service");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<AdditionalService> getAll() {
        Session session = null;
        List<AdditionalService> list = new ArrayList<AdditionalService>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (ArrayList<AdditionalService>)session.createCriteria(AdditionalService.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }
}
