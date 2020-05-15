package com.roommealorderdetail.model;

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
import java.util.List;



public class RoomMealOrderDetailJDBCDAO implements RoomMealOrderDetailDAO{

	@Override
	public List<RoomMealOrderDetailVO> findAll() {
		List<RoomMealOrderDetailVO> roomMealOrderDetails = new ArrayList<RoomMealOrderDetailVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, MEAL_NO, QUANTITY, PRICE FROM ROOM_MEAL_ORDER_DETAIL";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RoomMealOrderDetailVO roomMealOrderDetail = new RoomMealOrderDetailVO();
				
				roomMealOrderDetail.setRoom_meal_order_no(rs.getString("ROOM_MEAL_ORDER_NO"));
				roomMealOrderDetail.setMeal_no(rs.getString("MEAL_NO"));
				roomMealOrderDetail.setQuantity(rs.getInt("QUANTITY"));
				roomMealOrderDetail.setPrice(rs.getInt("PRICE"));
				
				roomMealOrderDetails.add(roomMealOrderDetail);
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return roomMealOrderDetails;
	}

	@Override
	public void delete(String room_meal_order_no) {
		String sql = "DELETE FROM ROOM_MEAL_ORDER_DETAIL WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, room_meal_order_no);
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException qe) {
			qe.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void insertWithConnection(RoomMealOrderDetailVO roomMealOrderDetailVO, Connection conn) {
		String sql = "INSERT INTO ROOM_MEAL_ORDER_DETAIL(ROOM_MEAL_ORDER_NO, MEAL_NO, PRICE, QUANTITY) " + 
				"?, ?, ?, ?)";
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// price 要先透過 meal_no 從 MealDAO 的 findPrice() 方法中獲得
			// 在 controller 層中包到 RoomMealOrderDetail 類別中取出使用
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomMealOrderDetailVO.getRoom_meal_order_no());
			stmt.setString(2, roomMealOrderDetailVO.getMeal_no());
			stmt.setInt(3, roomMealOrderDetailVO.getPrice());
			stmt.setInt(4, roomMealOrderDetailVO.getQuantity());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public List<RoomMealOrderDetailVO> findByOrderNo(String room_meal_order_no) {
		List<RoomMealOrderDetailVO> roomMealOrderDetails = new ArrayList<RoomMealOrderDetailVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, MEAL_NO, QUANTITY, PRICE FROM ROOM_MEAL_ORDER_DETAIL " +
				"WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, room_meal_order_no);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RoomMealOrderDetailVO roomMealOrderDetail = new RoomMealOrderDetailVO();
				
				roomMealOrderDetail.setRoom_meal_order_no(rs.getString("ROOM_MEAL_ORDER_NO"));
				roomMealOrderDetail.setMeal_no(rs.getString("MEAL_NO"));
				roomMealOrderDetail.setQuantity(rs.getInt("QUANTITY"));
				roomMealOrderDetail.setPrice(rs.getInt("PRICE"));
				
				roomMealOrderDetails.add(roomMealOrderDetail);
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return roomMealOrderDetails;
	}

	@Override
	public List<RoomMealOrderDetailVO> findByMealNo(String meal_no) {
		List<RoomMealOrderDetailVO> roomMealOrderDetails = new ArrayList<RoomMealOrderDetailVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, MEAL_NO, QUANTITY, PRICE FROM ROOM_MEAL_ORDER_DETAIL " +
				"WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, meal_no);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RoomMealOrderDetailVO roomMealOrderDetail = new RoomMealOrderDetailVO();
				
				roomMealOrderDetail.setRoom_meal_order_no(rs.getString("ROOM_MEAL_ORDER_NO"));
				roomMealOrderDetail.setMeal_no(rs.getString("MEAL_NO"));
				roomMealOrderDetail.setQuantity(rs.getInt("QUANTITY"));
				roomMealOrderDetail.setPrice(rs.getInt("PRICE"));
				
				roomMealOrderDetails.add(roomMealOrderDetail);
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return roomMealOrderDetails;
	}

	@Override
	public void insert(RoomMealOrderDetailVO roomMealorderdetail) {
		String sql = "INSERT INTO ROOM_MEAL_ORDER_DETAIL(ROOM_MEAL_ORDER_NO, MEAL_NO, PRICE, QUANTITY) " + 
				"?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// price 要先透過 meal_no 從 MealDAO 的 findPrice() 方法中獲得
			// 在 controller 層中包到 RoomMealOrderDetail 類別中取出使用
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomMealorderdetail.getRoom_meal_order_no());
			stmt.setString(2, roomMealorderdetail.getMeal_no());
			stmt.setInt(3, roomMealorderdetail.getPrice());
			stmt.setInt(4, roomMealorderdetail.getQuantity());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(RoomMealOrderDetailVO roomMealorderdetail) {
		String sql = "UPDATE ROOM_MEAL_ORDER_DETAIL SET QUANTITY=? "
				+ "WHERE ROOM_MEAL_ORDER_NO=? AND MEAL_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, roomMealorderdetail.getQuantity());
			stmt.setString(2, roomMealorderdetail.getRoom_meal_order_no());
			stmt.setString(3, roomMealorderdetail.getMeal_no());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException qe) {
			qe.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(RoomMealOrderDetailVO roomMealorderdetail) {
		String sql = "DELETE FROM ROOM_MEAL_ORDER_DETAIL WHERE ROOM_MEAL_ORDER_NO=? AND MEAL_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomMealorderdetail.getRoom_meal_order_no());
			stmt.setString(2, roomMealorderdetail.getMeal_no());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException qe) {
			qe.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		RoomMealOrderDetailJDBCDAO dao = new RoomMealOrderDetailJDBCDAO();
//		RoomMealOrderDetailVO roomMealOrderDetail = new RoomMealOrderDetailVO();
//		String room_meal_order_no1 = "ROM-20200322-003";
//		String meal_no1 = "M0001";
//		Integer price1 = new Integer(100);
//		Integer quantity1 = new Integer(50);
//		
//		roomMealOrderDetail.setRoom_meal_order_no(room_meal_order_no1);
//		roomMealOrderDetail.setMeal_no(meal_no1);
//		roomMealOrderDetail.setPrice(price1);
//		roomMealOrderDetail.setQuantity(quantity1);
//		
//		dao.update(roomMealOrderDetail);
		
		String room_meal_order_no1 = "ROM-20200415-022";
		String meal_no1 = "M0024";
		Integer quantity1 = new Integer(50);
//		List<RoomMealOrderDetailVO> list = dao.findByMealNo(meal_no1);
//		List<RoomMealOrderDetailVO> list = dao.findByOrderNo(room_meal_order_no1);
		
//		List<RoomMealOrderDetailVO> list = dao.findByMealNo(room_meal_order_no1);
		
		RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();
//		List<RoomMealOrderDetailVO> list = roomMealOrderDetailSvc.getRoomMealOrderDetailByOrderNo(room_meal_order_no1);
//		
////		RoomMealOrderDetailVO roomMealOrderDetailVO = dao.findByMealNo(meal_no);
//		
//		for (RoomMealOrderDetailVO roomMealOrderDetailVO : list) {
//			String room_meal_order_no = roomMealOrderDetailVO.getRoom_meal_order_no();
//			String meal_no = roomMealOrderDetailVO.getMeal_no();
//			Integer price = roomMealOrderDetailVO.getPrice();
//			Integer quantity = roomMealOrderDetailVO.getQuantity();
//			
//			System.out.println("{" + room_meal_order_no + ", " + meal_no + ", " + price + ", " + quantity + "}");
//		}
		
		roomMealOrderDetailSvc.updateQuantity(room_meal_order_no1, meal_no1, quantity1);
		
	}
	
	
	
	
}
