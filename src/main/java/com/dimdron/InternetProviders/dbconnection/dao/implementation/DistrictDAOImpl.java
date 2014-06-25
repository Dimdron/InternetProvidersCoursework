package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.DistrictDAO;
import com.dimdron.InternetProviders.dbconnection.model.District;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DistrictDAOImpl implements DistrictDAO{
    @Override
    public boolean insertUnique(District district) {
        District d = getByName(district.getName());
        if (d != null)
            return false;
        insert(district);
        return true;
    }

    @Override
    public void insert(District district) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(district);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Add district " + district);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(District district) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(district);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Update district "  + district);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(District district) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(district);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Delete district "  + district);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<District> getAll() {
        Session session = null;
        List<District> list = new ArrayList<District> ();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "from com.dimdron.InternetProviders.dbconnection.model.District";
            list = (List<District>)session.createQuery(query).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public List<District> getCoverage(Integer provider_id) {
        Session session = null;
        List<District> list = new ArrayList<District> ();
        try { //com.dimdron.InternetProviders.dbconnection.model.
            session = HibernateUtil.getSessionFactory().openSession();
            String query =
                    "select d from com.dimdron.InternetProviders.dbconnection.model.District d "
                            + "JOIN d.providers p where p.id = :providerId";
            list = (List<District>)session.createQuery(query).setInteger("providerId", provider_id).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public District getByName(String name) {
        Session session = null;
        District district;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select d from District d where d.name = :name";
            district = (District)session.createQuery(query).setString("name", name).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return district;
    }

    @Override
    public District getById(Integer id) {
        Session session = null;
        District district;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select d from District d where d.id = :id";
            district = (District)session.createQuery(query).setInteger("id", id).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
       return district;
    }
}
