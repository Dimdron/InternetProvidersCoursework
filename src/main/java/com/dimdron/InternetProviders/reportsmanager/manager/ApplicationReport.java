package com.dimdron.InternetProviders.reportsmanager.manager;

import com.dimdron.InternetProviders.reportsmanager.annotations.Report;
import javafx.fxml.FXML;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 05.06.14
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationReport {
    @Report private String address;
    @Report private String region;
    @Report private String provider;
    @Report private String problem ;
    @Report private String state;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
