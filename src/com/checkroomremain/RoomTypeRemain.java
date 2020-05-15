package com.checkroomremain;

import java.io.Serializable;

public class RoomTypeRemain implements Serializable{//不同的房型的剩餘數量
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String room_Type_No;
	String rooom_Type_Name;
	Integer remain;
	Integer person_Capacity;
	Integer total_People;
	
	public RoomTypeRemain() {
		super();
	}
	public RoomTypeRemain(String room_Type_No, String rooom_Type_Name, Integer remain, Integer person_Capacity,
			Integer total_People) {
		super();
		this.room_Type_No = room_Type_No;
		this.rooom_Type_Name = rooom_Type_Name;
		this.remain = remain;
		this.person_Capacity = person_Capacity;
		this.total_People = total_People;
	}
	public String getRoom_Type_No() {
		return room_Type_No;
	}
	public void setRoom_Type_No(String room_Type_No) {
		this.room_Type_No = room_Type_No;
	}
	public String getRooom_Type_Name() {
		return rooom_Type_Name;
	}
	public void setRooom_Type_Name(String rooom_Type_Name) {
		this.rooom_Type_Name = rooom_Type_Name;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	public Integer getPerson_Capacity() {
		return person_Capacity;
	}
	public void setPerson_Capacity(Integer person_Capacity) {
		this.person_Capacity = person_Capacity;
	}
	public Integer getTotal_People() {
		return total_People;
	}
	public void setTotal_People(Integer total_People) {
		this.total_People = total_People;
	}
	@Override
	public String toString() {
		return "RoomRemain [room_Type_No=" + room_Type_No + ", rooom_Type_Name=" + rooom_Type_Name + ", remain="
				+ remain + ", person_Capacity=" + person_Capacity + ", total_People=" + total_People + "]";
	}
	
}
