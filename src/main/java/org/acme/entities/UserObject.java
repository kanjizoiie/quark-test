package org.acme.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class UserObject extends PanacheEntity {
    public String name;
    public String realname;

    public String getName() {
        return name;
    }

    public UserObject setName(String name) {
        this.name = name;
        return this;
    }

    public String getRealname() {
        return realname;
    }

    public UserObject setRealname(String realname) {
        this.realname = realname;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserObject)) return false;
        UserObject that = (UserObject) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getRealname(), that.getRealname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRealname());
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", id=" + id +
                '}';
    }
}