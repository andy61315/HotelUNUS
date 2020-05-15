package com.resreservation.controller;

import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cus.model.CustomerService;
import com.cus.model.CustomerVO;
import com.resreservation.model.ResReservationService;
import com.resreservation.model.ResReservationVO;

public class ResReservationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		// 查詢一筆詳情
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("reservationNo");// (預約編號)輸入字串是否為空判斷
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入預約編號");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String reservationNo = str;// 將輸入字串指定給預約編號



				/*************************** 2.開始查詢資料 *****************************************/
				ResReservationService resrSvc = new ResReservationService();
				// 以預約編號為參數查詢
				ResReservationVO resrVO = resrSvc.getOneResReservation(reservationNo);

				if (resrVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/******************* 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("resrVO", resrVO);
				// 若查詢有值，到資料庫取出的VO物件,存入req
				// 並呈現查詢結果
				String url = "/back-end/resreservation/listOneResReservation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 查詢成功轉交 listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		//------------------------------------------------------------------------------------
		// 查詢一筆詳情
				if ("getOne_For_Front".equals(action)) { // 來自select_page.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*********** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						String str = req.getParameter("reservationNo");// (預約編號)輸入字串是否為空判斷
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入預約編號");
						}

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/resreservation/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}

						String reservationNo = str;// 將輸入字串指定給預約編號



						/*************************** 2.開始查詢資料 *****************************************/
						ResReservationService resrSvc = new ResReservationService();
						// 以預約編號為參數查詢
						ResReservationVO resrVO = resrSvc.getOneResReservation(reservationNo);

						if (resrVO == null) {
							errorMsgs.add("查無資料");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/resreservation/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}

						/******************* 3.查詢完成,準備轉交(Send the Success view) *************/
						req.setAttribute("resrVO", resrVO);
						// 若查詢有值，到資料庫取出的VO物件,存入req
						// 並呈現查詢結果
						String url = "/front-end/resreservation/listOneCusReservation.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOne.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/resreservation/select_page.jsp");
						failureView.forward(req, res);
					}
				}
		// -----以餐廳+日期查詢訂位------------------------------------------------------------

		if ("getDate_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("reservationDate");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入預約日期");
				}

				 //若無輸入日期
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				java.sql.Date reservationDate = null;
				try {
					reservationDate = java.sql.Date.valueOf(req.getParameter("reservationDate").trim());
				} catch (IllegalArgumentException e) {
					reservationDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String resNo = req.getParameter("resNo").trim();
//				if (resNo == null || resNo.trim().length() == 0) {
//					errorMsgs.add("餐廳請勿空白");
//				}
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}

				/*************************** 2.開始查詢資料 *****************************************/
				ResReservationService resrSvc = new ResReservationService();
				List<ResReservationVO> list = resrSvc.getOneDate(reservationDate,resNo);
                
				
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/******************* 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/resreservation/listDateResReservation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料2:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/resreservation/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//--------以顧客查詢所有明細----------------------------------------------------------------
		if ("getCus_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String custId = req.getParameter("custId");//1
			

				
				/*************************** 2.開始查詢資料 *****************************************/
				ResReservationService resrSvc = new ResReservationService();//2
				List<ResReservationVO> list = resrSvc.getCustomer(custId);//以list裝查詢結果
				

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// 若有錯誤訊息則留在當前頁面
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resreservation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/******************* 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("list", list); // 3.資料庫取出的VO物件,存入req
				String url = "/front-end/resreservation/listCusResReservation.jsp";//轉送路徑
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料2:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/resreservation/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ---------------------------------------------------------------------------

		if ("getOne_For_Update".equals(action)) { // 來自listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();// 適合中途新增刪除
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String reservationNo = new String(req.getParameter("reservationNo"));
				

				/*************************** 2.開始查詢資料 ****************************************/

				ResReservationService resrSvc = new ResReservationService();
				ResReservationVO resrVO = resrSvc.getOneResReservation(reservationNo);
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("resrVO", resrVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/resreservation/update_ResReservation_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resreservation/listAllResReservation.jsp");
				failureView.forward(req, res);
			}
		}

		//只有後台可以用的update
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String reservationNo = req.getParameter("reservationNo").trim();
				

				String custId = req.getParameter("custId").trim();
				if (custId == null || custId.trim().length() == 0) {
					errorMsgs.add("cust編號:請勿空白");
				}
				

				String resNo = req.getParameter("resNo").trim();
				if (resNo == null || resNo.trim().length() == 0) {
					errorMsgs.add("餐廳編號:請勿空白");
				}
				

				java.sql.Date reservationDate = null;
				try {
					reservationDate = java.sql.Date.valueOf(req.getParameter("reservationDate").trim());
				} catch (IllegalArgumentException e) {
					reservationDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				
				Integer resvPeriod = null;
				try {
					resvPeriod = new Integer(req.getParameter("resvPeriod").trim());
				} catch (NumberFormatException e) {
					resvPeriod = 0;
					errorMsgs.add("用餐時段請填數字.");
				}

				
				Integer resvPeople = null;
				try {
					resvPeople = new Integer(req.getParameter("resvPeople").trim());
				} catch (NumberFormatException e) {
					resvPeople = 2;
					errorMsgs.add("預約人數請填數字.");
				}


				// 放入VO
				ResReservationVO resrVO = new ResReservationVO();
				resrVO.setReservationNo(reservationNo);
				resrVO.setCustId(custId);
				resrVO.setResNo(resNo);
				resrVO.setReservationDate(reservationDate);
				resrVO.setResvPeriod(resvPeriod);
				resrVO.setResvPeople(resvPeople);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resrVO", resrVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/update_ResReservation_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ResReservationService resrSvc = new ResReservationService();
				boolean doubleOrder = resrSvc.getDouleOrder(resrVO);
				//訂位時段人數
				int people=resvPeople+resrSvc.getOnePeriod(reservationDate, resvPeriod, resNo);
				
				if(people<=20) {
					if(!doubleOrder) {
					 resrSvc.updateResReservation(reservationNo, custId, resNo, reservationDate, resvPeriod,
							resvPeople);
					resrVO=resrSvc.getOneResReservation(reservationNo);
					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
					req.setAttribute("resrVO", resrVO);
					// 資料庫update成功後,正確的的VO物件,存入req
					String url = "/back-end/resreservation/listOneResReservation.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
					
					}else {
						resrVO = resrSvc.getOneResReservation(reservationNo);
						req.setAttribute("resrVO", resrVO);
						errorMsgs.add("該時段已預約，請確認");
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/resreservation/update_ResReservation_input.jsp");
						failureView.forward(req, res);
					}
					
				}else {
					resrVO = resrSvc.getOneResReservation(reservationNo);
					req.setAttribute("resrVO", resrVO);
					errorMsgs.add("預約人數已滿，請選擇其他時段");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/update_ResReservation_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resreservation/update_ResReservation_input.jsp");
				failureView.forward(req, res);
			}
		}

		//-----後台預約新增-----------------------------------------------------------------------------
		if ("insert".equals(action)) { // 來自add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
				//預約編號為流水編號，狀態為預設

				String custMail = req.getParameter("custMail");
				//System.out.println(custMail);
				if (custMail == null || custMail.trim().length() == 0) {
					errorMsgs.add("客戶mail: 請勿空白");
				}
				CustomerService custSvc = new CustomerService();
				CustomerVO custVO = new CustomerVO();
				
				//以mail找到該客戶
				custVO = custSvc.getOneCusByEmail(custMail);
				//System.out.println(custVO);
				String custId = custVO.getCus_Id();
				
				
				

				String resNo = req.getParameter("resNo").trim();
				//System.out.println(resNo);確定有無取到值
				
				if (resNo == null || resNo.trim().length() == 0) {
					errorMsgs.add("餐廳id: 請勿空白");
				}

				java.sql.Date reservationDate = null;
				try {
					reservationDate = java.sql.Date.valueOf(req.getParameter("reservationDate").trim());
				} catch (IllegalArgumentException e) {
					reservationDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer resvPeriod = null;
				try {
					resvPeriod = new Integer(req.getParameter("resvPeriod").trim());
				} catch (NumberFormatException e) {
					resvPeriod = 0;
					errorMsgs.add("用餐時段請填數字.");
				}

				Integer resvPeople = null;
				try {
					resvPeople = new Integer(req.getParameter("resvPeople").trim());
				} catch (NumberFormatException e) {
					resvPeople = 2;
					errorMsgs.add("預約人數請填數字.");
				}



				/*********************** 2.判斷重複預約或預約已滿 的錯誤處理 *************************/

				ResReservationVO resrVO = new ResReservationVO();
				resrVO.setCustId(custId);
				resrVO.setResNo(resNo);
				resrVO.setReservationDate(reservationDate);
				resrVO.setResvPeriod(resvPeriod);
				resrVO.setResvPeople(resvPeople);

				//如果有上述錯誤訊息進到這裡
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resrVO", resrVO); //回傳含有輸入格式錯誤的reservationVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/addResReservation.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ResReservationService resrSvc = new ResReservationService();
				int People= resrVO.getResvPeople()+resrSvc.getOnePeriod(reservationDate, resvPeriod, resNo);
				boolean doubleOrder = resrSvc.getDouleOrder(resrVO);

				//boolean full = resrSvc.getFull(reservationDate, resvPeriod, resNo);

				if (People<= 20) {
					if(!doubleOrder) {
						//若無重複預約
						resrVO = resrSvc.addResReservation(custId, resNo, reservationDate, resvPeriod, resvPeople);
						//為了跳轉依日期列出的預約
						List<ResReservationVO> list = resrSvc.getOneDate(reservationDate, resNo);
						req.setAttribute("list", list);
						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						String url = "/back-end/resreservation/listDateResReservation.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);
					}else {
						errorMsgs.add("該時段已預約，請確認");
						req.setAttribute("resrVO", resrVO); //回傳含有輸入格式錯誤的reservationVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/resreservation/addResReservation.jsp");
						failureView.forward(req, res);
					}
					

				}else {
					req.setAttribute("resrVO", resrVO); //回傳含有輸入格式錯誤的reservationVO物件,也存入req
					errorMsgs.add("預約人數已滿，請選擇其他時段");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/resreservation/addResReservation.jsp");
					failureView.forward(req, res);
				}
				
			
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resreservation/addResReservation.jsp");
				failureView.forward(req, res);
			}
		}
		
		//----------------------------------------------------------------------------------------
		if ("insertcus".equals(action)) { // 來自前台add.jsp的請求//訂位表單

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("insertErrorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
				//預約編號為流水編號，狀態為預設

				String custId = req.getParameter("custId").trim();//客戶id
				

				String resNo = req.getParameter("resNo").trim();
				

				java.sql.Date reservationDate = null;
				try {
					reservationDate = java.sql.Date.valueOf(req.getParameter("reservationDate").trim());
					
				} catch (IllegalArgumentException e) {
					reservationDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer resvPeriod = null;
				try {
					resvPeriod = new Integer(req.getParameter("resvPeriod").trim());
					
				} catch (NumberFormatException e) {
					resvPeriod = 0;
					errorMsgs.add("用餐時段請填數字.");
				}

				Integer resvPeople = null;
				try {
					resvPeople = new Integer(req.getParameter("resvPeople").trim());
				} catch (NumberFormatException e) {
					resvPeople = 2;
					errorMsgs.add("預約人數請填數字.");
				}



				/*********************** 2.判斷重複預約或預約已滿 的錯誤處理 *************************/

				ResReservationVO resrVO = new ResReservationVO();//包裝新增資料
				resrVO.setCustId(custId);
				resrVO.setResNo(resNo);
				resrVO.setReservationDate(reservationDate);
				resrVO.setResvPeriod(resvPeriod);
				resrVO.setResvPeople(resvPeople);

				// 
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resrVO", resrVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resreservation/addCusReservation.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ResReservationService resrSvc = new ResReservationService();
				int People= resrVO.getResvPeople()+resrSvc.getOnePeriod(reservationDate, resvPeriod, resNo);
				boolean doubleOrder = resrSvc.getDouleOrder(resrVO);

				
				

				if (People<= 20) {
					if(!doubleOrder) {
						resrVO = resrSvc.addResReservation(custId, resNo, reservationDate, resvPeriod, resvPeople);
						List<ResReservationVO> list = resrSvc.getCustomer(custId);
						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						req.setAttribute("list", list);
						String url = "/front-end/resreservation/listCusResReservation.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功應改成listOne.jsp
						successView.forward(req, res);
					}else {
						req.setAttribute("resrVO", resrVO);
						errorMsgs.add("該時段已預約，請確認");
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/resreservation/addCusReservation.jsp");
						failureView.forward(req, res);
					}
					

				}else {
					req.setAttribute("resrVO", resrVO);
					errorMsgs.add("預約人數已滿，請選擇其他時段");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resreservation/addCusReservation.jsp");
					failureView.forward(req, res);
				}
				
				
			

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/resreservation/addCusReservation.jsp");
				failureView.forward(req, res);
			}
		}
		
		//---------------------------------------------------------------------------------------------------

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String reservationNo = req.getParameter("reservationNo");

				/*************************** 2.開始刪除資料 ***************************************/
				ResReservationService resrSvc = new ResReservationService();
				resrSvc.deleteResReservation(reservationNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/resreservation/listAllResReservation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resreservation/listAllResReservation.jsp");
				failureView.forward(req, res);
			}
		}

		if ("cancel".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String reservationNo = req.getParameter("reservationNo");

				/*************************** 2.開始刪除資料 ***************************************/
				ResReservationService resrSvc = new ResReservationService();
				resrSvc.updateStatus(reservationNo);
				//取得VO
				ResReservationVO resrVO = resrSvc.getOneResReservation(reservationNo);
				req.setAttribute("resrVO", resrVO);

				/*************************** 3.取消完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/resreservation/listOneResReservation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功後,轉交回送出的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("取消失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resreservation/listAllResReservation.jsp");
				failureView.forward(req, res);
			}
		}
		
		//------------更改狀態為已入座------------------------------------------------------------
		
		if ("update2".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String reservationNo = req.getParameter("reservationNo");

				/*************************** 2.開始刪除資料 ***************************************/
				ResReservationService resrSvc = new ResReservationService();
				resrSvc.updateStatus2(reservationNo);
				//取得VO
				ResReservationVO resrVO = resrSvc.getOneResReservation(reservationNo);
				req.setAttribute("resrVO", resrVO);

				/*************************** 3.取消完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/resreservation/listOneResReservation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功後,轉交回送出的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("取消失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/resreservation/listAllResReservation.jsp");
				failureView.forward(req, res);
			}
		}
		
		//------跳窗顯示預約已取消--------------------------------------------------------------
		if ("cancelfront".equals(action)) { // 來自前台listAllCus取消預約

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String reservationNo = req.getParameter("reservationNo");
						

				/*************************** 2.開始修改資料 ***************************************/
				ResReservationService resrSvc = new ResReservationService();
				resrSvc.updateStatus(reservationNo);
				//取得VO
				ResReservationVO resrVO = resrSvc.getOneResReservation(reservationNo);
				req.setAttribute("resrVO", resrVO);
				
				/*************************** 3.取消完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/resreservation/listOneCusReservation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功後,轉交回送出的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("取消失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/resreservation/listCusResReservation.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
