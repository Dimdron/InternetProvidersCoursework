package com.dimdron.InternetProviders.reportsmanager.manager;

import com.dimdron.InternetProviders.reportsmanager.annotations.Report;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 03.06.14
 * Time: 2:04
 * To change this template use File | Settings | File Templates.
 */
public class ProviderReport {
    @Report private String nameProvider;
    @Report private String telephoneProviders;
    @Report private String siteProvider;
    @Report private String quality ;
    @Report private String listRegion;
    @Report private String listConnection;
    @Report private String countTariffs;

    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public String getSiteProvider() {
        return siteProvider;
    }

    public void setSiteProvider(String siteProvider) {
        this.siteProvider = siteProvider;
    }

    public String getTelephoneProviders() {
        return telephoneProviders;
    }

    public void setTelephoneProviders(String telephoneProviders) {
        this.telephoneProviders = telephoneProviders;
    }

    public String getListRegion() {
        return listRegion;
    }

    public void setListRegion(String listRegion) {
        this.listRegion = listRegion;
    }

    public String getListConnection() {
        return listConnection;
    }

    public void setListConnection(String listConnection) {
        this.listConnection = listConnection;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCountTariffs() {
        return countTariffs;
    }

    public void setCountTariffs(String countTariffs) {
        this.countTariffs = countTariffs;
    }
}
