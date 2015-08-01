package com.easytutor.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by root on 29.06.15.
 */

@Entity
@Table(name = "tests_results")
public class TestResult implements Serializable {

    private UUID testId;
    private int max;
    private double current;
    private Test test;

    @Id
    @Column(name = "test_id")
    public UUID getTestId() {
        return testId;
    }

    public void setTestId(UUID testId) {
        this.testId = testId;
    }

    @Column(name = "max")
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Column
    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return current + "/" + max;
    }
}
