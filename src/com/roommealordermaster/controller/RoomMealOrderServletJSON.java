package com.roommealordermaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.roommealordermaster.model.RoomMealOrderMasterService;

/**
 * Servlet implementation class RoomMealOrderServletJSON
 */
@WebServlet("/order/orderJson.do")
public class RoomMealOrderServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		JSONObject output = new JSONObject();
		System.out.println("orderJson : "+action);
	
		if ("addOrder".equals(action)) {
			// 為了出錯的時候不讓 cart 內的資料消失所以用 try catch
			try {
				List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
				System.out.println("cart 執行前 : "+cart);
				System.out.println("執行前47");
				// 後面要改成選正在進行(checkin)的訂單
				String b_order_no = (String) request.getParameter("b_order_no");
				
				// 後面要改成透過顧客ID找是哪個房間(下拉式選單)
				String room_no = (String) request.getParameter("room_no");
				
				String special_requirement = request.getParameter("special_requirement");
				int total = cart.stream().mapToInt(e -> (int)e.get("quantity")*(int)e.get("price")).sum();
				
				Integer total_price = Integer.valueOf(total);
				System.out.println("total_price="+total_price);
				// 轉交服務層
				RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
				roomMealOrderMasterSvc.addRoomMealOrderMasterWithOrderDetailAndTotal(b_order_no, room_no, 
								special_requirement, cart, total_price);
				
				output.put("success", "Y");
				request.getSession().removeAttribute("cart");
				request.getSession().removeAttribute("itemNumberInCart");
				request.getSession().removeAttribute("total");
				
				System.out.println("cart 執行後 :"+request.getSession().getAttribute("cart"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("getNewTotal".equals(action)) {
			
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
			String meal_no_input = request.getParameter("meal_no");
			System.out.println("meal_no_input : "+meal_no_input);
			
			Integer total = 0;
			if (cart != null) {
				
				for (Map<String, Object> item : cart) {
					String meal_no = (String) item.get("meal_no");
					Integer price = (Integer) item.get("price");
					Integer quantity = (Integer) item.get("quantity");
					Integer quantityInput = null;
					
					try {
						quantityInput = Integer.valueOf(request.getParameter("quantityInput"));
						if (quantityInput < 0) {
							throw new NumberFormatException();
						}						
						
						if (meal_no_input.equals(meal_no)) {
							item.put("quantity", quantityInput);
						}
						
					} catch (NumberFormatException ne) {
						quantityInput = 0;
						item.put("quantity", quantityInput);
						ne.printStackTrace();
					}
				}
				
				total = cart.stream()
						.mapToInt(item -> (int)item.get("price") * (int)item.get("quantity"))
						.sum();
				
				System.out.println("total : "+total);
			}
			
			request.getSession().setAttribute("total", total.toString());
			output.put("total", total);
		} else if ("changeOMStatus".equals(action)) {
			// 接收參數
			String room_meal_order_no = request.getParameter("room_meal_order_no");
			Integer ro_order_status = Integer.valueOf(request.getParameter("ro_order_status"));
			
			System.out.println("room_meal_order_no="+room_meal_order_no);
			System.out.println("ro_order_status="+ro_order_status);
			// 轉交服務層
			RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
			roomMealOrderMasterSvc.updateStatus(ro_order_status, room_meal_order_no);
			
			output.put("success", "Y");
		}
		
		
		
		
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(output.toString());
		out.flush();
		out.close();
	}

}
