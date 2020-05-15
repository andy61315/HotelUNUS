package com.resmealom.model;

import java.util.List;

import com.meal.model.OrderMealVO;


public interface ResMealOrderMasterDAO_interface<T> {

	
	public List<T> getAll();

	public ResMealOrderMasterVO findByPrimaryKey(String id);
	
	public int  findTotalByRoom(String tableNo);

	public boolean add(T obj);

	public boolean update(T obj);

	public boolean delete(String id);
	
	public void insertWithDetails(ResMealOrderMasterVO resmomVO, List<OrderMealVO> list);

}
