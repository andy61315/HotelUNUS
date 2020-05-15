package com.restaurant.model;

import java.util.List;

public class RestaurantService {

	private RestaurantDAO_interface dao;
	
	public RestaurantService() {
		dao = new RestaurantJNDIDAO();
	}

	public RestaurantVO addRestaurant(String resName, Integer totalSeat,
			String resContact, String resPhone, Integer resStatus) {

		RestaurantVO restVO = new RestaurantVO();

		restVO.setResName(resName);
		restVO.setTotalSeat(totalSeat);
		restVO.setResContact(resContact);
		restVO.setResPhone(resPhone);
		restVO.setResStatus(resStatus);
		dao.add(restVO);

		return restVO;
	}
	
	public RestaurantVO updateRestaurant(String resNo, String resName, Integer totalSeat,
			String resContact, String resPhone, Integer resStatus) {

		RestaurantVO restVO = new RestaurantVO();

		restVO.setResNo(resNo);
		restVO.setResName(resName);
		restVO.setTotalSeat(totalSeat);
		restVO.setResContact(resContact);
		restVO.setResPhone(resPhone);
		restVO.setResStatus(resStatus);
		dao.updateStatus(restVO);

		return restVO;
	}
	
	
	
	public void deleteRestaurant(String resNo) {
		dao.delete(resNo);
	}
	
	public RestaurantVO getOneRestaurant(String resNo) {
		return dao.findByPrimaryKey(resNo);
	}
	
	public List<RestaurantVO> getAll() {
		return dao.getAll();
	}
	

	public RestaurantVO getByPrimaryKey(String pk) {
		return dao.findByPrimaryKey(pk);
	}
	
}
