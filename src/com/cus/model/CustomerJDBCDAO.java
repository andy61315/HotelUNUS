package com.cus.model;
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
import java.util.List;
import java.sql.Date;

import common.Common.*;
public class CustomerJDBCDAO implements CustomerDAO_interface{
	
	private static final String INSERT_STMT =
			"INSERT INTO CUSTOMER (cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,idf_Pic) VALUES('CUS'||TO_CHAR(SEQ_CUS_ID.nextval,'FM0000000'), ? ,? ,? ,? ,? ,? ,?,?)";
		private static final String GET_ALL_STMT =
			"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck From CUSTOMER order by cus_Id";
		private static final String GET_ONE_STMT =
			"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck From CUSTOMER where cus_Id = ?";
		private static final String UPDATE = 
			"UPDATE CUSTOMER set cus_Name = ?,cus_Email = ?,cus_Cel = ?,country = ?,id_Num = ?,cus_Bir = ?,cus_Password = ?,cus_Ck = ?,idf_Pic = ? where cus_Id = ?";
		private static final String CUS_EMAIL_CK =
			"SELECT cus_Email From CUSTOMER where cus_Email = ?";
		private static final String ID_NUM_CK =
			"SELECT id_Num From CUSTOMER where id_Num = ?";
//		前端-------隔離線
		private static final String GET_ONE_CUS_BY_EMAIL=
			"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck From CUSTOMER where cus_email= ?";
		
		
	public CustomerJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(CustomerVO customerVO) {
				
		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
			
			pstmt.setString(1, customerVO.getCus_Name());
			pstmt.setString(2, customerVO.getCus_Email());
			pstmt.setString(3, customerVO.getCus_Cel());
			pstmt.setInt(4, customerVO.getCountry());
			pstmt.setString(5, customerVO.getId_Num());
			pstmt.setDate(6, (Date) customerVO.getCus_Bir());
			pstmt.setString(7, customerVO.getCus_Password());
			pstmt.setDate(8, (Date) customerVO.getReg_Date());
			pstmt.setBytes(9, customerVO.getIdf_Pic());
			pstmt.setInt(10, customerVO.getCus_Ck());
			
			
			pstmt.executeUpdate();
			
			//Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		}

	}
	
	@Override
	public void update(CustomerVO customerVO) {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATE);) {
			
			pstmt.setString(1, customerVO.getCus_Name());
			pstmt.setString(2, customerVO.getCus_Email());
			pstmt.setString(3, customerVO.getCus_Cel());
			pstmt.setInt(4, customerVO.getCountry());
			pstmt.setString(5, customerVO.getId_Num());
			pstmt.setDate(6, (Date) customerVO.getCus_Bir());
			pstmt.setString(7, customerVO.getCus_Password());
			pstmt.setDate(8, (Date) customerVO.getReg_Date());
			pstmt.setBytes(9, customerVO.getIdf_Pic());
			pstmt.setInt(10, customerVO.getCus_Ck());
			pstmt.setString(11, customerVO.getCus_Id());
			
			pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();

	}

	}
	
	
	
	
	@Override
	public CustomerVO findByPrimaryKey(String cus_Id) {
		CustomerVO customerVO = null;
		ResultSet rs = null;

		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT);)
		{
			pstmt.setString(1, cus_Id);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				//Customer_Vo 摰儔�Domain objects
				customerVO = new CustomerVO();
				customerVO.setCus_Id(rs.getString("cus_id"));
				customerVO.setCus_Name(rs.getString("cus_name"));
				customerVO.setCus_Email(rs.getString("cus_email"));
				customerVO.setCus_Cel(rs.getString("cus_cel"));
				customerVO.setCountry(rs.getInt("country"));
				customerVO.setId_Num(rs.getString("id_num"));
				customerVO.setCus_Bir(rs.getDate("cus_bir"));
				customerVO.setCus_Password(rs.getString("cus_password"));
				customerVO.setReg_Date(rs.getDate("reg_Date"));
				customerVO.setIdf_Pic(rs.getBytes("idf_pic"));
				customerVO.setCus_Ck(rs.getInt("cus_ck"));
			}
			}catch(SQLException e) {
				{e.printStackTrace();}
			}
			return customerVO;
		}
	@Override
	public List<CustomerVO> getAll() {
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		CustomerVO customerVO = null;
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
						ResultSet rs = pstmt.executeQuery();) {
		
			while(rs.next()) {
				//Domain objects
				customerVO = new CustomerVO();
				customerVO.setCus_Id(rs.getString("cus_id"));
				customerVO.setCus_Name(rs.getString("cus_name"));
				customerVO.setCus_Email(rs.getString("cus_email"));
				customerVO.setCus_Cel(rs.getString("cus_cel"));
				customerVO.setCountry(rs.getInt("country"));
				customerVO.setId_Num(rs.getString("id_num"));
				customerVO.setCus_Bir(rs.getDate("cus_bir"));
				customerVO.setCus_Password(rs.getString("cus_password"));
				customerVO.setReg_Date(rs.getDate("reg_Date"));
				customerVO.setIdf_Pic(rs.getBytes("idf_pic"));
				customerVO.setCus_Ck(rs.getInt("cus_Ck"));
				list.add(customerVO);
			}}catch(SQLException e)
			{e.printStackTrace();}

		return list;
	}
	public static void main(String[] args) {
		CustomerJDBCDAO dao = new CustomerJDBCDAO();
		
//		//�憓�
//		Customer_Vo cusvo = new Customer_Vo();
//		cusvo.setCus_Name("Roger");
//		cusvo.setCus_Email("jinnchang945@gmail.com");
//		cusvo.setCus_Cel("0938777777");
//		cusvo.setCountry(1);
//		cusvo.setId_Num("G115556669");
//		cusvo.setCus_Bir(java.sql.Date.valueOf("1992-01-27"));
//		cusvo.setCus_Password("123456");
//		cusvo.setReg_Date(java.sql.Date.valueOf("2020-03-29"));
//		cusvo.setIdf_Pic(null);
//		cusvo.setCus_Ck(1);
//		
//		int upCount_insert = dao.insert(cusvo);
//		System.out.println(upCount_insert);
		
//		//靽格
//		CustomerVO cusvo2 = new CustomerVO();
//		cusvo2.setCus_Id("CUS0000002");
//		cusvo2.setCus_Name("JACK");
//		cusvo2.setCus_Email("jack945@gmail.com");
//		cusvo2.setCus_Cel("0977777777");
//		cusvo2.setCountry(1);
//		cusvo2.setId_Num("G115556666");
//		cusvo2.setCus_Bir(java.sql.Date.valueOf("1977-01-27"));
//		cusvo2.setCus_Password("123456");
//		cusvo2.setReg_Date(java.sql.Date.valueOf("2020-03-29"));
//		cusvo2.setIdf_Pic(null);
//		cusvo2.setCus_Ck(1);
//		
//		System.out.println(dao.update(cusvo2));
//		
//		//��
//		System.out.println(dao.delete("CUS0000007"));
		
		//�閰�
		List<CustomerVO> list = dao.getAll();
			for(CustomerVO aCus : list) {
				System.out.print(aCus.getCus_Id() + ",");
				System.out.print(aCus.getCus_Name() + ",");
				System.out.print(aCus.getCus_Password() + ",");
				System.out.print(aCus.getCus_Email() + ",");
				System.out.print(aCus.getId_Num() + ",");
				System.out.print(aCus.getCus_Bir() + ",");
				System.out.print(aCus.getCountry() + ",");
				System.out.print(aCus.getIdf_Pic() + ",");
				System.out.print(aCus.getReg_Date() + ",");
				System.out.print(aCus.getCus_Cel() + ",");
				System.out.print(aCus.getCus_Ck() + ",");
				System.out.println();
				
			}
	}


	@Override
	public CustomerVO cusemailcheck(String cus_Email) {
		// TODO 自動產生的方法 Stub
		return null ;
	}


	@Override
	public void delete(String cus_Id) {
		// TODO 自動產生的方法 Stub
		
	}


	@Override
	public boolean id_numcheck(String id_Num) {
		// TODO 自動產生的方法 Stub
		return false;
	}


	@Override
	public CustomerVO getOneCusByEmail(String cus_Email) {
		// TODO 自動產生的方法 Stub
		return null;
	}


	@Override
	public void frontinsert(CustomerVO customerVO) {
		// TODO 自動產生的方法 Stub
		
	}


	@Override
	public CustomerVO getOneCusById(String id_Num) {
		// TODO 自動產生的方法 Stub
		return null;
	}


	@Override
	public void updateCk(CustomerVO customerVO) {
		// TODO 自動產生的方法 Stub
		
	}



	

}
