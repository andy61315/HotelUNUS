package com.roommealordermaster.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.meal.model.MealVO;
import com.roommealorderdetail.model.RoomMealOrderDetailJNDIDAO;
import com.roommealorderdetail.model.RoomMealOrderDetailVO;

import common.jdbcUtil_CompositeQuery_RoomMealOM;
import oracle.net.aso.r;

public class RoomMealOrderMasterJDBCDAO implements RoomMealOrderMasterDAO{

	private void populate(List<RoomMealOrderMasterVO> roomMealOrderMasters, ResultSet rs) throws SQLException{
		RoomMealOrderMasterVO roomMealOrderMaster = new RoomMealOrderMasterVO();
		
		roomMealOrderMaster.setRoom_meal_order_no(rs.getString("ROOM_MEAL_ORDER_NO"));
		roomMealOrderMaster.setB_order_no(rs.getString("B_ORDER_NO"));
		roomMealOrderMaster.setRoom_no(rs.getNString("ROOM_NO"));
		roomMealOrderMaster.setEmp_id(rs.getString("EMP_ID"));
		roomMealOrderMaster.setTotal_price(rs.getInt("TOTAL_PRICE"));
		roomMealOrderMaster.setSpecial_requirement(rs.getString("SPECIAL_REQUIREMENT"));
		roomMealOrderMaster.setRo_order_status(rs.getInt("RO_ORDER_STATUS"));
		roomMealOrderMaster.setOrder_date(rs.getDate("ORDER_DATE"));
		
		roomMealOrderMasters.add(roomMealOrderMaster);
	}
	
	
	@Override
	public void orderTotalUpdate(Integer total, String pk) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void statusUpdate(Integer status, String pk) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(String pk) {
		String sqlOrderMaster = "DELETE FROM ROOM_MEAL_ORDER_MASTER WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// temp 代處理
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sqlOrderMaster);
			stmt.setString(1, pk);
			
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
	public List<RoomMealOrderMasterVO> findAll(Map<String, String[]> map) {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT * FROM ROOM_MEAL_ORDER_MASTER "
				+ jdbcUtil_CompositeQuery_RoomMealOM.get_WhereCondition(map)
				+ "order by ROOM_MEAL_ORDER_NO";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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
		
		return roomMealOrderMasters;
	}


	@Override
	public List<RoomMealOrderMasterVO> findAll() {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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
		
		return roomMealOrderMasters;
	}

	@Override
	public List<RoomMealOrderMasterVO> findCookingOrder() {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER WHERE RO_ORDER_STATUS=0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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
		
		return roomMealOrderMasters;
	}

	@Override
	public List<RoomMealOrderMasterVO> findShippingOrder() {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER WHERE RO_ORDER_STATUS=1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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

		return roomMealOrderMasters;
	}

	@Override
	public List<RoomMealOrderMasterVO> findShippedOrder() {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER WHERE RO_ORDER_STATUS=2";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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

		return roomMealOrderMasters;
	}
	
	@Override
	public List<RoomMealOrderMasterVO> findClosedOrder() {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER WHERE RO_ORDER_STATUS=3";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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

		return roomMealOrderMasters;
	}

	@Override
	public List<RoomMealOrderMasterVO> findCancelOrder() {
		List<RoomMealOrderMasterVO> roomMealOrderMasters = new ArrayList<RoomMealOrderMasterVO>();
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER WHERE RO_ORDER_STATUS=4";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(roomMealOrderMasters, rs);
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

		return roomMealOrderMasters;
	}
	
	@Override
	public RoomMealOrderMasterVO findByPk(String pk) {
		RoomMealOrderMasterVO roomMealOrderMaster = null;
		String sql = "SELECT ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, EMP_ID, TOTAL_PRICE, " +
				"SPECIAL_REQUIREMENT, RO_ORDER_STATUS, ORDER_DATE FROM ROOM_MEAL_ORDER_MASTER WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				roomMealOrderMaster = new RoomMealOrderMasterVO();
				
				roomMealOrderMaster.setRoom_meal_order_no(rs.getString("ROOM_MEAL_ORDER_NO"));
				roomMealOrderMaster.setB_order_no(rs.getString("B_ORDER_NO"));
				roomMealOrderMaster.setRoom_no(rs.getNString("ROOM_NO"));
				roomMealOrderMaster.setEmp_id(rs.getString("EMP_ID"));
				roomMealOrderMaster.setTotal_price(rs.getInt("TOTAL_PRICE"));
				roomMealOrderMaster.setSpecial_requirement(rs.getString("SPECIAL_REQUIREMENT"));
				roomMealOrderMaster.setRo_order_status(rs.getInt("RO_ORDER_STATUS"));
				roomMealOrderMaster.setOrder_date(rs.getDate("ORDER_DATE"));
				
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

		return roomMealOrderMaster;
	}

	@Override
	public void insert(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sql = "INSERT INTO ROOM_MEAL_ORDER_MASTER(ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, ORDER_DATE, "
				+ "RO_ORDER_STATUS, SPECIAL_REQUIREMENT) " + 
				"VALUES('ROM-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_ROOM_MEAL_ORDER_NO.NEXTVAL,'FM000'), " + 
				"?, ?, ?, 0, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomMealOrderMaster.getB_order_no());
			stmt.setString(2, roomMealOrderMaster.getRoom_no());
			stmt.setDate(3, sqlDate);
			stmt.setString(4, roomMealOrderMaster.getSpecial_requirement());
			
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
	public void insertWithOrderDetail(RoomMealOrderMasterVO roomMealOrderMasterVO, List<Map<String, Object>> cart) {
		String sql = "INSERT INTO ROOM_MEAL_ORDER_MASTER(ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, ORDER_DATE, "
				+ "RO_ORDER_STATUS, SPECIAL_REQUIREMENT) " + 
				"VALUES('ROM-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_ROOM_MEAL_ORDER_NO.NEXTVAL,'FM000'), " + 
				"?, ?, ?, 0, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			conn.setAutoCommit(false);
			
			String cols[] = {"ROOM_MEAL_ORDER_NO"};
			stmt = conn.prepareStatement(sql, cols);
			stmt.setString(1, roomMealOrderMasterVO.getB_order_no());
			stmt.setString(2, roomMealOrderMasterVO.getRoom_no());
			stmt.setDate(3, sqlDate);
			stmt.setString(4, roomMealOrderMasterVO.getSpecial_requirement());
			
			stmt.executeUpdate();
			
			String next_room_meal_order_no = null;
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				next_room_meal_order_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_room_meal_order_no +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			RoomMealOrderDetailJNDIDAO dao = new RoomMealOrderDetailJNDIDAO();
			System.out.println("list.size()-A="+cart.size());
			
			for (Map<String, Object> item : cart) {
				RoomMealOrderDetailVO aRoomMealOrderDetailVO = new RoomMealOrderDetailVO();
				String meal_no = (String) item.get("meal_no");
				Integer price = (Integer) item.get("price");
				Integer quantity = (Integer) item.get("quantity");
				
				aRoomMealOrderDetailVO.setRoom_meal_order_no(next_room_meal_order_no);
				aRoomMealOrderDetailVO.setMeal_no(meal_no);
				aRoomMealOrderDetailVO.setPrice(price);
				aRoomMealOrderDetailVO.setQuantity(quantity);
				
				dao.insertWithConnection(aRoomMealOrderDetailVO, conn);
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
				
	}


	@Override
	public void insertWithOrderDetailAndTotal(RoomMealOrderMasterVO roomMealOrderMasterVO,
			List<Map<String, Object>> cart) {
		String sql = "INSERT INTO ROOM_MEAL_ORDER_MASTER(ROOM_MEAL_ORDER_NO, B_ORDER_NO, ROOM_NO, ORDER_DATE, "
				+ "RO_ORDER_STATUS, SPECIAL_REQUIREMENT, TOTAL_PRICE) " + 
				"VALUES('ROM-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_ROOM_MEAL_ORDER_NO.NEXTVAL,'FM000'), " + 
				"?, ?, ?, 0, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			conn.setAutoCommit(false);
			
			String cols[] = {"ROOM_MEAL_ORDER_NO"};
			stmt = conn.prepareStatement(sql, cols);
			stmt.setString(1, roomMealOrderMasterVO.getB_order_no());
			stmt.setString(2, roomMealOrderMasterVO.getRoom_no());
			stmt.setDate(3, sqlDate);
			stmt.setString(4, roomMealOrderMasterVO.getSpecial_requirement());
			stmt.setInt(5, roomMealOrderMasterVO.getTotal_price());
			
			stmt.executeUpdate();
			
			String next_room_meal_order_no = null;
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				next_room_meal_order_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_room_meal_order_no +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			RoomMealOrderDetailJNDIDAO dao = new RoomMealOrderDetailJNDIDAO();
			System.out.println("list.size()-A="+cart.size());
			
			for (Map<String, Object> item : cart) {
				RoomMealOrderDetailVO aRoomMealOrderDetailVO = new RoomMealOrderDetailVO();
				String meal_no = (String) item.get("meal_no");
				Integer price = (Integer) item.get("price");
				Integer quantity = (Integer) item.get("quantity");
				
				aRoomMealOrderDetailVO.setRoom_meal_order_no(next_room_meal_order_no);
				aRoomMealOrderDetailVO.setMeal_no(meal_no);
				aRoomMealOrderDetailVO.setPrice(price);
				aRoomMealOrderDetailVO.setQuantity(quantity);
				
				dao.insertWithConnection(aRoomMealOrderDetailVO, conn);
			}
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}


	@Override
	public void update(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sql = "UPDATE ROOM_MEAL_ORDER_MASTER SET B_ORDER_NO=?, ROOM_NO=?, "
				+ "SPECIAL_REQUIREMENT=?, TOTAL_PRICE=? ORDER_DATE=?, RO_ORDER_STATUS=?, EMP_ID=? "
				+ "WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomMealOrderMaster.getB_order_no());
			stmt.setString(2, roomMealOrderMaster.getRoom_no());
			stmt.setString(3, roomMealOrderMaster.getSpecial_requirement());
			stmt.setInt(4, roomMealOrderMaster.getTotal_price());
			stmt.setDate(5, sqlDate);
			stmt.setInt(6, roomMealOrderMaster.getRo_order_status());
			stmt.setString(7, roomMealOrderMaster.getEmp_id());
			stmt.setString(8, roomMealOrderMaster.getRoom_meal_order_no());
			
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
	public void cookedUpdate(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sql = "UPDATE ROOM_MEAL_ORDER_MASTER SET RO_ORDER_STATUS=1, ORDER_DATE=?, "
				+ "EMP_ID=?, TOTAL_PRICE=? "
				+ "WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setDate(1, sqlDate);
			stmt.setString(2, roomMealOrderMaster.getEmp_id());
			stmt.setInt(3, roomMealOrderMaster.getTotal_price());
			stmt.setString(4, roomMealOrderMaster.getRoom_meal_order_no());
			
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
	public void shippedUpdate(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sql = "UPDATE ROOM_MEAL_ORDER_MASTER SET RO_ORDER_STATUS=2, ORDER_DATE=?, "
				+ "TOTAL_PRICE=? "
				+ "WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setDate(1, sqlDate);
			stmt.setInt(2, roomMealOrderMaster.getTotal_price());
			stmt.setString(3, roomMealOrderMaster.getRoom_meal_order_no());
			
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
	public void orderClosedUpdate(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sql = "UPDATE ROOM_MEAL_ORDER_MASTER SET RO_ORDER_STATUS=3, ORDER_DATE=? "
				+ "WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, sqlDate);
			stmt.setString(2, roomMealOrderMaster.getRoom_meal_order_no());
			
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
	public void orderCancelUpdate(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sql = "UPDATE ROOM_MEAL_ORDER_MASTER SET RO_ORDER_STATUS=4, ORDER_DATE=? "
				+ "WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, sqlDate);
			stmt.setString(2, roomMealOrderMaster.getRoom_meal_order_no());
			
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
	public void delete(RoomMealOrderMasterVO roomMealOrderMaster) {
		String sqlOrderMaster = "DELETE FROM ROOM_MEAL_ORDER_MASTER WHERE ROOM_MEAL_ORDER_NO=?";
		String sqlOrderDetail = "DELETE FROM ROOM_MEAL_ORDER_DETAIL WHERE ROOM_MEAL_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// temp 代處理
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sqlOrderDetail);
			stmt.setString(1, roomMealOrderMaster.getRoom_meal_order_no());
			
			stmt = conn.prepareStatement(sqlOrderMaster);
			stmt.setString(1, roomMealOrderMaster.getRoom_meal_order_no());
			
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
	
	public static void main(String[] args) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("room_meal_order_no", new String[] { "" });
		map.put("b_order_no", new String[] { "" });
		map.put("ro_order_status", new String[] { "0" });
		
		RoomMealOrderMasterJDBCDAO dao = new RoomMealOrderMasterJDBCDAO();
		dao.delete("ROM-20200322-005");
//		List<RoomMealOrderMasterVO> list = dao.findAll(map);
		
//		for (RoomMealOrderMasterVO roomMealOrderMasterVO : list) {
//			String no = roomMealOrderMasterVO.getRoom_meal_order_no();
//			System.out.println(no);
//		}
		
	}
	
	

}
