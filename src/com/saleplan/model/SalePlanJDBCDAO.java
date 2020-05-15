package com.saleplan.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.*;
import java.sql.Date;
import java.util.*;


import com.sod.model.SaleOrderDetailJDBCDAO;
import com.sod.model.SaleOrderDetailVO;



public class SalePlanJDBCDAO implements SalePlanDAO_interface{
	private static final String INSERT_STMT = 
			"INSERT INTO SALEPLAN (SAPL_NO, SAPL_NAME,DETAIL,START_DATE,END_DATE,SAPL_DISCOUNT,STATUS) VALUES ('SALP'||TO_CHAR(SEQ_ROOM_ID.NEXTVAL,'FM0000'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT SAPL_NO,SAPL_NAME,DETAIL,START_DATE,END_DATE ,SAPL_DISCOUNT,STATUS FROM SALEPLAN ";
	private static final String UPDATE = 
			"UPDATE SALEPLAN set SAPL_NAME=?, DETAIL=?, START_DATE=? ,END_DATE=? ,SAPL_DISCOUNT=? ,STATUS=? where SAPL_NO = ?";
	private static final String GET_ONE_STMT ="SELECT * FROM SALEPLAN where SAPL_NO = ? ";
	private static final String GET_ONE_STMT_FROM_NAME="SELECT * FROM SALEPLAN WHERE SAPL_NAME LIKE ?";
	private static final String DELETE_SOD = "DELETE FROM SALE_ORDER_DETAIL where SAPL_NO = ?";
	private static final String DELETE_SPL = "DELETE FROM SALEPLAN where SAPL_NO = ?";
	public SalePlanJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public SalePlanVO findByRoomType(String room_Type_No, Date in_Date, Date out_Date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SalePlanVO saleplan) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		Connection con = null;

		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
			
			
			pstmt.setString(1, saleplan.getSapl_name());
			pstmt.setString(2,saleplan.getDetail());
			pstmt.setDate(3, saleplan.getStart_date());
			pstmt.setDate(4,saleplan.getEnd_date());
			pstmt.setDouble(5,saleplan.getSapl_discount());
			pstmt.setInt(6,saleplan.getStatus());


			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		return updateCount;
	}

	@Override
	public boolean update(SalePlanVO saleplan) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATE);) {
			
			
			
			pstmt.setString(1, saleplan.getSapl_name());
			pstmt.setString(2,saleplan.getDetail());
			pstmt.setDate(3, saleplan.getStart_date());
			pstmt.setDate(4,saleplan.getEnd_date());
			pstmt.setDouble(5,saleplan.getSapl_discount());
			pstmt.setInt(6,saleplan.getStatus());
			pstmt.setString(7, saleplan.getSapl_no());
			
			rowCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}


	@Override
	public List<SalePlanVO> getAll() {
		// TODO Auto-generated method stub
		List<SalePlanVO> list=new ArrayList<SalePlanVO>();
		ResultSet rs = null;
	
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);)
		{	
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SalePlanVO salename=new SalePlanVO();
				salename.setSapl_no(rs.getString("sapl_no"));
				salename.setSapl_name(rs.getString("sapl_name"));
				salename.setDetail(rs.getString("detail"));
				salename.setStart_date(rs.getDate("start_date"));
				salename.setEnd_date(rs.getDate("end_date"));
				salename.setSapl_discount(rs.getDouble("sapl_discount"));
				salename.setStatus(rs.getInt("status"));
				list.add(salename);
		}}catch(SQLException e) 
		{e.printStackTrace();}
		return list;
	}

	private static StringBuffer Magic_Select_Stmt=null;
	@Override
	public List<SalePlanVO> getSreach(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List<SalePlanVO> list = new ArrayList<>();
		Magic_Select_Stmt = new StringBuffer("SELECT * FROM SALEPLAN WHERE ");
		String[] valueArray = null;
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key =(String) iterator.next();
			Magic_Select_Stmt.append(key+" in (");
			valueArray = map.get(key);
			for(int i = 0; i < valueArray.length; i++) {
				Magic_Select_Stmt.append("\'" + valueArray[i] + "\'" + ((i == valueArray.length-1)?"":","));
			}
			Magic_Select_Stmt.append(") " + (iterator.hasNext()? " and " : ""));
		}
		System.out.println(Magic_Select_Stmt);	
		
		try(Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(Magic_Select_Stmt.toString());){
			
		rs = pstmt.executeQuery();
		while(rs.next()) {
			SalePlanVO salename=new SalePlanVO();
			salename.setSapl_no(rs.getString("sapl_no"));
			salename.setSapl_name(rs.getString("sapl_name"));
			salename.setDetail(rs.getString("detail"));
			salename.setStart_date(rs.getDate("start_date"));
			salename.setEnd_date(rs.getDate("end_date"));
			salename.setSapl_discount(rs.getDouble("sapl_discount"));
			salename.setStatus(rs.getInt("status"));
			list.add(salename);

		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
		
}

	
	
	
	public static void main(String[] args) {
	
		SalePlanJDBCDAO sale=new SalePlanJDBCDAO();
		SalePlanVO salename = new SalePlanVO();
		//SALP0001	春季優惠	四人房一律85折(限平日)	09-MAR-20	09-MAY-20	0.9	1	
			double discount = 0.88;
			salename.setSapl_name("兒童節優惠");
			salename.setDetail("四人房一律88折");
			salename.setStart_date(java.sql.Date.valueOf("2020-04-01"));
			salename.setEnd_date(java.sql.Date.valueOf("2020-04-12"));
			salename.setSapl_discount( 0.88);
			salename.setStatus(1);
			
		 
			List<SaleOrderDetailVO> addRoomType = new ArrayList<SaleOrderDetailVO>();
			
			SaleOrderDetailVO sod1=new SaleOrderDetailVO();
			sod1.setRoom_type_no("RT005");
			
//			sod1.setSapl_price((int)Math.round(4000*discount));
			System.out.println((int)Math.round(4000*discount));
			SaleOrderDetailVO sod2=new SaleOrderDetailVO();
			sod2.setRoom_type_no("RT006");
//			sod2.setSapl_price((int)Math.round(6000*discount));
			
			addRoomType.add(sod1);
			addRoomType.add(sod2);
			
//INSERT	
			sale.insertWithSod(salename,addRoomType);
//			System.out.println(count);
			
////updateRoomData
//			boolean count1 = sale.update(salename);
//			System.out.println(count1);
			
			
////Magic_Select
//			Map<String, String[]> map = new HashMap<>();
//			String[] rtn = new String[]{new String("RT004")};
//			String[] rtName = new String[]{new String("1")};
//			map.put("room_type_no", rtn);
//			map.put("clean_status", rtName);
//			List<RoomVO> list1 =room.getSreach(map);
//			System.out.println(map);
//			for(RoomVO vo : list1) {
//				System.out.println( "1" + vo );
//			}
			
//getAll
			
//			List<SaleplanVO> list = room.getAll();
//			for(SaleplanVO sale : list) {
//				System.out.print(sale.getRoom_id() + ",");
//				System.out.print(sale.getRoom_type_no() + ",");
//				System.out.print(sale.getRoom_no() + ",");
//				System.out.print(sale.getRoom_status() + ",");
//				System.out.print(sale.getClean_status()+"\n");
//				System.out.println("-----------------------------");
//getOne
//		salename = sale.getOneSearch("春季");
//		System.out.println(salename.getSapl_no());
//		System.out.println(salename.getSapl_name());
//		System.out.println(salename.getDetail());	
//		System.out.println(salename.getStart_date());
//		System.out.println(salename.getEnd_date());
//		System.out.println(salename.getSapl_discount());
//		System.out.println(salename.getStatus());
//		
	
	}
	

	@Override
	public SalePlanVO findByPrimaryKey(String sapl_no) {
		// TODO Auto-generated method stub
		SalePlanVO salVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD); 
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, sapl_no);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				salVo =new SalePlanVO();
				salVo.setSapl_no(rs.getString("sapl_no"));
				salVo.setSapl_name(rs.getString("sapl_name"));
				salVo.setDetail(rs.getString("detail"));
				salVo.setStart_date(rs.getDate("start_date"));
				salVo.setEnd_date(rs.getDate("end_date"));
				salVo.setSapl_discount(rs.getDouble("sapl_discount"));
				salVo.setStatus(rs.getInt("status"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
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
		return salVo;
	}
	
	public SalePlanVO getOneSearch(String sapl_name) {
		// TODO Auto-generated method stub
		SalePlanVO salVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD); 
			pstmt = con.prepareStatement(GET_ONE_STMT_FROM_NAME);
			
			pstmt.setString(1, "%"+sapl_name+"%");
	
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				salVo =new SalePlanVO();
				salVo.setSapl_no(rs.getString("sapl_no"));
				salVo.setSapl_name(rs.getString("sapl_name"));
				salVo.setDetail(rs.getString("detail"));
				salVo.setStart_date(rs.getDate("start_date"));
				salVo.setEnd_date(rs.getDate("end_date"));
				salVo.setSapl_discount(rs.getDouble("sapl_discount"));
				salVo.setStatus(rs.getInt("status"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
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
		return salVo;
	}

	@Override
	public SalePlanVO getOneFromName(String sapl_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithSod(SalePlanVO saleplan, List<SaleOrderDetailVO> list) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD); 
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"SAPL_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, saleplan.getSapl_name());
			pstmt.setString(2,saleplan.getDetail());
			pstmt.setDate(3, saleplan.getStart_date());
			pstmt.setDate(4,saleplan.getEnd_date());
			pstmt.setDouble(5,saleplan.getSapl_discount());
			pstmt.setInt(6,saleplan.getStatus());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_splno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_splno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_splno +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			SaleOrderDetailJDBCDAO dao = new SaleOrderDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (SaleOrderDetailVO sod : list) {
				sod.setSapl_no(next_splno) ;
				dao.insert2(sod,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_splno + "時,共有員工" + list.size()
					+ "人同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

	}

	
	@Override
	public void delete(String sapl_no) {
		// TODO Auto-generated method stub
		int updateCount_Sod = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD); 

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_SOD);
			pstmt.setString(1, sapl_no);
			updateCount_Sod = pstmt.executeUpdate();
			// 再刪除部門
			pstmt = con.prepareStatement(DELETE_SPL);
			pstmt.setString(1, sapl_no);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除優惠編號" + sapl_no + "時,共有優惠明細" + updateCount_Sod
					+ "個同時被刪除");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
}
