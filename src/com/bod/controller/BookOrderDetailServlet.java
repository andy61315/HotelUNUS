package com.bod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bod.model.*;




public class BookOrderDetailServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getBodByNoJSON".equals(action)) {
			String b_order_no = req.getParameter("b_order_no");
			List<BookOrderDetailVO> list = new BookOrderDetailService().getBookRoom(b_order_no);
//			System.out.println("list = " + list);
			JSONArray output = new JSONArray(list);
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
//			System.out.println("output = " + output);
			out.write(output.toString());
			out.flush();
			out.close();
		}
		
		if("deleteByJSON".equals(action)) {
			String b_order_no = req.getParameter("b_order_no");
			String room_type_no = req.getParameter("room_Type_No");
			System.out.println("BOD DAO room_type_no = " + room_type_no);
			BookOrderDetailService bodSvc = new BookOrderDetailService();
			bodSvc.deletebodVo(b_order_no,room_type_no);
			JSONObject output = new JSONObject();
			try {
				output.put("success", "Y");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			out.write(output.toString());
			out.flush();
			out.close();
			
		}
		
		
		if("getOne_For_Display".equals(action)) { //來自select_page.jsp??��?��??
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				
	//1.輸入?��誤格式�?��?��??**************************************************************
				String str = req.getParameter("b_order_no");
				String str1 = req.getParameter("room_type_no");
//				System.out.println(str1);
				System.out.println(str);
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("請輸?��訂單編�??");
				}else if(str1 == null || (str1.trim()).length()==0) {
					errorMsgs.add("請輸?��?��??�編???");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
					failureView.forward(req,res);
				return;//程�?�中?��
				}
				String b_order_no = null;
				String room_type_no = null;
				try {
					 b_order_no = new String(str);
					 room_type_no = new String(str1);
//					 room_type_no= new String(str1);
				}catch(Exception e) {
					errorMsgs.add("訂單編�?�格式�?�正�?");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	//2.?��詢�?��??********************************************************************************
				
				BookOrderDetailService bodSvc = new BookOrderDetailService();
				BookOrderDetailVO bodVo = bodSvc.getOneBod(b_order_no,room_type_no);


				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page_jsp");
					failureView.forward(req,res);
					return;
				}
			
	//3.?��詢�?��?��?��?��?��?�交
				req.setAttribute("bodVo",bodVo);
				String url = "/back-end/bod/listOneBod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
	
	
			}
	//4.?��他可?��??�錯誤�?��??
			catch(Exception e)
			{
				errorMsgs.add("請修正：");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getBookRoomDisplay".equals(action)) { 
			//來自select_page.jsp??��?��??
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			
			try {
				

				String str = req.getParameter("b_order_no");
				String requestURL = req.getParameter("requestURL");
				System.out.println(str+"getBookRoomDisplay");
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req,res);
				return;//程�?�中?��
				}
				String b_order_no = null;
				
				try {
					 b_order_no = new String(str);

				}catch(Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
	
				
				BookOrderDetailService bodSvc = new BookOrderDetailService();
				List<BookOrderDetailVO> list = bodSvc.getBookRoom(b_order_no);

				

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req,res);
					return;
				}
			
	
				req.setAttribute("list2",list);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			
	
			}

			catch(Exception e)
			{
				errorMsgs.add("請修正"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getRoomBookDisplay".equals(action)) { 
		
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				
	//1.輸入?��誤格式�?��?��??**************************************************************
				String str = req.getParameter("room_type_no");
				System.out.println(str);
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("請輸?��訂單編�??");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
					failureView.forward(req,res);
				return;//程�?�中?��
				}
				String room_type_no = null;
				
				try {
					room_type_no = new String(str);

				}catch(Exception e) {
					errorMsgs.add("訂單編�?�格式�?�正�?");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	//2.?��詢�?��??********************************************************************************
				
				BookOrderDetailService bodSvc = new BookOrderDetailService();
				List<BookOrderDetailVO> list = bodSvc.getSearch("room_type_no",room_type_no);

				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page_jsp");
					failureView.forward(req,res);
					return;
				}
			
	//3.?��詢�?��?��?��?��?��?�交
				req.getSession().setAttribute("list1",list);
				String url = "/back-end/bod/showRoomAllBook.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

	
			}
	//4.?��他可?��??�錯誤�?��??
			catch(Exception e)
			{
				errorMsgs.add("?��此�?��??");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
		
			try {
				//1.?��?��請�?��?�數
				String b_order_no = req.getParameter("b_order_no");
				String room_type_no = req.getParameter("room_type_no");
				//2.??��?�查�?
				BookOrderDetailService bodSvc =new BookOrderDetailService();
				BookOrderDetailVO bodVo = bodSvc.getOneBod(b_order_no,room_type_no);
				//3.轉�??
				req.setAttribute("bodVo", bodVo);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bod/update_bod_input.jsp");
				failureView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("?��法�?��?��?�修?��??��?��?��??"+ e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/bod/listAllBod.jsp");
				failureView.forward(req, res);
			}
		}

//?��?��＝�?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��??
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String b_order_no = req.getParameter("b_order_no").trim();
				String room_type_no = req.getParameter("room_type_no").trim();
//				if (b_order_no == null || b_order_no.trim().length() == 0) {
//					errorMsgs.add("訂單編�??: 請勿空白");
//				} else if(room_type_no == null || room_type_no.trim().length() == 0) {
//					errorMsgs.add("?��??�編???: 請勿空白");
//				}
				
				Integer quantity = null;
				try {
					quantity = new Integer(req.getParameter("quantity").trim());
				} catch (NumberFormatException e) {
					quantity=1;
					errorMsgs.add("?��??��?�能空白");
				}
				
				Integer total_add_people = null;
				try {
					total_add_people = new Integer(req.getParameter("total_add_people").trim());
				} catch (NumberFormatException e) {
					total_add_people=0;
					errorMsgs.add("人數不能空白");
				}
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
					
				} catch (NumberFormatException e) {
					price=0;
					errorMsgs.add("?��?��不能空白");
				}
				
				
				
				BookOrderDetailVO bodVo = new BookOrderDetailVO();
				bodVo.setB_order_no(b_order_no);
				bodVo.setRoom_type_no(room_type_no);
				bodVo.setQuantity(quantity);
				bodVo.setTotal_add_people(total_add_people);
				bodVo.setPrice(price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodVo",bodVo); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bod/update_bod_input.jsp");
					failureView.forward(req, res);
					return; //程�?�中?��
				}
				/***************************2.??��?�修?��資�??*****************************************/
				BookOrderDetailService bodSvc =new BookOrderDetailService();
				bodVo = bodSvc.updatabodVo(b_order_no, room_type_no, quantity, total_add_people, price);
				
				/***************************3.修改完�??,準�?��?�交(Send the Success view)*************/
				req.setAttribute("bodVo", bodVo);

				String url = "/back-end/bod/listOneBod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改??��?��??,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("修改資�?�失???:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/bod/update_bod_input.jsp");
				failureView.forward(req, res);
			}
		}

//?��增�?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��??
		
		 if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��??*************************/
					String b_order_no = req.getParameter("b_order_no").trim();
					String room_type_no = req.getParameter("room_type_no").trim();
					if (b_order_no == null || b_order_no.trim().length() == 0) {
						errorMsgs.add("訂單編�??: 請勿空白");
					} else if(room_type_no == null || room_type_no.trim().length() == 0) {
						errorMsgs.add("?��??�編???: 請勿空白");
					}
					
					
					Integer quantity = null;
					try {
						quantity = new Integer(req.getParameter("quantity").trim());
					} catch (NumberFormatException e) {
						quantity=0;
						errorMsgs.add("?��??��?�能空白");
					}
					
					Integer total_add_people = null;
					try {
						total_add_people = new Integer(req.getParameter("total_add_people").trim());
					} catch (NumberFormatException e) {
						total_add_people=0;
						errorMsgs.add("人數不能空白");
					}
					
					Integer price = null;
					try {
						price = new Integer(req.getParameter("price").trim());
					} catch (NumberFormatException e) {
						price=0;
						errorMsgs.add("?��?��不能空白");
					}
					
				
		

					BookOrderDetailVO bodVo = new BookOrderDetailVO();
					bodVo.setB_order_no(b_order_no);
					bodVo.setRoom_type_no(room_type_no);
					bodVo.setQuantity(quantity);
					bodVo.setTotal_add_people(total_add_people);
					bodVo.setPrice(price);

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("bodVo",bodVo); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/bod/addBod.jsp");
						failureView.forward(req, res);
						return; //程�?�中?��
					}
					
					/***************************2.??��?�新增�?��??***************************************/
					BookOrderDetailService bodSvc =new BookOrderDetailService();
					bodVo = bodSvc.addBod(b_order_no, room_type_no, quantity, total_add_people, price);
					
					/***************************3.?��增�?��??,準�?��?�交(Send the Success view)***********/
					String url = "/back-end/bod/listAllBod.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
					
					/***************************?��他可?��??�錯誤�?��??**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bod/addBod.jsp");
					failureView.forward(req, res);
				}
			}
		 
		 
		 if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
			
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.?��?��請�?��?�數***************************************/
					String b_order_no = req.getParameter("b_order_no");
					String room_type_no = req.getParameter("room_type_no");
					
					/***************************2.??��?�刪?��資�??***************************************/
					BookOrderDetailService bodSvc = new BookOrderDetailService();
					bodSvc.deletebodVo(b_order_no,room_type_no);
					
					/***************************3.?��?��完�??,準�?��?�交(Send the Success view)***********/								
					String url = "/back-end/bod/listAllBod.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// ?��?��??��?��??,轉交??��?�出?��?��??��?��?�網???
					successView.forward(req, res);
					
					/***************************?��他可?��??�錯誤�?��??**********************************/
				} catch (Exception e) {
					errorMsgs.add("?��?��資�?�失???:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/bod/listAllBod.jsp");
					failureView.forward(req, res);
				}
			}
		
	}
	
	
}
