package com.favoritemeal.model;

import java.util.List;

public interface FavoriteMealDAO {
	List<FavoriteMealVO> findAll(String cus_id);
	
	void create(FavoriteMealVO favoratemeal);
	
	void update(FavoriteMealVO favoratemeal);
	
	void delete(FavoriteMealVO favoratemeal);
	
}
