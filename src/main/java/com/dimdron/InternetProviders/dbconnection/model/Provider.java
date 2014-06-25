package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "t_providers")
public class Provider {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "site", length = 100)
    private String site;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Tariff> tariffs = new HashSet<Tariff>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="t_coverage",
            joinColumns = @JoinColumn(name="provider_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="district_id", referencedColumnName="id"))
    private Set<District> districts = new HashSet<District>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="PROVIDERS_TYPES",
            joinColumns = @JoinColumn(name="PROVIDER_ID", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="TYPE_ID", referencedColumnName="id"))
    private Set<ConnectionType> connectionTypes = new HashSet<ConnectionType>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "provider", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "providers", cascade = CascadeType.ALL)
    private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();

    public Provider() {}

    public Provider(String name, String site) {
        setName(name);
        setSite(site);
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<ConnectionType> getConnectionTypes() {
        return connectionTypes;
    }

    public void setConnectionTypes(Set<ConnectionType> connectionTypes) {
        this.connectionTypes = connectionTypes;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public double getCommunicationQuality() {
        double sum = 0;
        if(reviews.size() == 0)
            return 0;
        for(Review quality : reviews) {
            sum += quality.getCommunication();
        }
        return sum/reviews.size();
    }

    public double getRateQuality() {
        double sum = 0;
        if(reviews.size() == 0)
            return 0;
        for(Review quality : reviews) {
            sum += quality.getRate();
        }
        return sum/reviews.size();
    }

    public double getSupportQuality() {
        double sum = 0;
        if(reviews.size() == 0)
            return 0;
        for(Review quality : reviews) {
            sum += quality.getSupport();
        }
        return sum/reviews.size();
    }

    public String getTelephone() {
        if (telephone == null || telephone.length() == 0)
            return "None";
        else
            return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String connectionsToString() {
        if(connectionTypes.size() == 0)
            return "None" ;
        StringBuilder str = new StringBuilder();
        for (ConnectionType c : connectionTypes)
            str.append(c).append(", ");
        return str.replace(str.length()-3, str.length()-1, "").toString();
    }

    public String districtToString() {
        if(districts.size() == 0)
            return "None" ;
        StringBuilder str = new StringBuilder();
        for (District d : districts)
            str.append(d).append(", ");
        return str.replace(str.length()-3, str.length()-1, "").toString();
    }

    public double getCommonQuality() {
        double sum = getRateQuality() + getSupportQuality() + getCommunicationQuality();
        return  sum/3;
    }
}
