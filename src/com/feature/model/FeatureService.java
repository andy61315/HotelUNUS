package com.feature.model;

import java.util.List;

public class FeatureService {
	private FeatureJNDIDAO dao;
	
	public FeatureService() {
		dao = new FeatureJNDIDAO();
	}

	public List<FeatureVO> getAll() {
		return dao.findAll();
	}
	
	public FeatureVO getOneFeature(String pk) {
		return dao.findByPk(pk);
	}
	
	
}
