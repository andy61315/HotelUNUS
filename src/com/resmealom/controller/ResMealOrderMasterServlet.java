package com.resmealom.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.resmealom.model.ResMealOrderMasterService;
import com.resmealom.model.ResMealOrderMasterVO;

public class ResMealOrderMasterServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 取参数的方法。把jsp文件中的数据读取到出来。然后就可以封装利用起来

		System.out.println(action);
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			// 送出查詢條件
			

			List<String> errorMsgs = new LinkedList<String>();// 錯誤訊息集合
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view 錯誤處理畫面.
			req.setAttribute("errorMsgs", errorMsgs);// 設定屬性:偕同

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("resMealOrderNo");// 取得select表單輸入訂單編號(value的概念)
                System.out.println(str);
                
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");// 錯誤訊息1
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// 若有錯誤訊息存在集合中
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmealom/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String resMealOrderNo = null;

				try {
					resMealOrderNo = str;
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmealom/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ResMealOrderMasterService resmomSvc = new ResMealOrderMasterService();// 工頭
				ResMealOrderMasterVO resmomVO = resmomSvc.getOneMaster(resMealOrderNo);
				System.out.println("resmomVO"+resmomVO);
				
				if (resmomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmealom/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("resmomVO", resmomVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/resmealom/listOneResMealOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmealom/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String resMealOrderNo = req.getParameter("resMealOrderNo");
				 System.out.println(resMealOrderNo);

				/*************************** 2.開始查詢資料 ****************************************/

				ResMealOrderMasterService resmomSvc = new ResMealOrderMasterService();
				ResMealOrderMasterVO resmomVO = resmomSvc.getOneMaster(resMealOrderNo);
				System.out.println(resmomVO);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("resmomVO", resmomVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/resmealom/update_ResMealOrderMaster_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				// System.out.println(successView);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resmealom/listAllResMealOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String resMealOrderNo = req.getParameter("resMealOrderNo").trim();

				String bOrderNo = req.getParameter("bOrderNo").trim();

				Integer tableNo = null;
				try {
					tableNo = new Integer(req.getParameter("tableNo").trim());
				} catch (NumberFormatException e) {
					tableNo = 0;
					errorMsgs.add("桌號請填數字.");
				}

				Integer totalPrice = null;
				try {
					totalPrice = new Integer(req.getParameter("totalPrice").trim());
				} catch (NumberFormatException e) {
					totalPrice = 0;
					errorMsgs.add("總價請填數字.");
				}

				java.sql.Date orderDate = null;
				try {
					orderDate = java.sql.Date.valueOf(req.getParameter("orderDate").trim());
				} catch (IllegalArgumentException e) {
					orderDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer orderStatus = null;
				try {
					orderStatus = new Integer(req.getParameter("orderStatus").trim());
				} catch (NumberFormatException e) {
					orderStatus = 0;
					errorMsgs.add("狀態請填數字.");
				}

				String specialRequirement = req.getParameter("specialRequirement").trim();
				if (specialRequirement == null || specialRequirement.trim().length() == 0) {
					errorMsgs.add("特殊需求:請勿空白");
				}

				// 放入VO
				ResMealOrderMasterVO resmomVO = new ResMealOrderMasterVO();
				resmomVO.setResMealOrderNo(resMealOrderNo);
				resmomVO.setbOrderNo(bOrderNo);
				resmomVO.setTableNo(tableNo);
				resmomVO.setTotalPrice(totalPrice);
				resmomVO.setOrderDate(orderDate);
				resmomVO.setOrderStatus(orderStatus);
				resmomVO.setSpecialRequirement(specialRequirement);
				// System.out.println(resmomVO);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resmomVO", resmomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resmealom/update_ResMealOrderMaster_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ResMealOrderMasterService resmomSvc = new ResMealOrderMasterService();
				resmomVO = resmomSvc.updateResMealOrderMaster(resMealOrderNo, bOrderNo, tableNo, totalPrice, orderDate,
						orderStatus, specialRequirement);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("resmomVO", resmomVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/resmealom/listOneResMealOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resmealom/update_ResMealOrderMaster_input.jsp");
				failureView.forward(req, res);
			}
		}

		//訂單由點餐結帳新增，不會在這新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				

				//訂房編號可以為空
				//若為訂房客人，則退房時再一起結帳
				String bOrderNo = req.getParameter("bOrderNo").trim();
				

				
				Integer tableNo = null;
				try {
					tableNo = new Integer(req.getParameter("tableNo").trim());
				} catch (NumberFormatException e) {
					tableNo = 0;
					errorMsgs.add("桌號請填數字.");
				}

				Integer totalPrice = null;
				try {
					totalPrice = new Integer(req.getParameter("totalPrice").trim());
				} catch (NumberFormatException e) {
					totalPrice = 0;
					errorMsgs.add("總價請填數字.");
				}

			

				String specialRequirement = req.getParameter("specialRequirement").trim();
				if (specialRequirement == null || specialRequirement.trim().length() == 0) {
					errorMsgs.add("特殊需求:請勿空白");
				}

				// 放入VO
				ResMealOrderMasterVO resmomVO = new ResMealOrderMasterVO();
				
				resmomVO.setbOrderNo(bOrderNo);
				resmomVO.setTableNo(tableNo);
				resmomVO.setTotalPrice(totalPrice);
				resmomVO.setSpecialRequirement(specialRequirement);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resmomVO", resmomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resmealom/addResMealOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
//				ResMealOrderMasterService resmomSvc = new ResMealOrderMasterService();
//				resmomSvc.insertWithDetails(resmomVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/resmealom/listAllResMealOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmealom/addResMealOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		//刪除功能
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String resMealOrderNo = req.getParameter("resMealOrderNo");

				/*************************** 2.開始刪除資料 ***************************************/
				ResMealOrderMasterService resmomSvc = new ResMealOrderMasterService();
				resmomSvc.deleteResMealOrderMaster(resMealOrderNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/resmealom/listAllResMealOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				//若遇刪資料被參照到則會無法刪除
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resmealom/listAllResMealOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
