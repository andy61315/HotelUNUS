package com.cus.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.apache.catalina.Session;

import com.cus.model.*;

import common.Cus_MailService;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5* 1024 * 1024, maxRequestSize = 5*5 *1024 *1024)

public class CusServlet extends HttpServlet {

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
				String str = req.getParameter("cus_Id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String cus_Id = null;
				try {
					cus_Id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CustomerService customerSvc = new CustomerService();
				CustomerVO customerVO = customerSvc.getOneCus(cus_Id);
				if (customerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("customerVO", customerVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/customer/listOneCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/customer/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneId_For_Display".equals(action)) { // 來自select_page.jsp的請求(身分證字號)

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("id_Num");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員身分證字號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String id_Num = null;
				try {
					id_Num = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員身分證格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CustomerService customerSvc = new CustomerService();
				CustomerVO customerVO = customerSvc.getOneCusById(id_Num);
				if (customerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("customerVO", customerVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/customer/listOneCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/customer/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCus.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestUrl = req.getParameter("hiddenUrl");
			try {
				/***************************1.接收請求參數****************************************/
				String cus_Id = new String(req.getParameter("cus_Id"));
				
				/***************************2.開始查詢資料****************************************/
				CustomerService customerSvc = new CustomerService();
				CustomerVO customerVO = customerSvc.getOneCus(cus_Id);
								 
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("customerVO", customerVO);         // 資料庫取出的customerVO物件,存入req
				if( "backEnd".equals(requestUrl)) {
					String url = "/back-end/customer/update_cus_insert.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}else if( "frontEnd".equals(requestUrl)) {
					String url = "/front-end/customer/update_cus_front.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				if( "backEnd".equals(requestUrl)) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/listAllCus.jsp");
					failureView.forward(req, res);
				}else if( "frontEnd".equals(requestUrl)) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer/listOneCus.jsp");
				failureView.forward(req, res);
				}
				
			}
		}

		
		
//		---------登入------------
		if("Sign In".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//1.接受帳號密碼登入
				String cus_email = req.getParameter("cus_Email");
				String cus_password = req.getParameter("cus_PassWord");
				
				//2.
				CustomerService cusSvc = new CustomerService();
				CustomerVO customerVO = cusSvc.getOneCusByEmail(cus_email);
				
				//3.確認帳號密碼是否空白，如果都有輸入就讓程式繼續執行，不然就程式終止，這樣就不會跑到下面的帳密不正確
				if(cus_email == null || cus_email.length() == 0)
					errorMsgs.add("帳號不能空白");
				if(cus_password == null || cus_password.length() == 0)
					errorMsgs.add("密碼不能空白");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/front-end/homepage/Login.jsp");
					failure.forward(req, res);
					return;//程式終止
				}
				//確認帳號密碼是否正確
				if(customerVO == null || !(cus_password.equals(customerVO.getCus_Password()))) {
					errorMsgs.add("帳號/密碼不正確");
				}
				
				if(customerVO.getCus_Ck().equals(2)) {
//					RequestDispatcher failure = req.getRequestDispatcher("/front-end/homepage/Login.jsp");
//					failure.forward(req, res);
					errorMsgs.add("您的帳號已停權，請聯繫客服解鎖");
//					return;
				}
				else if(customerVO.getCus_Ck().equals(0)){
					errorMsgs.add("您的帳號未驗證，請先驗證帳號");
					RequestDispatcher failure = req.getRequestDispatcher("/front-end/customer/MailCheck.jsp");
					HttpSession session = req.getSession();
					session.setAttribute("customerVO", customerVO); 
					failure.forward(req, res);		
					return;//程式終止
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failure = req.getRequestDispatcher("/front-end/homepage/Login.jsp");
					failure.forward(req, res);
					return;//程式終止
				}
				//讓會員資料存入session，在其他頁面瀏覽的時候還是維持會員登入的狀態
				HttpSession session = req.getSession();
				session.setAttribute("customerVO", customerVO); 
				
				String location = (String) session.getAttribute("location");
				String url =  null;
				if(location == null) {
					url = req.getContextPath()+"/front-end/index.jsp";
				}else {
					url = location;
				}
				
				res.sendRedirect(url);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
							
			}catch(Exception e) {
				errorMsgs.add("登入失敗" + "("+e.getMessage()+")");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/homepage/Login.jsp");
				failureView.forward(req, res);
			}
		}
//-----------Login Add註冊-------------
		if("Sign Up".equals(action)) {//來自Login.jsp  Sign Up請求
			List<String>errorMsgs2 = new LinkedList<String>();
			req.setAttribute("errorMsgs2", errorMsgs2);
			
//			1.接受參數/格式錯誤處理
			String cus_Email = req.getParameter("cus_Email1").trim();
			CustomerService cusemailSvc = new CustomerService();
			CustomerVO customerVO = cusemailSvc.getCusEmail(cus_Email);
			if (customerVO != null) {
				errorMsgs2.add("電子郵件信箱重複註冊");
			}else {
				if(cus_Email == null || cus_Email.trim().length()==0) {
					errorMsgs2.add("電子郵件信箱請勿空白");
				}
			}
			
			String cus_Name = req.getParameter("cus_Name1");
			String cus_NameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (cus_Name == null || cus_Name.trim().length() == 0) {
				errorMsgs2.add("會員姓名: 請勿空白");
			} else if(!cus_Name.trim().matches(cus_NameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs2.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }			
			
			String id_Num = req.getParameter("id_Num1").trim();
			CustomerService id_NumSvc = new CustomerService();
			boolean id_numReg= id_NumSvc.getId_Num(id_Num);
			if (id_numReg==false) {
				errorMsgs2.add("身分證字號重複註冊");
			}else {
				if (id_Num == null || id_Num.trim().length() == 0) {
				errorMsgs2.add("身分證字號請勿空白");	
				}
			}
			String cus_Password = req.getParameter("cus_Password1").trim();
			String pswReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if(cus_Password==null || cus_Password.trim().length()==0) {
				errorMsgs2.add("密碼：請勿空白");
			}else if(!cus_Password.trim().matches(pswReg)) {
				errorMsgs2.add("密碼只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
			}		
			String cus_Cel = "0";
			Integer country = 1;
			java.sql.Date cus_Bir = null;
			byte[]idf_Pic=null;
			
			//Login端產生亂數驗證碼
			String captcha = "";
			char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',	'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
			Random ran = new Random();
			
			for (int i =0 ;  i<6 ; i++ ) {
					captcha+=(CHARS[ran.nextInt(CHARS.length)]);
			}
			
			CustomerVO customerVO3 = new CustomerVO();
			customerVO3.setCus_Email(cus_Email);
			customerVO3.setCus_Name(cus_Name);
			customerVO3.setId_Num(id_Num);
			customerVO3.setCus_Password(cus_Password);
			customerVO3.setCus_Cel(cus_Cel);
			customerVO3.setCountry(country);
			customerVO3.setCus_Bir(cus_Bir);
			customerVO3.setIdf_Pic(idf_Pic);
			customerVO3.setCaptcha(captcha);
			
			if (!errorMsgs2.isEmpty()) {
				req.setAttribute("customerVO", customerVO3); // 含有輸入格式錯誤的empVO物件,也存入req
				req.setAttribute("hasError", 1);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/homepage/Login.jsp");
				failureView.forward(req, res);
				return;
			}
//			-----------2.新增資料-----------
			CustomerService customerSvc = new CustomerService();
			customerSvc.addCus(cus_Name, cus_Email, cus_Cel, country, id_Num, cus_Bir, cus_Password, idf_Pic, captcha);
			customerVO = customerSvc.getCusEmail(cus_Email);
//			----------3.新增資料完成/轉交----------
			//讓會員資料存入session，在其他頁面瀏覽的時候還是維持會員登入的狀態
			HttpSession session = req.getSession();
			session.setAttribute("customerVO", customerVO); 
			String url = "/front-end/customer/listOneCus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		
		
//		---------------登出-----------------
		if("logout".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//登出之後去拿掉session裡的attribute
				HttpSession session = req.getSession();
				session.removeAttribute("customerVO");
				String url = req.getContextPath()+"/front-end/index.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交Login.jsp
//				successView.forward(req, res);
				req.getSession().removeAttribute("location");
				res.sendRedirect(url);
				
				
			}catch(Exception e) {
				errorMsgs.add("登入失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("update".equals(action)) { // 來自update_emp_insert.jsp的請求
			String requestUrl = req.getParameter("hiddenUrl");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String cus_Id = new String(req.getParameter("cus_Id").trim());
				String cus_Name = req.getParameter("cus_Name");
				String cus_NameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cus_Name == null || cus_Name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!cus_Name.trim().matches(cus_NameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String cus_Email = req.getParameter("cus_Email").trim();
				if (cus_Email == null || cus_Email.trim().length() == 0) {
					errorMsgs.add("電子郵件信箱請勿空白");
				}

				
				String cus_Cel = req.getParameter("cus_Cel").trim();
				if (cus_Cel == null || cus_Cel.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				}
				
				Integer country = null;
				try {
					country = new Integer(req.getParameter("country").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("國別請填數字.");
				}
				
				String id_Num = req.getParameter("id_Num").trim();
				if (id_Num == null || id_Num.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				java.sql.Date cus_Bir = null;
				try {
					cus_Bir = java.sql.Date.valueOf(req.getParameter("cus_Bir").trim());
					//////system.out.out.println(req.getParameter("cus_Bir"));
				} catch (IllegalArgumentException e) {
					cus_Bir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}
				
				String cus_Password = req.getParameter("cus_Password").trim();
				if (cus_Password == null || cus_Password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
//				圖片上傳(upload)
				Part part1 = req.getPart("upfile1");
			
				byte[] idf_Pic =null;
				
				if(part1.getSize() != 0) {
					InputStream is1 = part1.getInputStream();
					idf_Pic = new byte[is1.available()];
					is1.read(idf_Pic);
				}else {
					CustomerService customerSvc = new CustomerService();
					CustomerVO customerVO = customerSvc.getOneCus(cus_Id);
					idf_Pic = customerVO.getIdf_Pic();
				} 
								
				String cus_CkGot = new String (req.getParameter("cus_Ck").trim());
				Integer cus_Ck = Integer.parseInt(cus_CkGot);
				
				CustomerService customerSvc2 = new CustomerService();
				CustomerVO captchaVO = customerSvc2.getOneCus(cus_Id);
				String captcha = captchaVO.getCaptcha();
				
				CustomerVO customerVO = new CustomerVO();
				customerVO.setCus_Id(cus_Id);
				customerVO.setCus_Name(cus_Name);
				customerVO.setCus_Email(cus_Email);
				customerVO.setCus_Cel(cus_Cel);
				customerVO.setCountry(country);
				customerVO.setId_Num(id_Num);
				customerVO.setCus_Bir(cus_Bir);
				customerVO.setCus_Password(cus_Password);
				customerVO.setIdf_Pic(idf_Pic);
				customerVO.setCus_Ck(cus_Ck);
				customerVO.setCaptcha(captcha);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("customerVO", customerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					if( "backEnd".equals(requestUrl)) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/customer/update_cus_insert.jsp");
						failureView.forward(req, res);
					}else if( "frontEnd".equals(requestUrl)) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/customer/update_cus_front.jsp");
					failureView.forward(req, res);
					}
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CustomerService customerSvc = new CustomerService();
				customerVO = customerSvc.updateCus(cus_Name, cus_Email, cus_Cel, country, id_Num, cus_Bir, cus_Password, idf_Pic, cus_Ck, cus_Id, captcha);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("customerVO", customerVO); // 資料庫update成功後,正確的的empVO物件,存入req

				HttpSession session = req.getSession();
				session.setAttribute("customerVO", customerVO); 
				if( "backEnd".equals(requestUrl)) {
					String url = "/back-end/customer/listOneCus.jsp";//這裡需要修改
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}else if( "frontEnd".equals(requestUrl)) {
					String url = "/front-end/customer/listOneCus.jsp";//這裡需要修改
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}
				
				
				/***************************其他可能的錯誤處理*************************************/
		}

		
        if ("insert".equals(action)) { // 來自addCus.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String cus_Name = req.getParameter("cus_Name");
				String cus_NameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cus_Name == null || cus_Name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!cus_Name.trim().matches(cus_NameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String cus_Email = req.getParameter("cus_Email").trim();
				CustomerService cusemailSvc = new CustomerService();
				CustomerVO customerVO2= cusemailSvc.getCusEmail(cus_Email);
				if (customerVO2 != null) {
					errorMsgs.add("電子郵件信箱重複註冊");
				}else {
					if (cus_Email == null || cus_Email.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");	
					}
				}

		
				String cus_Cel = req.getParameter("cus_Cel").trim();
				if (cus_Cel == null || cus_Cel.trim().length() == 0) {
					errorMsgs.add("行動電話請勿空白");
				}
				
				String countryStr = req.getParameter("country");
				Integer country =null;
					if (countryStr == null) {
						errorMsgs.add("國別請勿空白");
					}else {
						country = new Integer(countryStr);
					} 
				
				String id_Num = req.getParameter("id_Num").trim();
				CustomerService id_NumSvc = new CustomerService();
				boolean id_numReg= id_NumSvc.getId_Num(id_Num);
				if (id_numReg==false) {
					errorMsgs.add("身分證字號重複註冊");
				}else {
					if (id_Num == null || id_Num.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");	
					}
				}
				
				java.sql.Date cus_Bir = null;
				try {
					cus_Bir = java.sql.Date.valueOf(req.getParameter("cus_Bir").trim());
				} catch (IllegalArgumentException e) {
					cus_Bir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}

				
				String cus_Password = req.getParameter("cus_Password").trim();
				if (cus_Password == null || cus_Password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
//				圖片上傳(add)
				Part part1 = req.getPart("upfile1");
				InputStream is1 = part1.getInputStream();
				byte[] idf_Pic = new byte[is1.available()];
				is1.read(idf_Pic);
				
				String captcha = "";
				char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',	'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
				Random ran = new Random();
				
				for (int i =0 ;  i<6 ; i++ ) {
						captcha+=(CHARS[ran.nextInt(CHARS.length)]);
				}

				CustomerVO customerVO = new CustomerVO();
				customerVO.setCus_Name(cus_Name);
				customerVO.setCus_Email(cus_Email);
				customerVO.setCus_Cel(cus_Cel);
				customerVO.setCountry(country);
				customerVO.setId_Num(id_Num);
				customerVO.setCus_Bir(cus_Bir);
				customerVO.setCus_Password(cus_Password);
				customerVO.setIdf_Pic(idf_Pic);
				customerVO.setCaptcha(captcha);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("customerVO", customerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/customer/addCus.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CustomerService customerSvc = new CustomerService();
				customerVO = customerSvc.addCus(cus_Name, cus_Email, cus_Cel, country, id_Num, cus_Bir, cus_Password, idf_Pic, captcha);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/customer/listAllCus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/customer/addCus.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("getOne_CK".equals(action)) { // 前端驗證會員
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String cus_Id = new String(req.getParameter("cus_Id"));
				/***************************2.開始查詢資料****************************************/
				//////system.out.println("查詢開始");
				CustomerService customerSvc = new CustomerService();
				CustomerVO customerVO = customerSvc.getOneCus(cus_Id);
				
								 
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("customerVO", customerVO);  
				if(customerVO.getCus_Ck()==1) {
					errorMsgs.add("您已驗證過。");
					String url = "/front-end/customer/update_cus_front.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}else if(customerVO.getCus_Ck()==2) {
					errorMsgs.add("您的會員已註銷，請聯繫客服。");
					String url = "/front-end/customer/update_cus_front.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}else if(customerVO.getCus_Ck()==0) {
					String url ="/front-end/customer/MailCheck.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}
	
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());

					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer/listOneCus.jsp");
				failureView.forward(req, res);
				}
		}
		
		
		if ("checkCaptcha".equals(action)) { // 送出驗證
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String cus_Id = new String(req.getParameter("cus_Id"));
				/***************************2.開始查詢資料****************************************/
				//////system.out.println("查詢開始");
				CustomerService customerSvc = new CustomerService();
				CustomerVO customerGet = customerSvc.getOneCus(cus_Id);
				//////system.out.println(cus_Id);
				Integer cus_Ck  = customerGet.getCus_Ck();//查詢cus_Ck
				//////system.out.println(cus_Ck);
				String captcha = req.getParameter("captcha").trim();//抓取網頁輸入亂數
				String captchaIn = customerGet.getCaptcha().trim();
				//////system.out.println("我看看"+captcha+captchaIn+"顯示");
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				if(captchaIn.equals(captcha)) {//抓到資料庫亂數比對網頁輸入
					////system.out.println("確認驗證碼");
					cus_Ck = 1;
					CustomerVO customerVO5 = new CustomerVO();
					customerVO5.setCus_Id(cus_Id);
					customerVO5.setCus_Ck(cus_Ck);
					customerVO5 = customerSvc.updateCk(cus_Ck, cus_Id);
					
					CustomerService customerSvc5 = new CustomerService();
					CustomerVO customerVO = customerSvc5.getOneCus(cus_Id);
					req.setAttribute("customerVO", customerVO);
					HttpSession session = req.getSession();
					session.setAttribute("customerVO", customerVO); 
					errorMsgs.add("您驗證已通過。");
					String url = "/front-end/customer/update_cus_front.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}else if(captchaIn != captcha) {
					errorMsgs.add("驗證碼錯誤，請重新驗證。");
					String url = "/front-end/customer/MailCheck.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				}else {
					////system.out.println("亂七八糟");
				}
	
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());

					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer/listOneCus.jsp");
				failureView.forward(req, res);
				}
				
		}	
		
		if ("mailSend".equals(action)) { // 信件送出驗證
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String cus_Id = new String(req.getParameter("cus_Id"));
				/***************************2.開始查詢資料****************************************/
				Cus_MailService mailService = new Cus_MailService();
				CustomerService customerSvc = new CustomerService();
				CustomerVO customerVO = customerSvc.getOneCus(cus_Id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("customerVO", customerVO);  
				String cusName = customerVO.getCus_Name();
				String cusEmail = customerVO.getCus_Email();
				String cusCaptcha = customerVO.getCaptcha();
				String messageText = "Hello! " + cusName + "\n"+"歡迎加入Hotel UNUS，請至會員修改處輸入驗證碼。"+"\n"+" 請謹記此驗證碼: " + cusCaptcha ; 
				
				mailService.sendMail(cusEmail, "會員註冊驗證碼通知", messageText);
				
				String url = "/front-end/customer/MailCheck.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());

				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer/listOneCus.jsp");
			failureView.forward(req, res);
			}
			
		}		
	}
}
	

