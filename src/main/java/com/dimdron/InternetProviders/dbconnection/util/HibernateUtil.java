package com.dimdron.InternetProviders.dbconnection.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate/hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        if (sessionFactory!=null && sessionFactory.isClosed())
            sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
