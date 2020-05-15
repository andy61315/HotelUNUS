package android.com.room.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.room.model.RoomJNDIDAO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

interface CleanStatus {
	public static final int OUT_OF_ORDER = 0;
	public static final int CLEANED = 1;
	public static final int UNCLEANED = 2;
}

public class RoomServletAndroid extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RoomJNDIDAO roomJNDIDAO = new RoomJNDIDAO();
		List<RoomVO> roomList = roomJNDIDAO.getAll();
		writeText(res, (new Gson()).toJson(roomList));
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RoomService roomService = new RoomService();
		List<RoomVO> roomList = roomService.getAll();
		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null)
			jsonIn.append(line);
		System.out.println("input: " + jsonIn); // example: input:
												// {"action":"updateCleanStatusToCleaned","room_no":"109"}

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if ("getUncleanedRoom".equals(action)) {
			List<RoomVO> uncleanedRoom = getUncleanedRoom(roomList);
			writeText(res, gson.toJson(uncleanedRoom));

		} else if ("getDoneRoom".equals(action)) {
			List<RoomVO> doneRoom = getDoneRoom(roomList);
			writeText(res, gson.toJson(doneRoom));

		} else if ("updateCleanStatusToOutOfOrder".equals(action)) {
			String room_no = jsonObject.get("room_no").getAsString();
			System.out.println("room_no: " + room_no);

			roomService.updateRoomCleanStatus(room_no, CleanStatus.OUT_OF_ORDER);
			System.out.println("updateCleanStatusToOutOfOrder: done");

		} else if ("updateCleanStatusToCleaned".equals(action)) {
			String room_no = jsonObject.get("room_no").getAsString();
			System.out.println("room_no: " + room_no);

			roomService.updateRoomCleanStatus(room_no, CleanStatus.CLEANED);
			System.out.println("updateCleanStatusToCleaned: done");

		} else if ("updateCleanStatusToUncleaned".equals(action)) {
			String room_no = jsonObject.get("room_no").getAsString();
			System.out.println("room_no: " + room_no);

			roomService.updateRoomCleanStatus(room_no, CleanStatus.UNCLEANED);
			System.out.println("updateCleanStatusToUncleaned: done");

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

	private List<RoomVO> getUncleanedRoom(List<RoomVO> roomList) {
		List<RoomVO> list = new ArrayList<>();
		for (int i = 0; i < roomList.size(); i++) {
			RoomVO room = roomList.get(i);
			if (room.getClean_status().intValue() == CleanStatus.UNCLEANED)
				list.add(room);
		}
		return list;
	}

	private List<RoomVO> getDoneRoom(List<RoomVO> roomList) {
		List<RoomVO> list = new ArrayList<>();
		for (int i = 0; i < roomList.size(); i++) {
			RoomVO room = roomList.get(i);
			if (room.getClean_status() == CleanStatus.CLEANED || room.getClean_status() == CleanStatus.OUT_OF_ORDER)
				list.add(room);
		}
		return list;
	}
}
