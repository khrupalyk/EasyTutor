package com.easytutor.models;

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

	public Test() {
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

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "test", cascade = CascadeType.ALL)
	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	@Column(name = "submission_time")
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.test", cascade=CascadeType.ALL)
	public List<TestsQuestions> getTestsQuestions() {
		return testsQuestions;
	}

	public void setTestsQuestions(List<TestsQuestions> testsQuestions) {
		this.testsQuestions = testsQuestions;
	}

}