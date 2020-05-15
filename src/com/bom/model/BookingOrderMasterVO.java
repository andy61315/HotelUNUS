package com.bom.model;

import java.io.Serializable;

import java.sql.*;

public class BookingOrderMasterVO implements Serializable, Comparable<BookingOrderMasterVO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String b_Order_No;
	private String cus_Id;
	private Integer total_Price;
	private Date start_Date;
	private Date end_Date;
	private Date co_Time;
	private Date order_Date;
	private Integer status;
	
	
	public BookingOrderMasterVO() {
		super();
	}


	public BookingOrderMasterVO(String b_Order_No, String cus_Id, Integer total_Price, Date start_Date, Date end_Date,
			Date co_Time, Date order_Date, Integer status) {
		super();
		this.b_Order_No = b_Order_No;
		this.cus_Id = cus_Id;
		this.total_Price = total_Price;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.co_Time = co_Time;
		this.order_Date = order_Date;
		this.status = status;
	}


	public String getB_Order_No() {
		return b_Order_No;
	}


	public void setB_Order_No(String b_Order_No) {
		this.b_Order_No = b_Order_No;
	}


	public String getCus_Id() {
		return cus_Id;
	}


	public void setCus_Id(String cus_Id) {
		this.cus_Id = cus_Id;
	}


	public Integer getTotal_Price() {
		return total_Price;
	}


	public void setTotal_Price(Integer total_Price) {
		this.total_Price = total_Price;
	}


	public Date getStart_Date() {
		return start_Date;
	}


	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}


	public Date getEnd_Date() {
		return end_Date;
	}


	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}


	public Date getCo_Time() {
		return co_Time;
	}


	public void setCo_Time(Date co_Time) {
		this.co_Time = co_Time;
	}


	public Date getOrder_Date() {
		return order_Date;
	}


	public void setOrder_Date(Date order_Date) {
		this.order_Date = order_Date;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "BookingOrderMasterVO [b_Order_No=" + b_Order_No + ", cus_Id=" + cus_Id + ", total_Price=" + total_Price
				+ ", start_Date=" + start_Date + ", end_Date=" + end_Date + ", co_Time=" + co_Time + ", order_Date="
				+ order_Date + ", status=" + status + "]";
	}


	@Override
	public int compareTo(BookingOrderMasterVO vo) {
		
		return vo.getB_Order_No().compareTo(this.getB_Order_No());
	}

	
}
