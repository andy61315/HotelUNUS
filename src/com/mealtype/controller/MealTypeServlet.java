package com.mealtype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mealtype.model.MealTypeService;
import com.mealtype.model.MealTypeVO;

/**
 * Servlet implementation class MealTypeServlet
 */
@WebServlet("/mealType/mealType.do")
public class MealTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String pk = req.getParameter("meal_type_no");
				
				MealTypeService mealTypeSvc = new MealTypeService();
				MealTypeVO mealTypeVO = mealTypeSvc.findByPK(pk);

				req.setAttribute("mealTypeVO", mealTypeVO);
				String url = "/back-end/mealType/update_mealType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mealType/listAllMealType.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String meal_type_no = req.getParameter("meal_type_no");
				
				String type_name = req.getParameter("type_name");
				if (meal_type_no == null || meal_type_no.trim().length() == 0) {
					errorMsgs.add("餐點類別名稱: 請勿空白");
				}
				
				
				MealTypeVO mealTypeVO = new MealTypeVO();
				mealTypeVO.setMeal_type_no(meal_type_no);
				mealTypeVO.setType_name(type_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealTypeVO", mealTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mealType/update_mealType_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				MealTypeService mealTypeSvc = new MealTypeService();
				mealTypeVO = mealTypeSvc.updateMealType(type_name, meal_type_no);
				
				
				req.setAttribute("mealTypeVO", mealTypeVO);
				String url = "/back-end/mealType/listAllMealType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mealType/update_mealType_input.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String pk = req.getParameter("meal_type_no");
				
				/***************************2.開始刪除資料***************************************/
				MealTypeService mealTypeSvc = new MealTypeService();
				mealTypeSvc.deleteMealType(pk);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/mealType/listAllMealType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mealType/listAllMealType.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String type_name = req.getParameter("type_name");
				if (type_name == null || type_name.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				MealTypeService mealTypeSvc = new MealTypeService();
				mealTypeSvc.addMealType(type_name);
				
				String url = "/back-end/mealType/listAllMealType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("Exception"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mealType/listAllMealType.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getAll".equals(action)) {
			
		}
		
		
		
		
		
	}

}
