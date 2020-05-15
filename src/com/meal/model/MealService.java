package com.meal.model;

import java.sql.Date;
import java.util.List;

public class MealService{
	
	MealDAO mealDAO = null;
	
	public MealService() {
		mealDAO = new MealJNDIDAO();
	}

	public void deleteMeal(String pk) {
		mealDAO.delete(pk);
	}

	public List<MealVO> getAll() {
		return mealDAO.findAll();
	}
	
	public List<OrderMealVO> getByRestaurant(String resNo){//依餐廳列出餐點
		return mealDAO.findByRestaurant(resNo);
	}

	public MealVO addMeal(String meal_name, Integer price, String res_no, String meal_type_no, Integer meal_status, 
			String meal_introduction, byte[] meal_picture, Date meal_date) {
		
		MealVO mealVO = new MealVO();
		
		mealVO.setMeal_introduction(meal_introduction);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_picture(meal_picture);
		mealVO.setMeal_status(meal_status);
		mealVO.setMeal_type_no(meal_type_no);
		mealVO.setPrice(price);
		mealVO.setRes_no(res_no);
		mealVO.setMeal_date(meal_date);
		mealDAO.insert(mealVO);
		
		return mealVO;
	}

	public MealVO getOneMeal(String pk) {
		return mealDAO.findByPk(pk);
	}

	public MealVO updateMeal(String meal_name, Integer price, String res_no, String meal_type_no, Integer meal_status, 
			String meal_introduction, byte[] meal_picture, String meal_no, Date meal_date) {
		
		MealVO mealVO = new MealVO();
		
		mealVO.setMeal_introduction(meal_introduction);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_picture(meal_picture);
		mealVO.setMeal_status(meal_status);
		mealVO.setMeal_type_no(meal_type_no);
		mealVO.setPrice(price);
		mealVO.setRes_no(res_no);
		mealVO.setMeal_no(meal_no);
		mealVO.setMeal_date(meal_date);
		mealDAO.update(mealVO);
		
		return mealVO;
	}
	
	public MealVO updateMealWithoutPicture(String meal_name, Integer price, String res_no, String meal_type_no, Integer meal_status, 
			String meal_introduction, String meal_no, Date meal_date) {
		MealVO mealVO = new MealVO();
		
		mealVO.setMeal_introduction(meal_introduction);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_status(meal_status);
		mealVO.setMeal_type_no(meal_type_no);
		mealVO.setPrice(price);
		mealVO.setRes_no(res_no);
		mealVO.setMeal_no(meal_no);
		mealVO.setMeal_date(meal_date);
		mealDAO.updateWithoutPicture(mealVO);
		
		return mealVO;
	}
	
}
