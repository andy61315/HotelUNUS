package com.mealtype.model;

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

public class MealTypeJDBCDAO implements MealTypeDAO{

	
	@Override
	public List<MealTypeVO> findAll() {
		List<MealTypeVO> mealTypes = new ArrayList<MealTypeVO>();
		String sql = "SELECT MEAL_TYPE_NO, TYPE_NAME FROM MEAL_TYPE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MealTypeVO mealType = new MealTypeVO();
				
				mealType.setMeal_type_no(rs.getString("MEAL_TYPE_NO"));
				mealType.setType_name(rs.getString("TYPE_NAME"));
				
				mealTypes.add(mealType);
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
		
		return mealTypes;
	}

	@Override
	public MealTypeVO findByPk(String pk) {
		MealTypeVO mealType = new MealTypeVO();
		String sql = "SELECT MEAL_TYPE_NO, TYPE_NAME FROM MEAL_TYPE WHERE MEAL_TYPE_NO=?";
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
				mealType.setMeal_type_no(rs.getString("MEAL_TYPE_NO"));
				mealType.setType_name(rs.getString("TYPE_NAME"));
				
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
		
		return mealType;
	}

	@Override
	public void create(MealTypeVO mealType) {
		String sql = "INSERT INTO MEAL_TYPE(MEAL_TYPE_NO, TYPE_NAME) " + 
				"VALUES('MT'||TO_CHAR(SEQ_MEAL_TYPE_NO.nextval,\'FM000\'),?)";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealType.getType_name());
			
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
	public void update(MealTypeVO mealType) {
		String sql = "UPDATE MEAL_TYPE SET TYPE_NAME=? WHERE MEAL_TYPE_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mealType.getMeal_type_no());
			stmt.setString(2, mealType.getType_name());
			
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
	public void delete(String pk) {
		String sql = "DELETE FROM MEAL_TYPE where MEAL_TYPE_NO=?";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pk);
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException qe) {
			throw new RuntimeException("A database error occured. "
					+ qe.getMessage());
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
