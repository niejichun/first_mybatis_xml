package com.itheima.domain;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", uid=" + uid +
                ", rid=" + rid +
                '}';
    }
}
