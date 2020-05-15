package com.resmealom.model;

import java.sql.Date;

public class ResMealOrderMasterVO implements java.io.Serializable{
    //餐廳用餐訂單
	private String resMealOrderNo;//用餐訂單編號
	private String bOrderNo;//訂房訂單編號
	private Integer tableNo;//桌號
	private Integer totalPrice;//總價
	private Date orderDate;//訂單日期
	private Integer orderStatus;//訂單狀態
	private String specialRequirement;//特殊要求
	
	
	public ResMealOrderMasterVO(String resMealOrderNo, String bOrderNo, Integer tableNo, Integer totalPrice, Date orderDate,
			Integer orderStatus, String specialRequirement) {
		super();
		this.resMealOrderNo = resMealOrderNo;
		this.bOrderNo = bOrderNo;
		this.tableNo = tableNo;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.specialRequirement = specialRequirement;
	}
	
	

	public ResMealOrderMasterVO() {
		super();
	}


	

	@Override
	public String toString() {
		return "ResMealOrderMasterVO [resMealOrderNo=" + resMealOrderNo + ", bOrderNo=" + bOrderNo + ", tableNo="
				+ tableNo + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", specialRequirement=" + specialRequirement + "]";
	}



	public String getResMealOrderNo() {
		return resMealOrderNo;
	}

	public void setResMealOrderNo(String resMealOrderNo) {
		this.resMealOrderNo = resMealOrderNo;
	}

	public String getbOrderNo() {
		return bOrderNo;
	}

	public void setbOrderNo(String bOrderNo) {
		this.bOrderNo = bOrderNo;
	}

	public Integer getTableNo() {
		return tableNo;
	}

	public void setTableNo(Integer tableNo) {
		this.tableNo = tableNo;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSpecialRequirement() {
		return specialRequirement;
	}

	public void setSpecialRequirement(String specialRequirement) {
		this.specialRequirement = specialRequirement;
	}
	
	
	
	
	
}
