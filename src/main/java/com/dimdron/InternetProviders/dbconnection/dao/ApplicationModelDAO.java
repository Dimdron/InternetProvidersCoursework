package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.ApplicationModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 05.06.14
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationModelDAO {
    public void insert(ApplicationModel application);

    public void update(ApplicationModel application);

    public void delete(ApplicationModel application);

    public List<ApplicationModel> getAll();

    public List<ApplicationModel> getAll(Boolean state);

    public List<ApplicationModel>getByDistrict(Integer district_id);

    public List<ApplicationModel> getByDistrict(Integer district_id, Boolean state);

    public List<ApplicationModel> getByProviderAndDistrict(Integer provider_id, Integer district_id);

    public List<ApplicationModel> getByProviderAndDistrict(Integer provider_id, Integer district_id, Boolean state);
}
