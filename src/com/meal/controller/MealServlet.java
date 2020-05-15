package com.meal.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.InterruptedNamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.meal.model.MealService;
import com.meal.model.MealVO;

import common.InputStreamUtils;

/**
 * Servlet implementation class MealServlet
 */
@WebServlet("/meal/meal.do")
@MultipartConfig(fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String meal_no = request.getParameter("meal_no");
				if (meal_no == null || meal_no.trim().length() == 0) {
					errorMsgs.add("無法取得餐點編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/meal/listAllMeal.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(meal_no);
				
				request.setAttribute("mealVO", mealVO);
				String url = "/front-end/meal/listOneMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改失敗:" + e.getMessage());
				RequestDispatcher failureView = request.
						getRequestDispatcher("/front-end/meal/listAllMeal.jsp"); 
				failureView.forward(request, response);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String meal_no = request.getParameter("meal_no");
				if (meal_no == null || meal_no.trim().length() == 0) {
					errorMsgs.add("無法取得餐點編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/meal/listAllMeal.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(meal_no);
				
				request.setAttribute("mealVO", mealVO);
				String url = "/back-end/meal/update_meal_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改失敗:" + e.getMessage());
				RequestDispatcher failureView = request.
						getRequestDispatcher("/back-end/meal/listAllMeal.jsp"); 
				failureView.forward(request, response);
			}
		}
		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String meal_no = request.getParameter("meal_no");
				String meal_name = request.getParameter("meal_name");
				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("餐點名稱:請勿空白");
				}
				
				Integer price = null;
				try {
					price = new Integer(request.getParameter("price"));
					if (price < 0) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException ne) {
					price = 0;
					errorMsgs.add("價格請填正整數" + ne.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("價格輸入錯誤" + e.getMessage());
				}
				
				// 下拉式選單
				String res_no = request.getParameter("res_no");
				String meal_type_no = request.getParameter("meal_type_no");
				Integer meal_status = new Integer(request.getParameter("meal_status"));
				
				Date meal_date = null;
				try {
					meal_date = Date.valueOf(request.getParameter("meal_date").trim());
				} catch (IllegalArgumentException e) {
					meal_date=new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String meal_introduction = request.getParameter("meal_introduction");
				
				byte[] meal_picture = null;
				Part part = null;
				try {
					// 圖片處理
					part = request.getPart("meal_picture");
					if (part != null) {
						meal_picture = InputStreamUtils.InputStreamTOByte(part);
					}
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("圖片修改失敗");
				}
				
				MealVO mealVO = new MealVO();
				mealVO.setMeal_introduction(meal_introduction);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_no(meal_no);
				mealVO.setMeal_picture(meal_picture);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_type_no(meal_type_no);
				mealVO.setPrice(price);
				mealVO.setRes_no(res_no);
				mealVO.setMeal_date(meal_date);
				
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("mealVO", mealVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/meal/update_meal_input.jsp");
					failureView.forward(request, response);
					return;
				}
				
				MealService mealSvc = new MealService();
				if (part == null || part.getSize() == 0) {
					mealVO = mealSvc.updateMealWithoutPicture(meal_name, price, res_no, meal_type_no, 
							meal_status, meal_introduction, meal_no, meal_date);
				} else {
					mealVO = mealSvc.updateMeal(meal_name, price, res_no, meal_type_no, meal_status, 
						meal_introduction, meal_picture, meal_no, meal_date);
				}
				
						
				request.setAttribute("mealVO", mealVO);
				String url = "/back-end/meal/listAllMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/meal/update_meal_input.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String meal_no = request.getParameter("meal_no");
				
				MealService mealSvc = new MealService();
				mealSvc.deleteMeal(meal_no);
				
				String url = "/back-end/meal/listAllMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = request.
						getRequestDispatcher("/back-end/meal/addMeal.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String meal_name = request.getParameter("meal_name");
				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("請輸入餐點名稱");
				}
				
				Integer price = null;
				try {
					price = new Integer(request.getParameter("price"));
					if (price < 0) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException ne) {
					price = 0;
					errorMsgs.add("價格請填正整數");
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("價格輸入錯誤" + e.getMessage());
				}
				
				String res_no = request.getParameter("res_no");
				String meal_type_no = request.getParameter("meal_type_no");
				Integer meal_status = new Integer(request.getParameter("meal_status"));

				Date meal_date = null;
				try {
					meal_date = Date.valueOf(request.getParameter("meal_date").trim());
				} catch (IllegalArgumentException e) {
					meal_date = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String meal_introduction = request.getParameter("meal_introduction");
				
				byte[] meal_picture = null;
				try {
					// 圖片處理
					Part part = request.getPart("meal_picture");
					
					if (part != null) {
						meal_picture = InputStreamUtils.InputStreamTOByte(part);
					} else {
						meal_picture = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("圖片修改失敗");
				}
				
				MealVO mealVO = new MealVO();
				mealVO.setMeal_introduction(meal_introduction);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_picture(meal_picture);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_type_no(meal_type_no);
				mealVO.setPrice(price);
				mealVO.setRes_no(res_no);
				mealVO.setMeal_date(meal_date);
				
				
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("mealVO", mealVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/meal/addMeal.jsp");
					failureView.forward(request, response);
					return;
				}
				
				MealService mealSvc = new MealService();
				mealVO = mealSvc.addMeal(meal_name, price, res_no, meal_type_no, meal_status, 
						meal_introduction, meal_picture, meal_date);
						
				String url = "/back-end/meal/listAllMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("發生錯誤" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/meal/addMeal.jsp");
				failureView.forward(request, response);
			}
		}
		
		//加入購物車
		if ("addToCart".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String meal_no = request.getParameter("meal_no");
				if (meal_no == null || meal_no.trim().length() == 0) {
					errorMsgs.add("meal_no 為 null 或是空字串");
				}
				String meal_name = request.getParameter("meal_name");
				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("meal_name 為 null 或是空字串");
				}
				
				Integer price = null;
				try {
//					price = Integer.valueOf(request.getParameter("price"));
					price = new Integer(request.getParameter("price"));
					if (price < 0) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException ne) {
					price = 0;
					errorMsgs.add("請輸入正整數" + ne.getMessage());
				} catch (Exception e) {
					errorMsgs.add("價格發生錯誤" + e.getMessage());
				}
				
				List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
				
				if (cart == null) {
					cart = new ArrayList<Map<String,Object>>();
					request.getSession().setAttribute("cart", cart);
				}
				
				int flag = 0;
				for (Map<String, Object> item : cart) {
					String meal_noInCart = (String) item.get("meal_no");
					// 購物車內已經有選擇的商品時
					if (meal_no.equals(meal_noInCart)) {
						Integer quantity = (Integer) item.get("quantity");
						// 後面要換成下拉式選單選數量
						quantity++;
						item.put("quantity", quantity);
						
						flag++;
					}
				}
				
				if (flag == 0) {
					// 沒有選擇的商品時
					Map<String, Object> item = new HashMap<String, Object>();
				
					item.put("meal_no", meal_no);
					item.put("meal_name", meal_name);
					item.put("price", price);
					item.put("quantity", 1);
					
					cart.add(item);
				}
				
				Integer itemNumberInCart = new Integer(cart.size());
				request.getSession().setAttribute("itemNumberInCart", itemNumberInCart);
				System.out.println(cart);
				
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.
							getRequestDispatcher("/front-end/meal/listOneMeal.jsp");
					failureView.forward(request, response);
				}
				
				String url = "/front-end/meal/listAllMeal.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("加入購物車錯誤:" + e.getMessage());
				RequestDispatcher failureView = request.
						getRequestDispatcher("/front-end/meal/listOneMeal.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
		
		
		
		
	}

	
}
