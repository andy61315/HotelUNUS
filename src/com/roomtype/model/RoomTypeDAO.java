package com.roomtype.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomTypeDAO implements RoomTypeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO room_type "
			+ "(room_type_no,room_type_name,article, person_capacity, add_people, price, holiday_price, workingday_price, total_people,ROOM_TYPE_STATUS) " 
			+ "VALUES ('RT'||TO_CHAR(SEQ_ROOM_TYPE_NO.NEXTVAL,'FM000'),?,?,?,?,?,?,?,?,?)";// 

	private static final String UPDATE_BASIC_STMT = // 
			"UPDATE room_type SET room_type_name = ?, article = ?,  "
					+ "person_capacity = ?, add_people = ?,	price = ?,  holiday_price = ?, workingday_price = ?, total_people = ?, "
					+ " room_Type_status = ? WHERE room_type_no = ?";

	private static final String UPDATE_STAR_STMT = // 搜尋資料庫內原有的星號/人數，並加上新的星號/人數 (
			"UPDATE room_type  SET star_amount = " + "(select star_amount from room_type where room_type_no = ?) + ?,"
					+ "STAR_PEOPLE = (select STAR_PEOPLE from room_type where room_type_no = ?) + 1 "
					+ "WHERE  room_type_no = ?";
	private static final String UPDATE_ROOM_STATUS_STMT = "UPDATE  room_type SET ROOM_Type_STATUS = ? WHERE  room_type_no = ?";

	private static final String SELECT_ALL_STMT = "SELECT * FROM ROOM_TYPE";

	private static final String FIND_ONE_STMT =  "SELECT * FROM ROOM_TYPE WHERE room_type_no = ?";

	private static final String FIND_SIMILAR_STMT =  "SELECT * FROM ROOM_TYPE WHERE Person_Capacity = ?";
	
	private static StringBuffer Magic_Select_Stmt = null;
	


	@Override
	public String insert(RoomTypeVO rtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_Room_Type_No = null;
		try {
			String cols[] = { "room_type_no" };
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, rtVO.getRoom_Type_Name());
			pstmt.setString(2, rtVO.getArticle());
			pstmt.setInt(3, rtVO.getPerson_Capacity());
			pstmt.setInt(4, rtVO.getAdd_People());
			pstmt.setInt(5, rtVO.getPrice());
			pstmt.setInt(6, rtVO.getHoliday_Price());
			pstmt.setInt(7, rtVO.getWorkingDay_Price());
			pstmt.setInt(8, rtVO.getTotal_People());
			pstmt.setInt(9, rtVO.getRoom_Type_Status());

			System.out.println(pstmt.executeUpdate());
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next())
				next_Room_Type_No = rs.getString(1);
			System.out.println("新房型PK為 " + next_Room_Type_No);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return next_Room_Type_No;
	}

	@Override
	public void updateBasic(RoomTypeVO rtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BASIC_STMT);
			pstmt.setString(1, rtVO.getRoom_Type_Name());
			pstmt.setString(2, rtVO.getArticle());
			//pstmt.setInt(3, rtVO.getQuantity());//這個另外在SQL處理
			pstmt.setInt(3, rtVO.getPerson_Capacity());
			pstmt.setInt(4, rtVO.getAdd_People());
			pstmt.setInt(5, rtVO.getPrice());
			pstmt.setInt(6, rtVO.getHoliday_Price());
			pstmt.setInt(7, rtVO.getWorkingDay_Price());
			pstmt.setInt(8, rtVO.getTotal_People());
			pstmt.setInt(9, rtVO.getRoom_Type_Status());
			pstmt.setString(10, rtVO.getRoom_Type_No());

			System.out.println("成功上傳筆數 : " + pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updateStars(String room_Type_No, Integer star) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAR_STMT);

			pstmt.setString(1, room_Type_No);
			pstmt.setInt(2, star);
			pstmt.setString(3, room_Type_No);
			pstmt.setString(4, room_Type_No);

			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void updateStatus(String room_Type_No, Integer room_Type_Status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ROOM_STATUS_STMT);

			pstmt.setInt(1, room_Type_Status);
			pstmt.setString(2, room_Type_No);

			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	
	@Override
	public RoomTypeVO findOneByNo(String room_Type_No) {
		ResultSet rs = null;
		RoomTypeVO rtVO = new RoomTypeVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_STMT);
			pstmt.setString(1, room_Type_No);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				rtVO.setRoom_Type_No(rs.getString("ROOM_TYPE_NO"));
				rtVO.setRoom_Type_Name(rs.getString("ROOM_TYPE_NAME"));
				rtVO.setArticle(rs.getString("ARTICLE"));
				rtVO.setQuantity(rs.getInt("QUANTITY"));
				rtVO.setPerson_Capacity(rs.getInt("PERSON_CAPACITY"));
				rtVO.setAdd_People(rs.getInt("ADD_PEOPLE"));
				rtVO.setPrice(rs.getInt("Price"));
				rtVO.setHoliday_Price(rs.getInt("HOLIDAY_PRICE"));
				rtVO.setWorkingDay_Price(rs.getInt("WORKINGDAY_PRICE"));
				rtVO.setTotal_People(rs.getInt("Total_People"));
				rtVO.setStar_Amount(rs.getInt("Star_Amount"));
				rtVO.setStar_People(rs.getInt("Star_People"));
				rtVO.setRoom_Type_Status(rs.getInt("Room_Type_Status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rtVO;
	}
	
	
	@Override
	public List<RoomTypeVO> getAllBy(Map<String, String[]> map) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomTypeVO rtVO = null;
		List<RoomTypeVO> list = new ArrayList<>();

		Magic_Select_Stmt = new StringBuffer("SELECT * FROM ROOM_TYPE ");
		String[] valueArray = null;
		Iterator<String> iterator = map.keySet().iterator();
		if(iterator.hasNext()) {
//			boolean ifHasCondition = true;//用來加where
			//
			Magic_Select_Stmt.append(" WHERE ");
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Magic_Select_Stmt.append(key + " in ( ");
				valueArray = map.get(key);
				for (int i = 0; i < valueArray.length; i++) {
					Magic_Select_Stmt.append("\'" + valueArray[i] + "\'" + ((i == valueArray.length - 1) ? "" : ","));
				}
				Magic_Select_Stmt.append(") " + (iterator.hasNext() ? " and " : ""));
			}
		}
//		System.out.println(Magic_Select_Stmt);

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Magic_Select_Stmt.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				rtVO = new RoomTypeVO();
				rtVO.setRoom_Type_No(rs.getString("ROOM_TYPE_NO"));
				rtVO.setRoom_Type_Name(rs.getString("ROOM_TYPE_NAME"));
				rtVO.setArticle(rs.getString("ARTICLE"));
				rtVO.setQuantity(rs.getInt("QUANTITY"));
				rtVO.setPerson_Capacity(rs.getInt("PERSON_CAPACITY"));
				rtVO.setAdd_People(rs.getInt("ADD_PEOPLE"));
				rtVO.setPrice(rs.getInt("Price"));
				rtVO.setHoliday_Price(rs.getInt("HOLIDAY_PRICE"));
				rtVO.setWorkingDay_Price(rs.getInt("WORKINGDAY_PRICE"));
				rtVO.setTotal_People(rs.getInt("Total_People"));
				rtVO.setStar_Amount(rs.getInt("Star_Amount"));
				rtVO.setStar_People(rs.getInt("Star_People"));
				rtVO.setRoom_Type_Status(rs.getInt("Room_Type_Status"));
				list.add(rtVO);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<RoomTypeVO> getAll() {
		ResultSet rs = null;
		RoomTypeVO rtVO = null;
		List<RoomTypeVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				rtVO = new RoomTypeVO();
				rtVO.setRoom_Type_No(rs.getString("ROOM_TYPE_NO"));
				rtVO.setRoom_Type_Name(rs.getString("ROOM_TYPE_NAME"));
				rtVO.setArticle(rs.getString("ARTICLE"));
				rtVO.setQuantity(rs.getInt("QUANTITY"));
				rtVO.setPerson_Capacity(rs.getInt("PERSON_CAPACITY"));
				rtVO.setAdd_People(rs.getInt("ADD_PEOPLE"));
				rtVO.setPrice(rs.getInt("Price"));
				rtVO.setHoliday_Price(rs.getInt("HOLIDAY_PRICE"));
				rtVO.setWorkingDay_Price(rs.getInt("WORKINGDAY_PRICE"));
				rtVO.setTotal_People(rs.getInt("Total_People"));
				rtVO.setStar_Amount(rs.getInt("Star_Amount"));
				rtVO.setStar_People(rs.getInt("Star_People"));
				rtVO.setRoom_Type_Status(rs.getInt("Room_Type_Status"));
				list.add(rtVO);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}


}
