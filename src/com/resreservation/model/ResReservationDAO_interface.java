package com.resreservation.model;

import java.sql.Date;
import java.util.List;

public interface ResReservationDAO_interface<T> {

	
	List<T> getAll();//查全部
	
	List<ResReservationVO> findByCustomer(String id);//依客戶編號查詢訂位

	void add(T obj);

	void update(T obj); 
	
	void updateStatus(String id);//更新狀態(取消預約)
	
	void updateStatus2(String id);//更新狀態(已入座)
	
	public List<ResReservationVO> findByDate(Date date, String restNo);//依日期查詢訂位
	
	
	int findPeopleByPeriod(Date date,Integer period,String resNo);//依時段查詢人數
	
	int doubleOrder(ResReservationVO rest);//預約時段筆數
	
	boolean isDoubleOrder(ResReservationVO rest);
	
	
	
	ResReservationVO findByPrimaryKey(String id);//查一筆資料
	
	void delete(String id);

}
