package com.room.model;

import java.util.*;


public interface RoomDAO_interface {
	public int insert(RoomVO roomVo);
	public void updateRoomData(RoomVO roomVo);
	public void updateRoomStatus(RoomVO roomVo);
	public void updateRoomCleanStatus(RoomVO roomVo);
	public void updateRoomCus(RoomVO roomVo);
	public List<RoomVO> getAll();
	public List<RoomVO> getSearch(Map<String, String[]> map);
	public RoomVO findByPrimaryKey(String room_id);
	public List<RoomVO> getRoomByCount(String room_type_no,String b_order_no);
	List<RoomVO> getRoomByBorderNo(String b_order_no);
	public void ofShellRoomStatus(String room_Type_No);
	public void checkOut(List<String> roomNoList);
}
