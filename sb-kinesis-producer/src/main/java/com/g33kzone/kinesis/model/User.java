package com.g33kzone.kinesis.model;

public class User {

	private String name;

	private int year;
	
	public User() {
	}

	public User(String name, int year) {
		super();
		this.name = name;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", year=" + year + "]";
	}

}
