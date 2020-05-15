package com.sod.model;

import java.util.List;

public class SaleOrderDetailService {
	private SaleOrderDetailDAO_interface dao;
	public SaleOrderDetailService() {
		dao = new SaleOrderDetailJNDIDAO();
	}
	public SaleOrderDetailVO insert(String room_type_no, String sapl_no) {
		SaleOrderDetailVO salVO = new SaleOrderDetailVO();
		salVO.setRoom_type_no(room_type_no);
		salVO.setSapl_no(sapl_no);
//		salVO.setSapl_price(sapl_price);
		dao.insert(salVO);
		return salVO;
	}
	public SaleOrderDetailVO update(String room_type_no,String sapl_no) {
		SaleOrderDetailVO salVO = new SaleOrderDetailVO();
		salVO.setRoom_type_no(room_type_no);
		salVO.setSapl_no(sapl_no);
//		salVO.setSapl_price(sapl_price);
		dao.update(salVO);
		return salVO;
	}
	
	public SaleOrderDetailVO findByPrimaryKey(String room_type_no,String  sapl_no) {
		return dao.findByPrimaryKey(room_type_no,sapl_no);
	}
	public List<SaleOrderDetailVO> getAll(){
		return dao.getAll();
	}
	public void deletSod(String room_type_no,String sapl_no) {
		 dao.delete(room_type_no,sapl_no);
	}
	public List<SaleOrderDetailVO> getSodBySalNo(String sapl_no){
		return dao.getSodBySalNo(sapl_no);
	}
	
	public boolean deleteBySal(String  sapl_no) {
		return dao.deleteBySal(sapl_no);
	}
}
