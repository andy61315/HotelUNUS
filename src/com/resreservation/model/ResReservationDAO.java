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

public class ResReservationDAO implements ResReservationDAO_interface<ResReservationVO> {

	public ResReservationDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<ResReservationVO> getAll() {//listAll

		List<ResReservationVO> rlist = new ArrayList<>();

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status from res_reservation where res_no=?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {
			

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					String reservationNo = rs.getString(1);
					String custId = rs.getString(2);
					String resNo = rs.getString(3);
					Date reservationDate = rs.getDate(4);
					Integer resvPeriod = rs.getInt(5);
					Integer resvPeople = rs.getInt(6);
					Integer resvStatus = rs.getInt(7);

					ResReservationVO reservation = new ResReservationVO(reservationNo, custId, resNo, reservationDate,
							resvPeriod, resvPeople, resvStatus);// ok
					rlist.add(reservation);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		}
		return rlist;
	}

	@Override
	public ResReservationVO findByPrimaryKey(String id) {// 以預約編號查詢一筆
		ResReservationVO reservation = null;

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status "
				+ "from res_reservation where reservation_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setString(1, id);// 對應sql查詢問號
			try (ResultSet rs = ps.executeQuery();) {
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
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

		return reservation;
	}

	@Override
	public void add(ResReservationVO rest) {
		// 新增預約編號為自動編號，狀態為預設值(不用寫)

		String sql = "insert into res_reservation ( "
				+ "reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people) "
				+ "values ('RV-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_RESERVATION_NO.NEXTVAL,'FM000'), ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {
			// 由VO取得屬性值送進資料庫
			// ps.setString(1, rest.getReservationNo()); //改採自動編號
			ps.setString(1, rest.getCustId());
			ps.setString(2, rest.getResNo());
			ps.setDate(3, rest.getReservationDate());
			ps.setInt(4, rest.getResvPeriod());
			ps.setInt(5, rest.getResvPeople());

			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void update(ResReservationVO rest) {// 更改預約資訊(預約編號及顧客編號都不能改)

		String sql = "update res_reservation set res_no=?, reservation_date=?, "
				+ " resv_period=?, resv_people=? where reservation_no=? ";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, rest.getResNo());
			ps.setDate(2, rest.getReservationDate());
			ps.setInt(3, rest.getResvPeriod());
			ps.setInt(4, rest.getResvPeople());
			ps.setString(5, rest.getReservationNo());

			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void updateStatus(String id) { //取消預約
		String sql = "UPDATE res_reservation set resv_Status= 0 where reservation_no=? ";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			// 取得更新後資訊送進資料庫

			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}
	
	@Override
	public void updateStatus2(String id) { //已入座
		String sql = "UPDATE res_reservation set resv_Status= 2 where reservation_no=? ";
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			// 取得更新後資訊送進資料庫

			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	};

	@Override
	public void delete(String id) {// 刪除資料>>應該不會用到

		String sql = "delete from res_reservation where reservation_no = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, id);
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public int findPeopleByPeriod(Date date, Integer period, String resNo) {
		// 依日期+時段+餐廳+預約狀態 查人數

		int count = 0;
		Integer resvPeople;

		String sql_query = "SELECT RESV_PEOPLE FROM RES_RESERVATION WHERE RESERVATION_DATE = ? AND RESV_PERIOD = ? AND RES_NO = ? AND RESV_STATUS =1";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setDate(1, date);
			ps.setInt(2, period);
			ps.setString(3, resNo);

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					resvPeople = rs.getInt("resv_people");
					count += resvPeople;

				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return count;
	}

	@Override
	public int doubleOrder(ResReservationVO rest) {// 判斷是否達預約人數上限

		String sql_query = "SELECT COUNT(RESV_PERIOD) FROM RES_RESERVATION WHERE RESERVATION_DATE = ? AND RESV_PERIOD = ? AND RES_NO = ?"
				+ " AND RESV_STATUS =1 AND CUS_ID= ?";
		int count = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setDate(1, rest.getReservationDate());
			ps.setInt(2, rest.getResvPeriod());
			ps.setString(3, rest.getResNo());
			ps.setString(4,rest.getCustId());
			
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					count = rs.getInt("COUNT(RESV_PERIOD)");
				}
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
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setDate(1, rest.getReservationDate());
			ps.setInt(2, rest.getResvPeriod());
			ps.setString(3, rest.getResNo());
			ps.setString(4,rest.getCustId());
			
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					count = rs.getInt("COUNT(RESV_PERIOD)");
				}
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		
		if(count>0) {
			return true;
		}else {
			return false;
		}

	};

	
	@Override
	public List<ResReservationVO> findByCustomer(String id) {// 以客戶編號查詢訂單

		List<ResReservationVO> rlist = new ArrayList<>();

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status "
				+ "from res_reservation where cus_id = ? and resv_status = 1";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setString(1, id);// 對應sql查詢問號
			try (ResultSet rs = ps.executeQuery();) {
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
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

		return rlist;
	}

	@Override
	public List<ResReservationVO> findByDate(Date date, String restNo) {// 以日期+餐廳查詢訂位

		List<ResReservationVO> rlist = new ArrayList<>();

		String sql_query = "select reservation_no, cus_id, res_no, reservation_date, resv_period, resv_people, resv_status "
				+ "from res_reservation where RESERVATION_DATE = ? and RES_NO = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setDate(1, date);// 對應sql查詢問號
			ps.setString(2, restNo);
			try (ResultSet rs = ps.executeQuery();) {
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
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

		return rlist;
	}

	public static void main(String[] args) {

		ResReservationDAO dao = new ResReservationDAO();

		//dao.updateStatus("RV-20200323-003");
		
		
		dao.updateStatus2("RV-20200501-005");
		;

		// 新增ok
//		ResReservationVO ResRVO1 = new ResReservationVO();
//		//ResRVO1.setReservationNo("RV-20200325-008"); 
//		ResRVO1.setCustId("CUS0000001");
//		ResRVO1.setResNo("REST000003");
//		ResRVO1.setReservationDate(java.sql.Date.valueOf("2020-03-01"));
//		ResRVO1.setResvPeriod(3);
//		ResRVO1.setResvPeople(6);
//		dao.add(ResRVO1);

		// 修改ok
//		ResReservationVO ResRVO2 = new ResReservationVO();
//		
//		ResRVO2.setReservationNo("RV-20200323-002");
//		ResRVO2.setCustId("CUS0000002");
//		ResRVO2.setResNo("REST000002");
//		ResRVO2.setReservationDate(java.sql.Date.valueOf("2020-05-01"));
//		ResRVO2.setResvPeriod(2);
//		ResRVO2.setResvPeople(10);
//		
//		System.out.println(ResRVO2);
//		dao.update(ResRVO2);

//		
//
//		// 前台客戶查詢
		//List<ResReservationVO> RVO3list = dao.findByDate(java.sql.Date.valueOf("2020-04-16"),"REST000001");

		List<ResReservationVO> RVO3list = dao.findByCustomer("CUS0000004");
//
//		// 什麼時候會用"預約編號"查詢單筆??
		// ResReservationVO ResRVO3 = dao.findByPrimaryKey("RV-20200323-005");
		for (ResReservationVO cRes : RVO3list) {
			System.out.print(cRes.getReservationNo() + ",");
			System.out.print(cRes.getCustId() + ",");
			System.out.print(cRes.getResNo() + ",");
			System.out.print(cRes.getReservationDate() + ",");
			System.out.print(cRes.getResvPeriod() + ",");
			System.out.print(cRes.getResvPeople() + ",");
			System.out.print(cRes.getResvStatus() + ",");
			System.out.println();
		}

		System.out.println("\n客戶搜---------------------");
//		
//		// 用日期及時段查詢訂位人數
		int ResRVO4 = dao.findPeopleByPeriod(java.sql.Date.valueOf("2020-04-16"), 1, "REST000001");
//				
		System.out.print("此時段訂為人數為 " + ResRVO4 + "人");

		// boolean full = dao.isPeopleMax(java.sql.Date.valueOf("2020-04-16"), 1,
		// "REST000001");
		ResReservationVO ResRVO2 = new ResReservationVO();
		ResRVO2.setReservationNo("RV-20200428-007");
		ResRVO2.setCustId("CUS0000004");
		ResRVO2.setResNo("REST000001");
		ResRVO2.setReservationDate(java.sql.Date.valueOf("2020-04-28"));
		ResRVO2.setResvPeriod(0);
		ResRVO2.setResvPeople(10);
		System.out.println(ResRVO2);
		int full = dao.doubleOrder(ResRVO2);
		System.out.println(full);
		
		boolean doo = dao.isDoubleOrder(ResRVO2);

		System.out.println("\n--------" + full + "-------"+doo+"------");

		// 查詢全部/餐廳/日期/時段
		//List<ResReservationVO> list = dao.getAll();

//		for (ResReservationVO aRes : list) {
//			System.out.print(aRes.getReservationNo() + ",");
//			System.out.print(aRes.getCustId() + ",");
//			System.out.print(aRes.getResNo() + ",");
//			System.out.print(aRes.getReservationDate() + ",");
//			System.out.print(aRes.getResvPeriod() + ",");
//			System.out.print(aRes.getResvPeople() + ",");
//
//			System.out.println();
//		}
	}

}
