package com.dimdron.InternetProviders.dbconnection.usersession;

import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.EventLog;
import com.dimdron.InternetProviders.dbconnection.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 31.05.14
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class UserSession {
    private static UserSession instance;
    private static User user;

    public static synchronized UserSession getInstance() {
        if (instance == null)
            instance = new UserSession();
        return instance;
    }

    public User getUser() {
        return user;
    }

    public boolean sessionStart(String login, String password) {
        User user = Factory.getInstance().getUser().getByLogin(login);
        if (user == null)
            return false;
        if (!user.getPassword().equals(password))
            return false;
        this.user = user;
        return true;
    }

    public void logs(String event) {
        if (user != null)
            Factory.getInstance().getEventLogDAO().insert(new EventLog(event, user));
    }

    public void close() {
        user = null;
    }

    public boolean isOpen() {
        return user != null;
    }
}
