package com.resmealod.model;

import java.sql.Connection;
import java.util.List;

public interface ResMealOrderDetailDAO_interface<T> {

	
	public List<T> getAll();

	public ResMealOrderDetailVO findByPrimaryKey(String id, String no);
	

	public boolean add(T obj);
	
	public void add2(ResMealOrderDetailVO rmeal, Connection conn);

	public boolean update(T obj);

	public boolean delete(String id, String no);
	
	

}
