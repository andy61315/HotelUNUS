package com.roomtype.model;

import java.util.List;
import java.util.Map;

import com.bom.model.BookingOrderMasterVO;

public interface RoomTypeDAO_interface {
	public String insert(RoomTypeVO rtVO);//拉回剛新增的流水號

	public void updateBasic(RoomTypeVO rtVO);// 更新除了評價星數以外的資訊，包括狀態

	public void updateStars(String room_Type_No, Integer star);// 更新星星數, 評分人數

	public void updateStatus(String room_Type_No, Integer room_Type_Status);


	public List<RoomTypeVO> getAll();

	public List<RoomTypeVO> getAllBy(Map<String, String[]> map);//萬用查詢

	public RoomTypeVO findOneByNo(String room_Type_No);
}
