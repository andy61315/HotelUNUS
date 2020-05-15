package com.diaryreport.model;

import java.util.*;
import java.sql.*;

public class DiaryReportJDBCDAO implements DiaryReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G1";
	String passwd = "DA106G1";

	private static final String INSERT_STMT = 
		"INSERT INTO diaryreport(diary_report_no,cus_id,diary_no,report_project,report_date,diary_report_status) VALUES ('dire'||LPAD(to_char(SQL_DIARY_REPORT.NEXTVAL),6,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT diary_report_no,cus_id,diary_no,report_project,report_date,diary_report_status FROM DiaryReport order by diary_report_no";
	private static final String GET_ONE_STMT = 
		"SELECT diary_report_no,cus_id,diary_no,report_project,report_date,diary_report_status FROM DiaryReport where diary_report_no = ?";
	private static final String DELETE = 
		"DELETE FROM DiaryReport where diary_report_no = ?";
	private static final String UPDATE = 
		"UPDATE DiaryReport set cus_id=?, diary_no=?, report_project=?, report_date=?, diary_report_status=? where diary_report_no = ?";

	@Override
	public int insert(DiaryReportVO diaryReportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, diaryReportVO.getCus_id());
			pstmt.setString(2, diaryReportVO.getDiary_no());
			pstmt.setInt(3, diaryReportVO.getReport_project());
			pstmt.setDate(4, diaryReportVO.getReport_date());
			pstmt.setInt(5, diaryReportVO.getDiary_report_status());
			

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int update(DiaryReportVO diaryReportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, diaryReportVO.getCus_id());
			pstmt.setString(2, diaryReportVO.getDiary_no());
			pstmt.setInt(3, diaryReportVO.getReport_project());
			pstmt.setDate(4, diaryReportVO.getReport_date());
			pstmt.setInt(5, diaryReportVO.getDiary_report_status());
			pstmt.setString(6, diaryReportVO.getDiary_report_no());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int delete(String diary_report_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, diary_report_no);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public DiaryReportVO findByPrimaryKey(String diary_report_no) {

		DiaryReportVO diaryReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, diary_report_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				diaryReportVO = new DiaryReportVO();
				diaryReportVO.setDiary_report_no(rs.getString("diary_report_no"));
				diaryReportVO.setCus_id(rs.getString("cus_id"));
				diaryReportVO.setDiary_no(rs.getString("diary_no"));
				diaryReportVO.setReport_project(rs.getInt("diary_message_date"));
				diaryReportVO.setReport_date(rs.getDate("diary_message_content"));
				diaryReportVO.setDiary_report_status(rs.getInt("diary_message_status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return diaryReportVO;
	}

	@Override
	public List<DiaryReportVO> getAll() {
		List<DiaryReportVO> list = new ArrayList<DiaryReportVO>();
		DiaryReportVO diaryReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				diaryReportVO = new DiaryReportVO();
				diaryReportVO.setDiary_report_no(rs.getString("diary_report_no"));
				diaryReportVO.setCus_id(rs.getString("cus_id"));
				diaryReportVO.setDiary_no(rs.getString("diary_no"));
				diaryReportVO.setReport_project(rs.getInt("diary_message_date"));
				diaryReportVO.setReport_date(rs.getDate("diary_message_content"));
				diaryReportVO.setDiary_report_status(rs.getInt("diary_message_status"));
				
				list.add(diaryReportVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		DiaryReportJDBCDAO dao = new DiaryReportJDBCDAO();

		 //新增
//		DiaryMessageVO diaryMessageVO = new DiaryMessageVO();
//		diaryMessageVO.setCus_id("3");
//		diaryMessageVO.setDiary_no("2");
//		diaryMessageVO.setDiary_message_date(java.sql.Date.valueOf("2005-01-01"));
//		diaryMessageVO.setDiary_message_content("這是內容");
//		diaryMessageVO.setDiary_message_status(0);
//		
//		 int updateCount_insert = dao.insert(diaryMessageVO);
//		 System.out.println(updateCount_insert);
				

		 // 修改
//		 EmpVO empVO2 = new EmpVO();
//		 empVO2.setEmpno(7014);
//		 empVO2.setEname("�d�ç�2");
//		 empVO2.setJob("MANAGER2");
//		 empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		 empVO2.setSal(new Double(20000));
//		 empVO2.setComm(new Double(200));
//		 empVO2.setDeptno(20);
//		 int updateCount_update = dao.update(empVO2);
//		 System.out.println(updateCount_update);
				

		 // 刪除
//		 int updateCount_delete = dao.delete(7015);
//		 System.out.println(updateCount_delete);

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");
//
//		// 顯示列表
		List<DiaryReportVO> list = dao.getAll();
		for (DiaryReportVO diaryReportVO : list) {
			System.out.print(diaryReportVO.getDiary_report_no() + ",");
			System.out.print(diaryReportVO.getCus_id() + ",");
			System.out.print(diaryReportVO.getDiary_no() + ",");
			System.out.print(diaryReportVO.getReport_project() + ",");
			System.out.print(diaryReportVO.getReport_date() + ",");
			System.out.print(diaryReportVO.getDiary_report_no() + ",");
			System.out.print("----------------------------");
			System.out.println();
		}
	}
}	
