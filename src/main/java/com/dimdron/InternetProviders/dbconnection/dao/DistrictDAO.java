package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.District;

import java.util.List;

public interface DistrictDAO {

    public boolean insertUnique(District district);

    public void insert(District district);

    public void update(District district);

    public void delete(District district);

    public List<District> getAll();

    public List<District> getCoverage(Integer provider_id);

    public District getByName(String name);

    public District getById(Integer id);
}
