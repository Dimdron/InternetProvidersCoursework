package com.dimdron.InternetProviders.reportsmanager.manager;

import com.dimdron.InternetProviders.reportsmanager.annotations.Report;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 06.06.14
 * Time: 2:16
 * To change this template use File | Settings | File Templates.
 */
public class RepairmanReport {
    @Report private String code;
    @Report private String name;
    @Report private String performed;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerformed() {
        return performed;
    }

    public void setPerformed(String performed) {
        this.performed = performed;
    }
}
