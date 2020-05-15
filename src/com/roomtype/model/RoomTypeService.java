package com.roomtype.model;

import java.util.List;
import java.util.Map;



public class RoomTypeService {
	private RoomTypeDAO_interface dao;
	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}
	
	public String addRoomType( String room_Type_Name, String article, 
			Integer person_Capacity, Integer add_People, Integer price, Integer holiday_Price, 
			Integer workingDay_Price, Integer total_People, Integer room_Type_Status) {
		RoomTypeVO vo = new RoomTypeVO();
		vo.setRoom_Type_Name(room_Type_Name);;
		vo.setArticle(article);
		vo.setPerson_Capacity(person_Capacity);
		vo.setAdd_People(add_People);
		vo.setHoliday_Price(holiday_Price);
		vo.setWorkingDay_Price(workingDay_Price);
		vo.setPrice(price);
		vo.setTotal_People(total_People);
		vo.setRoom_Type_Status(room_Type_Status);
		return dao.insert(vo);
	}
	
	public void updateRoomTypeBasic(String room_Type_No,String room_Type_Name, String article, 
			Integer person_Capacity, Integer add_People, Integer price, Integer holiday_Price, 
			Integer workingDay_Price,Integer total_People, Integer room_Type_Status) {
		RoomTypeVO vo = new RoomTypeVO();
		vo.setRoom_Type_No(room_Type_No);
		vo.setRoom_Type_Name(room_Type_Name);;
		vo.setArticle(article);
		vo.setPerson_Capacity(person_Capacity);
		vo.setAdd_People(add_People);
		vo.setHoliday_Price(holiday_Price);
		vo.setWorkingDay_Price(workingDay_Price);
		vo.setPrice(price);
		vo.setTotal_People(total_People);
		vo.setRoom_Type_Status(room_Type_Status);
//		System.out.println(" vo = " + vo);
		dao.updateBasic(vo);
	}
	
	public void updateRoomTypeStatus(String room_Type_No, Integer room_Type_Status) {
		dao.updateStatus(room_Type_No, room_Type_Status);
	}
	
	public RoomTypeVO findOneByNo(String room_Type_No) {
		return dao.findOneByNo(room_Type_No);
	}
	
	public List<RoomTypeVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomTypeVO> getAllBy(Map<String, String[]> map) {
		return dao.getAllBy(map);
	}
}
