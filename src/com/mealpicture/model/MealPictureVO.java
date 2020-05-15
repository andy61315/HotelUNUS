package com.mealpicture.model;

import java.io.Serializable;

public class MealPictureVO implements Serializable{
	private String meal_picture_no;
	private String meal_no;
	private byte[] meal_pic;
	
	public MealPictureVO() {
		super();
	}
	
	public MealPictureVO(String meal_picture_no, String meal_no, byte[] meal_pic) {
		super();
		this.meal_picture_no = meal_picture_no;
		this.meal_no = meal_no;
		this.meal_pic = meal_pic;
	}
	
	public String getMeal_picture_no() {
		return meal_picture_no;
	}
	public void setMeal_picture_no(String meal_picture_no) {
		this.meal_picture_no = meal_picture_no;
	}
	public String getMeal_no() {
		return meal_no;
	}
	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}
	public byte[] getMeal_pic() {
		return meal_pic;
	}
	public void setMeal_pic(byte[] meal_pic) {
		this.meal_pic = meal_pic;
	}
	
	
}
