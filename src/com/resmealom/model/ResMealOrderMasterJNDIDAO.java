package com.resmealom.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.meal.model.OrderMealVO;
import com.resmealod.model.ResMealOrderDetailDAO;
import com.resmealod.model.ResMealOrderDetailVO;

public class ResMealOrderMasterJNDIDAO implements ResMealOrderMasterDAO_interface<ResMealOrderMasterVO> {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int  findTotalByRoom(String tableNo) {
		
		Integer totalPrice =null;
		String sql_query="select TOTAL_PRICE from RES_MEAL_ORDER_MASTER where B_ORDER_NO=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				totalPrice = rs.getInt(1);
				
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
		return totalPrice;
	};

	public void insertWithDetails(ResMealOrderMasterVO resmomVO, List<OrderMealVO> list) {

		String sql = "INSERT INTO res_meal_order_master (res_meal_order_no, b_order_no, table_no, total_price, special_requirement,order_status) "
				+ "VALUES ('RESM-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_RES_MEAL_ORDER_NO.NEXTVAL,'FM000'),?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false);

			String cols[] = { "RES_MEAL_ORDER_NO" };
			ps = conn.prepareStatement(sql, cols);
			ps.setString(1, resmomVO.getbOrderNo());
			ps.setInt(2, resmomVO.getTableNo());
			ps.setInt(3, resmomVO.getTotalPrice());
			ps.setString(4, resmomVO.getSpecialRequirement());
			ps.setInt(5, resmomVO.getOrderStatus());
			ps.executeUpdate();

			String next_resMealOrderNo = null;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				next_resMealOrderNo = rs.getString(1);
			}
			rs.close();

			// 同時新增明細
			ResMealOrderDetailDAO dao = new ResMealOrderDetailDAO();
			System.out.println("集合內有 :" + list.size());

			for (OrderMealVO aDetail : list) {
				ResMealOrderDetailVO detail = new ResMealOrderDetailVO();
				detail.setResMealOrderNo(next_resMealOrderNo);
				detail.setMealNo(aDetail.getMeal_no());
				detail.setPrice(aDetail.getPrice());
				detail.setQuantity(aDetail.getQuantity());
				dao.add2(detail, conn);
			}

			// 2●設定於 ps.executeUpdate()之後
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("集合B= " + list.size());
			System.out.println("新增訂單編號" + next_resMealOrderNo + "時，共有明細" + list.size() + "筆，同時被新增");

			// Handle any SQL errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public List<ResMealOrderMasterVO> getAll() {

		List<ResMealOrderMasterVO> rlist = new ArrayList<>();

		String sql_query = "select res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement "
				+ "from res_meal_order_master";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			rs = ps.executeQuery();

			while (rs.next()) {
				String resMealOrderNo = rs.getString(1);
				String bOrderNo = rs.getString(2);
				Integer tableNo = rs.getInt(3);
				Integer totalPrice = rs.getInt(4);
				Date orderDate = rs.getDate(5);
				Integer orderStatus = rs.getInt(6);
				String specialRequirement = rs.getString(7);

				ResMealOrderMasterVO rmeal = new ResMealOrderMasterVO(resMealOrderNo, bOrderNo, tableNo, totalPrice,
						orderDate, orderStatus, specialRequirement);
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
	public ResMealOrderMasterVO findByPrimaryKey(String id) {

		ResMealOrderMasterVO rmeal = null;

		String sql_query = "select res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement "
				+ "from res_meal_order_master where res_meal_order_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {

				String resMealOrderNo = rs.getString(1);
				String bOrderNo = rs.getString(2);
				Integer tableNo = rs.getInt(3);
				Integer totalPrice = rs.getInt(4);
				Date orderDate = rs.getDate(5);
				Integer orderStatus = rs.getInt(6);
				String specialRequirement = rs.getString(7);

				rmeal = new ResMealOrderMasterVO(resMealOrderNo, bOrderNo, tableNo, totalPrice, orderDate, orderStatus,
						specialRequirement);
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
		return rmeal;
	}

	@Override
	public boolean add(ResMealOrderMasterVO rmeal) {
		// 執行成功列數
		int rowCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "insert into res_meal_order_master ( "
				+ "res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getbOrderNo());
			ps.setInt(3, rmeal.getTableNo());
			ps.setInt(4, rmeal.getTotalPrice());
			ps.setDate(5, rmeal.getOrderDate());
			ps.setInt(6, rmeal.getOrderStatus());
			ps.setString(7, rmeal.getSpecialRequirement());

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
	public boolean update(ResMealOrderMasterVO rmeal) {
		// 更新成功筆數
		int rowCount = 0;
		String sql = "update res_meal_order_master "
				+ "set res_meal_order_no = ?, b_order_no = ?, table_no = ?, total_price = ?, order_date = ? , order_status = ? , special_requirement = ?"
				+ "where res_meal_order_no = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getbOrderNo());
			ps.setInt(3, rmeal.getTableNo());
			ps.setInt(4, rmeal.getTotalPrice());
			ps.setDate(5, rmeal.getOrderDate());
			ps.setInt(6, rmeal.getOrderStatus());
			ps.setString(7, rmeal.getSpecialRequirement());
			ps.setString(8, rmeal.getResMealOrderNo());

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
	public boolean delete(String id) {
		// 刪除成功筆數
		int rowCount = 0;
		String sql = "delete from res_meal_order_master where res_meal_order_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			// 送入欲刪除pk
			ps.setString(1, id);
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
