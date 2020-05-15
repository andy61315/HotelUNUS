package com.roomdate.model;

import java.io.Serializable;
import java.sql.Date;





public class RoomDateVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date_time;
	private Integer isHoliday;

	public RoomDateVO() {
		super();
	}

	public RoomDateVO(Date date_time, Integer isHoliday) {
		super();
		this.date_time = date_time;
		this.isHoliday = isHoliday;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public Integer getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(Integer isHoliday) {
		this.isHoliday = isHoliday;
	}

	@Override
	public String toString() {
		return "RoomDateVO [date_time=" + date_time + ", isHoliday=" + isHoliday + "]";
	}

}
