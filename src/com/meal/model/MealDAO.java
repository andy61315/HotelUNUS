package com.meal.model;

import java.util.List;

public interface MealDAO {
	List<MealVO> findAll();
	
	List<OrderMealVO> findByRestaurant(String resNo);//依餐廳列出餐點
	
	MealVO findByPk(String pk);
	
	// 分頁用
	List<MealVO> findStartEnd(int start, int end);
	
	void insert(MealVO mealVO);
	
	void update(MealVO mealVO);
	
	void delete(String pk);
	
	int findPrice(String pk);
	
	void updatePicture(MealVO mealVO);
	
	void updateWithoutPicture(MealVO mealVO);
	
}
