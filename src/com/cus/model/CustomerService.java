package com.cus.model;

import java.util.List;

public class CustomerService {

	private CustomerDAO_interface dao;

	public CustomerService() {
		dao = new CustomerDAO();
	}

	public CustomerVO addCus(String cus_Name, String cus_Email, String cus_Cel, Integer country, String id_Num, java.sql.Date cus_Bir, String cus_Password, byte[] idf_Pic, String captcha) {

		CustomerVO customerVO = new CustomerVO();

		customerVO.setCus_Name(cus_Name);
		customerVO.setCus_Email(cus_Email);
		customerVO.setCus_Cel(cus_Cel);
		customerVO.setCountry(country);
		customerVO.setId_Num(id_Num);
		customerVO.setCus_Bir(cus_Bir);
		customerVO.setCus_Password(cus_Password);
		customerVO.setIdf_Pic(idf_Pic);
		customerVO.setCaptcha(captcha);
		
		dao.insert(customerVO);

		return customerVO;
	}

	public CustomerVO updateCus(String cus_Name, String cus_Email, String cus_Cel, Integer country, String id_Num, java.sql.Date cus_Bir, String cus_Password,  byte[] idf_Pic, Integer cus_Ck, String cus_Id, String captcha) {

		CustomerVO customerVO = new CustomerVO();

		customerVO.setCus_Name(cus_Name);
		customerVO.setCus_Email(cus_Email);
		customerVO.setCus_Cel(cus_Cel);
		customerVO.setCountry(country);
		customerVO.setId_Num(id_Num);
		customerVO.setCus_Bir(cus_Bir);
		customerVO.setCus_Password(cus_Password);
		customerVO.setIdf_Pic(idf_Pic);
		customerVO.setCus_Ck(cus_Ck);
		customerVO.setCus_Id(cus_Id);
		customerVO.setCaptcha(captcha);
		
		dao.update(customerVO);

		return customerVO;
	}
	public CustomerVO getOneCus(String cus_Id) {
		return dao.findByPrimaryKey(cus_Id);
	}

	public List<CustomerVO> getAll() {
		return dao.getAll();
	}

public CustomerVO getCusEmail(String cus_Email){
	return dao.cusemailcheck(cus_Email);
	}

public boolean getId_Num(String id_Num){
	return dao.id_numcheck(id_Num);
	}

public CustomerVO getOneCusByEmail(String cus_Email) {
	return dao.getOneCusByEmail(cus_Email);
}
public CustomerVO getOneCusById(String id_Num) {
	return dao.getOneCusById(id_Num);
}
public CustomerVO addCusFront(String cus_Email, String cus_Name,  String id_Num,String cus_Password,String captcha) {
 
	CustomerVO customerVO = new CustomerVO();
	customerVO.setCus_Email(cus_Email);
	customerVO.setCus_Name(cus_Name);
	customerVO.setId_Num(id_Num);
	customerVO.setCus_Password(cus_Password);
	customerVO.setCaptcha(captcha);

	dao.frontinsert(customerVO);

	return customerVO;
}
public CustomerVO updateCk(Integer cus_Ck,String cus_Id) {

	CustomerVO customerVO = new CustomerVO();

	customerVO.setCus_Ck(cus_Ck);
	customerVO.setCus_Id(cus_Id);

	
	dao.updateCk(customerVO);

	return customerVO;
}
}