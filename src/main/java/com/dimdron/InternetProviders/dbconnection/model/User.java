package com.dimdron.InternetProviders.dbconnection.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 31.05.14
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "login", length = 100, nullable = false)
    private String login;

    @Column(name = "userlevel", nullable = false)
    private Byte level;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<EventLog> logs = new HashSet<EventLog>();

    public User() {}

    public User(String name, String login, String password, Byte level) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.level = level;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<EventLog> getLogs() {
        return logs;
    }

    public void setLogs(Set<EventLog> logs) {
        this.logs = logs;
    }

    @Override public String toString() {
        return login;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}
