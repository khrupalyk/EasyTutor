package com.easytutor.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tests")
public class Test implements java.io.Serializable {

	private UUID testId;
	private String name;
	private List<TestsQuestions> testsQuestions = new ArrayList<>();

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.question", cascade=CascadeType.ALL)
	public List<TestsQuestions> getTestsQuestions() {
		return testsQuestions;
	}

	public void setTestsQuestions(List<TestsQuestions> testsQuestions) {
		this.testsQuestions = testsQuestions;
	}

}