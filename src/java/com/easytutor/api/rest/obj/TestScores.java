package com.easytutor.api.rest.obj;

import java.io.Serializable;

/**
 * Created by root on 17.06.15.
 */
public class TestScores implements Serializable{

    private String id;
    private int scores;
    private int maxScores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getMaxScores() {
        return maxScores;
    }

    public void setMaxScores(int maxScores) {
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
