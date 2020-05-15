package com.diary.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.diary.model.*;
import com.diarymessage.model.DiaryMessageService;
import com.diarymessage.model.DiaryMessageVO;


public class DiaryServlet extends HttpServlet {

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
				String str = req.getParameter("diary_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入日誌編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String diary_no = null;
				try {
					diary_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("日誌編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DiaryService diarySvc = new DiaryService();
				DiaryVO diaryVO = diarySvc.getOneDiary(diary_no);
				if (diaryVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("diaryVO", diaryVO); // 資料庫取出的diaryVO物件,存入req
				String url = "/front-end/diary/listOneDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/diary/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String diary_no = new String(req.getParameter("diary_no"));
//
//				/***************************2.開始查詢資料****************************************/
//				DiaryService diarySvc = new DiaryService();
//				DiaryVO diaryVO = diarySvc.getOneDiary(diary_no);
//				System.out.println(diaryVO.getDiary_no());
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("diaryVO", diaryVO);         // 資料庫取出的diaryVO物件,存入req
//				String url = "/front-end/diary/update_diary_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/diary/listAllDiary.jsp");
//				failureView.forward(req, res);
//			}
//		}
			if ("getOne_For_Update".equals(action)) {// 來自update_diary_input
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
//				System.out.println(action);

				try {

					// 日誌編號
					String diary_no = req.getParameter("diary_no"); // 使用預設值

					String diary_title = req.getParameter("diary_title");
					String diary_titleReg = "^[^\\s]{2,50}$";
					if (diary_title == null || diary_title.trim().length() == 0) {
						errorMsgs.add("請輸入標題");
					} else if (!diary_title.trim().matches(diary_titleReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("標題長度必須在2到50之間");
					}

					String diary_content = req.getParameter("diary_content").trim();
					String temp = diary_content.replace(" ", "");
					if (diary_content == null || diary_content.trim().length() == 0 || "<p><br></p>".equals(diary_content) || temp.substring(3,temp.length()-4).isEmpty()) {
						errorMsgs.add("內容請勿空白");
					}

					
					// 狀態資料
					//Integer diary_status = new Integer(1);
					
					
					
					DiaryVO diaryVO = new DiaryVO();
					diaryVO.setDiary_no(diary_no);
					diaryVO.setDiary_title(diary_title);
					diaryVO.setDiary_content(diary_content);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("diaryVO", diaryVO);
						req.setAttribute("errorMsgs", errorMsgs);
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/update_diary_input.jsp");
						failureView.forward(req, res);
						return;
					}
					/***********2.開始修改資料******************/
					DiaryService diarySvc = new DiaryService();
					 diarySvc.updateDiary(diary_no, diary_title, diary_content);
					 diaryVO=diarySvc.getOneDiary(diary_no);
					/****************** 3.新增完成,準備轉交(Send the Success view)****************************/
					req.setAttribute("diaryVO", diaryVO);
					String url = "/front-end/diary/listOneDiary.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					/*************************** 其他可能的錯誤處理 **********/
				} catch (Exception e) {
					errorMsgs.add("其他問題" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/update_diary_input.jsp");
					failureView.forward(req, res);
				}
			}
		
		
//		if ("update".equals(action)) { 
//			// 來自update_emp_input.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

//
//				String diary_no = new String(req.getParameter("diary_no").trim());
//				String cus_id = req.getParameter("cus_id");
//				String cus_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (cus_id == null || cus_id.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!cus_id.trim().matches(cus_idReg)) { 
				//以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在8以內");
//	            }
//				
//				java.sql.Date diary_date = null;
//				try {
//					diary_date = java.sql.Date.valueOf(req.getParameter("diary_date").trim());
//				} catch (IllegalArgumentException e) {
//					diary_date=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				String diary_title = req.getParameter("diary_title").trim();
//				if (diary_title == null || diary_title.trim().length() == 0) {
//					errorMsgs.add("標題請勿空白");
//				}	
//						
//				String diary_content = null;
//				try {
//					diary_content = new String(req.getParameter("content").trim());
//				} catch (NumberFormatException e) {
//					diary_content = null;
//					errorMsgs.add("請填寫內容.");
//				}
//
//				Integer diary_status = new Integer(req.getParameter("diary_status").trim());
//
//				DiaryVO diaryVO = new DiaryVO();
//
//				diaryVO.setDiary_no(diary_no);
//				diaryVO.setCus_id(cus_id);
//				diaryVO.setDiary_date(diary_date);
//				diaryVO.setDiary_title(diary_title);
//				diaryVO.setDiary_content(diary_content);
//				diaryVO.setDiary_status(diary_status);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("diaryVO", diaryVO); 
			// 含有輸入格式錯誤的diaryVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/diary/update_diary_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				DiaryService diarySvc = new DiaryService();
//				diaryVO = diarySvc.updateDiary(diary_no, diary_title, diary_content,diary_status);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("diaryVO", diaryVO); // 資料庫update成功後,正確的的diaryVO物件,存入req
//				String url = "/front-end/diary/listOneDiary.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/diary/update_diary_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if("update".equals(action)) {//來自listOneDiary
			DiaryService diarySvc = new DiaryService();
			DiaryVO diaryVO = new DiaryVO();
			try {
				/*********1.接收請求參數******/
				String diary_no = req.getParameter("diary_no");
				System.out.println(diary_no);
				/*********2.取得資料******/
				diaryVO = diarySvc.getOneDiary(diary_no);
				req.setAttribute("diaryVO", diaryVO);
				/****************** 3.新增完成,準備轉交(Send the Success view)**********/
				String url = "/front-end/diary/update_diary_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch (Exception e) {
//				System.out.println("update出問題啦");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/select_page.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("diaryInsertErrorMsgs", errorMsgs);
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String diary_no = new String(req.getParameter("diary_no").trim());
				// 會員編號
				String cus_id = req.getParameter("cus_id"); // 使用預設值
				String diary_title = req.getParameter("diary_title");
				String diary_titleReg = "^[^\\s]{2,50}$";
				if (diary_title == null || diary_title.trim().length() == 0) {
					errorMsgs.add("請輸入標題");
				} else if (!diary_title.trim().matches(diary_titleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("標題長度必須在1到16之間");
				}
				
				java.sql.Date diary_date = null;
				try {
					System.out.println("diary_date = " + req.getParameter("diary_date"));
					diary_date = java.sql.Date.valueOf(req.getParameter("diary_date").trim());
				} catch (IllegalArgumentException e) {
					diary_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//				String diary_title = req.getParameter("diary_title").trim();
//				if (diary_title == null || diary_title.trim().length() == 0) {
//					errorMsgs.add("標題請勿空白!");
//				}						
//				String diary_content = req.getParameter("content").trim();
//				if (diary_content == null || diary_content.trim().length() == 0) {
//					errorMsgs.add("內容請勿空白!");
//				}	
				String diary_content = req.getParameter("content").trim();
				String temp = diary_content.replace(" ", "");
				if (diary_content == null || diary_content.trim().length() == 0 || "<p><br></p>".equals(diary_content) || temp.substring(3,temp.length()-4).isEmpty()) {
					errorMsgs.add("內容請勿空白");
				}
				System.out.println("content = " + req.getParameter("content"));
				
				Integer diary_status = null;
				try {
				diary_status = new Integer(req.getParameter("diary_status").trim());
				
					
				} catch (NumberFormatException e) {
					diary_status = 0;
					errorMsgs.add("狀態請勿空白!"
							+ e.getMessage());
					// TODO: handle exception
				} catch (Exception e) {
					diary_status = 0;
					errorMsgs.add("查無資料!"
							+ e.getMessage());
					// TODO: handle exception
				}
				DiaryVO diaryVO = new DiaryVO();

//				diaryVO.setDiary_no(diary_no);
				
				diaryVO.setCus_id(cus_id);
				diaryVO.setDiary_date(diary_date);
				diaryVO.setDiary_title(diary_title);
				diaryVO.setDiary_content(diary_content);
//				用預設值(JSP)
//				diaryVO.setDiary_status(diary_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diaryVO", diaryVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
					System.out.println("errorMsgs = " + errorMsgs);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/addDiary.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DiaryService diarySvc = new DiaryService();
				diaryVO = diarySvc.addDiary(cus_id, diary_date, diary_title, diary_content, diary_status);
				req.setAttribute("diaryVO", diaryVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/front-end/diary/listOneDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("其他問題" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/diary/addDiary.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("getSome_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String diary_key_name = req.getParameter("diary_key_name");
				System.out.println(diary_key_name);
				if (diary_key_name == null || (diary_key_name.trim()).length() == 0) {
					errorMsgs.add("請輸入關鍵字");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				DiaryService diarySvc = new DiaryService();
				List<DiaryVO> list = diarySvc.getSome(diary_key_name);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.getSession().setAttribute("diaryList", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/diary/listSomeDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("其他錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/select_page.jsp");
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
				String diary_no =req.getParameter("diary_no");
				
				/***************************2.開始刪除資料***************************************/
				DiaryService diarySvc = new DiaryService();
				diarySvc.deleteDiary(diary_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/diary/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/diary/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
