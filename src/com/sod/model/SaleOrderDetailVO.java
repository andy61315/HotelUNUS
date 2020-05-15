package com.sod.model;

import java.io.Serializable;

public class SaleOrderDetailVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String room_type_no;
	private String sapl_no;
//	private Integer sapl_price;

	public SaleOrderDetailVO() {
		super();
	}

	public SaleOrderDetailVO(String room_type_no, String sapl_no, Integer sapl_price) {
		super();
		this.room_type_no = room_type_no;
		this.sapl_no = sapl_no;
//		this.sapl_price = sapl_price;
	}

	public String getRoom_type_no() {
		return room_type_no;
	}

	public void setRoom_type_no(String room_type_no) {
		this.room_type_no = room_type_no;
	}

	public String getSapl_no() {
		return sapl_no;
	}

	public void setSapl_no(String sapl_no) {
		this.sapl_no = sapl_no;
	}

	@Override
	public String toString() {
		return "SaleOrderDetailVO [room_type_no=" + room_type_no + ", sapl_no=" + sapl_no + "]";
	}

//	public Integer getSapl_price() {
//		return sapl_price;
//	}
//
//	public void setSapl_price(Integer sapl_price) {
//		this.sapl_price = sapl_price;
//	}

	
}
