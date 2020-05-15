package com.bod.model;
import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.Common.*;


public class BookOrderDetailJDBCDAO implements BookOrderDetailDAO_interface {

	private static final String INSERT_STMT = 
			"INSERT INTO BOOKING_ORDER_DETAIL (B_ORDER_NO, ROOM_TYPE_NO, QUANTITY, TOTAL_ADD_PEOPLE, PRICE) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT B_ORDER_NO,ROOM_TYPE_NO,QUANTITY,TOTAL_ADD_PEOPLE,PRICE FROM BOOKING_ORDER_DETAIL order by B_ORDER_NO";
	private static final String GET_ONE_STMT = 
			"SELECT B_ORDER_NO,ROOM_TYPE_NO,QUANTITY,TOTAL_ADD_PEOPLE,PRICE FROM BOOKING_ORDER_DETAIL where B_ORDER_NO = ? and room_type_no=?";
	private static final String DELETE = 
			"DELETE FROM BOOKING_ORDER_DETAIL where B_ORDER_NO = ? ";
	private static final String UPDATE = 
			"UPDATE BOOKING_ORDER_DETAIL set ROOM_TYPE_NO=?, QUANTITY=?, TOTAL_ADD_PEOPLE=?, PRICE=? where B_ORDER_NO = ?";
	private static final String GET_STMT = 
			"SELECT B_ORDER_NO,ROOM_TYPE_NO,QUANTITY,TOTAL_ADD_PEOPLE,PRICE FROM BOOKING_ORDER_DETAIL where B_ORDER_NO = ?";

	
	
	public BookOrderDetailJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public int insert(BookOrderDetailVO bodvo) {
		// TODO Auto-generated method stub
		
		int updateCount = 0;
//		Connection con = null;

		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
			
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
			// Clean up JDBC resources
		} 
		return updateCount;			
	}

	@Override
	public void insert2(BookOrderDetailVO bodvo , Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
//		Connection con = null;

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
					System.err.println("rolled back-由-emp");
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
	
	@Override
	public boolean updateBookingData(BookOrderDetailVO bodvo) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATE);) {
			
			pstmt.setString(1, bodvo.getRoom_type_no());
			pstmt.setInt(2,bodvo.getQuantity());
			pstmt.setInt(3, bodvo.getTotal_add_people());
			pstmt.setInt(4,bodvo.getPrice());
			pstmt.setString(5, bodvo.getB_order_no());
			
			rowCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}


	@Override
	public boolean delete(String bookOrderNo ,String roomTypeNo) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(DELETE);) {
			ps.setString(1,bookOrderNo);
			ps.setString(2,roomTypeNo);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}	
	
	@Override
	public BookOrderDetailVO findByPrimaryKey(String b_order_no, String room_type_no) {
		// TODO Auto-generated method stub
		BookOrderDetailVO bod= null;
		ResultSet rs = null;
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = connection.prepareStatement(GET_ONE_STMT);)
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
	public  List<BookOrderDetailVO> findByBookOrderNo(String b_order_no) {
		// TODO Auto-generated method stub
		BookOrderDetailVO bod= null;
		List<BookOrderDetailVO> list=new ArrayList<BookOrderDetailVO>();
		ResultSet rs = null;
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); 
			PreparedStatement ps = connection.prepareStatement(GET_STMT);)
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

	@Override
	public List<BookOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<BookOrderDetailVO> list=new ArrayList<BookOrderDetailVO>();
		ResultSet rs = null;
	
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);)
		{	
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
			BookOrderDetailVO bod=new BookOrderDetailVO();
			bod.setB_order_no(rs.getString("B_ORDER_NO"));
			bod.setRoom_type_no(rs.getString("ROOM_TYPE_NO"));
			bod.setQuantity(rs.getInt("quantity"));
			bod.setTotal_add_people(rs.getInt("total_add_people"));
			bod.setPrice(rs.getInt("price"));
			list.add(bod);
		}}catch(SQLException e) 
		{e.printStackTrace();}
		return list;
	}


	private static StringBuffer Magic_Select_Stmt = null;

	@Override
	public List<BookOrderDetailVO> getSearch(Map<String, String[]> map) {
		ResultSet rs = null;
//		BookOrderDetailVO rtVO = null;
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
	
	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
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

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
////INSERT
		BookOrderDetailJDBCDAO bod=new BookOrderDetailJDBCDAO();
//		BookOrderDetailVO bodvo = new BookOrderDetailVO();
//		
//		bodvo.setB_order_no("BM-20200321-021");
//		bodvo.setRoom_type_no("RT001");
//		bodvo.setPrice(1);
//		bodvo.setQuantity(2);
//		bodvo.setTotal_add_people(1);
//		
//		int count = bod.insert(bodvo);
//		System.out.println(count);
//		
//		
////updateBookingData	
//
//		BookOrderDetailVO bodvo2 = new BookOrderDetailVO();
//		
//		bodvo2.setB_order_no("BM-20200321-020");
//		bodvo2.setRoom_type_no("RT001");
//		bodvo2.setPrice(4376);
//		bodvo2.setQuantity(2);
//		bodvo2.setTotal_add_people(1);
//		
//		System.out.println(bod.updateBookingData(bodvo2));
//		
////delete	
//		BookOrderDetailVO bodvo3 = new BookOrderDetailVO();
//		
//		bodvo3.setB_order_no("BM-20200321-003");
//		
//		System.out.println(bod.delete(bodvo3));

//findByPrimaryKey
//		String bodvo4 = "BM-20200321-019";
//		
//		System.out.println(bod.findByPrimaryKey(bodvo4));
//		
//		System.out.println("");
		
//		Map<String, String[]> map = new HashMap<>();
//		String[] rtn = new String[]{new String("BM-20200321-004")};
//		String[] rtName = new String[]{new String("RT004")};
//		map.put("B_ORDER_NO", rtn);
//		map.put("ROOM_TYPE_NO", rtName);
//		List<BookOrderDetailVO> list1 =bod.getSearch(map);
//		System.out.println(map);
//		for(BookOrderDetailVO vo : list1) {
//			System.out.println( "1" + vo );
//		}
	
	
	
	System.out.println(bod.findByBookOrderNo("BM-20200321-003"));
	}

		
//		List<BookOrderDetailVO> list = 	bod.getAll();
//		for (BookOrderDetailVO bodvo5 : list) {
//			System.out.print(bodvo5.getB_order_no() + ",");
//			System.out.print(bodvo5.getRoom_type_no() + ",");
//			System.out.print(bodvo5.getQuantity() + ",");
//			System.out.print(bodvo5.getTotal_add_people() + ",");
//			System.out.print(bodvo5.getPrice()+"\n");
//			System.out.println("-----------------------------");
//		}


}


	