package com.roomtype.model;

import java.io.Serializable;

public class RoomTypeVO implements Serializable {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private String room_Type_No;
	private String room_Type_Name;
	private String article;
	private Integer quantity;
	private Integer person_Capacity;
	private Integer add_People;
	private Integer price;
	private Integer holiday_Price;
	private Integer workingDay_Price;
	private Integer total_People;
	private Integer star_Amount;
	private Integer star_People;
	private Integer room_Type_Status;

	public RoomTypeVO() {
		super();
	}

	public RoomTypeVO(String room_Type_No, String room_Type_Name, String article, Integer quantity,
			Integer person_Capacity, Integer add_People, Integer price, Integer holiday_Price, Integer workingDay_Price,
			Integer total_People, Integer star_Amount, Integer star_People, Integer room_Type_Status) {
		super();
		this.room_Type_No = room_Type_No;
		this.room_Type_Name = room_Type_Name;
		this.article = article;
		this.quantity = quantity;
		this.person_Capacity = person_Capacity;
		this.add_People = add_People;
		this.price = price;
		this.holiday_Price = holiday_Price;
		this.workingDay_Price = workingDay_Price;
		this.total_People = total_People;
		this.star_Amount = star_Amount;
		this.star_People = star_People;
		this.room_Type_Status = room_Type_Status;
	}

	public String getRoom_Type_No() {
		return room_Type_No;
	}

	public void setRoom_Type_No(String room_Type_No) {
		this.room_Type_No = room_Type_No;
	}

	public String getRoom_Type_Name() {
		return room_Type_Name;
	}

	public void setRoom_Type_Name(String room_Type_Name) {
		this.room_Type_Name = room_Type_Name;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPerson_Capacity() {
		return person_Capacity;
	}

	public void setPerson_Capacity(Integer person_Capacity) {
		this.person_Capacity = person_Capacity;
	}

	public Integer getAdd_People() {
		return add_People;
	}

	public void setAdd_People(Integer add_People) {
		this.add_People = add_People;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getHoliday_Price() {
		return holiday_Price;
	}

	public void setHoliday_Price(Integer holiday_Price) {
		this.holiday_Price = holiday_Price;
	}

	public Integer getWorkingDay_Price() {
		return workingDay_Price;
	}

	public void setWorkingDay_Price(Integer workingDay_Price) {
		this.workingDay_Price = workingDay_Price;
	}

	public Integer getTotal_People() {
		return total_People;
	}

	public void setTotal_People(Integer total_People) {
		this.total_People = total_People;
	}

	public Integer getStar_Amount() {
		return star_Amount;
	}

	public void setStar_Amount(Integer star_Amount) {
		this.star_Amount = star_Amount;
	}

	public Integer getStar_People() {
		return star_People;
	}

	public void setStar_People(Integer star_People) {
		this.star_People = star_People;
	}

	public Integer getRoom_Type_Status() {
		return room_Type_Status;
	}

	public void setRoom_Type_Status(Integer room_Type_Status) {
		this.room_Type_Status = room_Type_Status;
	}

	@Override
	public String toString() {
		return "RoomTypeVO [room_Type_No=" + room_Type_No + ", room_Type_Name=" + room_Type_Name + ", article="
				+ article + ", quantity=" + quantity + ", person_Capacity=" + person_Capacity + ", add_People="
				+ add_People + ", price=" + price + ", holiday_Price=" + holiday_Price + ", workingDay_Price="
				+ workingDay_Price + ", total_People=" + total_People + ", star_Amount=" + star_Amount
				+ ", star_People=" + star_People + ", room_Type_Status=" + room_Type_Status + "]";
	}
	
	
}
