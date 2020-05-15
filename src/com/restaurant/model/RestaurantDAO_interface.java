package com.restaurant.model;

import java.util.List;

public interface RestaurantDAO_interface<T> {

	
	List<RestaurantVO> getAll();

	RestaurantVO findByPrimaryKey(String id);

	void add(RestaurantVO obj);

	void updateStatus(RestaurantVO obj);

	void delete(String id);

}
