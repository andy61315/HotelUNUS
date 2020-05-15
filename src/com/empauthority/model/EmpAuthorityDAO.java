package com.empauthority.model;

import java.util.List;

public interface EmpAuthorityDAO {
	void deleteByEmpID(String emp_id);
	
	List<EmpAuthorityVO> finByEmpID(String emp_id);
	
	void insertByEmpID(String emp_id);
}
