package com.diarymessage.model;

import java.util.*;
import java.sql.*;

public class DiaryMessageJDBCDAO implements DiaryMessageDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G1";
	String passwd = "DA106G1";

	private static final String INSERT_STMT = 
			"INSERT INTO diary_message(diary_message_no,cus_id,diary_no,diary_message_date,diary_message_content,diary_message_status) VALUES ('DIME'||LPAD(to_char(SEQ_DIARY_MESSAGE_NO.NEXTVAL),6,'0'), ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT diary_message_no,cus_id,diary_no,diary_message_date,diary_message_content,diary_message_status FROM Diary_Message order by diary_message_no";
		private static final String GET_ONE_STMT = 
			"SELECT diary_message_no,cus_id,diary_no,diary_message_date,diary_message_content,diary_message_status FROM Diary_Message where diary_message_no = ? and diary_message_status = 1";
		private static final String DELETE = 
			"DELETE FROM Diary_Message where diary_message_no = ?";
		private static final String UPDATE = 
			"UPDATE Diary_Message set cus_id=?, diary_no=?, diary_message_date=?, diary_message_content=?, diary_message_status=? where diary_message_no = ?";
		//列出該篇日誌的留言
		private static final String LIST_ALL_BYDIARYNO =
			"SELECT diary_message_no,cus_id,diary_no,diary_message_date,diary_message_content,diary_message_status FROM Diary_Message where diary_no =? and diary_message_status = 1 order by diary_message_no";
	@Override
	public int insert(DiaryMessageVO diaryMessageVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, diaryMessageVO.getCus_id());
			pstmt.setString(2, diaryMessageVO.getDiary_no());
			pstmt.setDate(3, diaryMessageVO.getDiary_message_date());
			pstmt.setString(4, diaryMessageVO.getDiary_message_content());
			pstmt.setInt(5, diaryMessageVO.getDiary_message_status());

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
	public int update(DiaryMessageVO diaryMessageVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, diaryMessageVO.getCus_id());
			pstmt.setString(2, diaryMessageVO.getDiary_no());
			pstmt.setDate(3, diaryMessageVO.getDiary_message_date());
			pstmt.setString(4, diaryMessageVO.getDiary_message_content());
			pstmt.setInt(5, diaryMessageVO.getDiary_message_status());
			pstmt.setString(6, diaryMessageVO.getDiary_message_no());

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
	public int delete(String diary_message_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, diary_message_no);
			
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
	public DiaryMessageVO findByPrimaryKey(String diary_message_no) {

		DiaryMessageVO diaryMessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, diary_message_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				diaryMessageVO = new DiaryMessageVO();
				diaryMessageVO.setDiary_message_no(rs.getString("diary_message_no"));
				diaryMessageVO.setCus_id(rs.getString("cus_id"));
				diaryMessageVO.setDiary_no(rs.getString("diary_no"));
				diaryMessageVO.setDiary_message_date(rs.getDate("diary_message_date"));
				diaryMessageVO.setDiary_message_content(rs.getString("diary_message_content"));
				diaryMessageVO.setDiary_message_status(rs.getInt("diary_message_status"));
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
		return diaryMessageVO;
	}

	@Override
	public List<DiaryMessageVO> getAll() {
		List<DiaryMessageVO> list = new ArrayList<DiaryMessageVO>();
		DiaryMessageVO diaryMessageVO = null;

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
				diaryMessageVO = new DiaryMessageVO();
				diaryMessageVO.setDiary_message_no(rs.getString("diary_message_no"));
				diaryMessageVO.setCus_id(rs.getString("cus_id"));
				diaryMessageVO.setDiary_no(rs.getString("diary_no"));
				diaryMessageVO.setDiary_message_date(rs.getDate("diary_message_date"));
				diaryMessageVO.setDiary_message_content(rs.getString("diary_message_content"));
				diaryMessageVO.setDiary_message_status(rs.getInt("diary_message_status"));
				
				list.add(diaryMessageVO); // Store the row in the vector
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
	public List<DiaryMessageVO> getMsgByDiaryno(String diary_no) {
		List<DiaryMessageVO> set = new ArrayList<>();
		DiaryMessageVO diaryMessageVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try { 
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LIST_ALL_BYDIARYNO);
			pstmt.setString(1, diary_no);
			rs = pstmt.executeQuery();

				
			while (rs.next()) {
				diaryMessageVO = new DiaryMessageVO();
				diaryMessageVO.setDiary_message_no(rs.getString("diary_message_no"));
				diaryMessageVO.setCus_id(rs.getString("cus_id"));
				diaryMessageVO.setDiary_no(rs.getString("diary_no"));
				diaryMessageVO.setDiary_message_date(rs.getDate("diary_message_date"));
				diaryMessageVO.setDiary_message_content(rs.getString("diary_message_content"));
				diaryMessageVO.setDiary_message_status(rs.getInt("diary_message_status"));
				set.add(diaryMessageVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	public static void main(String[] args) {

		DiaryMessageJDBCDAO dao = new DiaryMessageJDBCDAO();

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
//		List<DiaryMessageVO> list = dao.getAll();
//		for (DiaryMessageVO diaryMessageVO : list) {
//			System.out.print(diaryMessageVO.getDiary_message_no() + ",");
//			System.out.print(diaryMessageVO.getCus_id() + ",");
//			System.out.print(diaryMessageVO.getDiary_no() + ",");
//			System.out.print(diaryMessageVO.getDiary_message_date() + ",");
//			System.out.print(diaryMessageVO.getDiary_message_content() + ",");
//			System.out.print(diaryMessageVO.getDiary_message_status() + ",");
//			System.out.print("----------------------------");
//			System.out.println();
//		}
//		List<DiaryMessageVO> list = dao.getMsgByDiaryno("DI000002");
//		for (DiaryMessageVO diaryMessageVO : list) {
//			System.out.print(diaryMessageVO.getDiary_message_no() + ",");
//			System.out.print(diaryMessageVO.getCus_id() + ",");
//			System.out.print(diaryMessageVO.getDiary_no() + ",");
//			System.out.print(diaryMessageVO.getDiary_message_date() + ",");
//			System.out.print(diaryMessageVO.getDiary_message_content() + ",");
//			System.out.print(diaryMessageVO.getDiary_message_status() + ",");
//			System.out.print("----------------------------");
//			System.out.println();
//		}
	}
}	

//	@Override
//	public int insert(DiaryMessageVO diaryMessageVO) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int update(DiaryMessageVO diaryMessageVO) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public DiaryMessageVO findByPrimaryKey(Integer diaryMessageVO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int delete(Integer diary_message_no) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int insert(DiaryMessageVO diary_message_no) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int update(DiaryMessageVO diaryMessageVO) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public DiaryMessageVO findByPrimaryKey(String didiaryMessage_noary_no) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}