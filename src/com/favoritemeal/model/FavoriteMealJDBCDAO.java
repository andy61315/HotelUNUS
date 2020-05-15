package com.favoritemeal.model;

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


public class FavoriteMealJDBCDAO implements FavoriteMealDAO{

	@Override
	public List<FavoriteMealVO> findAll(String cus_id) {
		List<FavoriteMealVO> favoratemeals = new ArrayList<FavoriteMealVO>();
		String sql = "SELECT CUS_ID, MEAL_NO FROM FAVORITE_MEAL WHERE CUS_ID=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cus_id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FavoriteMealVO favorateMeal = new FavoriteMealVO();
				
				favorateMeal.setCus_id(rs.getString("CUS_ID"));
				favorateMeal.setMeal_no(rs.getString("MEAL_NO"));
				
				favoratemeals.add(favorateMeal);
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
		return favoratemeals;
	}

	@Override
	public void create(FavoriteMealVO favoratemeal) {
		String sql = "INSERT INTO FAVORITE_MEAL(CUS_ID, MEAL_NO) " + 
				"VALUES(?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, favoratemeal.getCus_id());
			stmt.setString(2, favoratemeal.getMeal_no());
			
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
	public void update(FavoriteMealVO favoratemeal) {
		String sql = "UPDATE FAVORITE_MEAL SET CUS_ID=? WHERE MEAL_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, favoratemeal.getCus_id());
			stmt.setString(2, favoratemeal.getMeal_no());
			
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
	public void delete(FavoriteMealVO favoratemeal) {
		String sql = "DELETE FROM FAVORITE_MEAL WHERE CUS_ID=? AND MEAL_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, favoratemeal.getCus_id());
			stmt.setString(2, favoratemeal.getMeal_no());
			
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


}
