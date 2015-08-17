package com.easytutor.models;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tests")
public class Test implements java.io.Serializable {

    private UUID testId;
    private String name;
    private String discipline;
    private String group;
    private Integer course;
    private List<TestsQuestions> testsQuestions = new ArrayList<>();
    private Date submissionTime;
    private TestResult testResult;
    private UserATutor userATutor;
    private Integer testCount;

    public Test() {
    }

    public Test(UUID testId) {
        this.testId = testId;
    }

    public Test(UUID testId, String name) {
        this.testId = testId;
        this.name = name;
    }

    public Test(UUID testId, String name, List<TestsQuestions> testsQuestions) {
        this.testId = testId;
        this.name = name;
        this.testsQuestions = testsQuestions;
    }

    @Transient
    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "test", cascade = CascadeType.ALL)
    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    @Column(name = "submission_time")
    @Temporal(TemporalType.DATE)
    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    @Column(name = "discipline_name")
    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Column(name = "groups")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Column(name = "course")
    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    @Id
    @Column(name = "test_id", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    public UUID getTestId() {
        return this.testId;
    }

    public void setTestId(UUID testId) {
        this.testId = testId;
    }

    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.test", cascade = CascadeType.ALL)
    public List<TestsQuestions> getTestsQuestions() {
        return testsQuestions;
    }

    public void setTestsQuestions(List<TestsQuestions> testsQuestions) {
        this.testsQuestions = testsQuestions;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atutor_user_id")
    public UserATutor getUserATutor() {
        return userATutor;
    }

    public void setUserATutor(UserATutor userATutor) {
        this.userATutor = userATutor;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", name='" + name + '\'' +
                ", discipline='" + discipline + '\'' +
                ", group='" + group + '\'' +
                ", course=" + course +
                ", testsQuestions=" + testsQuestions +
                ", submissionTime=" + submissionTime +
                ", testResult=" + testResult +
                ", userATutor=" + userATutor +
                ", testCount=" + testCount +
                '}';
    }
}