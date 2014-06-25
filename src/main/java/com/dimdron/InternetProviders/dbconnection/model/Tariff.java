package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "t_tariffs")
public class Tariff {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Column(name = "name", nullable = false, length = 100)
    private  String name;

    @Column(name = "price", nullable = false)
    private  Double price;

    @Column(name = "speed", nullable = false)
    private  Double speed;

    @Column(name = "description")
    private  String description;

    public Tariff() {}

    public Tariff(String tariff, Double price, Double speed, String description, Provider provider) {
        this.name = tariff;
        this.description = description;
        this.speed = speed;
        this.price = price;
        this.provider = provider;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
