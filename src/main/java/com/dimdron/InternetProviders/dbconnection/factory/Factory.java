package com.dimdron.InternetProviders.dbconnection.factory;

import com.dimdron.InternetProviders.dbconnection.dao.*;
import com.dimdron.InternetProviders.dbconnection.dao.implementation.*;

public class Factory {
    private static Factory instance = null;
    private static AdditionalServiceDAO serviceDAO = null;
    private static ConnectionTypeDAO typeDAO;
    private static DistrictDAO districtDAO;
    private static ProviderDAO providerDAO;
    private static ReviewDAO reviewDAO;
    private static TariffDAO tariffDAO;
    private static UserDAO userDAO;
    private static EventLogDAO eventLogDAO;
    private static ApplicationModelDAO applicationDAO;

    public static synchronized Factory getInstance() {
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public ApplicationModelDAO getApplicationModelDAO() {
        if (applicationDAO == null)
            applicationDAO = new ApplicationModelDAOImpl();
        return applicationDAO;
    }

    public EventLogDAO getEventLogDAO() {
        if (eventLogDAO == null)
            eventLogDAO = new EventLogDAOImpl();
        return eventLogDAO;
    }

    public AdditionalServiceDAO getServiceDAO() {
        if (serviceDAO == null){
            serviceDAO = new AdditionalServiceDAOImpl();
        }
        return serviceDAO;
    }

    public ConnectionTypeDAO getTypeDAO() {
        if (typeDAO == null){
            typeDAO = new ConnectionTypeDAOImpl();
        }
        return typeDAO;
    }

    public DistrictDAO getDistrictDAO() {
        if (districtDAO == null){
            districtDAO = new DistrictDAOImpl();
        }
        return districtDAO;
    }

    public ProviderDAO getProviderDAO() {
        if (providerDAO == null){
            providerDAO = new ProviderDAOImpl();
        }
        return providerDAO;
    }

    public ReviewDAO getReviewDAO() {
        if (reviewDAO == null){
            reviewDAO = new ReviewDAOImpl();
        }
        return reviewDAO;
    }

    public TariffDAO getTariffDAO() {
        if (tariffDAO == null){
            tariffDAO = new TariffDAOImpl();
        }
        return tariffDAO;
    }

    public UserDAO getUser() {
        if (userDAO == null)
            userDAO = new UserDAOImpl();
        return userDAO;
    }
}
