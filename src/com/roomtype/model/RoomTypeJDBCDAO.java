package com.roomtype.model;

import static common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RoomTypeJDBCDAO implements RoomTypeDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO room_type "
			+ "(room_type_no,room_type_name,article,quantity,"
			+ "    person_capacity, add_people, price, total_people,ROOM_Type_STATUS) " + "VALUES (?,?,?,?,?,?,?,?,?)";// 

	private static final String UPDATE_BASIC_STMT = // 9個數
			"UPDATE room_type SET room_type_name = ?, article = ?, quantity = ?, "
					+ "person_capacity = ?, add_people = ?,	price = ?, total_people = ?, "
					+ " room_Type_status = ? WHERE room_type_no = ?";

	private static final String UPDATE_STAR_STMT = // 搜尋資料庫內原有的星號/人數，並加上新的星號/人數 (
			"UPDATE room_type  SET star_amount = " + "(select star_amount from room_type where room_type_no = ?) + ?,"
					+ "STAR_PEOPLE = (select STAR_PEOPLE from room_type where room_type_no = ?) + 1 "
					+ "WHERE  room_type_no = ?";
	private static final String UPDATE_ROOM_STATUS_STMT = "UPDATE  room_type SET ROOM_Type_STATUS = ? WHERE  room_type_no = ?";

	private static final String SELECT_ALL_STMT = "SELECT * FROM ROOM_TYPE";

	private static StringBuffer Magic_Select_Stmt = null;
	Connection con = null;
	PreparedStatement pstmt = null;

	public RoomTypeJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateBasic(RoomTypeVO rtVO) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_BASIC_STMT);
			pstmt.setString(1, rtVO.getRoom_Type_Name());
			pstmt.setString(2, rtVO.getArticle());
			pstmt.setInt(3, rtVO.getQuantity());
			pstmt.setInt(4, rtVO.getPerson_Capacity());
			pstmt.setInt(5, rtVO.getAdd_People());
			pstmt.setInt(6, rtVO.getPrice());
			pstmt.setInt(7, rtVO.getTotal_People());
			pstmt.setInt(8, rtVO.getRoom_Type_Status());
			pstmt.setString(9, rtVO.getRoom_Type_No());

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
	public String insert(RoomTypeVO rtVO) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rtVO.getRoom_Type_No());
			pstmt.setString(2, rtVO.getRoom_Type_Name());
			pstmt.setString(3, rtVO.getArticle());
			pstmt.setInt(4, rtVO.getQuantity());
			pstmt.setInt(5, rtVO.getPerson_Capacity());
			pstmt.setInt(6, rtVO.getAdd_People());
			pstmt.setInt(7, rtVO.getPrice());
			pstmt.setInt(8, rtVO.getTotal_People());
			pstmt.setInt(9, rtVO.getRoom_Type_Status());

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
		return null;
	}

	@Override
	public void updateStars(String room_Type_No, Integer star) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public void updateStatus(String room_Type_No, Integer status) {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ROOM_STATUS_STMT);

			pstmt.setInt(1, status);
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
	public List<RoomTypeVO> getAllBy(Map<String, String[]> map) {
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
		System.out.println(Magic_Select_Stmt);

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
				rtVO.setTotal_People(rs.getInt("Total_People"));
				rtVO.setStar_Amount(rs.getInt("Star_Amount"));
				rtVO.setStar_People(rs.getInt("Star_People"));
				rtVO.setRoom_Type_Status(rs.getInt("Room_Status"));
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
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
				rtVO.setTotal_People(rs.getInt("Total_People"));
				rtVO.setStar_Amount(rs.getInt("Star_Amount"));
				rtVO.setStar_People(rs.getInt("Star_People"));
				rtVO.setRoom_Type_Status(rs.getInt("Room_Status"));
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

	// SELECT * FROM ROOM_TYPE WHERE ROOM_TYPE_NAME
	// in ('精緻雙人房','尊爵雙人房','小島四人房') and PERSON_CAPACITY in (2,4);

//	@Override
//	public List<RoomTypeVO> getAll(Map<String, String[]> map) {
//		
//		return null;
//	}

	public static void main(String[] args) {
		RoomTypeJDBCDAO dao = new RoomTypeJDBCDAO();
		RoomTypeVO rtVO1 = new RoomTypeVO();
		rtVO1.setRoom_Type_No("RT006");
		rtVO1.setRoom_Type_Name("隨緣十人鋪");
		rtVO1.setArticle("人隨緣，品質不隨緣");
		rtVO1.setQuantity(10);
		rtVO1.setPerson_Capacity(8);
		rtVO1.setAdd_People(2);
		rtVO1.setPrice(3000);
		rtVO1.setTotal_People(10);
//		dao.insert(rtVO1);

// 		update Basic Data
		RoomTypeVO rtVO2 = new RoomTypeVO();
		rtVO2.setRoom_Type_No("RT006");
		rtVO2.setRoom_Type_Name("海景四人房");
		rtVO2.setArticle("海");
		rtVO2.setQuantity(20);
		rtVO2.setPerson_Capacity(4);
		rtVO2.setAdd_People(0);
		rtVO2.setPrice(4000);
		rtVO2.setTotal_People(4);
		rtVO2.setRoom_Type_Status(0);
//		dao.updateBasic(rtVO2);

//		update stars/star_people
//		dao.updateStars("RT001",3);
//		dao.updateStatus("RT001",0);
//		
//		List<RoomTypeVO> list = dao.getAll();
//		for(RoomTypeVO vo : list) {
//			System.out.print(vo.getRoom_Type_No() + " ");
//			System.out.print(vo.getRoom_Type_Name() + " ");
//			System.out.print(vo.getArticle() + " ");
//			System.out.print(vo.getQuantity() + " ");
//			System.out.print(vo.getPerson_Capacity() + " ");
//			System.out.print(vo.getAdd_People() + " ");
//			System.out.print(vo.getPrice() + " ");
//			System.out.print(vo.getTotal_People() + " ");
//			System.out.print(vo.getStar_Amount() + " ");
//			System.out.print(vo.getStar_People() + " ");
//			System.out.print(vo.getRoom_Status() + " ");
//			System.out.println();
//		}

		Map<String, String[]> map = new HashMap<>();
		String[] rtn = new String[] { new String("RT001"), new String("RT002") };
		String[] rtName = new String[] { new String("精緻雙人房"), new String("尊爵雙人房") };
		map.put("ROOM_TYPE_NO", rtn);
		map.put("ROOM_TYPE_NAME", rtName);
		List<RoomTypeVO> list1 = dao.getAll();
//		System.out.println(map);
//		for (RoomTypeVO vo : list1) {
//			System.out.println("1" + vo);
//		}
		
		Map<String, String[]> map2 = new HashMap<>();
		map2.put("room_type_no",new String[]{"RT001"});
		System.out.println((dao.getAllBy(map2)));
	}

	@Override
	public RoomTypeVO findOneByNo(String room_Type_No) {
		// TODO Auto-generated method stub
		return null;
	}

}
