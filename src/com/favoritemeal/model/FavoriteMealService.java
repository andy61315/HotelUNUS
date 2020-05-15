package com.favoritemeal.model;

import java.util.List;

public class FavoriteMealService {
	FavoriteMealDAO dao;
	
	public FavoriteMealService() {
		dao = new FavoriteMealJDBCDAO();
	}

	public List<FavoriteMealVO> getAll(String cus_id) {
		return dao.findAll(cus_id);
	}
	
	public FavoriteMealVO insertOneMeal(String cus_id, String meal_no) {
		FavoriteMealVO favoriteMealVO = new FavoriteMealVO();
		
		favoriteMealVO.setCus_id(cus_id);
		favoriteMealVO.setMeal_no(meal_no);
		
		dao.create(favoriteMealVO);
		return favoriteMealVO;
	}
	
	public void deleteOneMeal(String cus_id, String meal_no) {
		FavoriteMealVO favoriteMealVO = new FavoriteMealVO();
		
		favoriteMealVO.setCus_id(cus_id);
		favoriteMealVO.setMeal_no(meal_no);
		
		dao.delete(favoriteMealVO);
	}
}
