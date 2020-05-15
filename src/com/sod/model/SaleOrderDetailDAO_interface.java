package com.sod.model;

import java.util.List;




public interface SaleOrderDetailDAO_interface {
	public int insert(SaleOrderDetailVO  sod);
	public boolean update(SaleOrderDetailVO  sod);
	public boolean delete(String room_type_no,String  sapl_no);
	public List<SaleOrderDetailVO> findSodFromRoomType(String sod);
	public SaleOrderDetailVO findByPrimaryKey(String room_type_no,String sapl_no);
	public List<SaleOrderDetailVO> getAll();
	public List<SaleOrderDetailVO> getSodBySalNo(String sapl_no);
	public boolean deleteBySal(String  sapl_no);
	//------------------------------------------------------
	public void insert2 (SaleOrderDetailVO saleOrderDetailVO , java.sql.Connection con);
}
