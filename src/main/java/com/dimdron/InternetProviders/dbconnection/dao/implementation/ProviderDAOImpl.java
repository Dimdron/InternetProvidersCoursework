package com.dimdron.InternetProviders.dbconnection.dao.implementation;


import com.dimdron.InternetProviders.dbconnection.dao.ProviderDAO;
import com.dimdron.InternetProviders.dbconnection.model.Provider;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProviderDAOImpl implements ProviderDAO {
    @Override
    public void insert(Provider provider) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(provider);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Add provider '" + provider+ "'");
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
    public void update(Provider provider) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(provider);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Update provider '" + provider+ "'");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Provider provider) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(provider);
            session.getTransaction().commit();
            UserSession.getInstance().logs("Delete provider '" + provider+ "'");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Provider> getAll() {
        Session session = null;
        List<Provider> list = new ArrayList<Provider>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (ArrayList<Provider>)session.createQuery("from Provider").list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public Provider getProviderById(int id) {
        Session session = null;
        Provider provider;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
          //  String query = "from Provider p where p.Id = :id";
            provider = (Provider)session.load(Provider.class, id);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return provider;
    }

    @Override
    public Provider getByName(String name) {
        Session session = null;
        Provider provider;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "select p from Provider p where p.name = :name";
            provider = (Provider)session.createQuery(query).setString("name", name).uniqueResult();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return provider;
    }

    @Override
    public List<Provider> getByPriceAndSpeed(double price, double speed) {
        Session session = null;
        List<Provider> providers;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT * FROM t_providers WHERE id IN " +
                    "(SELECT provider_id FROM t_tariffs WHERE price<=:price AND speed=>:speed)";
            providers = (ArrayList<Provider>)session.createSQLQuery(query)
                    .addEntity(Provider.class).setDouble("price", price).setDouble("speed", speed).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return providers;
    }

    @Override
    public List<Provider> getByPriceSpeedDistrict(Double price, Double speed, Integer district_id) {
        Session session = null;
        List<Provider> providers;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT * FROM t_providers WHERE id IN " +
                    "(SELECT DISTINCT provider_id FROM t_coverage JOIN  t_districts " +
                    "ON t_coverage.district_id = t_districts.id " +
                    "WHERE t_coverage.district_id = :district_id " +
                    "AND t_coverage.provider_id IN " +
                        "(SELECT provider_id FROM t_tariffs " +
                        "WHERE price<=:price AND speed>=:speed))";

            providers = (ArrayList<Provider>)session.createSQLQuery(query)
                    .addEntity(Provider.class)
                    .setInteger("district_id", district_id)
                    .setDouble("price", price)
                    .setDouble("speed", speed)
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return providers;
    }

    @Override
    public List<Provider> getByPriceSpeedConnectionType(Double price, Double speed, Integer type_id) {
        Session session = null;
        List<Provider> providers;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT * FROM t_providers WHERE id IN " +
                    "(SELECT provider_id FROM PROVIDERS_TYPES JOIN  t_connections " +
                    "ON PROVIDERS_TYPES.TYPE_ID = t_connections.id " +
                    "WHERE PROVIDERS_TYPES.TYPE_ID = :type_id " +
                    "AND PROVIDERS_TYPES.provider_id IN " +
                    "(SELECT provider_id FROM t_tariffs " +
                    "WHERE price<=:price AND speed>=:speed:speed))";

            providers = (ArrayList<Provider>)session.createSQLQuery(query)
                    .addEntity(Provider.class)
                    .setInteger("type_id", type_id)
                    .setDouble("price", price)
                    .setDouble("speed", speed)
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return providers;
    }

    @Override
    public List<Provider> getByPriceSpeedDistrictConnectionType(Double price, Double speed, Integer district_id, Integer type_id) {
        Session session = null;
        List<Provider> providers;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String query = "SELECT * FROM t_providers WHERE id IN " +
                    "(SELECT provider_id FROM PROVIDERS_TYPES JOIN  t_connections " +
                    "ON PROVIDERS_TYPES.TYPE_ID = t_connections.id " +
                    "WHERE PROVIDERS_TYPES.TYPE_ID = :type_id " +
                    "AND PROVIDERS_TYPES.PROVIDER_ID IN " +
                    "(SELECT DISTINCT provider_id FROM t_coverage JOIN  t_districts " +
                    "ON t_coverage.district_id = t_districts.id " +
                    "WHERE t_coverage.district_id = :district_id " +
                    "AND t_coverage.provider_id IN " +
                    "(SELECT provider_id FROM t_tariffs " +
                    "WHERE price<=:price AND speed>=:speed)))";

            providers = (ArrayList<Provider>)session.createSQLQuery(query)
                    .addEntity(Provider.class)
                    .setInteger("type_id",type_id)
                    .setInteger("district_id", district_id)
                    .setDouble("price", price)
                    .setDouble("speed", speed)
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return providers;
    }

}
