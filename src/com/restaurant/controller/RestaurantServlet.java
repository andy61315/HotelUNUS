package com.restaurant.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.model.RestaurantService;
import com.restaurant.model.RestaurantVO;




@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("resNo");
				System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String resNo = null;
				try {
					resNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("餐廳編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RestaurantService restSvc = new RestaurantService();
				System.out.println(restSvc);
				RestaurantVO restVO = restSvc.getOneRestaurant(resNo);
				System.out.println(restVO);
				if (restVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

				req.setAttribute("restVO", restVO); // �資料庫取出的empVO物件,存入req
				String url = "/back-end/restaurant/listOneRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/restaurant/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//-----------------------------------------------------------------------------------------
		if ("getOne_For_FrontDisplay".equals(action)) { // 來自前台的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String resNo = req.getParameter("resNo");
				//System.out.println(resNo);
				if (resNo == null || (resNo.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
								
				/***************************2.開始查詢資料*****************************************/
				RestaurantService restSvc = new RestaurantService();
				System.out.println(restSvc);
				RestaurantVO restVO = restSvc.getOneRestaurant(resNo);
				System.out.println(restVO);
				if (restVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

				req.setAttribute("restVO", restVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/restaurant/frontlistOneRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/frontlistAllRestaurant_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//------取得點餐餐廳資訊---------------------------------------------------------------------
				if ("getOne_For_Meal".equals(action)) { // 來自listAll.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					
					try {
						/***************************1.接收請求參數****************************************/
						String resNo = new String(req.getParameter("resNo"));
						
						
						/***************************2.開始查詢資料****************************************/
						RestaurantService restSvc = new RestaurantService();
						RestaurantVO restVO = restSvc.getOneRestaurant(resNo);//先查出要修改的餐廳
						
										
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.getSession().setAttribute("restVO", restVO);
						// 資料庫取出的restVO物件,存入session
						String url = "/back-end/resmeal/listOneShop.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						// 成功轉交 shop.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改資料" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/restaurant/listAllRestaurant.jsp");
						failureView.forward(req, res);
					}
				}
		
		//------取得要修改餐廳資訊---------------------------------------------------------------------
		if ("getOne_For_Update".equals(action)) { // 來自listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String resNo = new String(req.getParameter("resNo"));
				
				
				/***************************2.開始查詢資料****************************************/
				RestaurantService restSvc = new RestaurantService();
				RestaurantVO restVO = restSvc.getOneRestaurant(resNo);//先查出要修改的餐廳
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("restVO", restVO);         // 資料庫取出的restVO物件,存入req
				String url = "/back-end/restaurant/update_Restaurant_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 update.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/restaurant/listAllRestaurant.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
					
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String resNo = req.getParameter("resNo").trim();
				if (resNo == null || resNo.trim().length() == 0) {
					errorMsgs.add("餐廳編號:請勿空白");
				}
				
				String resName = req.getParameter("resName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (resName == null || resName.trim().length() == 0) {
					errorMsgs.add("餐廳名稱:請勿空白");
				} else if(!resName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐廳名稱:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Integer totalSeat = null;
				try {
					totalSeat = new Integer(req.getParameter("totalSeat").trim());
				} catch (NumberFormatException e) {
					totalSeat = 50;
					errorMsgs.add("座位請填數字.");
				}		
				
				
				
				String resContact = req.getParameter("resContact").trim();
				if (resContact == null || resContact.trim().length() == 0) {
					errorMsgs.add("聯絡人請勿空白");
				}
				
				String resPhone = req.getParameter("resPhone").trim();
				if (resPhone == null || resPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
						

				Integer resStatus = null;
				try {
					resStatus = new Integer(req.getParameter("resStatus").trim());
				} catch (NumberFormatException e) {
					resStatus = 0;
					errorMsgs.add("狀態請勿空白");
				}

		        //接受輸入參數
				RestaurantVO restVO = new RestaurantVO();
				
				restVO.setResNo(resNo);
				restVO.setResName(resName);
				restVO.setTotalSeat(totalSeat);
				restVO.setResContact(resContact);
				restVO.setResPhone(resPhone);
				restVO.setResStatus(resStatus);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/update_Restaurant_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RestaurantService restSvc = new RestaurantService();
				restVO = restSvc.updateRestaurant(resNo, resName, totalSeat, resContact, resPhone,resStatus);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restVO", restVO); 
				// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/restaurant/listOneRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // listOneEmp.jsp
				successView.forward(req, res);
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/restaurant/update_Restaurant_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String resNo = req.getParameter("resNo").trim();
//				if (resNo == null || resNo.trim().length() == 0) {
//					errorMsgs.add("請勿空白");
//				}
				
				String resName = req.getParameter("resName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (resName == null || resName.trim().length() == 0) {
					errorMsgs.add("餐廳名稱:請勿空白");
				} else if(!resName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
							
				Integer totalSeat = null;
				try {
					totalSeat = new Integer(req.getParameter("totalSeat").trim());
				} catch (NumberFormatException e) {
					totalSeat = 50;
					errorMsgs.add("座位請填數字.");
				}	
				
				
				String resContact = req.getParameter("resContact").trim();
				if (resContact == null || resContact.trim().length() == 0) {
					errorMsgs.add("聯絡人請勿空白");
				}
				
				String resPhone = req.getParameter("resPhone").trim();
				if (resPhone == null || resPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				
				Integer resStatus = null;
				try {
					resStatus = new Integer(req.getParameter("resStatus").trim());
				} catch (NumberFormatException e) {
					resStatus = 0;
					errorMsgs.add("狀態請勿空白");
				}

				RestaurantVO restVO = new RestaurantVO();
//				restVO.setResNo(resNo);
				restVO.setResName(resName);
				restVO.setTotalSeat(totalSeat);
				restVO.setResContact(resContact);
				restVO.setResPhone(resPhone);
				restVO.setResStatus(resStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/restaurant/addRestaurant.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RestaurantService restSvc = new RestaurantService();
				restVO = restSvc.addRestaurant(resName, totalSeat, resContact, resPhone,resStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/restaurant/listAllRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/restaurant/addRestaurant.jsp");
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
				String resNo = new String(req.getParameter("resNo"));
				
				/***************************2.開始刪除資料***************************************/
				RestaurantService restSvc = new RestaurantService();
				restSvc.deleteRestaurant(resNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/restaurant/listAllRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/restaurant/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
