package com.diarymessage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diary.model.DiaryService;
import com.diary.model.DiaryVO;
import com.diarymessage.model.DiaryMessageService;
import com.diarymessage.model.DiaryMessageVO;

public class DiaryMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
//		System.out.println("進入dopost");
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			// 來自 diary/listOneDiary.jsp的請求
			if ("getOne_For_Display".equals(action)) {
	
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	
				try {
					/*************************** 1.接收請求參數 ****************************************/
//					System.out.println("0");
					String diary_no = new String(req.getParameter("diary_no"));
		
					/*************************** 2.開始查詢資料 ****************************************/
					DiaryMessageService diaryMessageSvc = new DiaryMessageService();
					List<DiaryMessageVO> msgList = diaryMessageSvc.getAllMsg(diary_no);
//					System.out.println("2");
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("msgList", msgList);    // 資料庫取出的set物件,存入request
					DiaryVO diaryVO = new DiaryService().getOneDiary(diary_no);
					String url = null;
					if ("listOneDiary.jsp".equals(action))
						url = "/diary/listOneDiary.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp

					
//							else if ("listEmps_ByDeptno_B".equals(action))
//						url = "/dept/listAllDept.jsp";              // 成功轉交 dept/listAllDept.jsp

					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					System.out.println("4");
					/*************************** 其他可能的錯誤處理 ***********************************/
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
			
//			List<DiaryVO> list = new DiaryService().getAll();
//			for(DiaryVO s :list) {				
//			}
			if ("delete".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("diary_message_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/diarymessage/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String diary_message_no = null;
				try {
					diary_message_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DiaryMessageService diaryMessageSvc = new DiaryMessageService();
				DiaryMessageVO diaryMessageVO = diaryMessageSvc.getOneDiaryMessage(diary_message_no);
				if (diaryMessageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("diaryMessageVO", diaryMessageVO); // 資料庫取出的diaryVO物件,存入req
				String url = "/front-end/diary/listOneDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
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
				String diary_message_no = new String(req.getParameter("diary_message_no"));
//				System.out.println(diary_message_no);
				/***************************2.開始查詢資料****************************************/
				DiaryMessageService diaryMessageSvc = new DiaryMessageService();
				DiaryMessageVO diaryMessageVO = diaryMessageSvc.getOneDiaryMessage(diary_message_no);
//				System.out.println(diaryMessageVO.getDiary_message_no());
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("diaryMessageVO", diaryMessageVO);         // 資料庫取出的diaryVO物件,存入req
				String url = "/back-end/diarymessage/update_diaryMessage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
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
//				System.out.println(req.getParameter("diary_message_no"));

				String diary_message_no = new String(req.getParameter("diary_message_no").trim());
				String cus_id = req.getParameter("cus_id");
				String cus_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cus_id == null || cus_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!cus_id.trim().matches(cus_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在8以內");
	            }
				
				java.sql.Date diary_message_date = null;
				try {
					diary_message_date = java.sql.Date.valueOf(req.getParameter("diary_message_date").trim());
				} catch (IllegalArgumentException e) {
					diary_message_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String diary_no = req.getParameter("diary_title").trim();
				if (diary_no == null || diary_no.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	
						
				String diary_message_content = null;
				try {
					diary_message_content = new String(req.getParameter("content").trim());
				} catch (NumberFormatException e) {
					diary_message_content = null;
					errorMsgs.add("請填寫內容.");
				}

				Integer diary_message_status = new Integer(req.getParameter("diary_message_status").trim());

				DiaryMessageVO diaryMessageVO = new DiaryMessageVO();

				diaryMessageVO.setDiary_message_no(diary_message_no);
				diaryMessageVO.setCus_id(cus_id);
				diaryMessageVO.setDiary_no(diary_no);
				diaryMessageVO.setDiary_message_date(diary_message_date);
				diaryMessageVO.setDiary_message_content(diary_message_content);
				diaryMessageVO.setDiary_message_status(diary_message_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diaryMessageVO", diaryMessageVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/diarymessage/update_diaryMessage_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DiaryMessageService diaryMessageSvc = new DiaryMessageService();
				diaryMessageVO = diaryMessageSvc.updateDiaryMessage(diary_message_no, cus_id, diary_no, diary_message_date, diary_message_content, diary_message_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("diaryMessageVO", diaryMessageVO); // 資料庫update成功後,正確的的diaryVO物件,存入req
				String url = "/back-end/diarymessage/listOneDiaryMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/diarymessage/update_diaryMessage_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

//    		System.out.println("進入insert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("action ="+ action);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String diary_no = new String(req.getParameter("diary_no").trim());
				
				String cus_id = req.getParameter("cus_Id");
				if(cus_id == null || ("").equals(cus_id)) {
					errorMsgs.add("請先登入會員");
//					System.out.println("會員ID空白");
				}
//				System.out.println("cus_id = " + cus_id);
//				String cus_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (cus_id == null || cus_id.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!cus_id.trim().matches(cus_idReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在8以內");
//	            }
				
//				java.sql.Date diary_message_date = null;
//				try {
//					diary_message_date = java.sql.Date.valueOf(req.getParameter("diary_message_date").trim());
//				} catch (IllegalArgumentException e) {
//					diary_message_date=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				String diary_no = req.getParameter("diary_no").trim();
				if (diary_no == null || diary_no.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	
//				System.out.println("diary_no"+diary_no);	
				String diary_message_content = null;
				
				diary_message_content = new String(req.getParameter("content").trim());
				if (diary_message_content == null || diary_message_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}	
				Integer diary_message_status = null;
				
				diary_message_status = new Integer(1);
						
				
				DiaryMessageVO diaryMessageVO = new DiaryMessageVO();

//				diaryVO.setDiary_no(diary_no);
				
//				diaryMessageVO.setCus_id(cus_id);
				diaryMessageVO.setDiary_no(diary_no);
//				diaryMessageVO.setDiary_message_date(diary_message_date);
				diaryMessageVO.setDiary_message_content(diary_message_content);
				diaryMessageVO.setDiary_message_status(diary_message_status);
				DiaryService dSvcDiaryService=new DiaryService();
				DiaryVO diaryVO = dSvcDiaryService.getOneDiary(diary_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diaryVO", diaryVO);
					System.out.println("diaryVO = " + diaryVO);
					req.setAttribute("diaryMessageVO", diaryMessageVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DiaryMessageService diaryMessageSvc = new DiaryMessageService();
				diaryMessageVO = diaryMessageSvc.addDiaryMessage(cus_id, diary_no, diary_message_content, diary_message_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("diaryVO", diaryVO);
				req.setAttribute("diaryMessageVO", diaryMessageVO);
				String url = "/front-end/diary/listOneDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
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
				String diaryMessage = new String(req.getParameter("diary_message_no"));
				
				/***************************2.開始刪除資料***************************************/
				DiaryMessageService diaryMessageSvc = new DiaryMessageService();
				diaryMessageSvc.deleteDiaryMessage(diaryMessage);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/diary/listOneDiary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/diary/listOneDiary.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

