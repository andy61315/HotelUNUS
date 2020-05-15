package com.saleplan.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomtype.model.RoomTypeVO;
import com.saleplan.model.*;
import com.sod.model.SaleOrderDetailService;
import com.sod.model.SaleOrderDetailVO;


public class SalePlanServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		doPost(req,res);
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
				String str = req.getParameter("sapl_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請優惠編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/saleplan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String sapl_no = null;
				try {
					sapl_no = str;
				} catch (Exception e) {
					errorMsgs.add("優惠編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/saleplan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				 SalePlanService salSvc = new SalePlanService();
				 SalePlanVO salVo = salSvc.getOneSal(sapl_no);
				if (salVo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/saleplan/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("salVo", salVo); // 資料庫取出的SalePlanVO物件,存入req
				String url = "/back-end/saleplan/listOneSal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/saleplan/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String sapl_no = req.getParameter("sapl_no");
				
				/***************************2.開始查詢資料****************************************/
				SalePlanService salSvc = new SalePlanService();
				SalePlanVO salVo = salSvc.getOneSal(sapl_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("salVo", salVo);         // 資料庫取出的SalePlanVO物件,存入req
				String url = "/back-end/saleplan/update_sal_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/saleplan/listAllSal.jsp");
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
				String sapl_no =  req.getParameter("sapl_no").trim();
				
				String	 sapl_name = req.getParameter("sapl_name").trim();
				
				if (sapl_no == null || sapl_no.trim().length() == 0) {
					errorMsgs.add("請輸入優惠編號");
				} else if(sapl_name.length()==0) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠名稱不能留白");
	            }
				
				String detail = req.getParameter("detail").trim();
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}	
				
				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date").trim());
				} catch (IllegalArgumentException e) {
					start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Double sapl_discount = null;
				try {
					sapl_discount = new Double(req.getParameter("sapl_discount").trim());
				} catch (NumberFormatException e) {
					sapl_discount = 0.0;
					errorMsgs.add("薪水請填數字.");
				}

				Integer status = null;
				try {
					status = new Integer(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
					status = 0;
					errorMsgs.add("請輸入狀態.");
				}
				

				
				SalePlanVO salVo = new SalePlanVO();
				salVo.setSapl_no(sapl_no);
				salVo.setSapl_name(sapl_name);
				salVo.setDetail(detail);
				salVo.setStart_date(start_date);
				salVo.setEnd_date(end_date);
				salVo.setSapl_discount(sapl_discount);
				salVo.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("salVo", salVo); // 含有輸入格式錯誤的SalePlanVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/saleplan/update_sal_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				SalePlanService salSvc = new SalePlanService();
				salVo = salSvc.updateSal(sapl_name, detail, start_date, end_date,sapl_discount,status,sapl_no);
				System.out.println("123");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("salVo", salVo); // 資料庫update成功後,正確的的SalePlanVO物件,存入req
				String url = "/back-end/saleplan/listAllSal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/saleplan/update_sal_input.jsp");
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
				String sapl_name = req.getParameter("sapl_name");
				String sapl_no = req.getParameter("sapl_no");
				
				if (sapl_name == null || sapl_name.trim().length() == 0) {
					errorMsgs.add("優惠名稱請勿空白");
				} 
				
				String detail = req.getParameter("detail").trim();
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("優惠內容請勿空白");
				}
				
				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date").trim());
				} catch (IllegalArgumentException e) {
					start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				

				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				Double sapl_discount = null;
				try {
					sapl_discount = new Double(req.getParameter("sapl_discount").trim());
				} catch (NumberFormatException e) {
					sapl_discount = 0.0;
					errorMsgs.add("請修正折扣.");
				}
				
				String[] room_Type_Nos = req.getParameterValues("room_Type_No");
				if(room_Type_Nos==null) {
					errorMsgs.add("請選擇優惠房型");
				}

				Integer status = new Integer(req.getParameter("status").trim());

				SalePlanVO salVo = new SalePlanVO();
				salVo.setSapl_no(sapl_no);
				salVo.setSapl_name(sapl_name);
				salVo.setDetail(detail);
				salVo.setStart_date(start_date);
				salVo.setEnd_date(end_date);
				salVo.setSapl_discount(sapl_discount);
				salVo.setStatus(status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("salVo", salVo); // 含有輸入格式錯誤的SalePlanVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/saleplan/addSal.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SalePlanService salSvc = new SalePlanService();
				salSvc.insertWithSod(salVo, room_Type_Nos);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/saleplan/listAllSal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/saleplan/addSal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer sapl_no = new Integer(req.getParameter("sapl_no"));
//				
//				/***************************2.開始刪除資料***************************************/
//				SalePlanService salSvc = new SalePlanService();
//				salSvc.deleteEmp(sapl_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
	
	
}
