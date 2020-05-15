package com.checkroomremain;

public class Test1 {
	public static void main(String[] args) {
		String regexImg = ".+\\.(jpg|png|gif|bmp|jpeg)\\b";
		System.out.println("tm-img-04.jpg".matches(regexImg));
		System.out.println("and.png".matches(regexImg));
		System.out.println("and.jpeg".matches(regexImg));
		System.out.println("".matches(regexImg));
	}
}
