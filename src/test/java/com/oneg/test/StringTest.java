package com.oneg.test;

public class StringTest {
	public static void main(String[] args) {
		String s = "(123)-456-7890";
		s = s.replaceAll("[^a-zA-Z0-9]", "");
		System.out.println(s);
	}

}