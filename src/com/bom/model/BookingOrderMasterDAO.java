package com.bom.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
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

import com.bod.model.BookOrderDetailJDBCDAO;
import com.bod.model.BookOrderDetailJNDIDAO;
import com.bod.model.BookOrderDetailVO;
import com.roomtype.model.RoomTypeVO;

public class BookingOrderMasterDAO implements BookingOrderMasterDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "Insert into BOOKING_ORDER_MASTER (B_ORDER_NO, CUS_ID, TOTAL_PRICE, START_DATE, END_DATE, STATUS)"
			+ "    values (?, ? , ? , ? , ? , ? )";// 之後訂單編號要改流水號
	private static final String INSERT_STMT_GeneratedKey = 
	"INSERT INTO booking_order_master ( b_order_no, cus_id, total_price,start_date, end_date, status)  "
	+ "VALUES ('BM-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_B_ORDER_NO.NEXTVAL,'FM000'),?,?,?,?,?)";
	private static final String UPDATE_BOOKING_DATA_STMT = "UPDATE booking_order_master SET "
			+ "total_price = ?,  start_date =  ?,  end_date =  ?, co_time = ?, status = ? WHERE  b_order_no = ?";

	private static final String UPDATE_STATUS = "UPDATE booking_order_master SET  STATUS = ? WHERE b_order_no = ?";
	private static final String DELETE_STMT = "DELETE FROM booking_order_master WHERE b_order_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM booking_order_master WHERE b_order_no = ? ";
	private static final String GET_ALL_STMT = "SELECT * FROM booking_order_master　order by b_order_no desc";
	private static StringBuffer Magic_Select_Stmt = null;
	private static final String GET_ALL_BY_CUS = "SELECT * FROM booking_order_master WHERE cus_id = ? and STATUS=?" ;

	
	@Override
	public void insert(BookingOrderMasterVO bomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, bomVO.getB_Order_No());
			pstmt.setString(2, bomVO.getCus_Id());
			pstmt.setInt(3, bomVO.getTotal_Price());
			pstmt.setDate(4, bomVO.getStart_Date());
			pstmt.setDate(5, bomVO.getEnd_Date());
			pstmt.setInt(6, bomVO.getStatus());
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateBookingData(BookingOrderMasterVO bomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		"UPDATE booking_order_master SET total_price = ?,  start_date =  ?,  end_date =  ? WHERE  b_order_no = ?"
//+ "total_price = ?,  start_date =  ?,  end_date =  ?, co_time = ?, status = ? WHERE  b_order_no = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BOOKING_DATA_STMT);

			pstmt.setInt(1, bomVO.getTotal_Price());
			pstmt.setDate(2, bomVO.getStart_Date());
			pstmt.setDate(3, bomVO.getEnd_Date());
			pstmt.setDate(4, bomVO.getCo_Time());
			pstmt.setInt(5, bomVO.getStatus());
			pstmt.setString(6, bomVO.getB_Order_No());

			System.out.println(pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
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
	public void updateStatus(BookingOrderMasterVO BOMVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// TODO Auto-generated method stub
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, BOMVO.getStatus());
			pstmt.setString(2, BOMVO.getB_Order_No());

			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}
//	@Override
//	public void delete(String  b_Order_No) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		// TODO Auto-generated method stub
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE_STMT);
//			
//			pstmt.setString(1, b_Order_No);
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//	}

	@Override
	public BookingOrderMasterVO findByOrderId(String b_Order_No) {
		BookingOrderMasterVO bomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, b_Order_No);

			System.out.println(pstmt.execute() + "execute");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bomVO = new BookingOrderMasterVO();
				bomVO.setB_Order_No(rs.getString("B_ORDER_NO"));
				bomVO.setCus_Id(rs.getString("CUS_ID"));
				bomVO.setTotal_Price(rs.getInt("TOTAL_PRICE"));
				bomVO.setStart_Date(rs.getDate("START_DATE"));
				bomVO.setEnd_Date(rs.getDate("END_DATE"));
				bomVO.setCo_Time(rs.getDate("CO_TIME"));
				bomVO.setOrder_Date(rs.getDate("ORDER_DATE"));
				bomVO.setStatus(rs.getInt("STATUS"));
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

		return bomVO;
	}

	@Override
	public List<BookingOrderMasterVO> getAll() {
		List<BookingOrderMasterVO> orders = new ArrayList<>();
		BookingOrderMasterVO bomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bomVO = new BookingOrderMasterVO();
				bomVO.setB_Order_No(rs.getString("B_ORDER_NO"));
				bomVO.setCus_Id(rs.getString("CUS_ID"));
				bomVO.setTotal_Price(rs.getInt("TOTAL_PRICE"));
				bomVO.setStart_Date(rs.getDate("START_DATE"));
				bomVO.setEnd_Date(rs.getDate("END_DATE"));
				bomVO.setCo_Time(rs.getDate("CO_TIME"));
				bomVO.setOrder_Date(rs.getDate("ORDER_DATE"));
				bomVO.setStatus(rs.getInt("STATUS"));
				orders.add(bomVO);
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
		// TODO Auto-generated method stub
		return orders;
	}
	
	@Override
	public String insertWithBOD(BookingOrderMasterVO bomVO, List<BookOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_B_Order_No = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "B_ORDER_NO" };
			pstmt = con.prepareStatement(INSERT_STMT_GeneratedKey, cols);
			pstmt.setString(1, bomVO.getCus_Id());
			pstmt.setInt(2, bomVO.getTotal_Price());
			pstmt.setDate(3, bomVO.getStart_Date());
			pstmt.setDate(4, bomVO.getEnd_Date());
			pstmt.setInt(5, bomVO.getStatus());
			System.out.println(pstmt.executeUpdate());
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_B_Order_No = rs.getString(1);
				System.out.println("自增主鍵值= " + next_B_Order_No + "(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			BookOrderDetailJNDIDAO dao = new BookOrderDetailJNDIDAO();

			System.out.println("list.size()-A=" + list.size());
			for (BookOrderDetailVO vo : list) {
				vo.setB_order_no(next_B_Order_No);
				dao.insert2(vo, con);
			}

			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增訂單編號" + next_B_Order_No + "時,共有訂單明細" + list.size() + "筆同時被新增");

		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return next_B_Order_No;

	}

	@Override
	public List<BookingOrderMasterVO> getAllBy(Map<String, String[]> map) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingOrderMasterVO bomVO = null;
		List<BookingOrderMasterVO> list = new ArrayList<>();

		Magic_Select_Stmt = new StringBuffer("SELECT * FROM BOOKING_ORDER_MASTER ");
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Magic_Select_Stmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bomVO = new BookingOrderMasterVO();
				bomVO.setB_Order_No(rs.getString("B_ORDER_NO"));
				bomVO.setCus_Id(rs.getString("CUS_ID"));
				bomVO.setTotal_Price(rs.getInt("TOTAL_PRICE"));
				bomVO.setStart_Date(rs.getDate("START_DATE"));
				bomVO.setEnd_Date(rs.getDate("END_DATE"));
				bomVO.setCo_Time(rs.getDate("CO_TIME"));
				bomVO.setOrder_Date(rs.getDate("ORDER_DATE"));
				bomVO.setStatus(rs.getInt("STATUS"));
				list.add(bomVO);
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
		
		return list;
	}
	
	@Override
	public List<BookingOrderMasterVO> getAllByCus(String cus_id,int status) {
		List<BookingOrderMasterVO> orders = new ArrayList<>();
		BookingOrderMasterVO bomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_CUS);
			
			pstmt.setString(1,cus_id);
			pstmt.setInt(2,status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bomVO = new BookingOrderMasterVO();
				bomVO.setB_Order_No(rs.getString("B_ORDER_NO"));
				bomVO.setCus_Id(rs.getString("CUS_ID"));
				bomVO.setTotal_Price(rs.getInt("TOTAL_PRICE"));
				bomVO.setStart_Date(rs.getDate("START_DATE"));
				bomVO.setEnd_Date(rs.getDate("END_DATE"));
				bomVO.setCo_Time(rs.getDate("CO_TIME"));
				bomVO.setOrder_Date(rs.getDate("ORDER_DATE"));
				bomVO.setStatus(rs.getInt("STATUS"));
				orders.add(bomVO);
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
		// TODO Auto-generated method stub
		return orders;
	}

}
