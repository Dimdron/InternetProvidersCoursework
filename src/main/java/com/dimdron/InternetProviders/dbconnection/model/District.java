package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_districts")
public class District {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100 )
    private String name;

//    @ManyToMany
//    @JoinTable(name="coverage",
//            joinColumns = @JoinColumn(name="district_id", referencedColumnName="id"),
//            inverseJoinColumns = @JoinColumn(name="provider_id", referencedColumnName="id"))
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="t_coverage",
              joinColumns=@JoinColumn(name="district_id"),
            inverseJoinColumns=@JoinColumn(name="provider_id"))
    private Set<Provider> providers = new HashSet<Provider>();


    public District() {
    }

    public District(String name) {
        setName(name);
    }

    public District(int id, String name) {
        setName(name);
        setId(id);
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

    public Set<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }

    @Override
    public String toString() {
        return name ;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        String other = "";
        if (object instanceof District)
            other = ((District)object).getName();
        else if (object instanceof String)
            other = (String)object;
        else
            return false;

        if ((this.name == null && other != null) ||
                (this.name != null) && !this.name.equals(other))
            return false;
        return true;
    }
}
