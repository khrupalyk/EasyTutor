package com.easytutor.orm.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tests")
public class Test implements java.io.Serializable {

	private Integer testId;
	private String name;
	private List<Question> questions = new ArrayList<Question>(0);

	public Test() {
	}

	public Test(String name) {
		this.name = name;
	}

	public Test(String name, List<Question> stocks) {
		this.name = name;
		this.questions = stocks;
	}

	@Id
//	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "test_id", unique = true, nullable = false)
	public Integer getTestId() {
		return this.testId;
	}

	public void setTestId(Integer categoryId) {
		this.testId = categoryId;
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