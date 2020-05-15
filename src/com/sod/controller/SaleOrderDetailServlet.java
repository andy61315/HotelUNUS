package com.sod.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sod.model.SaleOrderDetailService;
import com.sod.model.SaleOrderDetailVO;

public class SaleOrderDetailServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("room_type_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/sod/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String room_type_no = null;
//				try {
//					room_type_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("房型編號不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/sod/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				String str2 = req.getParameter("sapl_no");
//				if (str2 == null || (str2.trim()).length() == 0) {
//					errorMsgs.add("優惠編號不能空白");
//				}
//				String	sapl_no=null;
//				try {
//					sapl_no = new String(str2);
//				} catch (Exception e) {
//					errorMsgs.add("優惠編號不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/sod/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				/***************************2.開始查詢資料*****************************************/
//				SaleOrderDetailService saleOrderDetailSvc = new SaleOrderDetailService();
//				SaleOrderDetailVO saleOrderDetailVO = saleOrderDetailSvc.findByPrimaryKey(room_type_no, sapl_no);
//				if (saleOrderDetailVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/sod/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("saleOrderDetailVO", saleOrderDetailVO); // 資料庫取出的SaleOrderDetailVO物件,存入req
//				String url = "/back-end/sod/listOneSod.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/sod/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String room_type_no = req.getParameter("room_type_no");
//				
//				String sapl_no = req.getParameter("sapl_no");
//				/***************************2.開始查詢資料****************************************/
//				SaleOrderDetailService saleOrderDetailSvc = new SaleOrderDetailService();
//				SaleOrderDetailVO saleOrderDetailVO = saleOrderDetailSvc.findByPrimaryKey(room_type_no, sapl_no);
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("saleOrderDetailVO", saleOrderDetailVO);         // 資料庫取出的SaleOrderDetailVO物件,存入req
//				String url = "/back-end/sod/update_sod_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/sod/listAllSod.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String room_type_no = req.getParameter("room_type_no");
				
				String sapl_no = req.getParameter("sapl_no");
				if (sapl_no == null || sapl_no.trim().length() == 0) {
					errorMsgs.add("優惠編號請勿空白");
				}
				
//				Integer sapl_price =null;
//				try {
//					sapl_price = new Integer (req.getParameter("sapl_price").trim());
//				} catch (NumberFormatException e) {
//					sapl_price = 0;
//					errorMsgs.add("價格請填數字.");
//				}
				

				SaleOrderDetailVO saleOrderDetailVO = new SaleOrderDetailVO();
				saleOrderDetailVO.setRoom_type_no(room_type_no);
				saleOrderDetailVO.setSapl_no(sapl_no);
//				saleOrderDetailVO.setSapl_price(sapl_price);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("saleOrderDetailVO", saleOrderDetailVO); // 含有輸入格式錯誤的SaleOrderDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sod/update_sod_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				SaleOrderDetailService saleOrderDetailSvc = new SaleOrderDetailService();
				saleOrderDetailVO = saleOrderDetailSvc.update(room_type_no,sapl_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("saleOrderDetailVO", saleOrderDetailVO); // 資料庫update成功後,正確的的SaleOrderDetailVO物件,存入req
				String url = "/back-end/sod/listOneSod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sod/update_sod_input.jsp");
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
				String room_type_no = req.getParameter("room_type_no");
				
				if (room_type_no == null || room_type_no.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} 
				
				String sapl_no = req.getParameter("sapl_no").trim();
				if (sapl_no == null || sapl_no.trim().length() == 0) {
					errorMsgs.add("編號請勿空白");
				}
				
//				Integer sapl_price = null;
//				try {
//					sapl_price = new Integer(req.getParameter("sapl_price").trim());
//				} catch (NumberFormatException e) {
//					sapl_price = 0;
//					errorMsgs.add("價格請填數字.");
//				}
				
				
				SaleOrderDetailVO saleOrderDetailVO = new SaleOrderDetailVO();
				saleOrderDetailVO.setRoom_type_no(room_type_no);
				saleOrderDetailVO.setSapl_no(sapl_no);
//				saleOrderDetailVO.setSapl_price(sapl_price);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("saleOrderDetailVO", saleOrderDetailVO); // 含有輸入格式錯誤的SaleOrderDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sod/addSod.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SaleOrderDetailService saleOrderDetailSvc = new SaleOrderDetailService();
				saleOrderDetailVO = saleOrderDetailSvc.insert(room_type_no, sapl_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/sod/listAllSod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sod/addSod.jsp");
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
				String room_type_no = req.getParameter("room_type_no");
				String sapl_no = req.getParameter("sapl_no").trim();
				/***************************2.開始刪除資料***************************************/
				SaleOrderDetailService saleOrderDetailSvc = new SaleOrderDetailService();
				saleOrderDetailSvc.deletSod(room_type_no, sapl_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/sod/listAllSod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sod/listAllSod.jsp");
				failureView.forward(req, res);
			}
		}
	}
}


