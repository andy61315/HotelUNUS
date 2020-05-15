package com.roomtypepicture.model;

import java.io.Serializable;

import java.util.Arrays;

public class RoomTypePictureVO implements Serializable {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private String room_Type_Picture_No;
	private String room_Type_No;
	private byte[] room_Type_Pic;

	public RoomTypePictureVO() {
		super();
	}

	public RoomTypePictureVO(String room_Type_Picture_No, String room_Type_No, byte[] room_Type_Pic) {
		super();
		this.room_Type_Picture_No = room_Type_Picture_No;
		this.room_Type_No = room_Type_No;
		this.room_Type_Pic = room_Type_Pic;
	}

	public String getroom_Type_Picture_No() {
		return room_Type_Picture_No;
	}

	public void setroom_Type_Picture_No(String room_Type_Picture_No) {
		this.room_Type_Picture_No = room_Type_Picture_No;
	}

	public String getRoom_Type_No() {
		return room_Type_No;
	}

	public void setRoom_Type_No(String room_Type_No) {
		this.room_Type_No = room_Type_No;
	}

	public byte[] getRoom_Type_Pic() {
		return room_Type_Pic;
	}

	public void setRoom_Type_Pic(byte[] room_Type_Pic) {
		this.room_Type_Pic = room_Type_Pic;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	@Override
	public String toString() {
		return "RoomTypePictureVO [room_Type_Picture_No=" + room_Type_Picture_No + ", room_Type_No=" + room_Type_No
				+ ", room_Type_Pic=" + Arrays.toString(room_Type_Pic) + "]";
	}
	
	
}
