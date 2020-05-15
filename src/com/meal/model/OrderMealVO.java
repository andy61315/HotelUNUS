package com.meal.model;


import java.util.Arrays;

public class OrderMealVO extends MealVO{
//繼承原meal父類別
	private static final long serialVersionUID = 1L;
	private Integer quantity;//餐點數量
	

	public OrderMealVO() {
		super();
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "OrderMealVO [quantity=" + quantity + ", getMeal_no()=" + getMeal_no() + ", getRes_no()=" + getRes_no()
				+ ", getMeal_type_no()=" + getMeal_type_no() + ", getPrice()=" + getPrice() + ", getMeal_name()="
				+ getMeal_name() + ", getMeal_introduction()=" + getMeal_introduction() + ", getMeal_date()="
				+ getMeal_date() + ", getMeal_status()=" + getMeal_status() + ", getMeal_picture()="
				+ Arrays.toString(getMeal_picture()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
