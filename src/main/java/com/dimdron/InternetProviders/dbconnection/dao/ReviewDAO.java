package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.Review;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 21.05.14
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public interface ReviewDAO {
    public void insert(Review provider);

    public void update(Review provider) ;

    public void delete (Review provider);

    public List<Review> getAll();
}
