package com.spotifyexample.model;

public class Token {
	private static String name;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Token.name = name;
	}

	@Override
	public String toString() {
		return "Token [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
