package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.Provider;
import java.util.List;

public interface ProviderDAO {

    public void insert(Provider provider);

    public void update(Provider provider) ;

    public void delete (Provider provider);

    public List<Provider> getAll();

    public Provider getProviderById(int id);

    public Provider getByName(String name);

    public List<Provider> getByPriceAndSpeed(double price, double speed);

    public List<Provider> getByPriceSpeedDistrict(Double price, Double speed, Integer district);

    public List<Provider> getByPriceSpeedConnectionType(Double price, Double speed, Integer connection);

    public List<Provider> getByPriceSpeedDistrictConnectionType(Double price,
                                                                Double speed, Integer district, Integer connection);
}
