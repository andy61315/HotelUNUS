package com.cus.model;

import java.util.*;

public interface CustomerDAO_interface {
	public void insert(CustomerVO customerVO);
	public void update(CustomerVO customerVO);
	public void delete(String cus_Id);
	public CustomerVO findByPrimaryKey(String cus_Id);
	public List<CustomerVO> getAll();
	public  CustomerVO cusemailcheck(String cus_Email);
	public  boolean id_numcheck(String id_Num);
	public CustomerVO getOneCusByEmail(String cus_Email);
	public void frontinsert(CustomerVO customerVO);
	public CustomerVO getOneCusById(String id_Num);
	public void updateCk(CustomerVO customerVO);
}
