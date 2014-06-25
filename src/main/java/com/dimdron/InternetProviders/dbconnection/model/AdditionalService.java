package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_services")
public class AdditionalService {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "information", length = 1000)
    private String information;

    @ManyToMany
    @JoinTable(name="provider_service",
            joinColumns = @JoinColumn(name="provider_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="service_id", referencedColumnName="id"))
    private Set<Provider> providers = new HashSet<Provider>();

    public AdditionalService() { }

    public AdditionalService(String name, String information) {
        setName(name);
        setInformation(information);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }
}
