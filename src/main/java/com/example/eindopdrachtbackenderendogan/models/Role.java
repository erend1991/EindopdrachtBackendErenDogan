package com.example.eindopdrachtbackenderendogan.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "rolename")
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}

