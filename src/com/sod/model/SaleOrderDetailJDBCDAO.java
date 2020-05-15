package com.sod.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.*;
import java.util.*;




public class SaleOrderDetailJDBCDAO implements SaleOrderDetailDAO_interface{
	private static final String INSERT_STMT = 
			"INSERT INTO SALE_ORDER_DETAIL (ROOM_TYPE_NO, SAPL_NO) VALUES (?,?,?)";
//	private static final String GET_ONE_STMT="SELECT SAPL_PRICE FROM SALE_ORDER_DETAIL where ROOM_TYPE_NO=? AND SAPL_NO = ? ";
	private static final String GET_ALL_STMT = 
			"SELECT ROOM_TYPE_NO,SAPL_NO FROM SALE_ORDER_DETAIL order by ROOM_TYPE_NO";
	private static final String DELETE = 
			"DELETE FROM SALE_ORDER_DETAIL where ROOM_TYPE_NO = ? AND SAPL_NO=?";
	private static final String UPDATE = 
			"UPDATE SALE_ORDER_DETAIL set  where ROOM_TYPE_NO = ? AND SAPL_NO=?";

	
	public SaleOrderDetailJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public List<SaleOrderDetailVO> getSodBySalNo(String sapl_no) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean deleteBySal(String sapl_no) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public int insert(SaleOrderDetailVO sodvo) {
		// TODO Auto-generated method stub
		
		int updateCount = 0;
		Connection con = null;

		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
			
			pstmt.setString(1, sodvo.getRoom_type_no());
			pstmt.setString(2, sodvo.getSapl_no());
//			pstmt.setInt(3,sodvo.getSapl_price());
			
		

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
	public boolean update(SaleOrderDetailVO sodvo) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATE);) {
			
			
			
//			pstmt.setInt(1,sodvo.getSapl_price());
			pstmt.setString(1, sodvo.getRoom_type_no());
			pstmt.setString(2, sodvo.getSapl_no());
			
			rowCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}


	@Override
	public boolean delete(String room_type_no,String  sapl_no) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(DELETE);) {
			ps.setString(1, room_type_no);
			ps.setString(2, sapl_no);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}	
	
	@Override
	public SaleOrderDetailVO findByPrimaryKey(String room_type_no,String sapl_no) {
		// TODO Auto-generated method stub
		SaleOrderDetailVO sod= null;
//		ResultSet rs = null;
//		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//				PreparedStatement ps = connection.prepareStatement(GET_ONE_STMT);)
//		{
//			ps.setString(1, room_type_no);
//			ps.setString(2, sapl_no);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				
//				sod = new SaleOrderDetailVO();
//				sod.setSapl_price(rs.getInt("sapl_price"));
//				
//				
//			}
//
//		}
//		
//		catch(SQLException e) 
//		{e.printStackTrace();}
//		
		
		return sod;
	}
	@Override
	public List<SaleOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<SaleOrderDetailVO> list=new ArrayList<SaleOrderDetailVO>();
		ResultSet rs = null;
	
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);)
		{	
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SaleOrderDetailVO sod=new SaleOrderDetailVO();
			sod.setRoom_type_no(rs.getString("room_type_no"));
			sod.setSapl_no(rs.getString("sapl_no"));
//			sod.setSapl_price(rs.getInt("sapl_price"));
			
			list.add(sod);
		}}catch(SQLException e) 
		{e.printStackTrace();}
		return list;
	}
	
	public static void main(String args[]) {
		SaleOrderDetailJDBCDAO sod = new SaleOrderDetailJDBCDAO();
		SaleOrderDetailVO sodVo = new SaleOrderDetailVO();
		
//		sodVo.setRoom_type_no("RT004");
//		sodVo.setSapl_no("SALP0002");
//		sodVo.setSapl_price(2100);
		
//		int count = sod.insert(sodVo);
//		System.out.println(count);

//		boolean count = sod.update(sodVo);
//		System.out.println(count);
		
		List<SaleOrderDetailVO> list = new ArrayList();
		list.add(sod.findByPrimaryKey("RT001", "SALP0001"));
		System.out.println(list);
	}






	@Override
	public List<SaleOrderDetailVO> findSodFromRoomType(String sod) {
		// TODO Auto-generated method stub
		
		return null;
	}




	@Override
	public void insert2(SaleOrderDetailVO saleOrderDetailVO, Connection con) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, saleOrderDetailVO.getRoom_type_no());
			pstmt.setString(2, saleOrderDetailVO.getSapl_no());
//			pstmt.setInt(3,saleOrderDetailVO.getSapl_price());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
		}

	}




	
}
