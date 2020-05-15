package com.meal.model;

import java.io.Serializable;
import java.sql.Date;

public class MealVO implements Serializable{
	private String meal_no;//餐點編號
	private String res_no;//餐廳編號
	private String meal_type_no;//餐點類別編號
	private Integer price;//價格
	private String meal_name;//餐點名稱
	private String meal_introduction;//餐點介紹
	private Date meal_date;//餐點日期
	private Integer meal_status;//餐點狀態
	private byte[] meal_picture;//圖片
	
	public MealVO() {
		super();
	}
	
	public MealVO(String meal_no, String res_no, String meal_type_no, Integer price, String meal_name,
			String meal_introduction, Date meal_date, Integer meal_status, byte[] meal_picture) {
		super();
		this.meal_no = meal_no;
		this.res_no = res_no;
		this.meal_type_no = meal_type_no;
		this.price = price;
		this.meal_name = meal_name;
		this.meal_introduction = meal_introduction;
		this.meal_date = meal_date;
		this.meal_status = meal_status;
		this.meal_picture = meal_picture;
	}

	public String getMeal_no() {
		return meal_no;
	}

	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}

	public String getRes_no() {
		return res_no;
	}

	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}

	public String getMeal_type_no() {
		return meal_type_no;
	}

	public void setMeal_type_no(String meal_type_no) {
		this.meal_type_no = meal_type_no;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getMeal_name() {
		return meal_name;
	}

	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}

	public String getMeal_introduction() {
		return meal_introduction;
	}

	public void setMeal_introduction(String meal_introduction) {
		this.meal_introduction = meal_introduction;
	}

	public Date getMeal_date() {
		return meal_date;
	}

	public void setMeal_date(Date meal_date) {
		this.meal_date = meal_date;
	}

	public Integer getMeal_status() {
		return meal_status;
	}

	public void setMeal_status(Integer meal_status) {
		this.meal_status = meal_status;
	}

	public byte[] getMeal_picture() {
		return meal_picture;
	}

	public void setMeal_picture(byte[] meal_picture) {
		this.meal_picture = meal_picture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meal_no == null) ? 0 : meal_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealVO other = (MealVO) obj;
		if (meal_no == null) {
			if (other.meal_no != null)
				return false;
		} else if (!meal_no.equals(other.meal_no))
			return false;
		return true;
	}
	
	
}
