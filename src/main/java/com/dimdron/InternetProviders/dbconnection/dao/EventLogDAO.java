package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.EventLog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 31.05.14
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public interface EventLogDAO {

    public void insert(EventLog user);

    public void update(EventLog user);

    public void delete(EventLog user);

    public List<EventLog> getAll();
}
