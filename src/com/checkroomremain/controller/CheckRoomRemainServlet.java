package com.checkroomremain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.checkroomremain.CheckRoomNumberDAO;
import com.checkroomremain.CheckRoomNumberService;
import com.checkroomremain.Combination;
import com.checkroomremain.DifferentNumberRoomNeeded;
import com.checkroomremain.DifferentNumberRoomRemain;
import com.checkroomremain.RoomTypeRemain;
import com.roomdate.model.RoomDateJNDIDAO;
import com.roomdate.model.RoomDateVO;

/**
 * Servlet implementation class CheckRoomRemainServlet
 */
//@WebServlet("/CheckRoomRemainServlet")
public class CheckRoomRemainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckRoomRemainServlet() {
        super();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("showAllCombination".equals(action)) {//秀所有組合 AJAX JSON版本
			//要做人數>房數的除錯
	//		List<Combination> list = (List<Combination>)session.getAttribute("list") ;
			String inputCheckIn = req.getParameter("check-in");
//			System.out.println(" inputCheckIn = " + inputCheckIn);
			String inputCheckOut = req.getParameter("check-out");
//			System.out.println("inputCheckOut " +inputCheckOut);
			Integer room = Integer.parseInt(req.getParameter("room")); 
			Integer adult = Integer.parseInt(req.getParameter("adult"));
			String difRoomNumWanted = req.getParameter("difRoomNumWanted");
//			System.out.println("difRoomNumWanted = " + difRoomNumWanted);
			CheckRoomNumberDAO dao = new CheckRoomNumberDAO();
			List<RoomTypeRemain> rtrlist = dao.getRemainNo(inputCheckIn, inputCheckOut);//取得每個房型在日期區間內各自剩多少房間可訂
			DifferentNumberRoomRemain difNumRoomRemain = dao.getRemainMap(rtrlist);//得到"不同人數的房型"剩幾間
			if(difRoomNumWanted.contains("one"))difNumRoomRemain.setOne(0);
			if(difRoomNumWanted.contains("two"))difNumRoomRemain.setTwo(0);
			if(difRoomNumWanted.contains("three"))difNumRoomRemain.setThree(0);
			if(difRoomNumWanted.contains("four"))difNumRoomRemain.setFour(0);
			if(difRoomNumWanted.contains("five"))difNumRoomRemain.setFive(0);
			if(difRoomNumWanted.contains("six"))difNumRoomRemain.setSix(0);
//			System.out.println("difNumRoomRemain = " + difNumRoomRemain);
			List<Combination> list  = dao.getCombination(difNumRoomRemain, room , adult);//
//			System.out.println("rtrlist = " + rtrlist);
//			System.out.println(list);
//			req.getSession().setAttribute("list", list);
			req.getSession().setAttribute("rtrlist", rtrlist);
			System.out.println(rtrlist);
//			System.out.println(new JSONArray(rtrlist));
//			System.out.println("JSONArray(list) = " + new JSONArray(list));
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.write(new JSONArray(list).toString());
			out.flush();
			out.close();
		}
//		if("showAllCombination".equals(action)) {//秀所有組合 JSP版本
//			//要做人數>房數的除錯
//			//		List<Combination> list = (List<Combination>)session.getAttribute("list") ;
//			String inputCheckIn = req.getParameter("check-in");
//			String inputCheckOut = req.getParameter("check-out");
//			Integer room = Integer.parseInt(req.getParameter("room")); 
//			Integer adult = Integer.parseInt(req.getParameter("adult"));
//			CheckRoomNumberDAO dao = new CheckRoomNumberDAO();
//			
//			List<RoomTypeRemain> rtrlist = dao.getRemainNo(inputCheckIn, inputCheckOut);
//			List<Combination> list  = dao.getCombination(dao.getRemainMap(rtrlist), room , adult);
//			req.getSession().setAttribute("list", list);
//			req.getSession().setAttribute("rtrlist", rtrlist);
////			System.out.println(new JSONArray(rtrlist));
//			
////			String url = "/front-end/checkroomremain/showAllCombination.jsp";
//			String url = "/front-end/checkroomremain/tryTableandJSON.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//		}
		
		if("showCombinationDetail".equals(action)) {//Ajax版本，點選組合跳出組合內的適用房型
			CheckRoomNumberService crnSvc = new CheckRoomNumberService();
			List<DifferentNumberRoomNeeded> roomNeededList = new ArrayList<>();
			DifferentNumberRoomNeeded need = new DifferentNumberRoomNeeded();
			String totalCombination = req.getParameter("totalCombination");//如果要跳分頁的話才需要這個提醒旅客自己的選擇
			String[] roomArr = {"one","two","three","four","five","six"};//showAllCombination那邊要送出的form表單隱藏欄位
			List<RoomTypeRemain> rtrlist =(List<RoomTypeRemain>) req.getSession().getAttribute("rtrlist");
			Date start_Date =  java.sql.Date.valueOf(req.getParameter("check-in"));
//			System.out.println(" inputCheckIn = " + inputCheckIn);
			Date end_Date =  java.sql.Date.valueOf(req.getParameter("check-out"));
//			JSONArray roomNeededList = new JSONArray();
//			JSONObject obj = null;
			String[] arr = {"one","two","three","four","five","six"};//showAllCombination那邊要送出的form表單隱藏欄位
			for(int i = 0; i < arr.length; i++) {
//				System.out.println(arr[i] + " = " +req.getParameter(arr[i]));
				Integer needInt = Integer.parseInt(req.getParameter(arr[i]));
				if(needInt != 0) {
					need = new DifferentNumberRoomNeeded();
					need.setHowManyPeople(i+1);
					need.setNeed(needInt);
					roomNeededList.add(need);
				}
			}
			RoomDateJNDIDAO rdDao = new RoomDateJNDIDAO();//取出日期區間中每天的種類(假日/非假日)
			List<RoomDateVO> rdList = rdDao.getByInterval(start_Date, end_Date);//只取三天兩夜的"兩夜"(沒有取道退房日期)
			JSONArray outputArr = new JSONArray();
			JSONObject obj = null;//要放1.房型編號2.房型名稱3.剩餘房數
			for(DifferentNumberRoomNeeded difNeed: roomNeededList) {
				for(RoomTypeRemain rtr:rtrlist) {
					if(difNeed.getHowManyPeople() == rtr.getPerson_Capacity()) {
						obj = new JSONObject();
						try {
							String room_Type_No = rtr.getRoom_Type_No();
							obj.put("room_Type_No", room_Type_No);
							obj.put("room_Type_Name", rtr.getRooom_Type_Name());
							obj.put("remain", rtr.getRemain());
							obj.put("add_People", rtr.getTotal_People()-rtr.getPerson_Capacity());
							//呼叫計算房型(需要先取得日期
							
							obj.put("price",crnSvc.getPrice(room_Type_No, rdList ));
							
							outputArr.put(obj);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.write(outputArr.toString());
			out.flush();
			out.close();
//			System.out.println(outputArr.toString());
//			JSONArray twoArray = new JSONArray();
//			twoArray.put(roomNeededList).put(new JSONArray(rtrlist));
//			String sendString = 
//			String sendString = "[{\"roomNeededList\":"+roomNeededList.toString()+"},{\"rtrlist\":"+rtrlist.toString()+"}]";
//			System.out.println(twoArray.toString());
//			req.setAttribute("totalCombination", totalCombination);
//			req.setAttribute("roomNeededList", roomNeededList);
			
//			String url = "showCombinationDetail.jsp";
//			String url = "/front-end/checkroomremain/showCombinationDetail.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
		}
		
		
		
		
//		if("showCombinationDetail".equals(action)) {//JSP版本
//			List<DifferentNumberRoomNeeded> roomNeededList = new ArrayList<>();
//			DifferentNumberRoomNeeded need = new DifferentNumberRoomNeeded();
//			String totalCombination = req.getParameter("totalCombination");
//			String[] arr = {"one","two","three","four","five","six"};//showAllCombination那邊要送出的form表單隱藏欄位
//			for(int i = 0; i < arr.length; i++) {
//				Integer needInt = Integer.parseInt(req.getParameter(arr[i]));
//				if(needInt != 0) {
//					need = new DifferentNumberRoomNeeded();
//					need.setHowManyPeople(i+1);
//					need.setNeed(needInt);
//					roomNeededList.add(need);
//				}
//			}
//			req.setAttribute("totalCombination", totalCombination);
//			req.setAttribute("roomNeededList", roomNeededList);
//			
//			String url = "showCombinationDetail.jsp";
////			String url = "/front-end/checkroomremain/showCombinationDetail.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//		}
	}

}
