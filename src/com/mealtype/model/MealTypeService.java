package com.mealtype.model;

import java.util.List;

public class MealTypeService {
	private MealTypeDAO mealTypeDAO;
	
	
	public MealTypeService() {
		mealTypeDAO = new MealTypeJNDIDAO();
	}

	public List<MealTypeVO> getAll() {
		return mealTypeDAO.findAll();
	}
	
	public MealTypeVO findByPK(String pk) {
		return mealTypeDAO.findByPk(pk);
	}
	
	public MealTypeVO updateMealType(String type_name, String meal_type_no) {
		MealTypeVO mealTypeVO = new MealTypeVO();
		
		mealTypeVO.setType_name(type_name);
		mealTypeVO.setMeal_type_no(meal_type_no);
		mealTypeDAO.update(mealTypeVO);
		
		return mealTypeVO;
	}
	
	public void deleteMealType(String pk) {
		mealTypeDAO.delete(pk);
	}
	
	public MealTypeVO addMealType(String type_name) {
		MealTypeVO mealTypeVO = new MealTypeVO();
		mealTypeVO.setType_name(type_name);
		mealTypeDAO.create(mealTypeVO);
		
		return mealTypeVO;
	}
}
