package com.bod.model;

import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BookOrderDetailJNDIDAO implements BookOrderDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO BOOKING_ORDER_DETAIL (B_ORDER_NO, ROOM_TYPE_NO, QUANTITY, TOTAL_ADD_PEOPLE, PRICE) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM BOOKING_ORDER_DETAIL order by B_ORDER_NO";
	private static final String GET_ONE_STMT = 
			"SELECT B_ORDER_NO,ROOM_TYPE_NO,QUANTITY,TOTAL_ADD_PEOPLE,PRICE FROM BOOKING_ORDER_DETAIL where B_ORDER_NO = ? and ROOM_TYPE_NO=? ";
	private static final String DELETE = 
			"DELETE FROM BOOKING_ORDER_DETAIL where B_ORDER_NO = ? AND  ROOM_TYPE_NO=?";
	private static final String UPDATE = 
			"UPDATE BOOKING_ORDER_DETAIL set QUANTITY=?, TOTAL_ADD_PEOPLE=?, PRICE=? where B_ORDER_NO = ? and ROOM_TYPE_NO=? ";

	private static final String GET_STMT = 
			"SELECT B_ORDER_NO,ROOM_TYPE_NO,QUANTITY,TOTAL_ADD_PEOPLE,PRICE FROM BOOKING_ORDER_DETAIL where B_ORDER_NO = ?";

	@Override
	public int insert(BookOrderDetailVO bodvo) {
		// TODO Auto-generated method stub
		
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bodvo.getB_order_no());
			pstmt.setString(2, bodvo.getRoom_type_no());
			pstmt.setInt(3,bodvo.getQuantity());
			pstmt.setInt(4, bodvo.getTotal_add_people());
			pstmt.setInt(5,bodvo.getPrice());
		

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;			
	}

	@Override
	public boolean updateBookingData(BookOrderDetailVO bodVo) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE);) {
			
			
			pstmt.setInt(1,bodVo.getQuantity());
			pstmt.setInt(2, bodVo.getTotal_add_people());
			pstmt.setInt(3,bodVo.getPrice());
			pstmt.setString(4, bodVo.getB_order_no());
			pstmt.setString(5, bodVo.getRoom_type_no());
			rowCount = pstmt.executeUpdate();
			System.out.println(bodVo.getB_order_no()+"b");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}


	@Override
	public boolean delete(String bookOrderNo ,String roomTypeNo) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		System.out.println("bookOrderNo = " + bookOrderNo);
		System.out.println("roomTypeNo = " + roomTypeNo);
		try(Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE);) {
			ps.setString(1,bookOrderNo);
			ps.setString(2,roomTypeNo);
			rowCount = ps.executeUpdate();
			System.out.println("rowCount = " + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}	
	
	@Override
	public BookOrderDetailVO findByPrimaryKey(String b_order_no,String room_type_no) {
		// TODO Auto-generated method stub
		BookOrderDetailVO bod= null;
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_ONE_STMT);)
		{
			ps.setString(1, b_order_no);
			ps.setString(2, room_type_no);
			rs = ps.executeQuery();
			while (rs.next()) {
			
				bod = new BookOrderDetailVO();
				bod.setB_order_no(rs.getString("B_ORDER_NO"));
				bod.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
				bod.setQuantity(rs.getInt("quantity"));
				bod.setTotal_add_people(rs.getInt("total_add_people"));
				bod.setPrice(rs.getInt("price"));
				
			}

		}
		
		catch(SQLException e) 
		{e.printStackTrace();}
		
		
		return bod;
	}

	@Override
	public List<BookOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<BookOrderDetailVO> list=new ArrayList<BookOrderDetailVO>();
		ResultSet rs = null;
//		Connection con = null;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);)
		{	
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
			BookOrderDetailVO bodVo=new BookOrderDetailVO();
			bodVo.setB_order_no(rs.getString("b_order_no"));
			bodVo.setRoom_type_no(rs.getString("room_type_no"));
			bodVo.setQuantity(rs.getInt("quantity"));
			bodVo.setTotal_add_people(rs.getInt("total_add_people"));
			bodVo.setPrice(rs.getInt("price"));
			list.add(bodVo);
		}}catch(SQLException e) 
		{e.printStackTrace();}
		return list;
	}
	
	@Override
	public List<BookOrderDetailVO> findByBookOrderNo(String b_order_no) {
		// TODO Auto-generated method stub
		List<BookOrderDetailVO> list=new ArrayList<BookOrderDetailVO>();
		BookOrderDetailVO bod= null;
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_STMT);)
		{
			ps.setString(1, b_order_no);
			
			rs = ps.executeQuery();
			while (rs.next()) {
			
				bod = new BookOrderDetailVO();
				bod.setB_order_no(rs.getString("B_ORDER_NO"));
				bod.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
				bod.setQuantity(rs.getInt("quantity"));
				bod.setTotal_add_people(rs.getInt("total_add_people"));
				bod.setPrice(rs.getInt("price"));
				list.add(bod);
				
			}

		}
		
		catch(SQLException e) 
		{e.printStackTrace();}
		
		return list;
	}	

	private static StringBuffer Magic_Select_Stmt = null;

	@Override
	public List<BookOrderDetailVO> getSearch(Map<String, String[]> map) {
		ResultSet rs = null;
//		BookOrderDetailVO rtVO = null;
//		Connection con = null;
		List<BookOrderDetailVO> list = new ArrayList<>();
		
		Magic_Select_Stmt = new StringBuffer("SELECT * FROM BOOKING_ORDER_DETAIL WHERE ");
		String[] valueArray = null;
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = (String)iterator.next();
			Magic_Select_Stmt.append(key + " in ( ");
			valueArray = map.get(key);
			for(int i = 0; i < valueArray.length; i++) {
				Magic_Select_Stmt.append("\'" + valueArray[i] + "\'" + ((i == valueArray.length-1)?"":","));
			}
			Magic_Select_Stmt.append(") " + (iterator.hasNext()? " and " : ""));
		}
		System.out.println(Magic_Select_Stmt);
	
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(Magic_Select_Stmt.toString());){
		
		rs = pstmt.executeQuery();
		while(rs.next()) {
			BookOrderDetailVO bod=new BookOrderDetailVO();
			bod.setB_order_no(rs.getString("B_ORDER_NO"));
			bod.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
			bod.setQuantity(rs.getInt("quantity"));
			bod.setTotal_add_people(rs.getInt("total_add_people"));
			bod.setPrice(rs.getInt("price"));
			list.add(bod);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}

	@Override
	public void insert2(BookOrderDetailVO bodvo, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bodvo.getB_order_no());
			pstmt.setString(2, bodvo.getRoom_type_no());
			pstmt.setInt(3,bodvo.getQuantity());
			pstmt.setInt(4, bodvo.getTotal_add_people());
			pstmt.setInt(5,bodvo.getPrice());
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if(con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-BookOrderDetailJNDIDAO");
					con.rollback();
				}catch(SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally{
			if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}

	

}	
		