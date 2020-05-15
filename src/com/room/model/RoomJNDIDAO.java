package com.room.model;

import java.sql.*;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.util.jdbcUtil_CompositeQuery_Emp2;

public class RoomJNDIDAO implements RoomDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO ROOM ( room_id, room_type_no, room_no) VALUES ('RM'||TO_CHAR(SEQ_ROOM_ID.NEXTVAL,'FM000'),?,?)";
	private static final String GET_ALL_STMT = "SELECT room_id,ROOM_TYPE_NO,room_no,room_status,clean_status,cus_id,tenant_name,tenant_phone,b_order_no FROM room order by room_no";
	private static final String UPDATE = "UPDATE room set ROOM_TYPE_NO=?, room_no=? where room_id=? ";
	private static final String UPDATEROOM = "update room set room_status =?  where room_no=?";
	private static final String OFSHELLROOM = "update room set room_status =5  where room_type_no=?";
	private static final String UPDATECLEAN = "UPDATE room set clean_status=? where room_no=?";
	private static final String UPDATECUS = "UPDATE room set cus_id=?,tenant_name=?,tenant_phone=?,b_order_no=? where room_no = ?";
	
	private static final StringBuffer CHECKOUTSTMT = new StringBuffer("UPDATE room SET room_status = 0, clean_status = 2, cus_id = \'\', "
																	+ "tenant_name = \'\', tenant_phone = \'\', b_order_no = \'\' WHERE room_no in ( "); 
	
	private static final String GET_ONE_STATUS = "SELECT room_id,ROOM_TYPE_NO,room_no,room_status,clean_status,cus_id,tenant_name,tenant_phone ,b_order_no from room WHERE room_id=?";

	private static final String GET_ROOM_BY_COUNT = "select * from ROOM where room_type_no = ? and ROWNUM <=(SELECT QUANTITY FROM BOOKING_ORDER_DETAIL WHERE ROOM_TYPE_NO=? AND B_ORDER_NO=?) AND (ROOM_STATUS =0 OR ROOM_STATUS=4) AND (CLEAN_STATUS = 1)";

	private static final String GET_ROOM_BY_B_ORDER_NO = "SELECT * FROM room WHERE b_order_no=?";

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
	public int insert(RoomVO roomVo) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);) {

			pstmt.setString(1, roomVo.getRoom_type_no());
			pstmt.setString(2, roomVo.getRoom_no());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return updateCount;
	}
	
	

//	private static final StringBuffer CHECKOUTSTMT = new StringBuffer("UPDATE room SET room_status = 0, clean_status = 2, cus_id = \"\", "
//																	+ "	tenant_name = \"\", tenant_phone = \"\", b_order_no = \"\" WHERE  "); 
//	
	@Override
	public void checkOut(List<String> roomNoList) {
		// TODO Auto-generated method stub
//		CHECKOUTSTMT
		int length = roomNoList.size();
		for(int i = 0; i < length; i++) {
			CHECKOUTSTMT.append("\'" + roomNoList.get(i) + "\'");
			if(i < length - 1) {
				CHECKOUTSTMT.append(" , ");
			}else {
				CHECKOUTSTMT.append(" ) ");
				
			}
		}
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(CHECKOUTSTMT.toString());) {
			System.out.println(CHECKOUTSTMT);
			System.out.println("checkout筆數 = " + pstmt.executeUpdate());
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void updateRoomData(RoomVO roomVo) {
		// TODO Auto-generated method stub
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE);) {
			pstmt.setString(1, roomVo.getRoom_type_no());
			pstmt.setString(2, roomVo.getRoom_no());
			pstmt.setString(3, roomVo.getRoom_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public List<RoomVO> getAll() {
		// TODO Auto-generated method stub
		List<RoomVO> list = new ArrayList<RoomVO>();
		ResultSet rs = null;

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);) {

			rs = ps.executeQuery();

			while (rs.next()) {
				RoomVO room = new RoomVO();
				room.setRoom_id(rs.getString("room_id"));
				room.setRoom_type_no(rs.getString("room_type_no"));
				room.setRoom_no(rs.getString("room_no"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setClean_status(rs.getInt("clean_status"));
				room.setCus_id(rs.getString("cus_id"));
				room.setTenant_name(rs.getString("tenant_name"));
				room.setTenant_phone(rs.getString("tenant_phone"));
				room.setB_order_no(rs.getString("b_order_no"));
				list.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomVO> getSearch(Map<String, String[]> map) {
		// TODO Auto-generated method stub

		List<RoomVO> list = new ArrayList<>();
		RoomVO room = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from room " + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
					+ "order by room_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room = new RoomVO();
				room.setRoom_id(rs.getString("room_id"));
				room.setRoom_type_no(rs.getString("room_type_no"));
				room.setRoom_no(rs.getString("room_no"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setClean_status(rs.getInt("clean_status"));
				room.setCus_id(rs.getString("cus_id"));
				room.setTenant_name(rs.getString("tenant_name"));
				room.setTenant_phone(rs.getString("tenant_phone"));
				room.setB_order_no(rs.getString("b_order_no"));
				list.add(room);
			}

			// Handle any SQL errors
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

	@Override
	public RoomVO findByPrimaryKey(String room_id) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		RoomVO room = null;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(GET_ONE_STATUS);) {
			pstmt.setString(1, room_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room = new RoomVO();
				room.setRoom_id(rs.getString("room_id"));
				room.setRoom_type_no(rs.getString("room_type_no"));
				room.setRoom_no(rs.getString("room_no"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setClean_status(rs.getInt("clean_status"));
				room.setCus_id(rs.getString("cus_id"));
				room.setTenant_name(rs.getString("tenant_name"));
				room.setTenant_phone(rs.getString("tenant_phone"));
				room.setB_order_no(rs.getString("b_order_no"));
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return room;
	}

	@Override
	public void updateRoomStatus(RoomVO roomVo) {
		// TODO Auto-generated method stub
		// "UPDATE room set room_status=? where room_no = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEROOM);

			pstmt.setInt(1, roomVo.getRoom_status());
			pstmt.setString(2, roomVo.getRoom_no());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sse) {
					sse.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	@Override
	public void ofShellRoomStatus(String room_Type_No) {
		// TODO Auto-generated method stub
		// "UPDATE room set room_status=? where room_no = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(OFSHELLROOM);
			
			pstmt.setString(1, room_Type_No);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sse) {
					sse.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public void updateRoomCleanStatus(RoomVO roomVo) {
		// TODO Auto-generated method stub
		// clean_status=? where room_no=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATECLEAN);

			pstmt.setInt(1, roomVo.getClean_status());
			pstmt.setString(2, roomVo.getRoom_no());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sse) {
					sse.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public void updateRoomCus(RoomVO roomVo) {
		// TODO Auto-generated method stub
		// cus_id,tenant_name,tenant_phone where room_no = ?
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATECUS);

			pstmt.setString(1, roomVo.getCus_id());
			pstmt.setString(2, roomVo.getTenant_name());
			pstmt.setString(3, roomVo.getTenant_phone());
			pstmt.setString(4, roomVo.getB_order_no());
			pstmt.setString(5, roomVo.getRoom_no());

			pstmt.executeUpdate();
		}

		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException sse) {
					sse.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public List<RoomVO> getRoomByCount(String room_type_no, String b_order_no) {
		// TODO Auto-generated method stub
		List<RoomVO> list = new ArrayList<RoomVO>();
		ResultSet rs = null;

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ROOM_BY_COUNT);) {
			ps.setString(1, room_type_no);
			ps.setString(2, room_type_no);
			ps.setString(3, b_order_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				RoomVO room = new RoomVO();
				room.setRoom_id(rs.getString("room_id"));
				room.setRoom_type_no(rs.getString("room_type_no"));
				room.setRoom_no(rs.getString("room_no"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setClean_status(rs.getInt("clean_status"));
				room.setCus_id(rs.getString("cus_id"));
				room.setTenant_name(rs.getString("tenant_name"));
				room.setTenant_phone(rs.getString("tenant_phone"));
				list.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomVO> getRoomByBorderNo(String b_order_no) {
		// TODO Auto-generated method stub
		List<RoomVO> list = new ArrayList<RoomVO>();
		ResultSet rs = null;

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ROOM_BY_B_ORDER_NO);) {

			ps.setString(1, b_order_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				RoomVO room = new RoomVO();
				room.setRoom_id(rs.getString("room_id"));
				room.setRoom_type_no(rs.getString("room_type_no"));
				room.setRoom_no(rs.getString("room_no"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setClean_status(rs.getInt("clean_status"));
				room.setCus_id(rs.getString("cus_id"));
				room.setTenant_name(rs.getString("tenant_name"));
				room.setTenant_phone(rs.getString("tenant_phone"));
				room.setB_order_no(rs.getString("b_order_no"));
				list.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}