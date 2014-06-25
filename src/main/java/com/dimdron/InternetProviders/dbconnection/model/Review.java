package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_reviews")
public class Review {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
    private Integer id;

    @Column(name = "comment", length = 1000, nullable = false)
    private String comment;

    @Column(name = "communication", nullable = false)
    private Byte communication;

    @Column(name = "rate", nullable = false)
    private Byte rate;

    @Column(name = "support", nullable = false)
    private Byte support;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    public Review() {}

    public Review(String comment, Byte support, Byte speed, Byte communication) {
        setComment(comment);
        setRate(speed);
        setCommunication(communication);
        setSupport(support);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Byte getSupport() {
        return support;
    }

    public void setSupport(Byte support) {
        this.support = support;
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }

    public Byte getCommunication() {
        return communication;
    }

    public void setCommunication(Byte communication) {
        this.communication = communication;
    }
}
