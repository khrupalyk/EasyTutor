package com.easytutor.orm.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * Created by root on 20.06.15.
 */
@Entity
@Table(name = "tests")
public class Test implements Serializable {


    private String id;


    private String name;

    private List<Question> questions = new ArrayList<>();

    @Id
    @Column(name = "test_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tests_questions", joinColumns = {
            @JoinColumn(name = "test_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") })
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
