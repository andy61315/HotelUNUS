package com.meal.model;

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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;




public class MealJDBCDAO implements MealDAO{
	
	private void populate(List<MealVO> meals, ResultSet rs) throws SQLException{
		MealVO meal = new MealVO();
		meal.setMeal_date(rs.getDate("MEAL_DATE"));
		meal.setMeal_introduction(rs.getString("MEAL_INTRODUCTION"));
		meal.setMeal_name(rs.getString("MEAL_NAME"));
		meal.setMeal_no(rs.getNString("MEAL_NO"));
		meal.setMeal_picture(rs.getBytes("MEAL_PICTURE"));
		meal.setMeal_status(rs.getInt("MEAL_STATUS"));
		meal.setMeal_type_no(rs.getString("MEAL_TYPE_NO"));
		meal.setPrice(rs.getInt("PRICE"));
		meal.setRes_no(rs.getString("RES_NO"));
		
		meals.add(meal);
	}
	
	@Override
	public void updatePicture(MealVO mealVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE MEAL SET MEAL_PICTURE=? WHERE MEAL_NO=?";
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setBytes(1, mealVO.getMeal_picture());
			stmt.setString(2, mealVO.getMeal_no());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void updateWithoutPicture(MealVO mealVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE MEAL SET RES_NO=?, MEAL_TYPE_NO=?, PRICE=?, MEAL_NAME=?, MEAL_INTRODUCTION=?, "
				+ "MEAL_DATE=?, MEAL_STATUS=? WHERE MEAL_NO=?";
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealVO.getRes_no());
			stmt.setString(2, mealVO.getMeal_type_no());
			stmt.setInt(3, mealVO.getPrice());
			stmt.setString(4, mealVO.getMeal_name());
			stmt.setString(5, mealVO.getMeal_introduction());
			stmt.setDate(6, mealVO.getMeal_date());
			stmt.setInt(7, mealVO.getMeal_status());
			stmt.setString(8, mealVO.getMeal_no());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public MealVO findByPk(String pk) {
		MealVO meal = new MealVO();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEAL WHERE MEAL_NO=?";
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				meal.setMeal_date(rs.getDate("MEAL_DATE"));
				meal.setMeal_introduction(rs.getString("MEAL_INTRODUCTION"));
				meal.setMeal_name(rs.getString("MEAL_NAME"));
				meal.setMeal_no(rs.getNString("MEAL_NO"));
				meal.setMeal_picture(rs.getBytes("MEAL_PICTURE"));
				meal.setMeal_status(rs.getInt("MEAL_STATUS"));
				meal.setMeal_type_no(rs.getString("MEAL_TYPE_NO"));
				meal.setPrice(rs.getInt("PRICE"));
				meal.setRes_no(rs.getString("RES_NO"));
			}
			
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace(System.err);
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return meal;
	}

	@Override
	public int findPrice(String pk) {
		String sql = "SELECT PRICE FROM MEAL WHERE MEAL_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			int price = 0;
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				price = rs.getInt("PRICE");
			}
			
			return price;
			
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

		return -1;
	}

	@Override
	public List<MealVO> findStartEnd(int start, int end) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MealVO> meals = new ArrayList<MealVO>();
		StringBuffer sqlStr = new StringBuffer("SELECT * FROM MEAL LIMIT 10 OFFSET 0");
		sqlStr.append(" LIMIT ").append(end - start);
		sqlStr.append(" OFFSET ").append(start);
		String sql = sqlStr.toString();
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(meals, rs);
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
		return meals;
	}

	@Override
	public void delete(String pk) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM MEAL WHERE MEAL_NO=?";
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
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
	public List<MealVO> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MealVO> meals = new ArrayList<MealVO>();
		String sql = "SELECT * FROM MEAL";

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				populate(meals, rs);
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
		
		return meals;
	}
	
	@Override
	public List<OrderMealVO> findByRestaurant(String resNo){
		
		List<OrderMealVO> list = new ArrayList<OrderMealVO>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEAL WHERE RES_NO = ?";
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, resNo);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				OrderMealVO meal = new OrderMealVO();
				meal.setMeal_date(rs.getDate("MEAL_DATE"));
				meal.setMeal_introduction(rs.getString("MEAL_INTRODUCTION"));
				meal.setMeal_name(rs.getString("MEAL_NAME"));
				meal.setMeal_no(rs.getNString("MEAL_NO"));
				meal.setMeal_picture(rs.getBytes("MEAL_PICTURE"));
				meal.setMeal_status(rs.getInt("MEAL_STATUS"));
				meal.setMeal_type_no(rs.getString("MEAL_TYPE_NO"));
				meal.setPrice(rs.getInt("PRICE"));
				meal.setRes_no(rs.getString("RES_NO"));
				//meal.setQuantity(rs.getInt("quantity"));
				list.add(meal);
			}
			
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace(System.err);
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	@Override
	public void insert(MealVO mealVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO MEAL (MEAL_NO, RES_NO, MEAL_TYPE_NO, PRICE, MEAL_NAME, MEAL_INTRODUCTION, MEAL_DATE, MEAL_STATUS, MEAL_PICTURE) "
				+ "VALUES ('M'||TO_CHAR(SEQ_MEAL_NO.nextval,'FM0000'), ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealVO.getRes_no());
			stmt.setString(2, mealVO.getMeal_type_no());
			stmt.setInt(3, mealVO.getPrice());
			stmt.setString(4, mealVO.getMeal_name());
			stmt.setString(5, mealVO.getMeal_introduction());
			stmt.setDate(6, mealVO.getMeal_date());
			stmt.setInt(7, mealVO.getMeal_status());
			stmt.setBytes(8, mealVO.getMeal_picture());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(MealVO mealVO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE MEAL SET RES_NO=?, MEAL_TYPE_NO=?, PRICE=?, MEAL_NAME=?, MEAL_INTRODUCTION=?, "
				+ "MEAL_DATE=?, MEAL_STATUS=?, MEAL_PICTURE=? WHERE MEAL_NO=?";
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealVO.getRes_no());
			stmt.setString(2, mealVO.getMeal_type_no());
			stmt.setInt(3, mealVO.getPrice());
			stmt.setString(4, mealVO.getMeal_name());
			stmt.setString(5, mealVO.getMeal_introduction());
			stmt.setDate(6, mealVO.getMeal_date());
			stmt.setInt(7, mealVO.getMeal_status());
			stmt.setBytes(8, mealVO.getMeal_picture());
			stmt.setString(9, mealVO.getMeal_no());
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		MealJDBCDAO dao = new MealJDBCDAO();
		List<OrderMealVO> list = new ArrayList<OrderMealVO>();//裝查詢結果
		
		list = dao.findByRestaurant("REST000001");//回傳查詢結果
		//用foreach跑迴圈滾出list內的物件
		for(OrderMealVO meal:list) {
			//以物件取得屬性
			System.out.println(meal.getMeal_name());
			System.out.println(meal.getMeal_no());
			System.out.println(meal.getRes_no());
			//System.out.println(meal.getQuantity());
		}
		
		
		
		
	}
	
	
}
