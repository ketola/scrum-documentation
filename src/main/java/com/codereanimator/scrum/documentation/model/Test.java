package com.codereanimator.scrum.documentation.model;

public class Test {
	public enum Type {JUNIT, MANUAL, ROBOT};
	
	private String name;
	private String description;
	private Type type;
	
	public Test(String name, String description, Type type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Type getType() {
		return type;
	}
	
}
