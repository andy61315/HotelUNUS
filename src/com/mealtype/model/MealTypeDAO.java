package com.mealtype.model;

import java.util.List;


public interface MealTypeDAO {
	List<MealTypeVO> findAll();
	
	MealTypeVO findByPk(String pk);
	
	void create(MealTypeVO mealType);
	
	void update(MealTypeVO mealType);
	
	void delete(String pk);
}
