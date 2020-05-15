package com.resreservation.model;

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
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ResReservationJNDIDAO implements ResReservationDAO_interface<ResReservationVO> {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	// 連線池版本
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
	public List<ResReservationVO> getAll() {
		// 查詢全部

		List<ResReservationVO> rlist = new ArrayList<>();

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status from res_reservation";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			rs = ps.executeQuery();

			while (rs.next()) {
				String reservationNo = rs.getString(1);
				String custId = rs.getString(2);
				String resNo = rs.getString(3);
				Date reservationDate = rs.getDate(4);
				Integer resvPeriod = rs.getInt(5);
				Integer resvPeople = rs.getInt(6);
				Integer resvStatus = rs.getInt(7);

				ResReservationVO reservation = new ResReservationVO(reservationNo, custId, resNo, reservationDate,
						resvPeriod, resvPeople, resvStatus);
				rlist.add(reservation);
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

	// 沒啥用
	@Override
	public ResReservationVO findByPrimaryKey(String id) {
		ResReservationVO reservation = null;

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status "
				+ "from res_reservation where reservation_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				String reservationNo = rs.getString(1);
				String custId = rs.getString(2);
				String resNo = rs.getString(3);
				Date reservationDate = rs.getDate(4);
				Integer resvPeriod = rs.getInt(5);
				Integer resvPeople = rs.getInt(6);
				Integer resvStatus = rs.getInt(7);

				reservation = new ResReservationVO(reservationNo, custId, resNo, reservationDate, resvPeriod,
						resvPeople, resvStatus);
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

		return reservation;
	}

	@Override
	public void add(ResReservationVO rest) {

		String sql = "insert into res_reservation ( "
				+ "reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people) "
				+ "values ('RV-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_RESERVATION_NO.NEXTVAL,'FM000'), ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			// ps.setString(1, rest.getReservationNo()); // 改自動編號
			ps.setString(1, rest.getCustId());
			ps.setString(2, rest.getResNo());
			ps.setDate(3, rest.getReservationDate());
			ps.setInt(4, rest.getResvPeriod());
			ps.setInt(5, rest.getResvPeople());

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

	}

	@Override
	public void update(ResReservationVO rest) {

		String sql = "update res_reservation set reservation_no = ? , cus_Id= ? ,res_no=?, reservation_date=?, "
				+ " resv_period=?, resv_people=?  where reservation_no=? ";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, rest.getReservationNo());
			ps.setString(2, rest.getCustId());
			ps.setString(3, rest.getResNo());
			ps.setDate(4, rest.getReservationDate());
			ps.setInt(5, rest.getResvPeriod());
			ps.setInt(6, rest.getResvPeople());
			ps.setString(7, rest.getReservationNo());

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

	}

	// 更改預約狀態
	@Override
	public void updateStatus(String id) {
		String sql = "UPDATE res_reservation set resv_Status= 0 where reservation_no=? ";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			// 取得更新後資訊送進資料庫

			ps.setString(1, id);

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

	}
	
	@Override
	public void updateStatus2(String id) {
		String sql = "UPDATE res_reservation set resv_Status= 2 where reservation_no=? ";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			// 取得更新後資訊送進資料庫

			ps.setString(1, id);

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

	}

	@Override
	public void delete(String id) {// 應該用不到

		String sql = "delete from res_reservation where reservation_no = ?";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, id);
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

	}

	@Override
	public int findPeopleByPeriod(Date date, Integer period, String resNo) {

		int count = 0;
		Integer resvPeople;

		String sql_query = "SELECT RESV_PEOPLE FROM RES_RESERVATION WHERE RESERVATION_DATE = ? AND RESV_PERIOD = ? AND RES_NO = ? AND RESV_STATUS >=1";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setDate(1, date);
			ps.setInt(2, period);
			ps.setString(3, resNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				resvPeople = rs.getInt("resv_people");
				count += resvPeople;

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
		return count;
	}

	@Override
	public List<ResReservationVO> findByCustomer(String id) { //依客戶ID查尋預約
		List<ResReservationVO> rlist = new ArrayList<>();

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status "
				+ "from res_reservation where cus_id = ? and resv_status = 1";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, id);// 對應sql查詢問號
			rs = ps.executeQuery();

			while (rs.next()) {
				String reservationNo = rs.getString(1);
				String custId = rs.getString(2);
				String resNo = rs.getString(3);
				Date reservationDate = rs.getDate(4);
				Integer resvPeriod = rs.getInt(5);
				Integer resvPeople = rs.getInt(6);
				Integer resvStatus = rs.getInt(7);

				ResReservationVO reservation = new ResReservationVO(reservationNo, custId, resNo, reservationDate,
						resvPeriod, resvPeople, resvStatus);
				rlist.add(reservation);
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
	public List<ResReservationVO> findByDate(Date date, String restNo) {// 以日期查詢訂位資訊

		List<ResReservationVO> rlist = new ArrayList<>();

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status "
				+ "from res_reservation where RESERVATION_DATE = ? and RES_NO = ? and resv_status >= 1 ORDER BY resv_period";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setDate(1, date);// 對應sql查詢問號
			ps.setString(2, restNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				String reservationNo = rs.getString(1);
				String custId = rs.getString(2);
				String resNo = rs.getString(3);
				Date reservationDate = rs.getDate(4);
				Integer resvPeriod = rs.getInt(5);
				Integer resvPeople = rs.getInt(6);
				Integer resvStatus = rs.getInt(7);

				ResReservationVO reservation = new ResReservationVO(reservationNo, custId, resNo, reservationDate,
						resvPeriod, resvPeople, resvStatus);
				rlist.add(reservation);
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
	public int doubleOrder(ResReservationVO rest) {
		String sql_query = "SELECT COUNT(RESV_PERIOD) FROM RES_RESERVATION WHERE RESERVATION_DATE = ? AND RESV_PERIOD = ? AND RES_NO = ?"
				+ " AND RESV_STATUS =1 AND CUS_ID= ?";
		int count = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setDate(1, rest.getReservationDate());
			ps.setInt(2, rest.getResvPeriod());
			ps.setString(3, rest.getResNo());
			ps.setString(4,rest.getCustId());
			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT(RESV_PERIOD)");
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return count;
	}

	@Override
	public boolean isDoubleOrder(ResReservationVO rest) {
		
		String sql_query = "SELECT COUNT(RESV_PERIOD) FROM RES_RESERVATION WHERE RESERVATION_DATE = ? AND RESV_PERIOD = ? AND RES_NO = ?"
				+ " AND RESV_STATUS =1 AND CUS_ID= ?";
		int count = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql_query);
			ps.setDate(1, rest.getReservationDate());
			ps.setInt(2, rest.getResvPeriod());
			ps.setString(3, rest.getResNo());
			ps.setString(4,rest.getCustId());
			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT(RESV_PERIOD)");
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

}
