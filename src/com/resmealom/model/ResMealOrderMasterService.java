package com.resmealom.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.meal.model.OrderMealVO;
import com.resmealod.model.ResMealOrderDetailVO;

public class ResMealOrderMasterService {

	private ResMealOrderMasterDAO_interface dao;
	
	public ResMealOrderMasterService() {
		dao = new  ResMealOrderMasterJNDIDAO();
	}
	
	public void insertWithDetails(ResMealOrderMasterVO resmomVO, List<OrderMealVO> list) {
		dao.insertWithDetails(resmomVO, list);
}
	 
	public int  findTotalByRoom(String tableNo) {
		
		return dao.findTotalByRoom(tableNo);
		
		}
	
	 public ResMealOrderMasterVO updateResMealOrderMaster(String resMealOrderNo, String bOrderNo, Integer tableNo, Integer totalPrice, 
			 Date orderDate,Integer orderStatus, String specialRequirement) {

		 ResMealOrderMasterVO resmVO = new ResMealOrderMasterVO();

		 resmVO.setResMealOrderNo(resMealOrderNo);
		 resmVO.setbOrderNo(bOrderNo);
		 resmVO.setTableNo(tableNo);
		 resmVO.setTotalPrice(totalPrice);
		 resmVO.setOrderDate(orderDate);
		 resmVO.setOrderStatus(orderStatus);
		 resmVO.setSpecialRequirement(specialRequirement);
			dao.update(resmVO);

			return resmVO;
		}
	 
	 public void deleteResMealOrderMaster(String resMealOrderNo) {
			dao.delete(resMealOrderNo);
		}

		public ResMealOrderMasterVO getOneMaster(String resMealOrderNo) {
			return dao.findByPrimaryKey(resMealOrderNo);
		}

		public List<ResMealOrderMasterVO> getAll() {
			return dao.getAll();
		}
	 
	 
}
