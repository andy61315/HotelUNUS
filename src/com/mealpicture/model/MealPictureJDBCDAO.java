package com.mealpicture.model;

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


public class MealPictureJDBCDAO implements MealPictureDAO{

	@Override
	public List<MealPictureVO> findAll() {
		List<MealPictureVO> mealpictures = new ArrayList<MealPictureVO>();
		String sql = "SELECT MEAL_PICTURE_NO, MEAL_NO, MEAL_PIC FROM MEAL_PICTURE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MealPictureVO mealpicture = new MealPictureVO();
				
				mealpicture.setMeal_picture_no(rs.getString("MEAL_PICTURE_NO"));
				mealpicture.setMeal_no(rs.getString("MEAL_NO"));
				mealpicture.setMeal_pic(rs.getBytes("MEAL_PIC"));
				
				mealpictures.add(mealpicture);
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
		
		return mealpictures;
	}

	@Override
	public List<MealPictureVO> findByMealNo(String mealNo) {
		List<MealPictureVO> mealpictures = new ArrayList<MealPictureVO>();
		String sql = "SELECT MEAL_PICTURE_NO, MEAL_NO, MEAL_PIC FROM MEAL_PICTURE WHERE MEAL_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealNo);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MealPictureVO mealpicture = new MealPictureVO();
				
				mealpicture.setMeal_picture_no(rs.getString("MEAL_PICTURE_NO"));
				mealpicture.setMeal_no(rs.getString("MEAL_NO"));
				mealpicture.setMeal_pic(rs.getBytes("MEAL_PIC"));
				
				mealpictures.add(mealpicture);
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
		
		return mealpictures;
	}

	@Override
	public void create(MealPictureVO mealpicture) {
		String sql = "INSERT INTO MEAL_PICTURE(MEAL_PICTURE_NO, MEAL_NO, MEAL_PIC) " + 
				"VALUES('MP'||TO_CHAR(SEQ_MEAL_PICTURE_NO.nextval,\'FM000\'),?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealpicture.getMeal_no());
			stmt.setBytes(2, mealpicture.getMeal_pic());
			
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
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MealPictureVO mealpicture) {
		String sql = "UPDATE MEAL_PICTURE SET MEAL_NO=?, MEAL_PIC=? WHERE MEAL_PICTURE_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealpicture.getMeal_no());
			stmt.setBytes(2, mealpicture.getMeal_pic());
			stmt.setString(3, mealpicture.getMeal_picture_no());
			
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
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String pk) {
		String sql = "DELETE FROM MEAL_PICTURE WHERE MEAL_PICTURE_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
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

}
