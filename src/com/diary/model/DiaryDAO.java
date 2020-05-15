package com.diary.model;

import java.util.*;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.diary.model.DiaryVO;
import com.diarymessage.model.DiaryMessageService;
import com.diarymessage.model.DiaryMessageVO;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.*;

public class DiaryDAO implements DiaryDAO_interface {
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
		"INSERT INTO Diary  (diary_no,cus_id,diary_date,diary_title,diary_content,diary_status) "
		+ "VALUES ('DI'||LPAD(to_char(SEQ_DIARY_NO.NEXTVAL),6,'0'), ?, SYSDATE, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Diary order by diary_no";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Diary where diary_status = 1 and diary_no = ?";
	//查詢下架日誌
	private static final String GET_ONEAUDIT_STMT = 
		"SELECT * FROM Diary where diary_no = ?";
	//修改狀態
	private static final String DELETE = 
		"UPDATE Diary SET diary_status = 0 WHERE diary_no = ?";
	//修改日誌內容
	private static final String UPDATE = 
		"UPDATE Diary set diary_title=?, diary_content=? where diary_no = ?";
	//查詢熱門日誌
	private static final String POPULAR = 
		"SELECT COUNT(*) FROM DIARY_MESSAGE WHERE DIARY_NO =?";
	//最新文章
	private static final String GET_NEW_STMT = 
		"SELECT * FROM Diary WHERE diary_status = 1 ORDER BY diary_date DESC";
	//關鍵字查詢
	private static final String GET_SOME_STMT = 
	"SELECT * FROM Diary WHERE diary_status = 1   AND diary_title LIKE (?) ";
	//查詢我的日誌
	private static final String GET_MY_DIARY_STMT =
	"SELECT * FROM Diary WHERE cus_id = ? ORDER BY diary_no DESC";
	@Override
	public int insert(DiaryVO diaryVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, diaryVO.getCus_id());
			pstmt.setString(2, diaryVO.getDiary_title());
			pstmt.setString(3, diaryVO.getDiary_content());
			pstmt.setInt(4, diaryVO.getDiary_status());

			pstmt.executeUpdate();

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
	public void update(DiaryVO diaryVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(1, diaryVO.getCus_id());
//			pstmt.setDate(2, diaryVO.getDiary_date());
			pstmt.setString(1, diaryVO.getDiary_title());
			pstmt.setString(2, diaryVO.getDiary_content());
//			pstmt.setInt(3, diaryVO.getDiary_status());
			pstmt.setString(3, diaryVO.getDiary_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		
	}

	@Override
	public void delete(DiaryVO diaryVO) {
//		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			System.out.println("delete_strat");
			pstmt.setString(1, diaryVO.getDiary_no());
			
			pstmt.executeUpdate();
			System.out.println("delete_finish");
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
//		return updateCount;
	}

	@Override
	public DiaryVO findByPrimaryKey(String diary_no) {

		DiaryVO diaryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, diary_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// diaryVO �]�٬� Domain objects
				diaryVO = new DiaryVO();
				diaryVO.setDiary_no(rs.getString("diary_no"));
				diaryVO.setCus_id(rs.getString("cus_id"));
				diaryVO.setDiary_date(rs.getDate("diary_date"));
				diaryVO.setDiary_title(rs.getString("diary_title"));
				diaryVO.setDiary_content(rs.getString("diary_content"));
				diaryVO.setDiary_status(rs.getInt("diary_status"));
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
		return diaryVO;
	}
	//查詢下架日誌
	public DiaryVO getAuditedOne(String diary_no) {

		DiaryVO diaryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEAUDIT_STMT);
			
			pstmt.setString(1, diary_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// diaryVO �]�٬� Domain objects
				diaryVO = new DiaryVO();
				diaryVO.setDiary_no(rs.getString("diary_no"));
				diaryVO.setCus_id(rs.getString("cus_id"));
				diaryVO.setDiary_date(rs.getDate("diary_date"));
				diaryVO.setDiary_title(rs.getString("diary_title"));
				diaryVO.setDiary_content(rs.getString("diary_content"));
				diaryVO.setDiary_status(rs.getInt("diary_status"));
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
		return diaryVO;
	}

	@Override
	public List<DiaryVO> getAll() {
		List<DiaryVO> list = new ArrayList<DiaryVO>();
		DiaryVO diaryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// diaryVO �]�٬� Domain objects
				diaryVO = new DiaryVO();
				diaryVO.setDiary_no(rs.getString("diary_no"));
				diaryVO.setCus_id(rs.getString("cus_id"));
				diaryVO.setDiary_date(rs.getDate("diary_date"));
				diaryVO.setDiary_title(rs.getString("diary_title"));
				diaryVO.setDiary_content(rs.getString("diary_content"));
				diaryVO.setDiary_status(rs.getInt("diary_status"));
				list.add(diaryVO); // Store the row in the vector
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
	//搜尋最新文章
		@Override
		public List<DiaryVO> getLatestDiaries(){
			List<DiaryVO> latestList = new ArrayList<DiaryVO>();
			DiaryVO diaryVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_NEW_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					diaryVO = new DiaryVO();
					diaryVO.setDiary_no(rs.getString("diary_no"));
					diaryVO.setCus_id(rs.getString("cus_id"));
					diaryVO.setDiary_date(rs.getDate("diary_date"));
					diaryVO.setDiary_title(rs.getString("diary_title"));
					diaryVO.setDiary_content(rs.getString("diary_content"));
					diaryVO.setDiary_status(rs.getInt("diary_status"));
					latestList.add(diaryVO); // Store the row in the vector
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
			return latestList;
		}
		//搜尋熱門文章
		public List<DiaryVO> getPopularDiaries(){
			DiaryMessageService DMSvc = new DiaryMessageService();
			List<DiaryMessageVO> list = DMSvc.getAll();
			List<DiaryVO> popularList = new ArrayList<>();
			Map<String,Integer> countMap =new TreeMap<String, Integer>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_SOME_STMT);
				rs = pstmt.executeQuery();

	
			
			for(DiaryMessageVO dm: list) {
				String diary_no = dm.getDiary_no();
				if(countMap.containsKey(diary_no)) {
					int count = countMap.get(diary_no);
					count++;
					countMap.put(diary_no, count);
				}else {
					countMap.put(diary_no, 1);
				}
			}
			
			//這裡將map.entrySet()轉換成treelist
			List<Map.Entry<String,Integer>> treeList = new ArrayList<Map.Entry<String,Integer>>(countMap.entrySet());
			//然後通過比較器來實現排序
			Collections.sort(treeList,new Comparator<Map.Entry<String,Integer>>(){
			//降序排序
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return -(o1.getValue().compareTo(o2.getValue()));
			}
			});
			for(int i = 0; i < 5; i++) {
				String diary_no = treeList.get(i).getKey();
				DiaryService diarySvc = new DiaryService();
				popularList.add(diarySvc.getOneDiary(diary_no));
			}
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
			return popularList;			
		}
		// 新增方法"關鍵字查一些"
		@Override
		public List<DiaryVO> getSome(String diary_key_name) {
			List<DiaryVO> list = new ArrayList<DiaryVO>();
			DiaryVO diaryVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_SOME_STMT);
				
				String diarykey = "%"+diary_key_name + "%";
				pstmt.setString(1, diarykey);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					diaryVO = new DiaryVO();
					diaryVO.setDiary_no(rs.getString("diary_no"));
					diaryVO.setCus_id(rs.getString("cus_id"));
					diaryVO.setDiary_date(rs.getDate("diary_date"));
					diaryVO.setDiary_title(rs.getString("diary_title"));
					diaryVO.setDiary_content(rs.getString("diary_content"));
					diaryVO.setDiary_status(rs.getInt("diary_status"));
					list.add(diaryVO);
				}

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		// 查詢我的日誌
		@Override
		public List<DiaryVO> getMyDiary(String cus_id) {

			List<DiaryVO> list = new ArrayList<DiaryVO>();
			DiaryVO diaryVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MY_DIARY_STMT);
				pstmt.setString(1, cus_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					diaryVO = new DiaryVO();
					diaryVO.setDiary_no(rs.getString("diary_no"));
					diaryVO.setCus_id(rs.getString("cus_id"));
					diaryVO.setDiary_date(rs.getDate("diary_date"));
					diaryVO.setDiary_title(rs.getString("diary_title"));
					diaryVO.setDiary_content(rs.getString("diary_content"));
					diaryVO.setDiary_status(rs.getInt("diary_status"));
					list.add(diaryVO);
				}

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
}
	

		 //新增
//		DiaryVO diaryVO1 = new DiaryVO();
//		diaryVO1.setCus_id("CUS0000001");
//		diaryVO1.setDiary_title("這是標題");
//		diaryVO1.setDiary_content("這是內容");
//		diaryVO1.setDiary_status(0);
//		
//		 int updateCount_insert = dao.insert(diaryVO1);
//		 System.out.println(updateCount_insert);
				

		 // 修改
//		DiaryVO diaryVO2 = new DiaryVO();
//		diaryVO2.setCus_id("CUS0000002");
//		diaryVO2.setDiary_date(java.sql.Date.valueOf("2020-03-31"));
//		diaryVO2.setDiary_title("這是標題");
//		diaryVO2.setDiary_content("修改內容");
//		diaryVO2.setDiary_status(0);
//		diaryVO2.setDiary_no("DI000002");
//		
//		 int updateCount_update = dao.update(diaryVO2);
//		 System.out.println(updateCount_update);
				

		 // 刪除
//		 int updateCount_delete = dao.delete("DI000002");
//		 System.out.println(updateCount_delete);

		// 查詢
//		DiaryVO diaryVO3 = dao.findByPrimaryKey("DI000001");
//		System.out.print(diaryVO3.getDiary_no() + ",");
//		System.out.print(diaryVO3.getCus_id() + ",");
//		System.out.print(diaryVO3.getDiary_date() + ",");
//		System.out.print(diaryVO3.getDiary_title() + ",");
//		System.out.print(diaryVO3.getDiary_content() + ",");
//		System.out.println(diaryVO3.getDiary_status() + ",");
//		System.out.println("---------------------");
//
//		// 顯示列表
		
