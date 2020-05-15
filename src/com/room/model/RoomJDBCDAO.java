package com.room.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.bod.model.BookOrderDetailJDBCDAO;
import com.bod.model.BookOrderDetailVO;


public class RoomJDBCDAO implements RoomDAO_interface{
	private static final String INSERT_STMT = 
			"INSERT INTO room (room_id, ROOM_TYPE_NO,room_no,room_status,clean_status) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT room_id,ROOM_TYPE_NO,room_no,room_status,clean_status FROM room ";
	private static final String UPDATE = 
			"UPDATE room set ROOM_TYPE_NO=?, room_no=?, room_status=? ,clean_status=? where room_id = ?";
	

	
	
	public RoomJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int insert(RoomVO roomVo) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		Connection con = null;

		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
			
			pstmt.setString(1, roomVo.getRoom_id());
			pstmt.setString(2, roomVo.getRoom_type_no());
			pstmt.setString(3,roomVo.getRoom_no());
			pstmt.setInt(4, roomVo.getRoom_status());
			pstmt.setInt(5,roomVo.getClean_status());
		

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		return updateCount;
	}

	public void updateRoomData(RoomVO roomVo) {
		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);)
		{
			pstmt.setString(1,roomVo.getRoom_type_no());
			pstmt.setString(2, roomVo.getRoom_no());
			pstmt.setInt(3,roomVo.getRoom_status());
			pstmt.setInt(4, roomVo.getClean_status());
			pstmt.setString(5,roomVo.getCus_id());
			pstmt.setString(6,roomVo.getTenant_name());
			pstmt.setString(7,roomVo.getTenant_phone());
			pstmt.setString(8,roomVo.getRoom_id());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} 
		
	}


	@Override
	public List<RoomVO> getAll() {
		// TODO Auto-generated method stub
		List<RoomVO> list=new ArrayList<RoomVO>();
		ResultSet rs = null;
	
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);)
		{	
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				RoomVO room=new RoomVO();
				room.setRoom_id(rs.getString("room_id"));
				room.setRoom_type_no(rs.getString("room_type_no"));
				room.setRoom_no(rs.getString("room_no"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setClean_status(rs.getInt("clean_status"));
				list.add(room);
		}}catch(SQLException e) 
		{e.printStackTrace();}
		return list;
	}

	private static StringBuffer magic_select_stmt=null;
	@Override
	public List<RoomVO> getSearch(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List<RoomVO> list = new ArrayList<>();
		magic_select_stmt = new StringBuffer("SELECT * FROM ROOM WHERE ");
		String[] valueArray = null;
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key =(String) iterator.next();
			magic_select_stmt.append(key+" in (");
			valueArray = map.get(key);
			for(int i = 0; i < valueArray.length; i++) {
				magic_select_stmt.append("\'" + valueArray[i] + "\'" + ((i == valueArray.length-1)?"":","));
			}
			magic_select_stmt.append(") " + (iterator.hasNext()? " and " : ""));
		}
		System.out.println(magic_select_stmt);	
		
		try(Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(magic_select_stmt.toString());){
			
		rs = pstmt.executeQuery();
		while(rs.next()) {
			RoomVO room=new RoomVO();
			room.setRoom_id(rs.getString("room_id"));
			room.setRoom_type_no(rs.getString("room_type_no"));
			room.setRoom_no(rs.getString("room_no"));
			room.setRoom_status(rs.getInt("room_status"));
			room.setClean_status(rs.getInt("clean_status"));
//			room.setCus_id(rs.getString("cus_id"));
//			room.setTenant_name(rs.getString("tenant_name"));
//			room.setTenant_phone(rs.getString("tenant_phone"));
//			
			list.add(room);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
		
}
	
	
	public static void main(String[] args) {
	
			RoomJDBCDAO room=new RoomJDBCDAO();
			RoomVO rom = new RoomVO();
			
//RM015	RT004	509	0	1					
			rom.setRoom_id("RM015");
			rom.setRoom_type_no("RT001");
			rom.setRoom_no("510");
			rom.setRoom_status(1);
			rom.setClean_status(1);
////INSERT			
//			int count = room.insert(rom);
//			System.out.println(count);
			
////updateRoomData
//			boolean count1 = room.updateRoomData(rom);
//			System.out.println(count1);
			
			
////Magic_Select
//			Map<String, String[]> map = new HashMap<>();
//			String[] rtn = new String[]{new String("RT004")};
//			String[] rtName = new String[]{new String("1")};
//			map.put("room_type_no", rtn);
//			map.put("clean_status", rtName);
//			List<RoomVO> list1 =room.getSreach(map);
//			System.out.println(map);
//			for(RoomVO vo : list1) {
//				System.out.println( "1" + vo );
//			}
			
//getAll
			
			List<RoomVO> list = room.getAll();
			for(RoomVO roomAll : list) {
				System.out.print(roomAll.getRoom_id() + ",");
				System.out.print(roomAll.getRoom_type_no() + ",");
				System.out.print(roomAll.getRoom_no() + ",");
				System.out.print(roomAll.getRoom_status() + ",");
				System.out.print(roomAll.getClean_status()+"\n");
				System.out.println("-----------------------------");
			}
	}

	@Override
	public RoomVO findByPrimaryKey(String room_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRoomStatus(RoomVO roomVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoomCleanStatus(RoomVO roomVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoomCus(RoomVO roomVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoomVO> getRoomByCount(String room_type_no, String b_order_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomVO> getRoomByBorderNo(String b_order_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ofShellRoomStatus(String room_Type_No) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOut(List<String> roomNoList) {
		// TODO Auto-generated method stub
		
	}



}
