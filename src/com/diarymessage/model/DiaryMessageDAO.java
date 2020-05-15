package com.diarymessage.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DiaryMessageDAO implements DiaryMessageDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO diary_message(diary_message_no,cus_id,diary_no,diary_message_date,diary_message_content,diary_message_status) VALUES ('DIME'||LPAD(to_char(SEQ_DIARY_MESSAGE_NO.NEXTVAL),6,'0'), ?, ?, sysdate, ?, ?)";
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, diaryMessageVO.getCus_id());
			pstmt.setString(2, diaryMessageVO.getDiary_no());
//			pstmt.setDate(3, diaryMessageVO.getDiary_message_date());
			pstmt.setString(3, diaryMessageVO.getDiary_message_content());
			pstmt.setInt(4, diaryMessageVO.getDiary_message_status());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		
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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, diaryMessageVO.getCus_id());
			pstmt.setString(2, diaryMessageVO.getDiary_no());
			pstmt.setDate(3, diaryMessageVO.getDiary_message_date());
			pstmt.setString(4, diaryMessageVO.getDiary_message_content());
			pstmt.setInt(5, diaryMessageVO.getDiary_message_status());
			pstmt.setString(6, diaryMessageVO.getDiary_message_no());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		
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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, diary_message_no);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		
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

			
			con = ds.getConnection();
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

			
			con = ds.getConnection();
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
	// 列出本篇網誌的留言
	public List<DiaryMessageVO> getMsgByDiaryno(String diary_no) {
		List<DiaryMessageVO> set = new ArrayList<DiaryMessageVO>();
		DiaryMessageVO diaryMessageVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try { 
	
			con = ds.getConnection();
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
		} catch (SQLException se) {
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
	
//	public List<DiaryMessageVO> getAll_Bydiaryno(String diary_no) {
//		List<DiaryMessageVO> list = new ArrayList<DiaryMessageVO>();
//		DiaryMessageVO diaryMessageVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(LIST_ALL_BYDIARYNO);
//			pstmt.setString(1, diary_no);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO �]�٬� Domain objects
//				diaryMessageVO = new DiaryMessageVO();
//				diaryMessageVO.setDiary_message_no(rs.getString("diary_message_no"));
//				diaryMessageVO.setCus_id(rs.getString("cus_id"));
//				diaryMessageVO.setDiary_no(rs.getString("diary_no"));
//				diaryMessageVO.setDiary_message_date(rs.getDate("diary_message_date"));
//				diaryMessageVO.setDiary_message_content(rs.getString("diary_message_content"));
//				diaryMessageVO.setDiary_message_status(rs.getInt("diary_message_status"));
//				
//				list.add(diaryMessageVO); // Store the row in the vector
//			}
//
//			// Handle any driver errors
//		
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
}	