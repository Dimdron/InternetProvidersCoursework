package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.AdditionalService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 21.05.14
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public interface AdditionalServiceDAO {
    public void insert(AdditionalService provider);

    public void update(AdditionalService provider) ;

    public void delete (AdditionalService provider);

    public List<AdditionalService> getAll();
}
