package com.employee.model;

import java.io.Serializable;

public class EmployeeVO implements Serializable{
	private String emp_id;
	private String dep_no;
	private String emp_name;
	private String emp_phone;
	private String emp_email;
	private String emp_password;
	private Integer emp_status;
	private byte[] emp_picture;
	
	public EmployeeVO() {
		super();
	}
	
	public EmployeeVO(String emp_id, String dep_no, String emp_name, String emp_phone, String emp_email,
			String emp_password, Integer emp_status, byte[] emp_picture) {
		super();
		this.emp_id = emp_id;
		this.dep_no = dep_no;
		this.emp_name = emp_name;
		this.emp_phone = emp_phone;
		this.emp_email = emp_email;
		this.emp_password = emp_password;
		this.emp_status = emp_status;
		this.emp_picture = emp_picture;
	}

	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDep_no() {
		return dep_no;
	}
	public void setDep_no(String dep_no) {
		this.dep_no = dep_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}


	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public Integer getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}
	public byte[] getEmp_picture() {
		return emp_picture;
	}
	public void setEmp_picture(byte[] emp_picture) {
		this.emp_picture = emp_picture;
	}
}
