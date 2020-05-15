package com.room.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import com.bod.model.BookOrderDetailService;
import com.bod.model.BookOrderDetailVO;
import com.bom.model.BookingOrderMasterService;
import com.bom.model.BookingOrderMasterVO;
import com.cus.model.CustomerService;
import com.cus.model.CustomerVO;
import com.resmealom.model.ResMealOrderMasterService;
import com.resmealom.model.ResMealOrderMasterVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roommealordermaster.model.RoomMealOrderMasterService;
import com.roommealordermaster.model.RoomMealOrderMasterVO;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class RoomServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String requestURL = req.getParameter("requestURL");
				String str = req.getParameter("room_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房間編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/back-end/room/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String room_id = null;
				try {
					room_id = str;
					System.out.println(room_id);
				} catch (Exception e) {
					errorMsgs.add("編號格式錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�d�߸��
				 *****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.getOneRoom(room_id);
				if (roomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3. *************/
				req.setAttribute("roomVO", roomVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************
				 * ��L�i�઺���~�B�z
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
//			System.out.println(requestURL);
			try {
				/*************************** 1. ****************************************/
				String room_id = req.getParameter("room_id");

				RoomService roomSvc = new RoomService();
				RoomVO roomVo = roomSvc.getOneRoom(room_id);

				req.setAttribute("roomVo", roomVo);
				String url = "/back-end/room/update_Room_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("getClean_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1. ****************************************/
				String room_id = req.getParameter("room_id");

				/***************************
				 * 2.�}�l�d�߸��
				 ****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVo = roomSvc.getOneRoom(room_id);

				
				req.setAttribute("roomVo", roomVo);
				String url = "/back-end/room/update_Clean_status.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("getCus_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1. ****************************************/
				String room_id = req.getParameter("room_id");

				/***************************
				 * 2.�}�l�d�߸��
				 ****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVo = roomSvc.getOneRoom(room_id);

				req.setAttribute("roomVo", roomVo);
				String url = "/back-end/room/update_Cus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("getRoomStatus_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1. ****************************************/
				String room_id = req.getParameter("room_id");

				RoomService roomSvc = new RoomService();
				RoomVO roomVo = roomSvc.getOneRoom(room_id);

//				req.setAttribute("roomVo", roomVo);
//				String url = "/back-end/room/update_Room_status.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				JSONObject obj = new JSONObject();
				obj.put("room_id", room_id);
				obj.put("room_status", roomVo.getRoom_status());
				obj.put("room_no",roomVo.getRoom_no());
				System.out.println(obj);
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

//update############################################################################		
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {

				String room_id = req.getParameter("room_id");
				System.out.println(room_id);

				String room_type_no = req.getParameter("room_type_no");

				String room_no = req.getParameter("room_no").trim();
				if (room_no == null || room_no.trim().length() == 0) {
					errorMsgs.add("房號請勿空白");
				}
				RoomService roomSvc = new RoomService();
			
				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_id(room_id);
				roomVO.setRoom_type_no(room_type_no);
				roomVO.setRoom_no(room_no);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_Room_input.jsp");
					failureView.forward(req, res);
					return;
				}

				RoomService RoomSvc = new RoomService();
				roomVO = RoomSvc.updateRoomData(room_id, room_type_no, room_no);
				roomVO =roomSvc.getOneRoom(room_id);
				if (requestURL.equals("/back-end/room/listRoom_ByCompositeQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<RoomVO> list = RoomSvc.getSearch(map);
					req.setAttribute("listRoom_ByCompositeQuery", list); // 複合查詢, 資料庫取出的list物件,存入request
				}

				req.setAttribute("roomVO", roomVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_Room_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateClean".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String requestURL = req.getParameter("requestURL");

				String room_no = req.getParameter("room_no").trim();
				if (room_no == null || room_no.trim().length() == 0) {
					errorMsgs.add("房號請勿空白");
				}

				Integer clean_status = new Integer(req.getParameter("clean_status").trim());

				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_no(room_no);
				roomVO.setClean_status(clean_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_Clean_status.jsp");
					failureView.forward(req, res);
					return;
				}

				RoomService RoomSvc = new RoomService();
				roomVO = RoomSvc.updateRoomCleanStatus(room_no, clean_status);

				if (requestURL.equals("/back-end/room/listRoom_ByCompositeQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<RoomVO> list = RoomSvc.getSearch(map);
					req.setAttribute("listRoom_ByCompositeQuery", list); // 複合查詢, 資料庫取出的list物件,存入request
				}

				req.setAttribute("roomVO", roomVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_Clean_status.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateCus".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				System.out.println("修改");
				String requestURL = req.getParameter("requestURL");
//		System.out.println(requestURL);
				String room_no = req.getParameter("room_no").trim();

				String cus_id = req.getParameter("cus_id");

				RoomService roomSvc = new RoomService();

				String tenant_name = null;
				tenant_name = req.getParameter("cus_Name");

				String tenant_phone = req.getParameter("cus_Cel");

				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_no(room_no);
				roomVO.setCus_id(cus_id);
				roomVO.setTenant_name(tenant_name);
				roomVO.setTenant_phone(tenant_phone);

				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("roomVO", roomVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/roomCusBySearch.jsp");
					failureView.forward(req, res);
					return;
				}
				String b_order_no=null;
				roomVO = roomSvc.updateRoomCus(room_no, cus_id, tenant_name, tenant_phone,b_order_no);
				roomVO = roomSvc.updateRoomStatus(room_no,1);
				roomVO = roomSvc.updateRoomCleanStatus(room_no, 1);
//				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
//				BookingOrderMasterVO bomVo = new BookingOrderMasterVO();
//				bomVo = bomSvc.get
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				List<RoomVO> list = roomSvc.getSearch(map);
				req.setAttribute("listRoom_ByCompositeQuery", list); // 複合查詢, 資料庫取出的list物件,存入request

				req.setAttribute("roomVO", roomVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/roomCusBySearch.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateRoomStatus".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				String requestURL = req.getParameter("requestURL");
				String room_no = req.getParameter("room_no").trim();
				if (room_no == null || room_no.trim().length() == 0) {
					errorMsgs.add("房號請勿空白");
				}
				Integer room_status = new Integer(req.getParameter("room_status").trim());
				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_no(room_no);
				roomVO.setRoom_status(room_status);

				RoomService RoomSvc = new RoomService();
				roomVO = RoomSvc.updateRoomStatus(room_no, room_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_Room_status.jsp");
					failureView.forward(req, res);
					return;
				}

//		if(requestURL.equals("/back-end/room/listRoom_ByCompositeQuery.jsp")){
//				HttpSession session = req.getSession();
//				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
//				List<RoomVO> list = RoomSvc.getSearch(map);
//				req.setAttribute("listRoom_ByCompositeQuery", list); // 複合查詢, 資料庫取出的list物件,存入request
//		}

				req.setAttribute("roomVo", roomVO);
//				req.setAttribute("requestURL", requestURL);
//				String url =requestURL;
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
				successView.forward(req, res);

			} catch (Exception e) {	
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/update_Room_status.jsp");
				failureView.forward(req, res);
			}
		}

		// insert####################################################################
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
					
//				String room_id = req.getParameter("room_id").trim();

				RoomService roomSvc = new RoomService();
				List<RoomVO> roomall = roomSvc.getAll();
				String room_type_no = req.getParameter("room_type_no");
				if(room_type_no ==null||room_type_no.trim().length()==0) {
					errorMsgs.add("請選擇房型");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
					failureView.forward(req, res);
					return;
				}
//				String room_no = req.getParameter("room_no").trim();
				String[] room_nos = req.getParameterValues("room_no");
				//移除陣列相同的值
				Set<String> StringSet = new HashSet<String>();
				 for (String element : room_nos) {
					 StringSet.add(element);
				}
				 String nonDuplicateArray[] = new String[StringSet.size()];
				 Object[] tempArray = StringSet.toArray();
				       for (int i = 0; i < tempArray.length; i++) {
				           nonDuplicateArray[i] = (String) tempArray[i];
				        }
				
				
				String room_no=null;
				for(int i=0;i<nonDuplicateArray.length;i++) {
					 room_no = nonDuplicateArray[i];
					
					if (room_no == null || room_no.trim().length() == 0) {
						errorMsgs.add("房號請勿空白");
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
						failureView.forward(req, res);
						return;
					}
						
					for (RoomVO room : roomall) {
						if (room_no.equals(room.getRoom_no())) {
							errorMsgs.add("已有此房號，不得新增");
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
							failureView.forward(req, res);
							return;
						}
					}
					RoomVO roomVO = new RoomVO();
//					roomVO.setRoom_id(room_id);
					roomVO.setRoom_type_no(room_type_no);
					roomVO.setRoom_no(room_no);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("roomVO", roomVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
						failureView.forward(req, res);
						return;
					}

					/***************************
					 * 2.�}�l�s�W���
					 ***************************************/
//					RoomService roomSvc = new RoomService();
					roomVO = roomSvc.addRoom(room_type_no, room_no);
					
					
				}
				

				String url = "/back-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listRoom_ByCompositeQuery".equals(action) ||"checkoutRoomSearch".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}

				RoomService roomSvc = new RoomService();
				List<RoomVO> list = roomSvc.getSearch(map);
				
				
				if("checkoutRoomSearch".equals(action)) {
					if(list.size()==0) {
						errorMsgs.add("查無此房客，請確認房客資訊");
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
						failureView.forward(req, res);
					}else {
						req.setAttribute("listRoom_ByCompositeQuery", list);
						session.removeAttribute("searchBomByCus_Id_Num");
						RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
						successView.forward(req, res);	
					}
				
				}else {
					req.setAttribute("listRoom_ByCompositeQuery", list);
					req.setAttribute("oneRoom",true);
					session.removeAttribute("searchBomByCus_Id_Num");
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
					successView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getCusFromTable".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// Retrieve form parameters.
				String room_id = req.getParameter("room_id");
				String requestURL = req.getParameter("requestURL");
				System.out.println(room_id);
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.getOneRoom(room_id);
				if (roomVO.getCus_id() == null || roomVO.getCus_id().trim().length() == 0) {
					errorMsgs.add("尚未有房客");
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("getCusFromTable", roomVO);

					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<RoomVO> list = roomSvc.getSearch(map);
					req.setAttribute("listRoom_ByCompositeQuery", list);

					boolean openModal = true;
					req.setAttribute("openModal", openModal);
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("getCusFrommTable", roomVO); // 資料庫取出的empVO物件,存入req
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				List<RoomVO> list = roomSvc.getSearch(map);
				req.setAttribute("listRoom_ByCompositeQuery", list);

				boolean openModal2 = true;
				req.setAttribute("openModal2", openModal2);

				System.out.println(requestURL);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		if ("search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			RoomService roomSvc = new RoomService();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
			List<RoomVO> list = roomSvc.getSearch(map);
			req.setAttribute("listRoom_ByCompositeQuery", list);
			try {
				String room_id = req.getParameter("room_id");
				
				RoomVO roomVO = roomSvc.getOneRoom(room_id);
				String id_Num = req.getParameter("id_Num");
				
				if (id_Num == null || id_Num.trim().length() == 0) {
					errorMsgs.add("請輸入會員身分證");
				}

				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("getCusFromTable", roomVO);
					boolean openModal = true;
					req.setAttribute("openModal", openModal);
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				CustomerService CusSvc = new CustomerService();
				CustomerVO cusVo = null;
				try {
					cusVo = CusSvc.getOneCusById(id_Num);
				} catch (Exception e) {
					errorMsgs.add("尚未有會員或身分證輸入錯誤");
					req.setAttribute("getCusFromTable", roomVO);
					boolean openModal = true;
					req.setAttribute("openModal", openModal);
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				req.setAttribute("getCusFromTable", roomVO);
				req.setAttribute("search", cusVo);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/roomCusBySearch.jsp");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無此會員");
//				errorMsgs.add(e.getMessage());
				boolean openModal = true;
				req.setAttribute("openModal", openModal);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}
		
		
		if("getBookRoomDisplay".equals(action)) { 
			//來自select_page.jsp??��?��??
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				
	//1.輸入?��誤格式�?��?��??**************************************************************
				String str = req.getParameter("b_order_no");
				
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
					failureView.forward(req,res);
				return;
				}
				String b_order_no = null;
				
				try {
					 b_order_no = new String(str);

				}catch(Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	//2.?��詢�?��??********************************************************************************
				
				BookOrderDetailService bodSvc = new BookOrderDetailService();
				List<BookOrderDetailVO> list = bodSvc.getBookRoom(b_order_no);
//				List<RoomVO> roomlist = new ArrayList<>();
				
				RoomService roomSvc = new RoomService();
//				RoomVO roomVo = new RoomVO();
				
//				
				List<ArrayList> roomlist2 = new ArrayList<>();
				
				
					for(int i =0;i<list.size();i++) {
						List<RoomVO> roomlist = new ArrayList<>();
					
						String room_type_no= list.get(i).getRoom_type_no();
						roomlist = roomSvc.getRoomByCount(room_type_no, b_order_no);
						roomlist2.add((ArrayList) roomlist);
					}
					
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page_jsp");
						failureView.forward(req,res);
						return;
					}
				
		//3.?��詢�?��?��?��?��?��?�交
					req.setAttribute("bodlist",list);
					req.setAttribute("roomlist2",roomlist2);
//					req.setAttribute("list2",list);
					String url = "/back-end/room/check_in.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);

				}
				
		
			
	//4.?��他可?��??�錯誤�?��??
			catch(Exception e)
			{
				errorMsgs.add("查無此身分證:"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/bod/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("checkin".equals(action)) {
			String str = req.getParameter("b_order_no");
			System.out.println("b_order_no = " + str );
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				System.out.println("checkin");
				String b_order_no = req.getParameter("b_order_no");
				HttpSession session = req.getSession();
//				String requestURL = req.getParameter("requestURL");
				Enumeration room_list = session.getAttributeNames();
				
				
				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				CustomerService cusSvc = new CustomerService();
				BookingOrderMasterVO bom = bomSvc.getOneBOM(b_order_no);
	
				CustomerVO cusVo = cusSvc.getOneCus(bom.getCus_Id());
				
				RoomService roomSvc = new RoomService();
//				RoomVO roomVo = new RoomVO();
				
				String[] room_ids = req.getParameterValues("room_ids");
				for(int i =0;i<room_ids.length; i++) {
					RoomVO roomVo  = roomSvc.getOneRoom(room_ids[i].trim());
				roomSvc.updateRoomCus(roomVo.getRoom_no(), cusVo.getCus_Id(), cusVo.getCus_Name(), cusVo.getCus_Cel(),b_order_no);
				roomSvc.updateRoomStatus(roomVo.getRoom_no(),1);
				roomSvc.updateRoomCleanStatus(roomVo.getRoom_no(), 1);
				}
				
	
				bomSvc.updateBookingOrderMaster(b_order_no,cusVo.getCus_Id() , bom.getTotal_Price(), bom.getStart_Date(), bom.getEnd_Date(),bom.getCo_Time(),1);
				List<BookingOrderMasterVO> cusBomList = bomSvc.getAllByCus(cusVo.getCus_Id(),0);
				boolean sweetalert = true;
				req.setAttribute("sweetalert", sweetalert);
//				session.setAttribute();
				session.removeAttribute("searchBomByCus_Id_Num");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/room.do?action=allCheckinBomDisplay");
				successView.forward(req, res);
				
			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
				failureView.forward(req, res);
			}
		
		}
if("checkout".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				System.out.println("checkout");
			
				HttpSession session = req.getSession();
//				String requestURL = req.getParameter("requestURL");
				RoomService roomSvc = new RoomService();
				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
				ResMealOrderMasterVO resMealOrderMasterVO = new ResMealOrderMasterVO();
				RoomMealOrderMasterVO RoomMealOrderMasterVO = new RoomMealOrderMasterVO();
				ResMealOrderMasterService  resMealOrderMasterSvc =new ResMealOrderMasterService();
//				RoomVO roomVo=null;
//				String[] room_ids = req.getParameterValues("room_ids");
				String b_order_no=req.getParameter("b_order_no");
				 Date date = new Date();
				 java.sql.Date co_time =new java.sql.Date(date.getTime());
				
					Map<String, String[]> map = new TreeMap<String, String[]>();
					
					
						//客房點餐價格加總
						List<RoomMealOrderMasterVO> roomMealOrderList =roomMealOrderMasterSvc.getAll();
						int roomMealOrderPrice=roomMealOrderList.stream()
						.filter(roomMealOrder -> roomMealOrder.getB_order_no().equals(b_order_no) && roomMealOrder.getRo_order_status()==3)
						.mapToInt( p-> p.getTotal_price())
						.sum();
						//餐廳點餐價格加總
						List<ResMealOrderMasterVO> resMealOrderMasterList =resMealOrderMasterSvc.getAll();
						int resMealOrderMasterPrice=resMealOrderMasterList.stream()
						.filter(resMealOrder -> resMealOrder.getbOrderNo().equals(b_order_no) && resMealOrder.getOrderStatus()==1)
						.mapToInt( p-> p.getTotalPrice())
						.sum();
						//訂單總價格
						int checkOutPrice = resMealOrderMasterPrice+roomMealOrderPrice;
						System.out.println(checkOutPrice);
						req.setAttribute("checkOutPrice", checkOutPrice);
						boolean checkOutalert = true;
						req.setAttribute("checkOutalert", checkOutalert);
						req.setAttribute("b_order_no", b_order_no);
						RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
						successView.forward(req, res);
						
				
					
//					
//					//更改點餐訂單狀態
////					List<RoomMealOrderMasterVO> updateroomMealOrderList =roomMealOrderMasterSvc.getAll();
////					updateroomMealOrderList.stream()
////					.filter(roomMealOrder -> roomMealOrder.getB_order_no().equals(b_order_no) && roomMealOrder.getRo_order_status()==3)
////					.collect(Collectors.toList()).stream()
////					.forEach(roomMealOrder ->roomMealOrderMasterSvc.updateRoomMealOrderMaster(roomMealOrder.getB_order_no(), b_order_no, roomMealOrder.getRoom_no(),roomMealOrder.getEmp_id(), roomMealOrder.getTotal_price(),3,roomMealOrder.getSpecial_requirement()));
					//更改餐廳訂單狀態
			
				
			} catch (Exception e) {

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
				failureView.forward(req, res);
			}
		
		}
		
if("checkoutpay".equals(action)) {

	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
	try {
//		System.out.println("checkout");
	
		HttpSession session = req.getSession();
//		String requestURL = req.getParameter("requestURL");
		RoomService roomSvc = new RoomService();
		BookingOrderMasterService bomSvc = new BookingOrderMasterService();
		RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();

		ResMealOrderMasterService  resMealOrderMasterSvc =new ResMealOrderMasterService();
		System.out.println("1==="+new Date());
		String b_order_no=req.getParameter("b_order_no");
		 Date date = new Date();
		 java.sql.Date co_time =new java.sql.Date(date.getTime());
		 BookingOrderMasterVO bomVo=bomSvc.getOneBOM(b_order_no);
			bomSvc.updateBookingOrderMaster(b_order_no,bomVo.getCus_Id(),bomVo.getTotal_Price(),bomVo.getStart_Date(),bomVo.getEnd_Date(), co_time, 2);
			Map<String, String[]> map = new TreeMap<String, String[]>();
		System.out.println("2==="+new Date());
		
	
		List<RoomVO> roomList = roomSvc.getRoomByBookOrderNo(b_order_no);
//		System.out.println(roomList);
//		roomList.stream().forEach(roomVO ->roomVO.getRoom_no()).;	
//		List<String> roomNoList = new ArrayList<>();
//		for(RoomVO room :roomList) {
//			roomNoList.add(room.getRoom_no());
//		}
//		roomSvc.checkOut(roomNoList);
		System.out.println("3==="+new Date());
		for(RoomVO room :roomList) {
			roomSvc.updateRoomCleanStatus(room.getRoom_no(), 2);
			roomSvc.updateRoomStatus(room.getRoom_no(), 0);
			roomSvc.updateRoomCus(room.getRoom_no(), "", "", "", "");
		}
		System.out.println("4==="+new Date());
		
		//更改點餐訂單狀態
//		List<RoomMealOrderMasterVO> updateroomMealOrderList =roomMealOrderMasterSvc.getAll();
//		updateroomMealOrderList.stream()
//		.filter(roomMealOrder -> roomMealOrder.getB_order_no().equals(b_order_no) && roomMealOrder.getRo_order_status()==2)
//		.collect(Collectors.toList()).stream()
//		.forEach(roomMealOrder ->roomMealOrderMasterSvc.updateRoomMealOrderMaster(roomMealOrder.getB_order_no(), b_order_no, roomMealOrder.getRoom_no(),roomMealOrder.getEmp_id(), roomMealOrder.getTotal_price(),3,roomMealOrder.getSpecial_requirement()));
		//更改餐廳訂單狀態
		List<ResMealOrderMasterVO> updateresMealOrderMasterList =resMealOrderMasterSvc.getAll();
		updateresMealOrderMasterList.stream()
		.filter(resMealOrder -> resMealOrder.getbOrderNo().equals(b_order_no) && resMealOrder.getOrderStatus()==1)
		.collect(Collectors.toList()).stream()
		.forEach(resMealOrder ->resMealOrderMasterSvc.updateResMealOrderMaster(resMealOrder.getResMealOrderNo(), resMealOrder.getbOrderNo(),resMealOrder.getTableNo(), resMealOrder.getTotalPrice(),resMealOrder.getOrderDate(), 2, resMealOrder.getSpecialRequirement()));
		boolean checkout = true;
	
		req.setAttribute("checkout",checkout);
//		session.removeAttribute("bomCheckoutList");
		RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/room.do?action=allCheckoutBomDisplay");
		successView.forward(req, res);		
		
	
	}catch (Exception e) {

		RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
		failureView.forward(req, res);
	}
		

	}

	if("allCheckinBomDisplay".equals(action) || "allCheckoutBomDisplay".equals(action) ) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		session.removeAttribute("bomCheckoutList");
		session.removeAttribute("searchBomByCus_Id_Num");
		try {
			
			java.util.Date date = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String checkInDate = sdf.format(date);
			BookingOrderMasterService bomSvc = new BookingOrderMasterService();
			
			if("allCheckinBomDisplay".equals(action)) {
			List<BookingOrderMasterVO> bomCheckinList = bomSvc.getAll().stream().filter(bomVo ->bomVo.getStatus()==0 && bomVo.getStart_Date().toString().equals(checkInDate)).collect(Collectors.toList());
			if(bomCheckinList.size()==0) {
				errorMsgs.add("今日無入住訂單");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
				failureView.forward(req, res);
				return;
			}
			
			session.setAttribute("searchBomByCus_Id_Num", bomCheckinList);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
			successView.forward(req, res);
		
			}else {
				RoomService roomSvc = new RoomService();
				
				List<BookingOrderMasterVO> bomCheckoutList = bomSvc.getAll().stream().filter(bomVo ->bomVo.getStatus()==1 && bomVo.getEnd_Date().toString().equals(checkInDate)).collect(Collectors.toList());
				if(bomCheckoutList.size()==0) {
					errorMsgs.add("今日無退房訂單");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
					failureView.forward(req, res);
					return;
				}
				session.setAttribute("bomCheckoutList", bomCheckoutList);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
				successView.forward(req, res);
				return;
				
				
				
			}
		}catch(Exception e) {
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
			failureView.forward(req, res);
		}

	}
	

		
		if("getCheckInRoomDisplay".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
			String b_order_no = req.getParameter("b_order_no");
			RoomService roomSvc = new RoomService();
					
					List<RoomVO> roomCheckoutList = roomSvc.getRoomByBookOrderNo(b_order_no);
					
					req.setAttribute("listRoom_ByCompositeQuery", roomCheckoutList);
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
					successView.forward(req, res);
					return;
					
					
					
			
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_out.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
