package com.meal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cus.model.CustomerVO;
import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.favoritemeal.model.FavoriteMealService;
import com.favoritemeal.model.FavoriteMealVO;
import com.meal.model.MealService;
import com.meal.model.MealVO;
import com.mealtype.model.MealTypeService;
import com.mealtype.model.MealTypeVO;
import com.restaurant.model.RestaurantService;
import com.restaurant.model.RestaurantVO;

import common.InputStreamUtils;
import oracle.net.aso.e;

/**
 * Servlet implementation class MealServletJSON
 */
@WebServlet("/meal/mealJson.do")
@MultipartConfig(fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JSONArray fakeData = new JSONArray();
       

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
		// 取得 init 的各個狀態參數代表的名稱
		Map<Integer, String> meal_status = (Map<Integer, String>) request.getServletContext().getAttribute("meal_status");
		JSONObject output = new JSONObject();
		//System.out.println(action);
		
		if ("addToCart".equals(action)) {
			// 接收函數
			String meal_no = request.getParameter("meal_no");
			//System.out.println(meal_no);
			String meal_name = request.getParameter("meal_name");
			//System.out.println(meal_name);
			Integer price = null;
			try {
				price = Integer.valueOf(request.getParameter("price"));
				//System.out.println(price);
//				price = new Integer(request.getParameter("price"));
				if (price < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ne) {
				price = 0;
				ne.printStackTrace();
			}
			
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
			
			// 沒有購物車就自己丟一個到 Session
			if (cart == null) {
				cart = new ArrayList<Map<String,Object>>();
				request.getSession().setAttribute("cart", cart);
			}
			
			int flag = 0;
			for (Map<String, Object> item : cart) {
				String meal_noInCart = (String) item.get("meal_no");
				// 購物車內已經有選擇的商品時
				if (meal_no.equals(meal_noInCart)) {
					Integer quantity = (Integer) item.get("quantity");
					// 詳細頁面用選定的數量(預設1), 一般頁面用++
					if (request.getParameter("selectQuantity") == null) {
						quantity++;
						
					} else {
						Integer selectQuantity = Integer.valueOf(request.getParameter("selectQuantity"));
						quantity += selectQuantity;
					}
					item.put("quantity", quantity);
					
					flag++;
				}
			}
			
			if (flag == 0) {
				// 沒有選擇的商品時
				Map<String, Object> item = new HashMap<String, Object>();
			
				item.put("meal_no", meal_no);
				item.put("meal_name", meal_name);
				item.put("price", price);
				item.put("quantity", 1);
				
				cart.add(item);
			}
			
			// 購物車內餐點數量
			Integer itemNumberInCart = new Integer(cart.size());
			output.put("itemNumberInCart", itemNumberInCart);
			request.getSession().setAttribute("itemNumberInCart", itemNumberInCart);
			
			
			// 購物車總價格
			Integer total = new Integer(cart.stream().mapToInt(e -> (int)e.get("price") * (int)e.get("quantity")).sum());
			output.put("total", total);
			request.getSession().setAttribute("total", total);
			
		} else if ("addToFavorite".equals(action)) {
			
			try {
			// 參數處理
			// 後面要改成從 Session 中拿
			// 例外處理?
			CustomerVO customerVO = (CustomerVO) request.getSession().getAttribute("customerVO");
			//System.out.println("customerVO : "+customerVO);
			String cus_id = customerVO.getCus_Id();
			//System.out.println("cus_id : "+cus_id);
			
//			String cus_id = request.getParameter("cus_id");
			String meal_no = request.getParameter("meal_no");
			//System.out.println(meal_no);
			
			
			// 有同一筆餐點資料時 PK 重複問題
			// 轉交服務層
			List<FavoriteMealVO> favoriteMealVOs = new FavoriteMealService().getAll(cus_id);
			List<FavoriteMealVO> newFavoriteMealVOs = favoriteMealVOs.stream()
					.filter(e -> e.getMeal_no().equals(meal_no))
					.collect(Collectors.toList());
			
			
			
			if (newFavoriteMealVOs.isEmpty()) {
				FavoriteMealService favoriteMealSvc = new FavoriteMealService();
				favoriteMealSvc.insertOneMeal(cus_id, meal_no);
				output.put("success", "Y");
				output.put("sysMessage", "收藏成功");
			} else {
				output.put("sysMessage", "該餐點已經被收藏過了~");
				//System.out.println("該餐點已經被收藏過了~");
			}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if ("getData".equals(action)) {
			// 資料處理
			String pageNo = request.getParameter("pageNo");
			int pageSize = Integer.parseInt(request.getParameter("pageSize"), 10);
			String pageSizeStr = request.getParameter("pageSize");
			String meal_type_no = request.getParameter("meal_type_no");
			String resNo = request.getParameter("resNo");
			//System.out.println("meal_type_no="+meal_type_no);
			//System.out.println("resNo="+resNo);
			//System.out.println("pageNo="+pageNo);
			//System.out.println("pageSize="+pageSize);
			
			List<MealVO> list = new MealService().getAll().stream()
										.filter(mealVO -> 1 == mealVO.getMeal_status())
										.collect(Collectors.toList());
			
			fakeData = new JSONArray(list);
			
			
			// filter, 有吃到什麼就篩選甚麼, 選到空白(val = -1) 的選項時就不過濾
			if (!("-1".equals(meal_type_no)) && !("-1".equals(resNo))) {
				List<MealVO> newList = list.stream()
						.filter(p -> p.getMeal_type_no().equals(meal_type_no))
						.filter(p -> p.getRes_no().equals(resNo))
						.collect(Collectors.toList());
				fakeData = new JSONArray(newList);
			} else {
				if (!("-1".equals(meal_type_no))) {
					List<MealVO> newList = list.stream()
											.filter(p -> p.getMeal_type_no().equals(meal_type_no))
											.collect(Collectors.toList());
					fakeData = new JSONArray(newList);
				}
				
				if (!("-1".equals(resNo))) {
					List<MealVO> newList = list.stream()
							.filter(p -> p.getRes_no().equals(resNo))
							.collect(Collectors.toList());
					fakeData = new JSONArray(newList);
				}
			}
			
			
			
			// 替換編號為名稱, 狀態為狀態對應的名稱
			JSONObject obj = new JSONObject();
			List<MealTypeVO> mealTypeList = new MealTypeService().getAll();
			List<RestaurantVO> restaurantList = new RestaurantService().getAll();
				try {
					for (int i = 0; i < fakeData.length(); i++) {
						obj = fakeData.getJSONObject(i);
						// 替換餐廳編號為名稱
						for (RestaurantVO restaurantVO : restaurantList) {
							if (obj.get("res_no").equals(restaurantVO.getResNo())) {
								obj.put("res_no", restaurantVO.getResName());
							}
						}
						// 替換餐點類別編號為名稱
						for (MealTypeVO mealTypeVO : mealTypeList) {
							if (obj.get("meal_type_no").equals(mealTypeVO.getMeal_type_no())) {
								obj.put("meal_type_no", mealTypeVO.getType_name());
							}
						}
						// 替換餐點狀態為代表名稱
						Integer status = obj.getInt("meal_status");
						obj.put("meal_status", meal_status.get(status));
						// 增加圖片路徑
						String mealImgTag = "<img src='" + request.getContextPath() + 
												"/GetMealPicture?meal_no="+ obj.getString("meal_no") +"'>";
						obj.put("mealImgTag", mealImgTag);
					}
				} catch (JSONException je) {
					je.printStackTrace();
				}
			
				
			// firstNo and pageSize
			Integer no = Integer.parseInt(pageNo);
			Integer page = Integer.parseInt(pageSizeStr);
			int startNo = (no > 1) ? (no - 1) * page : 0;
			JSONArray finalData = new JSONArray();
			int endNo = (fakeData.length() >= (startNo + page)) ? (startNo + page) : fakeData.length();
			for (int i = startNo; i < endNo; i++) {
				finalData.put(fakeData.getJSONObject(i));
			}
			
			
			int pageTotal = (int) fakeData.length()/pageSize;
			if (fakeData.length() % pageSize != 0) {
				pageTotal++;
			}
			output.put("pageTotal", pageTotal);
			output.put("rows", finalData);
			
		} else if ("findOneMeal".equals(action)) {
			// 透過 <a> 標籤重導向到單一餐點頁面
			String meal_no = request.getParameter("meal_no");
			//System.out.println(meal_no);
			
			MealService mealSvc = new MealService();
			MealVO mealVO = mealSvc.getOneMeal(meal_no);
			
			request.setAttribute("mealVO", mealVO);
			String url = "/front-end/meal/listOneMeal.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		} else if ("findMealsByCategory".equals(action)) {
			// 透過 <a> 標籤重導向到餐點頁面
			String meal_type_no = request.getParameter("meal_type_no");
			String resNo = request.getParameter("resNo");
			//System.out.println("meal_type_no : "+meal_type_no);
			//System.out.println("resNo : "+resNo);
			
			List<MealVO> mealList = new MealService().getAll();
 			
			// filter, 只能篩選一筆
			if (meal_type_no != null) {
				mealList = mealList.stream()
										.filter(p -> p.getMeal_type_no().equals(meal_type_no))
										.collect(Collectors.toList());
			} else if (resNo != null) {
				mealList = mealList.stream()
						.filter(p -> p.getRes_no().equals(resNo))
						.collect(Collectors.toList());
			}
			
			//System.out.println(mealList.toString());
			request.setAttribute("mealList", mealList);
			String url = "/front-end/meal/products.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		} else if ("getCartData".equals(action)) {
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
			JSONArray mealData = new JSONArray(cart);
			
			
			output.put("mealTotal", mealData.length());
			output.put("meals", mealData);
		} else if ("deleteOneMeal".equals(action)) {
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
			String meal_no = request.getParameter("meal_no");
			//System.out.println(meal_no);
			
			if (meal_no != null) {
				List<Map<String, Object>> newCart = cart.stream()
														.filter(p -> !(p.get("meal_no").equals(meal_no)))
														.collect(Collectors.toList());
				Integer itemNumberInCart = new Integer(newCart.size());
				Integer total = new Integer(newCart.stream().mapToInt(e -> (int)e.get("price") * (int)e.get("quantity")).sum());
				
				request.getSession().setAttribute("cart", newCart);
				request.getSession().setAttribute("itemNumberInCart", itemNumberInCart);
				request.getSession().setAttribute("total", total);
			}
		} else if ("getMealData".equals(action)) {
			String pageNo = request.getParameter("pageNo");
			String pageSize = request.getParameter("pageSize");
			String orderBy = request.getParameter("orderBy");
			String orderType = request.getParameter("orderType");
			String like = request.getParameter("like");
			like = like.trim();
			
			/* ===== 以下從資料庫下ＳＱＬ指令查找 ===== */
			// Make fake data
			List<MealVO> list = new MealService().getAll();
			fakeData = new JSONArray(list);
			
			// 先加入讓 bootstrap-table 可以排序
			List<MealTypeVO> mealTypeListTmep = new MealTypeService().getAll();
			List<RestaurantVO> restaurantListTemp = new RestaurantService().getAll();
				try {
					for (int i = 0; i < fakeData.length(); i++) {
						JSONObject objtemp = fakeData.getJSONObject(i);
						// 替換餐廳編號為名稱
						for (RestaurantVO restaurantVO : restaurantListTemp) {
							if (objtemp.get("res_no").equals(restaurantVO.getResNo())) {
								objtemp.put("resName", restaurantVO.getResName());
							}
						}
						// 替換餐點類別編號為名稱
						for (MealTypeVO mealTypeVO : mealTypeListTmep) {
							if (objtemp.get("meal_type_no").equals(mealTypeVO.getMeal_type_no())) {
								objtemp.put("type_name", mealTypeVO.getType_name());
							}
						}
						// 替換餐點狀態為代表名稱
						Integer status = objtemp.getInt("meal_status");
						objtemp.put("meal_status_name", meal_status.get(status));
						// 增加圖片路徑
						String mealImgTag = "<img src='" + request.getContextPath() + 
												"/GetMealPicture?meal_no="+ objtemp.getString("meal_no") +"' style='width: 100%; height: auto;'>";
						objtemp.put("mealImgTag", mealImgTag);
					}
				} catch (JSONException je) {
					je.printStackTrace();
				}
			
			
			// orderBy and orderType
			JSONArray sortedData = new JSONArray();
			List<JSONObject> jsonValues = new ArrayList<JSONObject>();
			for (int i = 0; i < fakeData.length(); i++) {
				jsonValues.add(fakeData.getJSONObject(i));
			}
			Collections.sort(jsonValues, new Comparator<JSONObject>() {
				@Override
				public int compare(JSONObject a, JSONObject b) {
					if("price".equals(orderBy)){
						Integer valA = (Integer) a.get(orderBy);
						Integer valB = (Integer) b.get(orderBy);
						if ("desc".equals(orderType)) 
							return -valA.compareTo(valB);
						else 
							return valA.compareTo(valB);
					} else {
						String valA = (String) a.get(orderBy);
						String valB = (String) b.get(orderBy);
						if ("desc".equals(orderType)) 
							return -valA.compareTo(valB);
						else 
							return valA.compareTo(valB);
					}
				}
			});
			for (int i = 0; i < fakeData.length(); i++) {
				sortedData.put(jsonValues.get(i));
			}

			// like
			JSONArray filterData = new JSONArray();
			if (!"".equals(like)) {
				for (int i = 0; i < sortedData.length(); i++) {
					JSONObject obj = (JSONObject) sortedData.get(i);
					Set<String> keys = obj.keySet();
					Iterator<String> a = keys.iterator();
					while (a.hasNext()) {
						String key = (String) a.next();
						String value = new String();
						if("price".equals(orderBy)){
							Integer tmp = (Integer) obj.get(key);
							value = tmp.toString();
						} else
							value = (String) obj.get(key);
						if (value.contains(like)) {
							filterData.put(sortedData.get(i));
							break;
						}
					}
				}
			} else {
				filterData = sortedData;
			}
			// total
			output.put("total", filterData.length());

			// firstNo and pageSize
			Integer no = Integer.parseInt(pageNo);
			Integer page = Integer.parseInt(pageSize);
			int startNo = (no > 1) ? (no - 1) * page : 0;
			JSONArray finalData = new JSONArray();
			int endNo = (filterData.length() >= (startNo + page)) ? (startNo + page) : filterData.length();
			for (int i = startNo; i < endNo; i++) {
				finalData.put(filterData.getJSONObject(i));
			}
			
			// 替換編號為名稱, 狀態為狀態對應的名稱
			JSONObject obj = new JSONObject();
			List<MealTypeVO> mealTypeList = new MealTypeService().getAll();
			List<RestaurantVO> restaurantList = new RestaurantService().getAll();
				try {
					for (int i = 0; i < finalData.length(); i++) {
						obj = finalData.getJSONObject(i);
						// 替換餐廳編號為名稱
						for (RestaurantVO restaurantVO : restaurantList) {
							if (obj.get("res_no").equals(restaurantVO.getResNo())) {
								obj.put("resName", restaurantVO.getResName());
							}
						}
						// 替換餐點類別編號為名稱
						for (MealTypeVO mealTypeVO : mealTypeList) {
							if (obj.get("meal_type_no").equals(mealTypeVO.getMeal_type_no())) {
								obj.put("type_name", mealTypeVO.getType_name());
							}
						}
						// 替換餐點狀態為代表名稱
						Integer status = obj.getInt("meal_status");
						obj.put("meal_status_name", meal_status.get(status));
						// 增加圖片路徑
						String mealImgTag = "<img src='" + request.getContextPath() + 
												"/GetMealPicture?meal_no="+ obj.getString("meal_no") +"' style='width: 100%; height: auto;'>";
						obj.put("mealImgTag", mealImgTag);
					}
				} catch (JSONException je) {
					je.printStackTrace();
				}
			
			
			output.put("rows", finalData);

			// delay 1 sec
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
		} else if ("update".equals(action)) {
			String meal_no = request.getParameter("meal_no");
			String res_no = request.getParameter("res_no");
			String meal_type_no = request.getParameter("meal_type_no");
			String meal_name = request.getParameter("meal_name");
			String meal_introduction = request.getParameter("meal_introduction");
			if (meal_introduction.isEmpty() || meal_introduction == null || meal_introduction.trim().length() == 0) {
				meal_introduction = " ";
			}
			Integer price = Integer.valueOf(request.getParameter("price")); 
			Integer status = Integer.valueOf(request.getParameter("meal_status"));
			Date meal_date = Date.valueOf(request.getParameter("meal_date"));
			byte[] meal_picture = null;
			Part part = null;
			
			try {
				// 圖片處理
				part = request.getPart("meal_picture");
				
				if (part != null) {
					meal_picture = InputStreamUtils.InputStreamTOByte(part);
				} else {
					meal_picture = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			MealService mealSvc = new MealService();
			if (part == null || part.getSize() == 0 || part.getSubmittedFileName() == null) {
				mealSvc.updateMealWithoutPicture(meal_name, price, res_no, meal_type_no, 
						status, meal_introduction, meal_no, meal_date);
			} else {
				mealSvc.updateMeal(meal_name, price, res_no, meal_type_no, status, 
						meal_introduction, meal_picture, meal_no, meal_date);
			}
			
			output.put("success", "Y");
		} else if ("insert".equals(action)) {
			String res_no = request.getParameter("res_no");
			String meal_type_no = request.getParameter("meal_type_no");
			String meal_name = request.getParameter("meal_name");
			String meal_introduction = request.getParameter("meal_introduction");
			if (meal_introduction.isEmpty() || meal_introduction == null || meal_introduction.trim().length() == 0) {
				meal_introduction = " ";
			}
			Integer price = Integer.valueOf(request.getParameter("price")); 
			Integer status = Integer.valueOf(request.getParameter("meal_status"));
			Date meal_date = new Date(new java.util.Date().getTime());
			//System.out.println("res_no : "+res_no);
			//System.out.println("meal_type_no : "+meal_type_no);
			//System.out.println("meal_name : "+meal_name);
			//System.out.println("meal_introduction : "+meal_introduction);
			//System.out.println("price : "+price);
			//System.out.println("status : "+status);
			//System.out.println("meal_date : "+meal_date);
			
			
			byte[] meal_picture = null;
			Part part = null;
			
			try {
				// 圖片處理
				part = request.getPart("meal_picture");
				
				if (part != null) {
					meal_picture = InputStreamUtils.InputStreamTOByte(part);
				} else {
					meal_picture = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			MealService mealSvc = new MealService();
			mealSvc.addMeal(meal_name, price, res_no, meal_type_no, status, meal_introduction, meal_picture, meal_date);
			
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
