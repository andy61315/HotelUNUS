package com.restaurant.model;

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

public class RestaurantJNDIDAO implements RestaurantDAO_interface<RestaurantVO> {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<RestaurantVO> getAll() {

		List<RestaurantVO> rlist = new ArrayList<>();
		RestaurantVO restaurant;

		String sql_query = "select res_no, res_name, total_seats, res_contact, res_phone, "
				+ " res_status from restaurant";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			rs = ps.executeQuery();

			while (rs.next()) {
				String resNo = rs.getString(1);
				String resName = rs.getString(2);
				Integer totalSeat = rs.getInt(3);
				String resContact = rs.getString(4);
				String resPhone = rs.getString(5);
				Integer resStatus = rs.getInt(6);
				restaurant = new RestaurantVO(resNo, resName, totalSeat, resContact, resPhone,resStatus);
				rlist.add(restaurant);
			}
		} catch (SQLException se) {
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
		return rlist;
	}

	@Override
	public RestaurantVO findByPrimaryKey(String id) {
		RestaurantVO restaurant = null;

		String sql_query = "select res_no, res_name, total_seats, res_contact, res_phone, res_status "
				+ "from restaurant where res_no= ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			

			if (rs.next()) {
				String resNo = rs.getString("res_no");
				String resName = rs.getString("res_name");
				Integer totalSeat = rs.getInt("total_seats");
				String resContact = rs.getString("res_contact");
				String resPhone = rs.getString("res_phone");
				Integer resStatus = rs.getInt("res_status");
				restaurant = new RestaurantVO(resNo, resName, totalSeat, resContact, resPhone,resStatus);
			}
		} catch (SQLException se) {
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

		return restaurant;
	}

	@Override
	public void add(RestaurantVO rest) {

		//餐廳編�?�為?��??�編???
		String sql = "insert into restaurant ( "
				+ "res_no, res_name, total_seats, res_contact, res_phone, res_status) "
				+ "values ('REST'||LPAD(TO_CHAR(SEQ_RES_NO.nextval),6,'0'), ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, rest.getResName());
			ps.setInt(2, rest.getTotalSeat());
			ps.setString(3, rest.getResContact());
			ps.setString(4, rest.getResPhone());
			ps.setInt(5, rest.getResStatus());

			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			
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

	}

	@Override
	public void updateStatus(RestaurantVO rest) {

		String sql = "update restaurant set res_status = ? where res_no = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, rest.getResStatus());
			ps.setString(2, rest.getResNo());

			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			
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

	}

	@Override
	public void delete(String id) {

		String sql = "delete from restaurant where res_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			
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

	}

}
