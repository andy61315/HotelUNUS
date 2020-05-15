package com.resreservation.model;

import java.sql.Date;
import java.util.List;

public class ResReservationService {

	private ResReservationDAO_interface dao;

	public ResReservationService() {
		dao = new ResReservationJNDIDAO();
	}

	//新增預約
	public ResReservationVO addResReservation(String custId, String resNo, Date reservationDate,
			Integer resvPeriod, Integer resvPeople) {

		ResReservationVO resrVO = new ResReservationVO();

		//resrVO.setReservationNo(reservationNo);
		resrVO.setCustId(custId);
		resrVO.setResNo(resNo);
		resrVO.setReservationDate(reservationDate);
		resrVO.setResvPeriod(resvPeriod);
		resrVO.setResvPeople(resvPeople);
		
		dao.add(resrVO);

		return resrVO;
	}
    //後台更改訂位資訊
	public ResReservationVO updateResReservation(String reservationNo, String custId, String resNo, Date reservationDate,
			Integer resvPeriod, Integer resvPeople) {

		ResReservationVO resrVO = new ResReservationVO();

		resrVO.setReservationNo(reservationNo);
		resrVO.setCustId(custId);
		resrVO.setResNo(resNo);
		resrVO.setReservationDate(reservationDate);
		resrVO.setResvPeriod(resvPeriod);
		resrVO.setResvPeople(resvPeople);
		
		dao.update(resrVO);

		return resrVO;
	}
	
	public  List<ResReservationVO> getCustomer(String id){
		return dao.findByCustomer(id);
	}
	
		
	//已預約編號查詢
	public ResReservationVO getOneResReservation(String reservationNo) {
		return dao.findByPrimaryKey(reservationNo);
	}
	
	//是否重複預約
	public boolean getDouleOrder(ResReservationVO rest) {
		return dao.isDoubleOrder(rest);
	}
	
	
	
	//依日期查詢訂位
	public List<ResReservationVO> getOneDate(Date date, String restNo) {
		return dao.findByDate(date, restNo);
	}
	
	//使用時段查詢人數
	public int getOnePeriod(Date date,Integer period ,String resNo) {
		return dao.findPeopleByPeriod(date,period,resNo);
	}
	
	//取消預約
	public void updateStatus(String id) {
		dao.updateStatus (id);
	}
	
	//更新入座
		public void updateStatus2(String id) {
			dao.updateStatus2 (id);
		}
	
	public List<ResReservationVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteResReservation(String reservationNo) {
		dao.delete(reservationNo);
	}
}
