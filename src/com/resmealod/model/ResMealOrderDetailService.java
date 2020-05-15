package com.resmealod.model;

import java.util.List;




public class ResMealOrderDetailService {
	
	private ResMealOrderDetailDAO_interface dao;
	
	public ResMealOrderDetailService() {
		dao = new ResMealOrderDetailJNDIDAO();
	}

	public ResMealOrderDetailVO addResMealOrderDetail(String resMealOrderNo, String mealNo, Integer price, 
			Integer quantity) {

		 ResMealOrderDetailVO resdVO = new ResMealOrderDetailVO();

		 resdVO.setResMealOrderNo(resMealOrderNo);
		 resdVO.setMealNo(mealNo);
		 resdVO.setPrice(price);
		 resdVO.setQuantity(quantity);
		 
		dao.add(resdVO);

		return resdVO;
	}
	
	public ResMealOrderDetailVO updateResMealOrderDetail(Integer price, 
			String mealNo, Integer quantity) {

		 ResMealOrderDetailVO resdVO = new ResMealOrderDetailVO();
		 
		 resdVO.setPrice(price);
		 resdVO.setMealNo(mealNo);
	
		 resdVO.setQuantity(quantity);
		 
		dao.update(resdVO);

			return resdVO;
		}
	
	 public void deleteResMealOrderDetail(String resMealOrderNo, String mealNo) {
			dao.delete(resMealOrderNo, mealNo);
		}

		public ResMealOrderDetailVO getOneDetail(String resMealOrderNo, String mealNo) {
			return dao.findByPrimaryKey(resMealOrderNo, mealNo);
		}

		public List<ResMealOrderDetailVO> getAll() {
			return dao.getAll();
		}
	
	
}
