package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.ApplicationModelDAO;
import com.dimdron.InternetProviders.dbconnection.model.ApplicationModel;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 05.06.14
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationModelDAOImpl implements ApplicationModelDAO {

    @Override
    public void insert(ApplicationModel application) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(application);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Add application " + application);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(ApplicationModel application) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(application);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Update application " + application);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(ApplicationModel application) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(application);
            session.getTransaction().commit();
            UserSession.getInstance().logs("delete application " + application);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<ApplicationModel> getAll() {
        Session session = null;
        List<ApplicationModel> res = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            res = (List<ApplicationModel>)session.createQuery("from ApplicationModel").list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public List<ApplicationModel> getAll(Boolean state) {
        if (state == null)
            return getAll();
        Session session = null;
        List<ApplicationModel> res = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            res = (List<ApplicationModel>)
                    session.createQuery("select a from ApplicationModel a where a.state=:state")
                            .setBoolean("state", state)
                            .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public List<ApplicationModel> getByDistrict(Integer district_id) {
        Session session = null;
        List<ApplicationModel> res = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            res = (List<ApplicationModel>)session.
                    createQuery("select a from ApplicationModel a where a.district_id=:district_id")
                    .setInteger("district_id", district_id)
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public List<ApplicationModel> getByDistrict(Integer district_id, Boolean state) {
        if (state == null)
            return getByDistrict(district_id);
        Session session = null;
        List<ApplicationModel> res = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            res = (List<ApplicationModel>)
                    session.createQuery("select a from ApplicationModel a " +
                            "where a.state=:state and a.district_id=:district_id")
                            .setBoolean("state", state)
                            .setInteger("district_id", district_id)
                            .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public List<ApplicationModel> getByProviderAndDistrict(Integer provider_id, Integer district_id) {
        Session session = null;
        List<ApplicationModel> res = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String query = "SELECT * FROM t_applications WHERE provider_id = :provider_id " +
                    "AND district_id=:district_id";
            res = (List<ApplicationModel>)
                    session.createSQLQuery(query)
                            .addEntity(ApplicationModel.class)
                            .setInteger("provider_id", provider_id)
                            .setInteger("district_id", district_id)
                            .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }

    @Override
    public List<ApplicationModel> getByProviderAndDistrict(Integer provider_id, Integer district_id, Boolean state) {
        if (state == null)
            return getByProviderAndDistrict(provider_id, district_id);
        Session session = null;
        List<ApplicationModel> res = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String query = "SELECT * FROM t_applications WHERE provider_id = :provider_id " +
                    "AND district_id=:district_id AND state=:state";
            res = (List<ApplicationModel>)
                        session.createSQLQuery(query)
                                .addEntity(ApplicationModel.class)
                                .setInteger("provider_id", provider_id)
                                .setInteger("district_id", district_id)
                                .setBoolean("state", state)
                            .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }
}
