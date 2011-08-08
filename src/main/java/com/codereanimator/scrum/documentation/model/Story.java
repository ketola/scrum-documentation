package com.codereanimator.scrum.documentation.model;

import java.util.List;

public class Story {
	private int number;
	private String description;
	private List<Test> tests;
	
	public Story(int number, String description) {
		super();
		this.number = number;
		this.description = description;
	}
	
	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
	
	
}
