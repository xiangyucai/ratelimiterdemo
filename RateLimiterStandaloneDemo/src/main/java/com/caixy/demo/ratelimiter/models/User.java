package com.caixy.demo.ratelimiter.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "T_USERS")
@Entity
public class User {

    @Id
    private int id;

    private String license;

    private int tps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getTps() {
        return tps;
    }

    public void setTps(int tps) {
        this.tps = tps;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", license=" + license + ", tps=" + tps + "]";
    }
}
