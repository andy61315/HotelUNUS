package com.roomdate.model;



import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomDateJNDIDAO implements RoomDateDAO_interface{

	private static final String INSERT_STMT = 
			"INSERT INTO room_date (DATE_TIME,ISHOLIDAY) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM room_date";
	private static final String GET_INTERVAL_STMT = 
			"SELECT * FROM room_date where date_time >= ? and date_time < ? order by date_time";
	private static final String GETONE ="SELECT ISHOLIDAY FROM ROOM_DATE where date_time=?";
	private static final String UPDATE ="UPDATE room_date SET ISHOLIDAY=? where date_time=?";
	private static final String DELETE ="DELETE FROM room_date where date_time=?";
	
	private static DataSource ds=null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}
		catch
		(NamingException e){
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void insert(RoomDateVO roomdateVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
	try {
		con=ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);
		
		
		java.sql.Date date1= new java.sql.Date(roomdateVO.getDate_time().getTime());
		pstmt.setDate(1, date1);
		pstmt.setInt(2, roomdateVO.getIsHoliday());
		
	

		 pstmt.executeUpdate();
	}
	catch(SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	}
	finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException sse) {
				sse.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	}

	@Override
	public void update(RoomDateVO roomdateVO) {
		// TODO Auto-generated method stub
		try (
				Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE);){
							
				pstmt.setInt(1, roomdateVO.getIsHoliday());
				pstmt.setDate(2, (java.sql.Date)roomdateVO.getDate_time());
				
				 pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
	}

	@Override
	public void delete(Date roomdate) {
		// TODO Auto-generated method stub
		try (
				Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(DELETE);){
							
				pstmt.setDate(1, (java.sql.Date) roomdate);
				
				 pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
	}

	@Override
	public int findByPrimaryKey(Date roomdate) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		int status=0;
		try (
				Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GETONE);){
			
				java.sql.Date date1= new java.sql.Date(roomdate.getTime());			
				pstmt.setDate(1, date1);
				
				 pstmt.executeUpdate();
				 rs = pstmt.executeQuery();
					while(rs.next()) {
						status= rs.getInt("isHoliday");
					}
				 

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
		return status;
	}

	@Override
	public List<RoomDateVO> getAll() {
		// TODO Auto-generated method stub
		List<RoomDateVO> list = new ArrayList<>();
		ResultSet rs = null;
		try (
				Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);){
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RoomDateVO roomdateVO  =new RoomDateVO();
				roomdateVO.setDate_time(rs.getDate("date_time"));
				roomdateVO.setIsHoliday(rs.getInt("isHoliday"));
			}
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
		return list;
	}
	
	
	@Override
	public List<RoomDateVO> getByInterval(java.sql.Date start_Date,java.sql.Date end_Date) {
		// TODO Auto-generated method stub
		List<RoomDateVO> list = new ArrayList<>();
		ResultSet rs = null;
		try (
				Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_INTERVAL_STMT);){
			pstmt.setDate(1, start_Date);
			pstmt.setDate(2, end_Date);
			rs = pstmt.executeQuery();
			RoomDateVO roomdateVO = null;
			while(rs.next()) {
				roomdateVO  =new RoomDateVO();
				roomdateVO.setDate_time(rs.getDate("date_time"));
				roomdateVO.setIsHoliday(rs.getInt("isHoliday"));
				list.add(roomdateVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		return list;
	}
	
	
}
