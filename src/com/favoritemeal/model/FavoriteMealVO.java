package com.favoritemeal.model;

import java.io.Serializable;

public class FavoriteMealVO implements Serializable{
	private String cus_id;
	private String meal_no;
	
	public FavoriteMealVO() {
		super();
	}
	
	public FavoriteMealVO(String cus_id, String meal_no) {
		super();
		this.cus_id = cus_id;
		this.meal_no = meal_no;
	}
	
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public String getMeal_no() {
		return meal_no;
	}
	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}
	
}
