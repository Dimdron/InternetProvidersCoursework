package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.ConnectionType;
import java.util.List;


public interface ConnectionTypeDAO {

    public boolean insertUnique(ConnectionType type);

    public void insert(ConnectionType type);

    public void delete(ConnectionType type);

    public void update(ConnectionType type);

    public List<ConnectionType> getAll();

    public ConnectionType getById(int id);

    public ConnectionType getByName(String name);
}
