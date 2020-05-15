package com.resmealod.model;

public class ResMealOrderDetailVO implements java.io.Serializable {
    //用餐訂單明細
	  
	private String resMealOrderNo;//用餐訂單編號
	private String mealNo;//餐點編號
	private Integer price;//單價
	private Integer quantity;//已取消
	
	public ResMealOrderDetailVO(String resMealOrderNo, String mealNo, Integer price,
			Integer quantity) {
		super();
		this.resMealOrderNo = resMealOrderNo;
		this.mealNo = mealNo;
		this.price = price;
		this.quantity = quantity;
	}
	
	

	public ResMealOrderDetailVO() {
		super();
	}



	public String getResMealOrderNo() {
		return resMealOrderNo;
	}

	public void setResMealOrderNo(String resMealOrderNo) {
		this.resMealOrderNo = resMealOrderNo;
	}

	public String getMealNo() {
		return mealNo;
	}

	public void setMealNo(String mealNo) {
		this.mealNo = mealNo;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
