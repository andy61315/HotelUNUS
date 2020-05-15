package com.mealpicture.model;

import java.util.List;


public interface MealPictureDAO {
	List<MealPictureVO> findAll();
	
	List<MealPictureVO> findByMealNo(String mealNo);
	
	void create(MealPictureVO mealpicture);
	
	void update(MealPictureVO mealpicture);
	
	void delete(String pk);
}
