package com.roomtypepicture.model;
import static common.Common.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;


public class RoomTypePictureJDBCDAO implements RoomTypePictureDAO_interface{
	private static final String INSERT_STMT = 
			"INSERT INTO room_type_picture (room_type_picture_no,room_type_no,room_type_pic) "
									+ "VALUES (('RTP'||TO_CHAR(SEQ_ROOM_TYPE_PIC_NO.NEXTVAL,'FM000')),?,?)";
	
	private static final String INSERT_STMT_CLOB = 
			"INSERT INTO room_type_picture (room_type_picture_no,room_type_no,ROOM_TYPE_PIC2) "
					+ "VALUES (?,?,?)";
	
	private static final String DELETE_ROOMTYPE_STMT = 
			"DELETE FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_NO = ?";
	
	private static final String DELETE_ROOMTYPE_NO_STMT = 
			"DELETE FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_PICTURE_NO in (";

	private static final String SELECT_ALL_STMT = 
			"SELECT * FROM ROOM_TYPE_PICTURE;";
	
	public RoomTypePictureJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(RoomTypePictureVO rtpVO) {
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
//			pstmt.setString(1, rtpVO.getroom_Type_Picture_No());
			pstmt.setString(1, rtpVO.getRoom_Type_No());
//			pstmt.setBytes(3, rtpVO.getRoom_type_pic());
			pstmt.setBytes(2, rtpVO.getRoom_Type_Pic());
			
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void insert1(RoomTypePictureVO rtpVO) { // Base64��
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT_CLOB);){
			FileInputStream fis = new FileInputStream("WebContent/logo.png");
			byte[] buf = new byte[fis.available()];
			Base64.Encoder encoder = Base64.getEncoder();
			fis.read(buf);
			String encodedStr = encoder.encodeToString(buf);
			pstmt.setString(1, rtpVO.getRoom_Type_No());
			pstmt.setString(2, rtpVO.getRoom_Type_No());
//			pstmt.setBytes(3, rtpVO.getRoom_type_pic());
			pstmt.setString(3, encodedStr);
			
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public void deleteByRoomTypePictureNo(String RoomTypePictureNo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteByRoomTypeNo(String RoomTypeNo) {//����
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(DELETE_ROOMTYPE_STMT);){
			pstmt.setString(1, RoomTypeNo);
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	

	@Override
	public void deleteByPicNo(List<String> PicNos) {
		// TODO Auto-generated method stub
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
			
			
			StringBuilder stmtStr = new StringBuilder(DELETE_ROOMTYPE_NO_STMT);
			stmtStr.append("?");//��蝚砌�蝑�
			for(int i = 1; i < PicNos.size() ; i++) {
				stmtStr.append(",?");
			}
			stmtStr.append(")");
			PreparedStatement pstmt = connection.prepareStatement(stmtStr.toString());
			for(int i = 0; i < PicNos.size() ; i++) {
				pstmt.setString(i+1, PicNos.get(i));
			}
//			pstmt.setString(1, RoomTypeNo);
			System.out.println(pstmt.executeUpdate());
//			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<RoomTypePictureVO> getAll() {
		List<RoomTypePictureVO> pics = new ArrayList<>();
		RoomTypePictureVO rtpVO = null;
		ResultSet rs = null;
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
			PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rtpVO = new RoomTypePictureVO();
				rtpVO.setroom_Type_Picture_No(rs.getString("ROOM_TYPE_PICTURE_NO"));
				rtpVO.setRoom_Type_No(rs.getString("room_Type_No"));
				rtpVO.setRoom_Type_Pic(rs.getBytes("room_Type_Pic"));
				pics.add(rtpVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return pics;
	}

	public static void main(String[] args) {
		RoomTypePictureJDBCDAO dao = new RoomTypePictureJDBCDAO();
		RoomTypePictureVO rtpVO1 = new RoomTypePictureVO();
		FileInputStream in = null;
		String p = "pic/p";
		String jpg = ".jpg";
		String picNo = "rtp000";
		String roomTypeNo = "RT00";
		try {
			for(int i = 1; i < 76; i ++) {
				if(i >= 10)picNo = "rtp00";
				in = new FileInputStream("c:\\roomtypepic\\" + i + ".jpg");
//				in = new FileInputStream(p + (i%10+1) + jpg);
				byte[] buf = new byte[in.available()];
				in.read(buf);
				rtpVO1.setroom_Type_Picture_No(picNo + i);
//				rtpVO1.setroom_Type	_Picture_No("rtp001");
				
				if((i%14+1)>=10) {
					roomTypeNo = "RT0";
				}else {
					roomTypeNo = "RT00";
					
				}
				System.out.println(roomTypeNo + (i%14+1));
				rtpVO1.setRoom_Type_No(roomTypeNo + (i%14+1));
				rtpVO1.setRoom_Type_Pic(buf);
					
				dao.insert(rtpVO1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		dao.deleteFromRoomTypeNo("RT001");
		
//		String[] del = {new String("rtp005"), 
//						new String("rtp0010"), 
//						new String("rtp0015")};
//		List<String> deleteLs = new ArrayList<>(Arrays.asList(del));
////		dao.deleteByPicNo(deleteLs);
//		
//		RoomTypePictureVO rtpVO2 = new RoomTypePictureVO();
//		rtpVO2.setRoom_Type_No(room_Type_No);
	}

	@Override
	public List<RoomTypePictureVO> getAllBy(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomTypePictureVO findByRoomTypeNo(String RoomTypeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
