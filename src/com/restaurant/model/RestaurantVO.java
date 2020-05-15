package com.restaurant.model;

public class RestaurantVO implements java.io.Serializable {
    //餐廳
	
	private String resNo;//餐廳編號
	private String resName;//餐廳名稱
	private Integer totalSeat;//座位數
	private String resContact;//聯絡人
	private String resPhone;//電話
	private Integer resStatus;//餐廳狀態
	
	public RestaurantVO(String resNo, String resName, Integer totalSeat, String resContact, String resPhone,
			Integer resStatus) {
		super();
		this.resNo = resNo;
		this.resName = resName;
		this.totalSeat = totalSeat;
		this.resContact = resContact;
		this.resPhone = resPhone;
		this.resStatus = resStatus;
	}
	
	

	public RestaurantVO() {
		super();
	}



	@Override
	public String toString() {
		return "餐廳編號=" + resNo + ", 餐廳名稱=" + resName + ", 總座位數=" + totalSeat + ", 連絡人="
				+ resContact + ", 電話=" + resPhone + ", 狀態=" + resStatus;
	}



	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Integer getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(Integer totalSeat) {
		this.totalSeat = totalSeat;
	}

	public String getResContact() {
		return resContact;
	}

	public void setResContact(String resContact) {
		this.resContact = resContact;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	
	public Integer getResStatus() {
		return resStatus;
	}

	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}
	
	
	
}
