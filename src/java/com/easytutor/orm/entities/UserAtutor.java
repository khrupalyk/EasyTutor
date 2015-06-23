package com.easytutor.orm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by root on 22.06.15.
 */

@Entity
@Table(name = "users_atutor")
public class UserAtutor implements Serializable{

    private int userId;
    private String name;

    @Id
    @Column(name = "user_atutor_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
