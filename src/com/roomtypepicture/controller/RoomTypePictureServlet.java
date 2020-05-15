package com.roomtypepicture.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.roomtypepicture.model.RoomTypePictureService;
import com.roomtypepicture.model.RoomTypePictureVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomTypePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			String room_Type_No = req.getParameter("room_Type_No");
			List<RoomTypePictureVO> list = new ArrayList<>();
			RoomTypePictureVO rtpVOInsert = null;
			System.out.println(47 + "INSERT");
			try {
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {// 處理每個要上傳的檔案，加入ArrayList
					try {//判斷是否有fileName
						String header = part.getHeader("content-disposition");
						String regexImg = ".+\\.(jpg|png|gif|bmp|jpeg)\"";
//						System.out.println(79 + "header = "+ header);
						if (header.contains("filename")) {
							InputStream in = null;
							byte[] buf = null;
							if (getFileNameFromPart(part).equals(null)) {//我也不確定需不需要加這個= =
								errorMsgs.add(getFileNameFromPart(part) + "檔案路徑有誤");
							}
							if(header==null || (!header.matches(regexImg))) {//測試是否為圖檔
								errorMsgs.add(getFileNameFromPart(part) + " : 不是圖片檔");
//								System.out.println(getFileNameFromPart(part) + " !header.matches(regexImg) = " + (!header.matches(regexImg)));
							}
							in = part.getInputStream();
							buf = new byte[in.available()];
							in.read(buf);
							rtpVOInsert = new RoomTypePictureVO();
							rtpVOInsert.setRoom_Type_No(room_Type_No);
							rtpVOInsert.setRoom_Type_Pic(buf);
							list.add(rtpVOInsert);
						}
					} catch (NullPointerException e) {
						errorMsgs.add("未選擇檔案");
					}
				}
				if (!errorMsgs.isEmpty()) {
					RoomTypePictureVO rtpVO = new RoomTypePictureVO();
					rtpVO.setRoom_Type_No(room_Type_No);
					req.setAttribute("rtpVO", rtpVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/RoomTypePicture/addRoomTypePicture.jsp");
					failureView.forward(req, res);
					return;
				}
				RoomTypePictureService rtpSvc = new RoomTypePictureService();
				for (RoomTypePictureVO rtpVO : list) {
					rtpVO = rtpSvc.addRoomTypePicture(room_Type_No, rtpVO.getRoom_Type_Pic());
				}
				String url = "/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp?room_Type_No=" + room_Type_No;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/RoomTypePicture/addRoomTypePicture.jsp");
				failureView.forward(req, res);
			}
		}

		if("getData".equals(action)) {
			String room_Type_No = req.getParameter("room_Type_No");
			RoomTypePictureService rtpSvc = new RoomTypePictureService();
			List<RoomTypePictureVO> rtpList= null;
			if("all".equals(room_Type_No)) {
				rtpList = rtpSvc.getAll();
			}else {
				Map<String,String[]> map = new HashMap<>();
				map.put("room_Type_No", new String[] {room_Type_No});
				rtpList = rtpSvc.getAllBy(map);
			}
			JSONArray output = new JSONArray();
			JSONObject obj = null;
			for(RoomTypePictureVO vo : rtpList) {
				obj = new JSONObject();
				try {
					obj.put("room_Type_Picture_No",vo.getroom_Type_Picture_No());
					obj.put("room_Type_No",vo.getRoom_Type_No());
					output.put(obj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			JSONArray output = new JSONArray();
//			for(RoomTypePictureVO vo : rtpList) {
//				output.put(new JSONObject(vo));
//			}
//			output.put
			System.out.println("rtpList = " + rtpList);
			System.out.println("output = " + output);
			
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
//			System.out.println(output);
			out.write(output.toString());
			out.flush();
			out.close();
			
		}
		
		
		
		
		
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			String url = req.getParameter("comeFrom");//判斷刪除是來自於listAll還是依房型顯示
			String url = "/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp";//判斷刪除是來自於listAll還是依房型顯示
//			String room_Type_No = req.getParameter("room_Type_No");
			try {
				/***************************1.接收請求參數***************************************/
				String room_Type_Picture_No = new String(req.getParameter("room_Type_Picture_No"));
				
				/***************************2.開始刪除資料***************************************/
				RoomTypePictureService rtpSvc = new RoomTypePictureService();
				rtpSvc.deleteByRoomTypePictureNo(room_Type_Picture_No);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	
	

//	
//	if("get_Room_Type_For_Display".equals(action)) {
//
//		List<String> errorMsgs = new LinkedList<>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			String room_Type_No = req.getParameter("room_Type_No");
////			String[] room_Type_No = {req.getParameter("room_Type_No")};
//			Map<String, String[]> map = new HashMap<>();
//		try {
////			map.put("room_Type_No", room_Type_No);
//			List<RoomTypePictureVO> list = new ArrayList<>();
//			RoomTypePictureService rtpSvc = new RoomTypePictureService();
//			list = rtpSvc.getAllBy(map);
////			req.setAttribute("list", list);
//			req.setAttribute("room_Type_No", room_Type_No);
//			String url = "/RoomTypePicture/listRoomTypePictureByRoomType.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//		}catch (Exception e) {
//			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req.getRequestDispatcher("/RoomTypePicture/addRoomTypePicture.jsp");
//			failureView.forward(req, res);
//		}
//	}
}
