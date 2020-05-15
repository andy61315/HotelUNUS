package com.roommealorderdetail.model;

import java.sql.Connection;
import java.util.List;

public class RoomMealOrderDetailService {
	private RoomMealOrderDetailDAO dao;
	
	
	public RoomMealOrderDetailService() {
		dao = new RoomMealOrderDetailJNDIDAO();
	}


	public List<RoomMealOrderDetailVO> getAll() {
		return dao.findAll();
	}

	public List<RoomMealOrderDetailVO> getRoomMealOrderDetailByOrderNo(String room_meal_order_no) {
		return dao.findByOrderNo(room_meal_order_no);
	}
	
	public List<RoomMealOrderDetailVO> getRoomMealOrderDetailByMealNo(String meal_no) {
		return dao.findByMealNo(meal_no);
	}
	
	public RoomMealOrderDetailVO addRoomMealOrderDetail(String room_meal_order_no, String meal_no, 
			Integer quantity, Integer price) {
		RoomMealOrderDetailVO roomMealOrderDetailVO = new RoomMealOrderDetailVO();
		
		roomMealOrderDetailVO.setMeal_no(meal_no);
		roomMealOrderDetailVO.setPrice(price);
		roomMealOrderDetailVO.setQuantity(quantity);
		roomMealOrderDetailVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.insert(roomMealOrderDetailVO);
		
		return roomMealOrderDetailVO;
	}
	
	public RoomMealOrderDetailVO addRoomMealOrderDetail(String room_meal_order_no, String meal_no, 
			Integer quantity, Integer price, Connection conn) {
		RoomMealOrderDetailVO roomMealOrderDetailVO = new RoomMealOrderDetailVO();
		
		roomMealOrderDetailVO.setMeal_no(meal_no);
		roomMealOrderDetailVO.setPrice(price);
		roomMealOrderDetailVO.setQuantity(quantity);
		roomMealOrderDetailVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.insertWithConnection(roomMealOrderDetailVO, conn);
		
		return roomMealOrderDetailVO;
	}
	
	public RoomMealOrderDetailVO updateQuantity(String room_meal_order_no, String meal_no, 
			Integer quantity) {
		RoomMealOrderDetailVO roomMealOrderDetailVO = new RoomMealOrderDetailVO();
		
		roomMealOrderDetailVO.setMeal_no(meal_no);
		roomMealOrderDetailVO.setQuantity(quantity);
		roomMealOrderDetailVO.setRoom_meal_order_no(room_meal_order_no);		
		
		dao.update(roomMealOrderDetailVO);
		
		return roomMealOrderDetailVO;
	}
	
	public RoomMealOrderDetailVO deleteByOrderNo(String room_meal_order_no) {
		RoomMealOrderDetailVO roomMealOrderDetailVO = new RoomMealOrderDetailVO();
		
		roomMealOrderDetailVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.delete(room_meal_order_no);
		
		return roomMealOrderDetailVO;
	}
	
	public RoomMealOrderDetailVO deleteOneOrderDetail(String room_meal_order_no, String meal_no) {
		RoomMealOrderDetailVO roomMealOrderDetailVO = new RoomMealOrderDetailVO();
		
		roomMealOrderDetailVO.setMeal_no(meal_no);
		roomMealOrderDetailVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.delete(roomMealOrderDetailVO);
		
		return roomMealOrderDetailVO;
	}
	
	
	
	
	
}
