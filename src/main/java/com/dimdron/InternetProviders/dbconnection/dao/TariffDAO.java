package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.Tariff;
import java.util.List;

public interface TariffDAO {

    public void insert(Tariff tariff);

    public void update(Tariff tariff);

    public void delete(Tariff tariff);

    public List<Tariff> getAll();

    public Double getMaxPrice();

    public Double getMaxSpeed();

    public Double getMinSpeed();
}
