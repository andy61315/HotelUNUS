package com.employee.model;

import java.util.List;

public class EmployeeService {
	private EmployeeDAO dao = null;

	public EmployeeService() {
		dao = new EmployeeJNDIDAO();
	}
			
	public List<EmployeeVO> getAll() {
		return dao.findAll();
	}
	
	public EmployeeVO getOneEmp(String pk) {
		return dao.findByPk(pk);
	}
	
	public EmployeeVO getOneEmpByEmail(String emp_email) {
		return dao.findByEmail(emp_email);
	}
	
	public EmployeeVO addEmp(String dep_no, String emp_name, String emp_phone, String emp_email,
			String emp_password, Integer emp_status, byte[] emp_picture) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setDep_no(dep_no);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_password(emp_password);
		employeeVO.setEmp_phone(emp_phone);
		employeeVO.setEmp_picture(emp_picture);
		employeeVO.setEmp_status(emp_status);
		
		dao.insert(employeeVO);
		return employeeVO;
	}
	
	public EmployeeVO addEmpWithoutPic(String dep_no, String emp_name, String emp_phone, String emp_email,
			String emp_password, Integer emp_status) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setDep_no(dep_no);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_password(emp_password);
		employeeVO.setEmp_phone(emp_phone);
		employeeVO.setEmp_status(emp_status);
		
		dao.insert(employeeVO);
		return employeeVO;
	}
	
	
	public EmployeeVO updateEmp(String emp_id, String dep_no, String emp_name, String emp_phone, String emp_email,
			 Integer emp_status, byte[] emp_picture) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setDep_no(dep_no);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_phone(emp_phone);
		employeeVO.setEmp_picture(emp_picture);
		employeeVO.setEmp_status(emp_status);
		employeeVO.setEmp_id(emp_id);
		
		dao.update(employeeVO);
		return employeeVO;
	}
	
	public EmployeeVO updateEmpWithoutPic(String emp_id, String dep_no, String emp_name, String emp_phone, String emp_email,
			 Integer emp_status) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setDep_no(dep_no);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_phone(emp_phone);
		employeeVO.setEmp_status(emp_status);
		employeeVO.setEmp_id(emp_id);
		
		dao.update(employeeVO);
		return employeeVO;
	}
	
	public void updateResign(String pk) {
		dao.updateResign(pk);
	}
	
	public void deleteEmp(String pk) {
		dao.delete(pk);
	}
	
	
	// true 登入成功, false 登入失敗(預設 false)
	// 要保證 emp_password 不能是 null, 不然會報錯
	public boolean empLogin(String emp_email, String emp_password) {
		EmployeeVO dbEmployeeVO = dao.findByEmail(emp_email);
		
		try {
			if (emp_password.equals(dbEmployeeVO.getEmp_password())) {
				return true;
			}
		} catch (NullPointerException ne) {
			throw new RuntimeException("Can't not find this email : " + ne.getMessage());
		}
		
		return false;
	}
	
	// true 是有重複信箱, false 是沒有重複
	public boolean emailCheck(String emp_email) {
		List<EmployeeVO> empList = dao.findAll();
		for (EmployeeVO employeeVO : empList) {
			if (emp_email.equals(employeeVO.getEmp_email())) {
				return true;
			}
		}
		return false;
	}
	
}
