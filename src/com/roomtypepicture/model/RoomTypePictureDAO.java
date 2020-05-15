package com.roomtypepicture.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.roomtype.model.RoomTypeVO;


public class RoomTypePictureDAO  implements RoomTypePictureDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO room_type_picture (room_type_picture_no,room_type_no,room_type_pic) "
									+ "VALUES (('RTP'||TO_CHAR(SEQ_ROOM_TYPE_PIC_NO.NEXTVAL,'FM000')),?,?)";
	
	private static final String DELETE_ROOMTYPE_STMT = 
			"DELETE FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_NO = ?";
	
	private static final String DELETE_ROOMTYPE_PICTURE_STMT = 
			"DELETE FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_PICTURE_NO = ?";
	
	private static final String DELETE_ROOMTYPE_NO_STMT = 
			"DELETE FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_PICTURE_NO in (";
	
	private static final String SELECT_SINGLE_STMT = 
			"SELECT room_type_picture_no,room_type_no FROM ROOM_TYPE_PICTURE WHERE ROOM_TYPE_PICTURE_NO = ?";
	
	private static final String SELECT_ALL_STMT = 
			"SELECT room_type_picture_no,room_type_no FROM ROOM_TYPE_PICTURE order by ROOM_TYPE_PICTURE_NO";
	
	@Override
	public void insert(RoomTypePictureVO rtpVO) {
		try(Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
//			pstmt.setString(1, rtpVO.getRoom_Type_No());//用流水號輸入
			pstmt.setString(1, rtpVO.getRoom_Type_No());
//			pstmt.setBytes(3, rtpVO.getRoom_type_pic());
			pstmt.setBytes(2, rtpVO.getRoom_Type_Pic());
			
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public RoomTypePictureVO findByRoomTypeNo(String RoomTypeNo) {
		RoomTypePictureVO vo = new RoomTypePictureVO();
		ResultSet rs = null;
		try(Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_SINGLE_STMT);){
			pstmt.setString(1, RoomTypeNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new RoomTypePictureVO();
				vo.setroom_Type_Picture_No(rs.getString("ROOM_TYPE_PICTURE_NO"));
				vo.setRoom_Type_No(rs.getString("room_Type_No"));
				
			}
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	
	@Override
	public void deleteByRoomTypeNo(String RoomTypeNo) {//房型
		try(Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_ROOMTYPE_STMT);){
			pstmt.setString(1, RoomTypeNo);
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}@Override
	public void deleteByRoomTypePictureNo(String RoomTypePictureNo) {//房型
		try(Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_ROOMTYPE_PICTURE_STMT);){
			pstmt.setString(1, RoomTypePictureNo);
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	

	@Override
	public void deleteByPicNo(List<String> PicNos) {
		// TODO Auto-generated method stub
		try(Connection connection = ds.getConnection();){
			
			
			StringBuilder stmtStr = new StringBuilder(DELETE_ROOMTYPE_NO_STMT);
			stmtStr.append("?");//先放第一筆
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
	public List<RoomTypePictureVO> getAllBy(Map<String, String[]> map) {
		List<RoomTypePictureVO> pics = new ArrayList<>();
		RoomTypePictureVO rtpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer Magic_Select_Stmt = new StringBuffer("SELECT * FROM ROOM_TYPE_PICTURE ");
		String[] valueArray = null;
		Iterator<String> iterator = map.keySet().iterator();
		if(iterator.hasNext()) {
//			boolean ifHasCondition = true;//用來加where
			//
			Magic_Select_Stmt.append(" WHERE ");
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Magic_Select_Stmt.append(key + " in ( ");
				valueArray = map.get(key);
				for (int i = 0; i < valueArray.length; i++) {
					Magic_Select_Stmt.append("\'" + valueArray[i] + "\'" + ((i == valueArray.length - 1) ? "" : ","));
				}
				Magic_Select_Stmt.append(") " + (iterator.hasNext() ? " and " : ""));
			}
			Magic_Select_Stmt.append("order by ROOM_TYPE_PICTURE_NO");
		}
//		System.out.println(Magic_Select_Stmt);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Magic_Select_Stmt.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rtpVO = new RoomTypePictureVO();
				rtpVO.setroom_Type_Picture_No(rs.getString("ROOM_TYPE_PICTURE_NO"));
				rtpVO.setRoom_Type_No(rs.getString("room_Type_No"));
//				rtpVO.setRoom_Type_Pic(rs.getBytes("room_Type_Pic"));
				pics.add(rtpVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pics;
	}
	@Override
	public List<RoomTypePictureVO> getAll() {
		List<RoomTypePictureVO> pics = new ArrayList<>();
		RoomTypePictureVO rtpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rtpVO = new RoomTypePictureVO();
				rtpVO.setroom_Type_Picture_No(rs.getString("ROOM_TYPE_PICTURE_NO"));
				rtpVO.setRoom_Type_No(rs.getString("room_Type_No"));
//				rtpVO.setRoom_Type_Pic(rs.getBytes("room_Type_Pic"));
				pics.add(rtpVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pics;
	}
}
