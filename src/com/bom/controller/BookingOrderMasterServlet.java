package com.bom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.Request;

import com.bod.model.BookOrderDetailService;
import com.bod.model.BookOrderDetailVO;
import com.bom.model.*;
import com.checkroomremain.CheckRoomNumberDAO;
import com.checkroomremain.RoomTypeRemain;
import com.cus.model.CustomerService;
import com.cus.model.CustomerVO;
import com.resmealod.model.ResMealOrderDetailService;
import com.resmealom.model.ResMealOrderMasterService;
import com.resmealom.model.ResMealOrderMasterVO;
import com.roommealorderdetail.model.RoomMealOrderDetailService;
import com.roommealordermaster.model.RoomMealOrderMasterService;
import com.roommealordermaster.model.RoomMealOrderMasterVO;

/**
 * Servlet implementation class BookingOrderMasterServlet
 */
public class BookingOrderMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getData".equals(action)) {
			String cus_Id = req.getParameter("cus_Id");
			Integer status = Integer.valueOf(req.getParameter("status"));
			String[] statusStr;
			if (status == 1) {
				statusStr = new String[] { "0", "1" };
			} else {
				statusStr = new String[] { status.toString() };
			}
			Map<String, String[]> map = new HashMap<>();
			for (int i = 0; i < statusStr.length; i++) {
				// System.out.println(statusStr[i]);
			}
			map.put("cus_Id", new String[] { cus_Id });
			map.put("status", statusStr);
			List<BookingOrderMasterVO> bomList = new BookingOrderMasterService().getAllBy(map);
			Collections.sort(bomList);
			System.out.println(bomList);
			JSONArray output = new JSONArray(bomList);
			JSONObject obj = null;
			for (int i = 0; i < output.length(); i++) {
				try {
					obj = output.getJSONObject(i);
					if (obj.getString("co_Time") == null)// 不知為何如果co_Time是空值的話JSONArray會吃不到，所以根本get不到"co_Time"，只好在EXCEPTION那邊接
						obj.put("co_Time", "尚未入住");
				} catch (JSONException e) {
					try {
						obj.put("co_Time", "尚未入住");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}

			}
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			// System.out.println("bomList = " + bomList);
			out.write(output.toString());
			out.flush();
			out.close();
		}

		if ("deleteBomByJSON".equals(action)) {
			String b_Order_No = req.getParameter("b_Order_No");
			// System.out.println("BOD DAO room_type_no = " + b_Order_No);
			BookingOrderMasterService bomSvc = new BookingOrderMasterService();
			bomSvc.cancelBookingOrderMaster(b_Order_No);
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

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				HttpSession session = req.getSession();
				String b_Order_No = req.getParameter("b_Order_No");
				if (b_Order_No == null || b_Order_No.trim().length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
					failureView.forward(req, res);
					return;// 等一下試試看這邊去掉會怎樣
				}

				// b_Order_No是字串，所以不用老師驗證empno為數字格式的那段
				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				BookingOrderMasterVO bomVO = bomSvc.getOneBOM(b_Order_No);
				if (bomVO == null) {
					errorMsgs.add("查無資料");
				}
				List<BookingOrderMasterVO> bomList = new ArrayList<>();
				bomList.add(bomVO);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}
				session.setAttribute("searchBomByCus_Id_Num", bomList);
				String url = "/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String b_Order_No = req.getParameter("b_Order_No");
				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				BookingOrderMasterVO bomVO = bomSvc.getOneBOM(b_Order_No);

				req.setAttribute("bomVO", bomVO);
				String url = "/back-end/BookingOrderMaster/update_BookingOrderMaster_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String b_Order_No = req.getParameter("b_Order_No");// 到時候不會有
				// System.out.println(b_Order_No);
				String cus_Id = req.getParameter("cus_Id");
				// System.out.println(cus_Id);
				Integer total_Price = Integer.parseInt(req.getParameter("total_Price"));
				Date start_Date = null;
				try {
					start_Date = java.sql.Date.valueOf(req.getParameter("start_Date"));
				} catch (IllegalArgumentException e) {
					start_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Date end_Date = null;
				try {
					end_Date = java.sql.Date.valueOf(req.getParameter("end_Date"));
				} catch (IllegalArgumentException e) {
					end_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Date co_Time = null;
				try {
					co_Time = java.sql.Date.valueOf(req.getParameter("co_Time"));
				} catch (IllegalArgumentException e) {
					co_Time = null;
				}
				Date order_Date = null;
				Integer status = Integer.parseInt(req.getParameter("status"));

				BookingOrderMasterVO bomVO = new BookingOrderMasterVO();// 目的是出錯時還能保留剛輸入的資料
				bomVO.setB_Order_No(b_Order_No);
				bomVO.setCus_Id(cus_Id);
				bomVO.setTotal_Price(total_Price);
				bomVO.setStart_Date(start_Date);
				bomVO.setEnd_Date(end_Date);
				bomVO.setCo_Time(co_Time);
				bomVO.setStatus(status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bomVO", bomVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/BookingOrderMaster/update_BookingOrderMaster_input.jsp");
					failureView.forward(req, res);
					return;
				}
				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				bomVO = bomSvc.updateBookingOrderMaster(b_Order_No, cus_Id, total_Price, start_Date, end_Date, co_Time,
						status);
				req.setAttribute("bomVO", bomVO);
				String url = "/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/BookingOrderMaster/update_BookingOrderMaster_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String b_Order_No = req.getParameter("b_Order_No");// 到時候不會有
				String cus_Id = req.getParameter("cus_Id");
				Integer total_Price = Integer.parseInt(req.getParameter("total_Price"));
				Date start_Date = null;
				try {
					start_Date = java.sql.Date.valueOf(req.getParameter("start_Date"));
				} catch (IllegalArgumentException e) {
					start_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Date end_Date = null;
				try {
					end_Date = java.sql.Date.valueOf(req.getParameter("end_Date"));
				} catch (IllegalArgumentException e) {
					end_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Integer status = Integer.parseInt(req.getParameter("status"));

				BookingOrderMasterVO bomVO = new BookingOrderMasterVO();// 目的是出錯時還能保留剛輸入的資料
				bomVO.setB_Order_No(b_Order_No);
				bomVO.setCus_Id(cus_Id);
				bomVO.setTotal_Price(total_Price);
				bomVO.setStart_Date(start_Date);
				bomVO.setEnd_Date(end_Date);
				bomVO.setStatus(status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bomVO", bomVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/BookingOrderMaster/addBookingOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				bomVO = bomSvc.addBookingOrderMaster(b_Order_No, cus_Id, total_Price, start_Date, end_Date, status);

				String url = "/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/BookingOrderMaster/addBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}

		}
//
//allJSON = {"BOM":{"end_Date":"2020-04-16","start_Date":"2020-04-15","status":0},

//"BOD":[{"roomselected":3,"roomTypePrice":2200,"addPeopleSelected":0,"addPeopleMax":0,"roomMax":20,"name":"漁火單人房","room_Type_No":"RT007"},
//{"roomselected":2,"roomTypePrice":6600,"addPeopleSelected":0,"addPeopleMax":0,"roomMax":20,"name":"白帆單人房","room_Type_No":"RT008"}]}
		if ("bookByJSON".equals(action)) {
			try {
				JSONObject allJSON = new JSONObject(req.getParameter("JSONArray"));// 取得JSON物件
				// System.out.println("allJSON = " + allJSON);
				JSONObject bomJSON = allJSON.getJSONObject("BOM");
				JSONArray bodJSON = allJSON.getJSONArray("BOD");
				System.out.println("bodJSON = " + bodJSON);
				BookingOrderMasterVO bomVO = new BookingOrderMasterVO();
				bomVO.setCus_Id((String) bomJSON.get("cus_Id"));
				bomVO.setStart_Date(java.sql.Date.valueOf((String) bomJSON.get("start_Date")));
				bomVO.setEnd_Date(java.sql.Date.valueOf((String) bomJSON.get("end_Date")));
				bomVO.setStatus(0);
				bomVO.setTotal_Price(0);// 設trigger

				List<BookOrderDetailVO> list = new ArrayList<BookOrderDetailVO>();
				BookOrderDetailVO vo = new BookOrderDetailVO();
				// System.out.println(" bomVO= " + bomVO);

				JSONObject obj;
				for (int i = 0; i < bodJSON.length(); i++) {
					obj = bodJSON.getJSONObject(i);
					vo = new BookOrderDetailVO();
					int quantity = obj.getInt("roomselected");
					vo.setRoom_type_no(obj.getString("room_Type_No"));	
					vo.setQuantity(quantity);
					int addPeopleSelected = obj.getInt("addPeopleSelected");
					vo.setTotal_add_people(addPeopleSelected);
					vo.setPrice(obj.getInt("roomTypePrice") * quantity + addPeopleSelected * 500);// 總價
					list.add(vo);
				}

				// 要把目前的剩餘數量再重新撈出來一次進行比對
				boolean bookingError = false;
				List<RoomTypeRemain> rtrList = new CheckRoomNumberDAO().getRemainNo((String) bomJSON.get("start_Date"),
						(String) bomJSON.get("end_Date"));
				List<String> roomTypeNoList = new ArrayList<String>();
				for(RoomTypeRemain rtr: rtrList) {//把所剩房型的房型編號弄成一個list，供下面比對
					roomTypeNoList.add(rtr.getRoom_Type_No());
				}
				String bookingLocation = bomJSON.getString("bookingLocation");
				for (RoomTypeRemain rtrVo : rtrList) {
					for (BookOrderDetailVO bodVo : list) {
						if( ! roomTypeNoList.contains(bodVo.getRoom_type_no())) {
							req.getSession().setAttribute("bookingErrorMsgs", "部分房型剛被訂完囉~請重新選擇");// 下面得用sendRedirect，這裡只好存session
							bookingError = true;
							System.out.println("全部訂完");
							res.sendRedirect(bookingLocation);
							break;
						}
						if (bodVo.getRoom_type_no().equals(rtrVo.getRoom_Type_No())) {
							if (bodVo.getQuantity() > rtrVo.getRemain()) {
								req.getSession().setAttribute("bookingErrorMsgs", "部分房型剛被訂完囉~請重新選擇");// 下面得用sendRedirect，這裡只好存session
								bookingError = true;
								System.out.println("剩餘數量不足");
								res.sendRedirect(bookingLocation);
								break;
							}
						}
					}
					if (bookingError)break;
				}
				if (bookingError)return;

//				System.out.println("BOM list= " + list);
				String newBomNo = new BookingOrderMasterService().addAllBookingData(bomVO, list);
				req.setAttribute("newBomNo", newBomNo);
				req.setAttribute("successfullyBooked", "Y");
				String params = "?newBomNo=" + newBomNo + "&successfullyBooked=Y";
				String url = req.getContextPath() + "/front-end/bookingordermaster/listBookingOrder.jsp" + params;
//				String url = "/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp?whichPage=90";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				res.sendRedirect(url);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String b_Order_No = req.getParameter("b_Order_No");

				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				bomSvc.cancelBookingOrderMaster(b_Order_No);
				String url = "/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		if ("searchBomByCus_Id_Num".equals(action) || "searchBomByCus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			session.removeAttribute("searchBomByCus_Id_Num");
			try {
				String requestURL =req.getParameter("requestURL");
				String id_Num = req.getParameter("id_Num");
				CustomerService CusSvc = new CustomerService();
				CustomerVO cusVo = CusSvc.getOneCusById(id_Num);
				if(cusVo == null) {
					errorMsgs.add("尚未有會員或身分證輸入錯誤");
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				System.out.println(cusVo.getCus_Id());
				//取得現在日期並轉成時間
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String checkInDate = sdf.format(date);

				BookingOrderMasterService bomSvc = new BookingOrderMasterService();
				List<BookingOrderMasterVO> bomList = bomSvc.getAllByCus(cusVo.getCus_Id(), 0);
				
				if("searchBomByCus_Id_Num".equals(action)) {
				//比對日期 符合的才可放入集合
				@SuppressWarnings("unlikely-arg-type")
				List<BookingOrderMasterVO> cusBomList= bomList.stream().filter(a->(a.getStart_Date().toString().equals(checkInDate))).collect(Collectors.toList());
				if(cusBomList.size()==0) {
					errorMsgs.add("今日尚未有此會員的訂房訂單");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
					failureView.forward(req, res);
					return;
				}
				session.setAttribute("searchBomByCus_Id_Num", cusBomList);
				String url = "/back-end/room/check_in.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				}else{
					Integer status = new Integer(req.getParameter("status"));
					
					bomList = bomSvc.getAllByCus(cusVo.getCus_Id(), status);
					if(bomList.size()==0) {
						errorMsgs.add("查無訂單");
						RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
						return;
					}
					session.setAttribute("searchBomByCus_Id_Num", bomList);
					String url = "/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					session.removeAttribute("searchBomByCus_Id_Num");
				}
				
			} catch (Exception e) {
				session.removeAttribute("searchBomByCus_Id_Num");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOrderMealDetailDisplay".equals(action)) {
			// 取得客房點餐明細 目前只有取得主檔
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			String b_order_no = req.getParameter("b_order_no");
			String requestURL = req.getParameter("requestURL");

			RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
			RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();

			List<RoomMealOrderMasterVO> getMealOrderMaster = roomMealOrderMasterSvc.getAll().stream()
					.filter(mealMaster -> mealMaster.getB_order_no().equals(b_order_no)).collect(Collectors.toList());

//				List<RoomMealOrderDetailVO> getMealOrderDetail =null;
//				for(RoomMealOrderMasterVO rmom :getMealOrderMaster) {
//					String MealOrderMaster_no = rmom.getRoom_meal_order_no();
//					getMealOrderDetail = roomMealOrderDetailSvc.getRoomMealOrderDetailByOrderNo(MealOrderMaster_no);
//				}

			req.setAttribute("getOrderMealDetailDisplay", getMealOrderMaster);

			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("查不到訂單");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/BookingOrderMaster/listAllBookingOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOrderResDetailDisplay".equals(action)) {
			// 取得餐廳點餐明細 目前只有取得主檔
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();

//			try {
			String b_order_no = req.getParameter("b_order_no");
			String requestURL = req.getParameter("requestURL");

			ResMealOrderMasterService resMealOrderMasterSvc = new ResMealOrderMasterService();
			ResMealOrderDetailService resMealOrderDetailSvc = new ResMealOrderDetailService();

			List<ResMealOrderMasterVO> getresMealOrderMasterSvc = resMealOrderMasterSvc.getAll().stream()
					.filter(resMaster -> resMaster.getbOrderNo().equals(b_order_no)).collect(Collectors.toList());

//				List<ResMealOrderDetailVO> getResMealOrderDetail =null;
//				for(ResMealOrderMasterVO resm :getresMealOrderMasterSvc) {
//					String resmealOrderMaster_no = resm.getResMealOrderNo();
//					getResMealOrderDetail.stream().filter(resNo -> resNo.getResMealOrderNo().equals(resmealOrderMaster_no)).collect(Collectors.toList());
//				}

			req.setAttribute("getOrderResDetailDisplay", getresMealOrderMasterSvc);
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("查不到訂單");
//				session.removeAttribute("searchBomByCus_Id_Num");
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/check_in.jsp");
//				failureView.forward(req, res);
//			}
		}

	}

	private Integer Integer(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
