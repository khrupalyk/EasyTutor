package com.easytutor.orm.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tests")
public class Test implements java.io.Serializable {

	private UUID testId;
	private String name;
	private List<Question> questions = new ArrayList<>(0);

	public Test() {
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


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tests")
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}