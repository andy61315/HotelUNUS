package com.resreservation.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ResReservationVO implements java.io.Serializable{
    
	
	private String reservationNo;
	private String custId;
	private String resNo;
	private Date reservationDate;
	private Integer resvPeriod;
	private Integer resvPeople;
	private Integer resvStatus;//新增狀態7
	
		
	public ResReservationVO(String reservationNo, String custId, String resNo, Date reservationDate, Integer resvPeriod,
			Integer resvPeople, Integer resvStatus) {
		super();
		this.reservationNo = reservationNo;
		this.custId = custId;
		this.resNo = resNo;
		this.reservationDate = reservationDate;
		this.resvPeriod = resvPeriod;
		this.resvPeople = resvPeople;
		this.resvStatus = resvStatus;
	}


	public ResReservationVO() {
		super();
	}

	
//new SimpleDateFormat("yyyy-MM-dd").format(publicationDate)
	



	public String getReservationNo() {
		return reservationNo;
	}

	@Override
	public String toString() {
		return "ResReservationVO [reservationNo=" + reservationNo + ", custId=" + custId + ", resNo=" + resNo
				+ ", reservationDate=" + reservationDate + ", resvPeriod=" + resvPeriod + ", resvPeople=" + resvPeople
				+ ", resvStatus=" + resvStatus + "]";
	}


	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Integer getResvPeriod() {
		return resvPeriod;
	}

	public void setResvPeriod(Integer resvPeriod) {
		this.resvPeriod = resvPeriod;
	}

	public Integer getResvPeople() {
		return resvPeople;
	}

	public void setResvPeople(Integer resvPeople) {
		this.resvPeople = resvPeople;
	}


	public Integer getResvStatus() {
		return resvStatus;
	}


	public void setResvStatus(Integer resvStatus) {
		this.resvStatus = resvStatus;
	}
	
	
}
