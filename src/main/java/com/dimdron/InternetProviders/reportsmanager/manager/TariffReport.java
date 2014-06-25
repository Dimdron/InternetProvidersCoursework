package com.dimdron.InternetProviders.reportsmanager.manager;

import com.dimdron.InternetProviders.reportsmanager.annotations.Report;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 04.06.14
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public class TariffReport {
    @Report private String nameTariff;
    @Report private Double speed;
    @Report private Double price;
    @Report private String providerName;
    @Report private String description;
    @Report private String region;
    @Report private String connection;


    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}
