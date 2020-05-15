package com.bod.model;

import java.io.Serializable; 

public class BookOrderDetailVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String b_order_no;
	private String room_type_no;
	private Integer quantity;
	private Integer total_add_people;
	private Integer price;

	public BookOrderDetailVO() {
		super();
	}

	public BookOrderDetailVO(String b_order_no, String room_type_no, Integer quantity, Integer total_add_people,
			Integer price) {
		super();
		this.b_order_no = b_order_no;
		this.room_type_no = room_type_no;
		this.quantity = quantity;
		this.total_add_people = total_add_people;
		this.price = price;
	}

	public String getB_order_no() {
		return b_order_no;
	}

	public void setB_order_no(String b_order_no) {
		this.b_order_no = b_order_no;
	}

	public String getRoom_type_no() {
		return room_type_no;
	}

	public void setRoom_type_no(String room_type_no) {
		this.room_type_no = room_type_no;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotal_add_people() {
		return total_add_people;
	}

	public void setTotal_add_people(Integer total_add_people) {
		this.total_add_people = total_add_people;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookingOrderDetailVO [b_order_no=" + b_order_no + ", room_type_no=" + room_type_no + ", quantity="
				+ quantity + ", total_add_people=" + total_add_people + ", price=" + price + "]";
	}

}
