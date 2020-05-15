package com.roomtypepicture.model;

import java.util.List;
import java.util.Map;


public class RoomTypePictureService {
	private RoomTypePictureDAO_interface dao ;
	
	public RoomTypePictureService() {
		dao = new RoomTypePictureDAO();	
	}
//	BookingOrderMasterVO
	public RoomTypePictureVO addRoomTypePicture( String room_Type_No, byte[] room_Type_Pic) {
		RoomTypePictureVO vo = new RoomTypePictureVO();
		vo.setRoom_Type_No(room_Type_No);
		vo.setRoom_Type_Pic (room_Type_Pic);
		dao.insert(vo);
		return vo;
	}
	
	public void deleteByRoomTypePictureNo(String RoomTypePictureNo) {
		dao.deleteByRoomTypePictureNo(RoomTypePictureNo);
	}
	
	public List<RoomTypePictureVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomTypePictureVO> getAllBy(Map<String, String[]> map){
		return dao.getAllBy(map);
	}
}
