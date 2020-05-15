package android.com.roommealordermaster.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.meal.model.*;
import com.roommealordermaster.model.*;
import com.roommealorderdetail.model.*;;

interface MealOrderStatus {
	public static final int PREPARING = 0; // 備餐中
	public static final int ONGOING = 1; // 出餐中
	public static final int ARRIVED = 2; // 已出餐(未收款)
	public static final int CLOSED = 3; // 已完成(已收款)
	public static final int CANCELED = 4; // 訂單取消

}

public class RoomMealOrderServletAndroid extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RoomMealOrderMasterService orderService = new RoomMealOrderMasterService();
		RoomMealOrderDetailService orderDetailService = new RoomMealOrderDetailService();
		List<RoomMealOrderMasterVO> orderList = orderService.getAll();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null)
			jsonIn.append(line);
		System.out.println("input: " + jsonIn);

		// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if ("getOngoingOrder".equals(action)) {
			List<RoomMealOrderMasterVO> ongoingOrderList = getOngoingOrder(orderList);
			writeText(res, gson.toJson(ongoingOrderList));

		} else if ("getOrderDetail".equals(action)) {
			String room_meal_order_no = jsonObject.get("room_meal_order_no").getAsString();
			System.out.println("room_meal_order_no: " + room_meal_order_no);

			List<RoomMealOrderDetailVO> orderDetailList = orderDetailService
					.getRoomMealOrderDetailByOrderNo(room_meal_order_no);
			writeText(res, gson.toJson(orderDetailList));

		} else if ("getDoneOrder".equals(action)) {
			List<RoomMealOrderMasterVO> doneOrderList = getDoneOrder(orderList);
			writeText(res, gson.toJson(doneOrderList));

		} else if ("updateOrderStatusToArrived".equals(action)) {
			String room_meal_order_no = jsonObject.get("room_meal_order_no").getAsString();
			int total_price = jsonObject.get("total_price").getAsInt();
			orderService.updateOrderToShipping(total_price, room_meal_order_no);

		} else if ("updateOrderStatusToCanceled".equals(action)) {
			String room_meal_order_no = jsonObject.get("room_meal_order_no").getAsString();
			orderService.updateOrderToCancel(room_meal_order_no);

		} else if ("updateOrderStatusToClosed".equals(action)) {
			String room_meal_order_no = jsonObject.get("room_meal_order_no").getAsString();
			orderService.updateOrderToClosed(room_meal_order_no);

		} else if ("getMealList".equals(action)) {
			String room_meal_order_no = jsonObject.get("room_meal_order_no").getAsString();
			System.out.println("room_meal_order_no: " + room_meal_order_no);

			List<RoomMealOrderDetailVO> orderDetailList = orderDetailService
					.getRoomMealOrderDetailByOrderNo(room_meal_order_no);

			MealService mealService = new MealService();
			List<MealVO> mealList = new ArrayList<>();
			for (int i = 0; i < orderDetailList.size(); i++) {
				RoomMealOrderDetailVO orderDetail = orderDetailList.get(i);
				String meal_no = orderDetail.getMeal_no();
				MealVO meal = mealService.getOneMeal(meal_no);
				mealList.add(meal);
			}
			writeText(res, gson.toJson(mealList));

		} else if ("getMealNameMap".equals(action)) {
			MealService mealService = new MealService();
			List<MealVO> mealList = mealService.getAll();

			Map<String, String> mealNameMap = new HashMap<>();

			for (int i = 0; i < mealList.size(); i++) {
				MealVO meal = mealList.get(i);
				mealNameMap.put(meal.getMeal_no(), meal.getMeal_name());
			}
			writeText(res, gson.toJson(mealNameMap));

		} else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

	private List<RoomMealOrderMasterVO> getOngoingOrder(List<RoomMealOrderMasterVO> orderList) {
		List<RoomMealOrderMasterVO> list = new ArrayList<>();
		for (int i = 0; i < orderList.size(); i++) {
			RoomMealOrderMasterVO order = orderList.get(i);
			if (order.getRo_order_status() == MealOrderStatus.ONGOING) { // 記得改回ONGOING!!!
				list.add(order);
			}
		}
		return list;
	}

	private List<RoomMealOrderMasterVO> getDoneOrder(List<RoomMealOrderMasterVO> orderList) {
		List<RoomMealOrderMasterVO> list = new ArrayList<>();
		for (int i = 0; i < orderList.size(); i++) {
			RoomMealOrderMasterVO order = orderList.get(i);
			if (order.getRo_order_status() == MealOrderStatus.CLOSED
					|| order.getRo_order_status() == MealOrderStatus.CANCELED) {
				list.add(order);
			}
		}
		return list;
	}

}
