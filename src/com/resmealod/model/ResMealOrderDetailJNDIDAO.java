package com.resmealod.model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ResMealOrderDetailJNDIDAO implements ResMealOrderDetailDAO_interface<ResMealOrderDetailVO> {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ResMealOrderDetailVO> getAll() {

		List<ResMealOrderDetailVO> rlist = new ArrayList<>();

		String sql_query = "select res_meal_order_no, meal_no, price, quantity "
				+ "from res_meal_order_detail";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			rs = ps.executeQuery();

			while (rs.next()) {
				String resMealOrderNo = rs.getString(1);
				String mealNo = rs.getString(2);
				Integer price = rs.getInt(3);
				Integer quantity = rs.getInt(4);

				// 將查詢撈出的資料放入VO物件，再放入集合
				ResMealOrderDetailVO rmeal = new ResMealOrderDetailVO(resMealOrderNo, mealNo, price, quantity);
				rlist.add(rmeal);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
		return rlist;
	}

	@Override
	public ResMealOrderDetailVO findByPrimaryKey(String id, String no) {

		ResMealOrderDetailVO rmeal = null;
		
		String sql_query = "select res_meal_order_no, meal_no, price, quantity "
				+ "from res_meal_order_detail where res_meal_order_no = ? and meal_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
			try {
				conn =ds.getConnection();
				ps = conn.prepareStatement(sql_query);
				ps.setString(1, id);
				ps.setString(2, no);
				rs =ps.executeQuery();
				
				if (rs.next()) {

					String resMealOrderNo = rs.getString(1);
					String mealNo = rs.getString(2);
					Integer price = rs.getInt(3);
					Integer quantity = rs.getInt(4);


					rmeal = new ResMealOrderDetailVO(resMealOrderNo, mealNo, price, quantity);
				}
			}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
		return rmeal;
	}

	@Override
	public boolean add(ResMealOrderDetailVO rmeal) {
		// 執行成功列數
		int rowCount = 0;
		String sql = "insert into res_meal_order_detail ( "
				+ "res_meal_order_no, meal_no, price, cooking, served, canceled ) " + "values (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps =conn.prepareStatement(sql);
			// getter取得物件資訊送入資料庫
			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getMealNo());
			ps.setInt(3, rmeal.getPrice());
			ps.setInt(4, rmeal.getQuantity());

			rowCount = ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		return rowCount != 0;
	}

	public void add2(ResMealOrderDetailVO rmeal, Connection conn) {
		String sql = "insert into res_meal_order_detail ( "
				+ "res_meal_order_no, meal_no, price,  quantity ) " + "values (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps =conn.prepareStatement(sql);
			// getter取得物件資訊送入資料庫
			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getMealNo());
			ps.setInt(3, rmeal.getPrice());
			ps.setInt(3, rmeal.getQuantity());

			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	};
	@Override
	public boolean update(ResMealOrderDetailVO rmeal) {
		// 更新成功筆數
		int rowCount = 0;
		String sql = "update res_meal_order_detail set price = ?, quantity = ? "
				+ "where res_meal_order_no = ? and meal_no = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn =ds.getConnection();
			ps = conn.prepareStatement(sql);

			// ps.setString(1, rmeal.getResMealOrderNo());
			// ps.setString(2, rmeal.getMealNo());
			ps.setInt(1, rmeal.getPrice());
			ps.setInt(2, rmeal.getQuantity());

			ps.setString(3, rmeal.getResMealOrderNo());
			ps.setString(4, rmeal.getMealNo());

			rowCount = ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		return rowCount != 0;
	}

	@Override
	public boolean delete(String id, String no) {
		// 刪除成功筆數

		int rowCount = 0;
		String sql = "delete from res_meal_order_detail where res_meal_order_no = ? and meal_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps =conn.prepareStatement(sql);
			// 送入欲刪除pk
			ps.setString(1, id);
			ps.setString(2, no);
			rowCount = ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		return rowCount != 0;
	}

	

}
