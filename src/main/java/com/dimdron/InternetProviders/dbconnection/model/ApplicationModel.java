package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 05.06.14
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "t_applications")
public class ApplicationModel {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @Column(name =  "address", nullable = false, length = 150)
    private String address;

    @Column(name =  "state", nullable = false)
    private Boolean state = false;

    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name = "repairman_id", nullable = true)
    private User repairman;

    @Column(name =  "text", nullable = false, length = 400)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    public ApplicationModel() {}

    public ApplicationModel(String address, District district, Provider provider, String text){
        this.provider = provider;
        this.text = text;
        this.district = district;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public User getRepairman() {
        return repairman;
    }

    public void setRepairman(User repairman) {
        this.repairman = repairman;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
