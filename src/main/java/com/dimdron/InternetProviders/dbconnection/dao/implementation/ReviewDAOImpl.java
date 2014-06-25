package com.dimdron.InternetProviders.dbconnection.dao.implementation;

import com.dimdron.InternetProviders.dbconnection.dao.ReviewDAO;
import com.dimdron.InternetProviders.dbconnection.model.Review;
import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 21.05.14
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
public class ReviewDAOImpl implements ReviewDAO {
    @Override
    public void insert(Review review) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(review);
            session.getTransaction().commit();
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
    public void update(Review review) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(review);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Review review) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(review);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Review> getAll() {
        Session session = null;
        List<Review> list = new ArrayList<Review>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (ArrayList<Review>)session.createCriteria(Review.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }
}
