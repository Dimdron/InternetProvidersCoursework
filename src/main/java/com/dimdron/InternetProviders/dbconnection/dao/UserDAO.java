package com.dimdron.InternetProviders.dbconnection.dao;

import com.dimdron.InternetProviders.dbconnection.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 31.05.14
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {
    public void insert(User user);

    public void update(User user);

    public void delete(User user);

    public List<User> getAll();

    public User getByLogin(String login);

    public Integer getPerformedById(Integer id);

    public List<User> getByType(Byte t);

}
