package com.diaryreport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diary.model.DiaryService;
import com.diary.model.DiaryVO;
import com.diaryreport.model.*;
//import com.diaryreport.model.DiaryReportVO;

public class DiaryReportServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insertReport".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer report_project = req.getParameter("report_project");
//				Integer report_projectReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
//				if (report_project == null || report_project.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!report_project.trim().matches(report_projectReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到200之間");
//	            }
				
				
				
				String diary_no = req.getParameter("diary_no").trim();
				String cus_id = req.getParameter("cus_id").trim();
				String report_project_string = req.getParameter("report_project").trim();
				Integer report_project = Integer.parseInt(report_project_string);
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					
//					req.setAttribute("action", "getOne_For_Display");
//					req.setAttribute("diary_report_no", diary_report_no); //暫無錯誤處裡
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/blog/blog.do");
//					failureView.forward(req, res);
//					return;
//				}
				
				/***************************2.開始新增資料***************************************/
				DiaryReportService diaryReportSvc = new DiaryReportService();
				diaryReportSvc.addDiaryReport(cus_id, diary_no, report_project);
				
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/diary/diary.do?action=getOne_For_Display&getOneDiary="+diary_no;
				req.setAttribute("diary_no", diary_no);				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		//後台處理檢舉
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String diary_noFromReport = req.getParameter("diary_noFromReport");
				String diary_report_no = req.getParameter("diary_report_no");
				Integer diary_report_status = new Integer(req.getParameter("diary_report_status"));
			/***************************2.開始新增資料***************************************/
				DiaryReportService  diaryReportSvc = new DiaryReportService();
				diaryReportSvc.updateDiaryReport(diary_report_no, diary_report_status);
				
				DiaryService  diarySvc = new DiaryService();
				
				diarySvc.deleteDiary(diary_noFromReport);
				
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/diaryreport/diary_report_Audited.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); //回到原頁面
//				successView.forward(req, res);				
				
				res.sendRedirect(req.getContextPath()+ url);
				return;
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				String url = "/back-end/diaryreport/diary_report.jsp";
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		//後台處理檢舉
				if ("updateaudited".equals(action)) {
					
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						
						String diary_report_no = req.getParameter("diary_report_no");
						Integer diary_report_status = new Integer(req.getParameter("diary_report_status"));
					/***************************2.開始新增資料***************************************/
						DiaryReportService  diaryReportSvc = new DiaryReportService();
						diaryReportSvc.updateDiaryReport(diary_report_no, diary_report_status);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
						String url = "/back-end/diaryreport/diary_report_Audited.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url); //回到原頁面
//						successView.forward(req, res);				
						
						res.sendRedirect(req.getContextPath()+ url);
						return;
						
					}catch (Exception e) {
						errorMsgs.add(e.getMessage());
						String url = "/back-end/diaryreport/diary_report.jsp";
						RequestDispatcher failureView = req
								.getRequestDispatcher(url);
						failureView.forward(req, res);
					}
				}
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("diary_report_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入日誌檢舉編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/diaryreport/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String diary_report_no = null;
//				try {
//					diary_report_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("日誌編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/diaryreport/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				DiaryReportService diaryReportSvc = new DiaryReportService();
//				DiaryReportVO diaryReportVO = diaryReportSvc.getOneDiaryReport(diary_report_no);
//				if (diaryReportVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/diaryreport/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("diaryReportVO", diaryReportVO); // 資料庫取出的diaryVO物件,存入req
//				String url = "/back-end/diaryreport/listOneDiaryReport.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/diaryreport/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String diary_report_no = new String(req.getParameter("diary_report_no"));
//				System.out.println(diary_report_no);
//				/***************************2.開始查詢資料****************************************/
//				DiaryReportService diaryReportSvc = new DiaryReportService();
//				DiaryReportVO diaryReportVO = diaryReportSvc.getOneDiaryReport(diary_report_no);
////				System.out.println(diaryReportVO.getDiary_report_no());
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("diaryReportVO", diaryReportVO);         // 資料庫取出的diaryVO物件,存入req
//				String url = "/back-end/diaryreport/update_diaryReport_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/diaryreport/listAllDiaryReport.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
////				System.out.println(req.getParameter("diary_report_no"));
//
//				String diary_report_no = new String(req.getParameter("diary_report_no").trim());
//				String cus_id = req.getParameter("cus_id");
//				String cus_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (cus_id == null || cus_id.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!cus_id.trim().matches(cus_idReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在8以內");
//	            }
//				
//				String diary_no = new String(req.getParameter("diary_no").trim());
//				
//				Integer report_project = new Integer(req.getParameter("report_project").trim());
//				
//				java.sql.Date report_date = null;
//				try {
//					report_date = java.sql.Date.valueOf(req.getParameter("report_date").trim());
//				} catch (IllegalArgumentException e) {
//					report_date=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				
//
//				Integer diary_report_status = new Integer(req.getParameter("diary_report_status").trim());
//
//				DiaryReportVO diaryReportVO = new DiaryReportVO();
//
//				diaryReportVO.setDiary_report_no(diary_report_no);
//				diaryReportVO.setCus_id(cus_id);
//				diaryReportVO.setDiary_no(diary_no);
//				diaryReportVO.setReport_project(report_project);
//				diaryReportVO.setReport_date(report_date);
//				diaryReportVO.setDiary_report_status(diary_report_status);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("diaryReportVO", diaryReportVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/diaryreport/update_diaryReport_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				DiaryReportService diaryReportSvc = new DiaryReportService();
//				diaryReportVO = diaryReportSvc.updateDiaryReport(diary_report_no, cus_id, diary_no, report_project, report_date, diary_report_status);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("diaryReportVO", diaryReportVO); // 資料庫update成功後,正確的的diaryVO物件,存入req
//				String url = "/back-end/diaryreport/listOneDiaryReport.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/diaryreport/update_diaryReport_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//
//    		System.out.println("進入insert");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
////				
//				
////				String diary_report_no = new String(req.getParameter("diary_report_no").trim());
//				String cus_id = req.getParameter("cus_id");
//				String cus_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (cus_id == null || cus_id.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!cus_id.trim().matches(cus_idReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在8以內");
//	            }
//				
//				String diary_no = new String(req.getParameter("diary_no").trim());
//				
//				Integer report_project = new Integer(req.getParameter("report_project").trim());
//				
//				java.sql.Date report_date = null;
//				try {
//					report_date = java.sql.Date.valueOf(req.getParameter("report_date").trim());
//				} catch (IllegalArgumentException e) {
//					report_date=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				
//
//				Integer diary_report_status = new Integer(req.getParameter("diary_report_status").trim());
//				try {
//				diary_report_status = new Integer(req.getParameter("diary_report_status").trim());
//				
//					
//				} catch (NumberFormatException e) {
//					diary_report_status = 0;
//					errorMsgs.add("狀態請勿空白!"
//							+ e.getMessage());
//					// TODO: handle exception
//				} catch (Exception e) {
//					diary_report_status = 0;
//					errorMsgs.add("查無資料!"
//							+ e.getMessage());
//					// TODO: handle exception
//				}
//				DiaryReportVO diaryReportVO = new DiaryReportVO();
//
////				diaryVO.setDiary_no(diary_no);
//				
//				diaryReportVO.setCus_id(cus_id);
//				diaryReportVO.setDiary_no(diary_no);
//				diaryReportVO.setReport_project(report_project);
//				diaryReportVO.setReport_date(report_date);
//				diaryReportVO.setDiary_report_status(diary_report_status);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("diaryReportVO", diaryReportVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/diaryreport/addDiaryReport.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				DiaryReportService diaryReportSvc = new DiaryReportService();
//				diaryReportVO = diaryReportSvc.addDiaryReport(cus_id, diary_no, report_project, report_date,diary_report_status);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/diaryreport/listAllDiaryReport.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/diaryreport/addDiaryReport.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String diary_report_no = new String(req.getParameter("diary_report_no"));
//				
//				/***************************2.開始刪除資料***************************************/
//				DiaryReportService diaryReportSvc = new DiaryReportService();
//				diaryReportSvc.deleteDiaryReport(diary_report_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back-end/diaryreport/listAllDiaryReport.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/diaryreport/listAllDiaryReport.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}

