package com.employee.model;

import java.util.List;


public interface EmployeeDAO {
	List<EmployeeVO> findAll();
	
	EmployeeVO findByPk(String pk);
	
	// Oracle 中 emp_email 欄位有 UNIQUE
	EmployeeVO findByEmail(String email);
	
	List<EmployeeVO> findByStatus(int status);
	
	void insert(EmployeeVO employee);
	
	void update(EmployeeVO employee);
	
	void updateWithoutPic(EmployeeVO employee);
	
	void delete(String pk);
	
	void updateResign(String pk);
}