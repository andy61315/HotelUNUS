package com.room.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomService {
	private RoomDAO_interface dao= null;
	
	public RoomService() {
		dao=new RoomJNDIDAO();
	}
	public void checkOut(List<String> roomNoList) {
		dao.checkOut(roomNoList);
	}
	
	public RoomVO addRoom(String room_type_no,String room_no){
		RoomVO room = new RoomVO();
		
		room.setRoom_type_no(room_type_no);
		room.setRoom_no(room_no);
	
		
		dao.insert(room);
		return room;
	} 
	
	public RoomVO updateRoomData(String room_id,String room_type_no,String room_no) {
		RoomVO room = new RoomVO();
		
		room.setRoom_id(room_id);
		room.setRoom_type_no(room_type_no);
		room.setRoom_no(room_no);
		

		dao.updateRoomData(room);
		return room;
	}
	
	public RoomVO updateRoomCus(String room_no,String cus_id,String tenant_name,String tenant_phone,String b_order_no) {
		RoomVO room = new RoomVO();
		room.setRoom_no(room_no);
		room.setCus_id(cus_id);
		room.setTenant_name(tenant_name);
		room.setTenant_phone(tenant_phone);
		room.setB_order_no(b_order_no);
		dao.updateRoomCus(room);
		return room;
	}

	public RoomVO updateRoomCleanStatus(String room_no,int clean_status) {
		RoomVO room = new RoomVO();	
		room.setRoom_no(room_no);
		room.setClean_status(clean_status);
		
		dao.updateRoomCleanStatus(room);
		return room;
	}
	
	public RoomVO updateRoomStatus(String room_no,int room_status) {
		RoomVO room = new RoomVO();
		
		
		room.setRoom_no(room_no);
		room.setRoom_status(room_status);
	
		dao.updateRoomStatus(room);
		return room;
	}
	
	public RoomVO getOneRoom(String room_id) {
		return dao.findByPrimaryKey(room_id);
	}
	
	public void ofShellRoom(String room_Type_No) {
		dao.ofShellRoomStatus(room_Type_No);
	}
	
	public List<RoomVO> getAll(){
		return dao.getAll();
	}
	
	public List<RoomVO> getSearch(Map<String,String[]> map){
		
		return dao.getSearch(map);
	}
	
	public List<RoomVO> getRoomByCount(String room_type_no,String b_order_no){
		
		return dao.getRoomByCount(room_type_no,b_order_no);
	}
	
	public List<RoomVO> getRoomByBookOrderNo(String b_order_no){
		return dao.getRoomByBorderNo(b_order_no);
	}
	
	public List<RoomVO> getRoomByCusID(String cus_Id) {
		// 根據 cus_Id filter 出 VO List
		List<RoomVO> filteredList = dao.getAll().stream()
										.filter(roomVO -> cus_Id.equals(roomVO.getCus_id()))
										.collect(Collectors.toList());
		return filteredList;
	}
}
