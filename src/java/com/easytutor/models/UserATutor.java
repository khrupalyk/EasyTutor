package com.easytutor.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22.06.15.
 */

@Entity
@Table(name = "users_atutor")
public class UserATutor implements Serializable{

    private String name;
    private List<Test> tests = new ArrayList<>();

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "userATutor")
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
