package com.feature.model;

import java.util.List;


public interface FeatureDAO {
List<FeatureVO> findAll();
	
FeatureVO findByPk(String pk);
	
	void create(FeatureVO feature);
	
	void update(FeatureVO feature);
	
	void delete(String pk);
}
