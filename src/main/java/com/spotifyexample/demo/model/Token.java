package com.spotifyexample.demo.model;

public class Token {
	private static String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		Token.id = id;
	}

	@Override
	public String toString() {
		return "Token [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}	
	
}
