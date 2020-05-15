package com.empauthority.model;

import java.io.Serializable;

public class EmpAuthorityVO implements Serializable{
	private String emp_id;
	private String func_no;
	
	public EmpAuthorityVO() {
		super();
	}

	public EmpAuthorityVO(String emp_id, String func_no) {
		super();
		this.emp_id = emp_id;
		this.func_no = func_no;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getFunc_no() {
		return func_no;
	}

	public void setFunc_no(String func_no) {
		this.func_no = func_no;
	}
	
	
	
}
