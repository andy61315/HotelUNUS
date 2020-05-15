package com.shopping.controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.meal.model.OrderMealVO;
import com.resmealom.model.ResMealOrderMasterService;
import com.resmealom.model.ResMealOrderMasterVO;
import com.restaurant.model.RestaurantService;
import com.room.model.RoomService;
import com.room.model.RoomVO;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();		
		//String meal_name = req.getParameter("meal_name");
		
		@SuppressWarnings("unchecked")
		List<OrderMealVO> buylist = (Vector<OrderMealVO>) session.getAttribute("shoppingcart");
		//
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {//

			 
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			 
			else if (action.equals("ADD")) {
				//取得餐點
				OrderMealVO ameal = getMeal(req);
				

				if (buylist == null) { 
					buylist = new Vector<OrderMealVO>();
					buylist.add(ameal);
				} else {
					if (buylist.contains(ameal)) {
						//override hashcode和equals比對
						OrderMealVO innerMeal = buylist.get(buylist.indexOf(ameal));
						innerMeal.setQuantity(innerMeal.getQuantity() + ameal.getQuantity());
						
					} else {
						buylist.add(ameal);
					}
				}
			}
			if(action.equals("pay")) {
				
				//記得清空購物車//是房客導向不同頁面
				session.removeAttribute("amount");
				session.removeAttribute("shoppingcart");
								
				String url = "/back-end/resmeal/listOneShop.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
				
			}
			//未checkout時就取得總價放入session防止頁面重導金額不見
			String amount = String.valueOf(getTotal(buylist));
			session.setAttribute("amount", amount);//總金額由cart get
			session.setAttribute("shoppingcart", buylist);//setAttribute("shoppingcart", buylist)
			String url = "/back-end/resmeal/listOneShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		//由餐點+訂單產生明細
		else if (action.equals("CHECKOUT")) {
			List <String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//做輸入判斷:桌號不可為空
				String str =req.getParameter("table_no");
				//若沒輸入
				if(str==null||str.trim().length()==0) {
					errorMsgs.add("請輸入桌號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resmeal/listOneShop.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer table_no = null;
				try {
					table_no = new Integer(str);
				}catch(NumberFormatException e) {
					errorMsgs.add("桌號格式不正確");
			    }
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resmeal/listOneShop.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
					
            //房號可以為空，但若為房客則由退房結帳
			String room_no =  req.getParameter("room_no");//由點餐jsp取得輸入房號
			String b_order_no=null;//訂房訂單編號
			//RoomVO roomVo = new RoomVO();
			RoomService roomSvc = new RoomService();
			List<RoomVO> roomList = roomSvc.getAll();//取得所有room物件
			
			
			if(room_no==null || room_no.trim().length()==0) {
				room_no = "";//若沒輸入字串則送入空字串
				
	
			}else {
				
				Integer roomNo = null;
				try {
					roomNo = new Integer(room_no);
				}catch(NumberFormatException e) {
					errorMsgs.add("房號格式不正確");
			    }
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resmeal/listOneShop.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			//若有輸入房號則比對房號是否為入住
			for(RoomVO room : roomList) {
				if(room.getRoom_no().equals(room_no) && room.getRoom_status()==1) {
					b_order_no = room.getB_order_no();//取得比對成功房號之訂單編號
					
//					if(b_order_no==null) {
//						errorMsgs.add("無此訂房訂單");
//						return;
//					}
				}else if(room.getRoom_no().equals(room_no) && room.getRoom_status()==0) {
					errorMsgs.add("查無此訂房紀錄");
				}
			}
						
		}
			
			//特殊要求可以為空		
			String require = req.getParameter("require");
			if(require==null || require.trim().equals("")) {
				require = "無";
			}
			//總價一定會有
			Integer total = null;
			try{
				total = new Integer(req.getParameter("total").trim());//cart
			} catch(NumberFormatException e) {
				errorMsgs.add("...");
			}
			
			
			if(!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmeal/listOneShop.jsp");
			failureView.forward(req, res);
			return;//若有錯誤訊息則不跳轉畫面
		}
			
	/***************************2.開始包裝資料*****************************************/
			//取得資料>>產生訂單ResMealOrderMasterVO
			ResMealOrderMasterVO resmomVO = new ResMealOrderMasterVO();
			resmomVO.setTableNo(table_no);
			resmomVO.setbOrderNo(b_order_no);//送入訂房訂單編號
			resmomVO.setSpecialRequirement(require);
			resmomVO.setTotalPrice(total);
			

			
			//取得購物車內容
			List<OrderMealVO>list = (List<OrderMealVO>)session.getAttribute("shoppingcart");
			
			
					
						

			if(room_no.trim().length()!=0) {
				resmomVO.setOrderStatus(1);
				//開始新增訂單
				ResMealOrderMasterService resSrv = new ResMealOrderMasterService();
				resSrv.insertWithDetails(resmomVO, list);
				String url = "/back-end/resmeal/Checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			} else {
				resmomVO.setOrderStatus(2);
				//開始新增訂單
				ResMealOrderMasterService resSrv = new ResMealOrderMasterService();
				resSrv.insertWithDetails(resmomVO, list);
				session.removeAttribute("amount");
				session.removeAttribute("shoppingcart");
				String url = "/back-end/resmeal/listOneShop.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
			
			
			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				if (e.getMessage().contains("parent key not found")) {
					errorMsgs.add("訂單號輸入錯誤");
				} else {
					errorMsgs.add("無法取得資料:" + e.getMessage());
				}
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmeal/listOneShop.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		
	}

	private OrderMealVO getMeal(HttpServletRequest req) {
		// 由listAllShop取得輸入參數
		String meal_no = req.getParameter("meal_no");
		String meal_name = req.getParameter("meal_name");
		Integer price = new Integer(req.getParameter("price"));
		Integer quantity = new Integer(req.getParameter("quantity"));

		OrderMealVO meal = new OrderMealVO();
		meal.setMeal_no(meal_no);
		meal.setMeal_name(meal_name);
		meal.setPrice(price);
		meal.setQuantity(quantity);
		return meal;
	}

	// 計算總價
	private Integer getTotal(List<OrderMealVO> buylist) {
		Integer total = 0;
		for (int i = 0; i < buylist.size(); i++) {
			OrderMealVO order = buylist.get(i);
			Integer price = order.getPrice();
			Integer quantity = order.getQuantity();
			total += (price * quantity);
		}
		return total;
	}
}