package com.roommealordermaster.model;

import java.util.List;
import java.util.Map;

public interface RoomMealOrderMasterDAO {
	List<RoomMealOrderMasterVO> findAll();
	
	List<RoomMealOrderMasterVO> findAll(Map<String, String[]> map);
	
	List<RoomMealOrderMasterVO> findCookingOrder();
	
	List<RoomMealOrderMasterVO> findShippingOrder();
	
	List<RoomMealOrderMasterVO> findShippedOrder();
	
	List<RoomMealOrderMasterVO> findClosedOrder();
	
	List<RoomMealOrderMasterVO> findCancelOrder();
	
	RoomMealOrderMasterVO findByPk(String pk);
	
	void insert(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void update(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void cookedUpdate(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void shippedUpdate(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void orderClosedUpdate(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void orderCancelUpdate(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void orderTotalUpdate(Integer total, String pk);
	
	void statusUpdate(Integer status, String pk);
	
	void delete(RoomMealOrderMasterVO roomMealOrderMaster);
	
	void delete(String pk);
	
	void insertWithOrderDetail(RoomMealOrderMasterVO roomMealOrderMasterVO, List<Map<String, Object>> cart);
	
	void insertWithOrderDetailAndTotal(RoomMealOrderMasterVO roomMealOrderMasterVO, List<Map<String, Object>> cart);
}
