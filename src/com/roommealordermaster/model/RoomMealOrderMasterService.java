package com.roommealordermaster.model;

import java.util.List;
import java.util.Map;

import oracle.net.aso.r;

public class RoomMealOrderMasterService {
	private RoomMealOrderMasterDAO dao;
	
	
	public RoomMealOrderMasterService() {
		dao = new RoomMealOrderMasterJNDIDAO();
	}


	public List<RoomMealOrderMasterVO> getAll() {
		return dao.findAll();
	}
	
	public List<RoomMealOrderMasterVO> getAll(Map<String, String[]> map) {
		return dao.findAll(map);
	}
	
	public List<RoomMealOrderMasterVO> getCookingOrder() {
		return dao.findCookingOrder();
	}
	
	public List<RoomMealOrderMasterVO> getShippingOrder() {
		return dao.findShippingOrder();
	}
	
	public List<RoomMealOrderMasterVO> getShippedOrder() {
		return dao.findShippedOrder();
	}
	
	public List<RoomMealOrderMasterVO> getClosedOrder() {
		return dao.findClosedOrder();
	}
	
	public List<RoomMealOrderMasterVO> getCancelOrder() {
		return dao.findCancelOrder();
	}
	
	public RoomMealOrderMasterVO getOneRoomMealOrderMaster(String pk) {
		return dao.findByPk(pk);
	}
	
	public RoomMealOrderMasterVO addRoomMealOrderMaster(String b_order_no, 
			String room_no, String special_requirement) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setB_order_no(b_order_no);
		roomMealOrderMasterVO.setRoom_no(room_no);
		roomMealOrderMasterVO.setSpecial_requirement(special_requirement);
		
		dao.insert(roomMealOrderMasterVO);
		
		return roomMealOrderMasterVO;
	}
	
	public RoomMealOrderMasterVO addRoomMealOrderMasterWithOrderDetail(String b_order_no, 
			String room_no, String special_requirement, List<Map<String, Object>> cart) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setB_order_no(b_order_no);
		roomMealOrderMasterVO.setRoom_no(room_no);
		roomMealOrderMasterVO.setSpecial_requirement(special_requirement);
		
		dao.insertWithOrderDetail(roomMealOrderMasterVO, cart);
		
		return roomMealOrderMasterVO;
	}	
	
	public RoomMealOrderMasterVO addRoomMealOrderMasterWithOrderDetailAndTotal(String b_order_no, 
			String room_no, String special_requirement, List<Map<String, Object>> cart, Integer total_price) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setB_order_no(b_order_no);
		roomMealOrderMasterVO.setRoom_no(room_no);
		roomMealOrderMasterVO.setSpecial_requirement(special_requirement);
		roomMealOrderMasterVO.setTotal_price(total_price);
		
		dao.insertWithOrderDetailAndTotal(roomMealOrderMasterVO, cart);
		
		return roomMealOrderMasterVO;
	}
	
	public RoomMealOrderMasterVO updateRoomMealOrderMaster(String room_meal_order_no, String b_order_no, 
			String room_no, String emp_id, Integer total_price, Integer ro_order_status, String special_requirement) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setB_order_no(b_order_no);
		roomMealOrderMasterVO.setEmp_id(emp_id);
		roomMealOrderMasterVO.setRo_order_status(ro_order_status);
		roomMealOrderMasterVO.setRoom_meal_order_no(room_meal_order_no);
		roomMealOrderMasterVO.setRoom_no(room_no);
		roomMealOrderMasterVO.setSpecial_requirement(special_requirement);
		roomMealOrderMasterVO.setTotal_price(total_price);
		
		dao.update(roomMealOrderMasterVO);
		
		return roomMealOrderMasterVO;
	}
	
	public RoomMealOrderMasterVO updateOrderToCooked(String emp_id, Integer total_price, String room_meal_order_no) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setEmp_id(emp_id);
		roomMealOrderMasterVO.setTotal_price(total_price);
		roomMealOrderMasterVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.cookedUpdate(roomMealOrderMasterVO);
		
		return roomMealOrderMasterVO;
	}
	
	public RoomMealOrderMasterVO updateOrderToShipping(Integer total_price, String room_meal_order_no) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setTotal_price(total_price);
		roomMealOrderMasterVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.shippedUpdate(roomMealOrderMasterVO);
		
		return roomMealOrderMasterVO;
	}
	
	public RoomMealOrderMasterVO updateOrderToClosed(String room_meal_order_no) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.orderClosedUpdate(roomMealOrderMasterVO);
		
		return roomMealOrderMasterVO;
	}
	
	public RoomMealOrderMasterVO updateOrderToCancel(String room_meal_order_no) {
		RoomMealOrderMasterVO roomMealOrderMasterVO = new RoomMealOrderMasterVO();
		
		roomMealOrderMasterVO.setRoom_meal_order_no(room_meal_order_no);
		
		dao.orderCancelUpdate(roomMealOrderMasterVO);
		
		return roomMealOrderMasterVO;		
	}
	
	public void updateTotal(Integer total, String pk) {
		dao.orderTotalUpdate(total, pk);
	}
	
	public void updateStatus(Integer status, String pk) {
		dao.statusUpdate(status, pk);
	}
	
	public void deleteRoomMealOrderMaster(String pk) {
		dao.delete(pk);
	}
	
}
