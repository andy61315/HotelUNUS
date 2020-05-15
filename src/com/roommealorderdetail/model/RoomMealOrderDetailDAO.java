package com.roommealorderdetail.model;

import java.sql.Connection;
import java.util.List;


public interface RoomMealOrderDetailDAO {
	List<RoomMealOrderDetailVO> findAll();
	
	List<RoomMealOrderDetailVO> findByOrderNo(String room_meal_order_no);
	
	List<RoomMealOrderDetailVO> findByMealNo(String meal_no);
	
	void insert(RoomMealOrderDetailVO roomMealorderdetail);
	
	void update(RoomMealOrderDetailVO roomMealorderdetail);
	
	void delete(RoomMealOrderDetailVO roomMealorderdetail);
	
	void delete(String room_meal_order_no);
	
	void insertWithConnection(RoomMealOrderDetailVO roomMealOrderDetailVO, Connection conn);
}
