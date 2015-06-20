package com.easytutor.rest.obj;

import java.io.Serializable;

/**
 * Created by root on 17.06.15.
 */
public class TestScores implements Serializable{

    private String id;
    private String scores;
    private String maxScores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getMaxScores() {
        return maxScores;
    }

    public void setMaxScores(String maxScores) {
        this.maxScores = maxScores;
    }

    @Override
    public String toString() {
        return "TestScores{" +
                "id='" + id + '\'' +
                ", scores='" + scores + '\'' +
                ", maxScores='" + maxScores + '\'' +
                '}';
    }
}
