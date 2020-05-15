package com.department.model;

import java.util.List;


public interface DepartmentDAO {
	List<DepartmentVO> findAll();
	
	DepartmentVO findByPk(String pk);
	
	void create(DepartmentVO department);
	
	void update(DepartmentVO department);
	
	void delete(String pk);
}
