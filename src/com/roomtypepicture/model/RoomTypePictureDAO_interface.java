package com.roomtypepicture.model;

import java.util.List;
import java.util.Map;

public interface RoomTypePictureDAO_interface {
	public void insert(RoomTypePictureVO rtpVO);
	public void deleteByPicNo (List<String> PicNos );//單一照片編號
	public List<RoomTypePictureVO> getAll();
	public void deleteByRoomTypePictureNo(String RoomTypePictureNo);
	public void deleteByRoomTypeNo(String RoomTypeNo);
	public List<RoomTypePictureVO> getAllBy(Map<String, String[]> map);
	RoomTypePictureVO findByRoomTypeNo(String RoomTypeNo);
}
