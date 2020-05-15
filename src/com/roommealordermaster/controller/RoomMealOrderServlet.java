package com.roommealordermaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.roommealorderdetail.model.RoomMealOrderDetailService;
import com.roommealorderdetail.model.RoomMealOrderDetailVO;
import com.roommealordermaster.model.RoomMealOrderMasterService;
import com.roommealordermaster.model.RoomMealOrderMasterVO;


/**
 * Servlet implementation class RoomMealOrderServlet
 */
@WebServlet("/order/order.do")
public class RoomMealOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action);
		
		// 第一次進去用預設的數量, 購物車內
		if ("viewCart".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

				if (cart == null) {
					errorMsgs.add("購物車為 null ");
				}
				
				Integer total = 0;
				if (cart != null) {
					total = cart.stream()
								.mapToInt(item -> (int)item.get("price") * (int)item.get("quantity"))
								.sum();
//					for (Map<String, Object> item : cart) {
////					String meal_no = (String) item.get("meal_no");
////					String meal_name = (String) item.get("meal_name");
//					Integer price = (Integer) item.get("price");
//					Integer quantity = (Integer) item.get("quantity");
//					
//					Integer subtotal = price * quantity;
//					total += subtotal;
//					}
					
					
					
				}
				
				if (!errorMsgs.isEmpty()) {
					String url = "/front-end/meal/listAllMeal.jsp";
					RequestDispatcher failureView = request.getRequestDispatcher(url);
					failureView.forward(request, response);
					return;
				}
				
				request.setAttribute("total", total);
				String url = "/front-end/meal/listCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				String url = "/front-end/meal/listAllMeal.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
			}
		}
		
		// 使用者修改數量, 改變總金額(給 onChange 用), 沒修改時取用 session cart 中的資料
		if ("addOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
				Integer total = 0;
				// 後面要改成選正在進行(checkin)的訂單
				String b_order_no = (String) request.getParameter("b_order_no");
				
				// 後面要改成透過顧客ID找是哪個房間(下拉式選單)
				String room_no = (String) request.getParameter("room_no");
				
				String special_requirement = request.getParameter("special_requirement");
				
				
				if (cart == null) {
					errorMsgs.add("購物車為空 ");
				} else {
					for (Map<String, Object> item : cart) {
						String meal_no = (String) item.get("meal_no");
						Integer price = (Integer) item.get("price");
						// 用 session 跟 page 的數量差異做判斷? 相等時沿用 list 的資料, 不相等時以 page 為優先去修改 session cart 的資料
						Integer quantity = (Integer) item.get("quantity");
						Integer quantityInput = null;
						try {
							quantityInput = Integer.valueOf(request.getParameter(meal_no + "_quantity"));
							System.out.println(quantityInput);
							if (quantityInput < 0) {
								throw new NumberFormatException();
							}
						} catch (NumberFormatException ne) {
							quantityInput = 0;
							ne.printStackTrace();
							errorMsgs.add("請輸入正整數" + ne.getMessage());
						}
						
						// 判斷小計的數量要取誰的
						Integer subtotal = 0;
						if (quantityInput != null && quantity != quantityInput) {
							// 更新後的數量放回去
							item.put("quantity", quantityInput);
							subtotal = price * quantityInput;
						} else {
							subtotal = price * quantity;
						}
						total += subtotal;
					}
				}
			
			request.setAttribute("total", total);
			
			
			// 服務層
			RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
			roomMealOrderMasterSvc.addRoomMealOrderMasterWithOrderDetail(b_order_no, room_no, special_requirement, cart);

			if (!errorMsgs.isEmpty()) {
				String url = "/front-end/meal/listCart.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
				return;
			}
			
			String url = "/front-end/order/orderFinished.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			request.getSession().removeAttribute("cart");
			request.getSession().removeAttribute("itemNumberInCart");
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改失敗" + e.getMessage());
				String url = "/front-end/meal/listCart.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
			}
			
		}
		
		if ("getOrderDetailFromOrderMaster".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			System.out.println("requestURL="+requestURL);
			
			try {
				// 確認資料
				String room_meal_order_no = request.getParameter("room_meal_order_no");
				System.out.println("room_meal_order_no="+room_meal_order_no);
				
				// 轉交服務層
				RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();
				List<RoomMealOrderDetailVO> roomMealOrderDetails = roomMealOrderDetailSvc.
						getRoomMealOrderDetailByOrderNo(room_meal_order_no);
				
				request.setAttribute("roomMealOrderDetails", roomMealOrderDetails);
				
				// 轉交服務層 orderMaster
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
				List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll(map);
				
				request.setAttribute("listOrderMasters_ByCompositeQuery", list);				
		
				
				// Bootstrap_modal
				boolean openModal=true;
				request.setAttribute("openModal",openModal);
				
				// 轉交
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
		
		if ("deleteOneOrderDetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try {
				// 接收資料
				String room_meal_order_no = request.getParameter("room_meal_order_no");
				String meal_no = request.getParameter("meal_no");
				
				// 轉交服務層
				RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();
				roomMealOrderDetailSvc.deleteOneOrderDetail(room_meal_order_no, meal_no);
				
//				// 想要 forward 回去之後保持該 orderMaster 燈箱的畫面
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
				
				
				// 想要 forward 回去之後保持該 VO 燈箱的畫面
				List<RoomMealOrderDetailVO> roomMealOrderDetails = roomMealOrderDetailSvc.
						getRoomMealOrderDetailByOrderNo(room_meal_order_no);
				
				int total = roomMealOrderDetails.stream()
						.filter(e -> room_meal_order_no.equals(e.getRoom_meal_order_no()))
						.mapToInt(e -> e.getPrice()*e.getQuantity()).sum();
				
				System.out.println("total="+total);
				roomMealOrderMasterSvc.updateTotal(total, room_meal_order_no);
				
				
				System.out.println(roomMealOrderDetails.size());
				System.out.println("room_meal_order_no : " + room_meal_order_no);
				if (roomMealOrderDetails.size() == 0) {
					// 商品詳細沒了之後要改變 orderMaster 狀態為取消
					roomMealOrderMasterSvc.updateOrderToCancel(room_meal_order_no);
				} else {
					boolean openModal=true;
					request.setAttribute("openModal",openModal);
				}
				boolean openModal=true;
				request.setAttribute("openModal",openModal);
				// 做完事之後再統一 setAttribute()
				List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll(map);
				request.setAttribute("listOrderMasters_ByCompositeQuery", list);
				request.setAttribute("roomMealOrderDetails", roomMealOrderDetails);
				
				
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("刪除失敗:" + e.getMessage());
				e.printStackTrace();
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			}
		}
		
		if ("updateOneOrderDetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			
			try {
				// 接收資料
				String room_meal_order_no = request.getParameter("room_meal_order_no");
				String meal_no = request.getParameter("meal_no");
				System.out.println("1 :"+room_meal_order_no + "_" +meal_no + "_quantity");
				System.out.println("2 :"+request.getParameter(room_meal_order_no + "_" +meal_no + "_quantity"));
				Integer quantity = new Integer(request.getParameter(room_meal_order_no + "_" +meal_no + "_quantity"));
				// 轉交服務層
				RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();
				roomMealOrderDetailSvc.updateQuantity(room_meal_order_no, meal_no, quantity);
				
//				// 想要 forward 回去之後保持該 orderMaster 燈箱的畫面
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
				
				
				// 想要 forward 回去之後保持該 VO 燈箱的畫面
				List<RoomMealOrderDetailVO> roomMealOrderDetails = roomMealOrderDetailSvc.
						getRoomMealOrderDetailByOrderNo(room_meal_order_no);
				
				int total = roomMealOrderDetails.stream()
						.filter(e -> room_meal_order_no.equals(e.getRoom_meal_order_no()))
						.mapToInt(e -> e.getPrice()*e.getQuantity()).sum();
				
				System.out.println("total="+total);
				roomMealOrderMasterSvc.updateTotal(total, room_meal_order_no);
				
				boolean openModal=true;
				request.setAttribute("openModal",openModal);
				List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll(map);
				// 做完事之後再統一 setAttribute()
				request.setAttribute("listOrderMasters_ByCompositeQuery", list);
				request.setAttribute("roomMealOrderDetails", roomMealOrderDetails);
				
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("刪除失敗:" + e.getMessage());
				e.printStackTrace();
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			}
			
		}
		
		if ("listOrderMasters_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);			
			
			String requestURL = request.getParameter("requestURL");
			System.out.println(requestURL);
			
			try {
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (request.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(request.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				}
				
				RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
				List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll(map);
				System.out.println("356查詢的訂餐訂單 = " + list);
				List<RoomMealOrderMasterVO> cookingList = roomMealOrderMasterSvc.getAll().stream()
						.filter(roomOMVO -> 1 == roomOMVO.getRo_order_status())
						.collect(Collectors.toList());
				
				request.setAttribute("listOrderMasters_ByCompositeQuery", list);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
			
		}
		
		if ("change2Cooked".equals(action)) {
			// 參數檢查
			String room_meal_order_no = request.getParameter("room_meal_order_no");
			String updateByWhitchOrder = request.getParameter("updateByWhitchOrder");
			String requestURL = request.getParameter("requestURL");
			
			System.out.println("room_meal_order_no="+room_meal_order_no);
			System.out.println("updateByWhitchOrder="+updateByWhitchOrder);
			System.out.println("requestURL="+requestURL);
			
			// 轉交服務層
			RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
			RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();
			
			int total = roomMealOrderDetailSvc.getAll().stream()
							.filter(roomMealODVO -> room_meal_order_no.equals(roomMealODVO.getRoom_meal_order_no()))
							.mapToInt(e -> e.getPrice())
							.sum();
			
			Integer total_price = Integer.valueOf(total);
			
			System.out.println("total="+total);
			roomMealOrderMasterSvc.updateOrderToCooked(null, total_price, room_meal_order_no);
			
			
			// forward
			request.setAttribute("updateByWhitchOrder", updateByWhitchOrder);
			String url = requestURL;
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		if ("changeOMStatus".equals(action)) {
			// 接收參數
			String room_meal_order_no = request.getParameter("room_meal_order_no");
			String requestURL = request.getParameter("requestURL");
			Integer ro_order_status = Integer.valueOf(request.getParameter("ro_order_status"));
			
			
			RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
			roomMealOrderMasterSvc.updateStatus(ro_order_status, room_meal_order_no);
			
			// 想要 forward 回去之後保持該 orderMaster 的畫面
			HttpSession session = request.getSession();
			Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
			List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll(map);
			// 做完事之後再統一 setAttribute()
			request.setAttribute("listOrderMasters_ByCompositeQuery", list);
			
			String url = requestURL;
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
		
	}

}
