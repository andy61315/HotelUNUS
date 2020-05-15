package com.roomtype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.room.model.RoomService;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;
import com.util.MapStatusOnStart;

public class RoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException  {
		res.setCharacterEncoding("UTF-8");
//		//System.out.println("進doPost");

		String action = req.getParameter("action");
//		//System.out.println("action = " + action);
		Map<Integer, String> roomType_Status = (Map<Integer, String>) req.getServletContext().getAttribute("roomType_Status");
//		//System.out.println("roomType_Status = " + roomType_Status);
		if ("getData".equals(action)) {
			List<RoomTypeVO> list = new RoomTypeService().getAll();
			JSONArray output = new JSONArray(list);
			//System.out.println(output);
			JSONObject obj = new JSONObject();
			for (int i = 0; i < output.length(); i++) {
				try {
					obj = output.getJSONObject(i);
					Double avgStars = ((((Integer) obj.get("star_Amount")).doubleValue()
							/ ((Integer) obj.get("star_People")).doubleValue()));
					if ((Integer) obj.get("star_People") == 0)
						avgStars = 0.0;
					obj.put("avgStars", avgStars);
					Integer status = obj.getInt("room_Type_Status");
					obj.put("room_Type_Status", roomType_Status.get(status));
					String picUrl = "<img src='" + req.getContextPath() + 
							"/RoomTypePicture/GetRoomTypePicture?action=getOneForDisplay&room_Type_No=" + obj.getString("room_Type_No") +
							"' width='100px' height='100px' >";
							
					obj.put("roomTypePicture",picUrl);
					String checkArticle = "<span class='checkArticle'>查看文案<span style=\"display:none;\">" + obj.getString("article") + "</span></span>";
					obj.put("checkArticle", checkArticle);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			//System.out.println(output);
			out.write(output.toString());
			out.flush();
			out.close();
		}

		if("checkIfNameExist".equals(action)) {
			JSONObject output = new JSONObject();
			String room_Type_Name = req.getParameter("room_Type_Name");
			Map<String, String[]> map = new HashMap<>();
			map.put("room_Type_Name",new String[] {room_Type_Name});
			int count = new RoomTypeService().getAllBy(map).size();
			try {
				output.put("available", ((count == 0)?"Y":"N"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
//			//System.out.println(output);
			out.write(output.toString());
			out.flush();
			out.close();
		}
		
		if ("insert".equals(action)) {
			JSONObject output = new JSONObject();
			
			try {
			String room_Type_No = req.getParameter("room_Type_No");
			String room_Type_Name = req.getParameter("room_Type_Name");
			String article = req.getParameter("article");
			Integer person_Capacity = Integer.valueOf(req.getParameter("person_Capacity"));
			Integer add_People = Integer.valueOf(req.getParameter("add_People"));
			Integer total_People = person_Capacity + person_Capacity;
			Integer price = Integer.valueOf(req.getParameter("price"));
			Integer holiday_Price = Integer.valueOf(req.getParameter("holiday_Price"));
			Integer workingDay_Price = Integer.valueOf(req.getParameter("workingDay_Price"));
			Integer room_Type_Status = Integer.valueOf(req.getParameter("room_Type_Status"));
			
				String next_Room_Type_No =new RoomTypeService().addRoomType( room_Type_Name, article, person_Capacity, add_People, price,
																			holiday_Price, workingDay_Price, total_People, room_Type_Status);
				output.put("success", "Y");
				output.put("next_Room_Type_No", next_Room_Type_No);
				new MapStatusOnStart().getDifCapRTToFront(req.getServletContext());
			} catch (JSONException e) {
				e.printStackTrace();
			} catch(Exception e) {
				// TODO Auto-generated catch block
				try {
					output.put("error",  e.getClass().getCanonicalName() + "  :  " + e.getMessage() );
//					//System.out.println("\n\n\n\n\n\n\n\n getMessage = \n\n\n\n\n\n\n\n" +  e.getStackTrace() + "\n\n\n\\n\n\n" + e.getMessage());
					res.setContentType("text/plain");
					PrintWriter out = res.getWriter();
					out.write(output.toString());
					out.flush();
					out.close();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			out.write(output.toString());
			out.flush();

		}
		
		if("update".equals(action)) {
//			//System.out.println("進update");
			JSONObject output = new JSONObject();
			try {
			
			String room_Type_No = req.getParameter("room_Type_No");
			String room_Type_Name = req.getParameter("room_Type_Name");
			String article = req.getParameter("article");
			Integer person_Capacity = Integer.valueOf(req.getParameter("person_Capacity"));
			Integer add_People = Integer.valueOf(req.getParameter("add_People"));
			Integer total_People = person_Capacity + person_Capacity;
			Integer price = Integer.valueOf(req.getParameter("price"));
			Integer holiday_Price = Integer.valueOf(req.getParameter("holiday_Price"));
			Integer workingDay_Price = Integer.valueOf(req.getParameter("workingDay_Price"));
			Integer room_Type_Status = Integer.valueOf(req.getParameter("room_Type_Status"));
			//System.out.println("room_Type_No in servlet = " + room_Type_No);
			new RoomTypeService().updateRoomTypeBasic(room_Type_No, room_Type_Name, article, person_Capacity, add_People, price, 
														holiday_Price, workingDay_Price,total_People, room_Type_Status);
			output.put("success", "Y");
			new MapStatusOnStart().getDifCapRTToFront(req.getServletContext());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					output.put("error",  e.getClass().getCanonicalName() + "  :  " + e.getMessage() );
//					//System.out.println("\n\n\n\n\n\n\n\n getMessage = \n\n\n\n\n\n\n\n" +  e.getStackTrace() + "\n\n\n\\n\n\n" + e.getMessage());
					res.setContentType("text/plain");
					PrintWriter out = res.getWriter();
					out.write(output.toString());
					out.flush();
					out.close();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} 
			
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			out.write(output.toString());
			out.flush();
			out.close();
		}
		
		if("delete".equals(action)) {
			JSONObject output = new JSONObject();
			String[] delArray = req.getParameterValues("delArray[]");//有這種取法!!!!!!!
			RoomTypeService rtSvc = new RoomTypeService();
			for(String del : delArray) {
				rtSvc.updateRoomTypeStatus(del, 0);
				new RoomService().ofShellRoom(del);
			}
			new MapStatusOnStart().getDifCapRTToFront(req.getServletContext());
			try {
				output.put("success", "Y");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			out.write(output.toString());
			out.flush();
			out.close();
		}
//
//--下架房型時，把該房型的房間一起下架(但不會做把原本下架的房型上架時，把所有房間一起上架的TRIGGER，邏輯上會有誤區)
//CREATE OR REPLACE TRIGGER trigger_Room_Type_update
//  AFTER UPDATE OF 
//      ROOM_TYPE_STATUS
//    ON ROOM_TYPE
//  FOR EACH ROW
//BEGIN
//    IF :new.ROOM_TYPE_STATUS = 0 THEN
//        UPDATE ROOM SET ROOM_STATUS = 5
//       WHERE ROOM.ROOM_TYPE_NO = :NEW.ROOM_TYPE_NO;
//    END IF;
//END;
///
	}

}
