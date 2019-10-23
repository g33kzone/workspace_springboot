package com.g33kzone.java;

public class Singleton {

	private static Singleton singleInstance = null;

	public String s;

	private Singleton() {
		super();
		// TODO Auto-generated constructor stub
		s = "Hello I am a string part of Singleton class";
	}

	public static Singleton getSingleInstance() {

		if (singleInstance == null) {
			singleInstance = new Singleton();
		}
		return singleInstance;
	}

}
