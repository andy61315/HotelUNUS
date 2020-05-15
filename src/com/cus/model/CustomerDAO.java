package com.cus.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Date;

public class CustomerDAO implements CustomerDAO_interface{
	private static DataSource ds = null;
	static{
		try{
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
		e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
		"INSERT INTO CUSTOMER (cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,idf_Pic,captcha) VALUES('CUS'||TO_CHAR(SEQ_CUS_ID.nextval,'FM0000000'), ? ,? ,? ,? ,?,? ,? ,?,?)";
	private static final String GET_ALL_STMT =
		"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck,captcha From CUSTOMER order by cus_Id";
	private static final String GET_ONE_STMT =
		"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck,captcha From CUSTOMER where cus_Id = ?";
	private static final String UPDATE = 
		"UPDATE CUSTOMER set cus_Name = ?,cus_Email = ?,cus_Cel = ?,country = ?,id_Num = ?,cus_Bir = ?,cus_Password = ?,cus_Ck = ?,idf_Pic = ?,captcha = ? where cus_Id = ?";
	private static final String CUS_EMAIL_CK =
		"SELECT * From CUSTOMER where cus_Email = ?";
	private static final String ID_NUM_CK =
		"SELECT id_Num From CUSTOMER where id_Num = ?";
	private static final String GET_ONE_CUS_BY_ID=
			"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck,captcha From CUSTOMER where id_Num= ?";
//	前端-------隔離線
	private static final String GET_ONE_CUS_BY_EMAIL=
		"SELECT cus_Id,cus_Name,cus_Email,cus_Cel,country,id_Num,cus_Bir,cus_Password,reg_Date,idf_Pic,cus_Ck,captcha From CUSTOMER where cus_email= ?";
	private static final String INSERT_FRONT =
			"INSERT INTO CUSTOMER (cus_Id,cus_Email,cus_Name,id_Num,cus_Password,captcha) VALUES('CUS'||TO_CHAR(SEQ_CUS_ID.nextval,'FM0000000'), ? ,? ,? ,?,? )";	
	private static final String UPDATECK = 
			"UPDATE CUSTOMER set cus_Ck = ? where cus_Id = ?";
	
	@Override
	public void insert(CustomerVO customerVO) {
		
		try (
			Connection connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);){
			
			pstmt.setString(1, customerVO.getCus_Name());
			pstmt.setString(2, customerVO.getCus_Email());
			pstmt.setString(3, customerVO.getCus_Cel());
			pstmt.setInt(4, customerVO.getCountry());
			pstmt.setString(5, customerVO.getId_Num());
			pstmt.setDate(6, (Date) customerVO.getCus_Bir());
			pstmt.setString(7, customerVO.getCus_Password());
			pstmt.setBytes(8, customerVO.getIdf_Pic());
			pstmt.setString(9,customerVO.getCaptcha());
						
			
			pstmt.executeUpdate();
			
			//Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		}
	
	}
	
	@Override
	public void update(CustomerVO customerVO) {
		try (Connection connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);) {
			
			pstmt.setString(1, customerVO.getCus_Name());
			pstmt.setString(2, customerVO.getCus_Email());
			pstmt.setString(3, customerVO.getCus_Cel());
			pstmt.setInt(4, customerVO.getCountry());
			pstmt.setString(5, customerVO.getId_Num());
			pstmt.setDate(6, (Date) customerVO.getCus_Bir());
			pstmt.setString(7, customerVO.getCus_Password());
			pstmt.setInt(8, customerVO.getCus_Ck());
			pstmt.setBytes(9, customerVO.getIdf_Pic());
			pstmt.setString(10, customerVO.getCaptcha());
			pstmt.setString(11, customerVO.getCus_Id());
			pstmt.executeUpdate();
			System.out.println(pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();

	}

	}
			
	
	@Override
	public CustomerVO findByPrimaryKey(String cus_Id) {
		CustomerVO customerVO = null;
		ResultSet rs = null;

		try(Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT);)
		{
			pstmt.setString(1, cus_Id);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
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
				customerVO.setCaptcha(rs.getString("captcha"));
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
		
		try(Connection con = ds.getConnection();
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
				customerVO.setCaptcha(rs.getString("captcha"));
				list.add(customerVO);
			}}catch(SQLException e)
			{e.printStackTrace();}

		return list;
	}

	@Override
	public void delete(String cus_Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerVO cusemailcheck(String cus_Email) {
		CustomerVO customerVO = null;
		ResultSet rs = null;

		try(Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(CUS_EMAIL_CK);)
		{
			pstmt.setString(1, cus_Email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return customerVO;
		}
	
	@Override
	public boolean id_numcheck(String id_Num) {
		CustomerVO customerVO = null;
		ResultSet rs = null;

		try(Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(ID_NUM_CK);)
		{
			pstmt.setString(1, id_Num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			customerVO = new CustomerVO();
			customerVO.setId_Num(rs.getString("id_Num"));
			return false;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
	
	@Override
	public CustomerVO getOneCusByEmail(String cus_Email) {
		CustomerVO customerVO = null;
		ResultSet rs = null;

		try(Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_CUS_BY_EMAIL);)
		{
			pstmt.setString(1, cus_Email);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
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
				customerVO.setCaptcha(rs.getString("captcha"));
			}
			}catch(SQLException e) {
				{e.printStackTrace();}
			}
			return customerVO;
		}
	
	@Override
	public CustomerVO getOneCusById(String id_Num) {
		CustomerVO customerVO = null;
		ResultSet rs = null;

		try(Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_CUS_BY_ID);)
		{
			pstmt.setString(1, id_Num);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
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
				customerVO.setCaptcha(rs.getString("captcha"));
			}
			}catch(SQLException e) {
				{e.printStackTrace();}
			}
			return customerVO;
		}
	@Override
	public void frontinsert(CustomerVO customerVO) {
		
		try (
			Connection connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_FRONT);){
			
			pstmt.setString(1, customerVO.getCus_Email());
			pstmt.setString(2, customerVO.getCus_Name());
			pstmt.setString(3, customerVO.getId_Num());
			pstmt.setString(4, customerVO.getCus_Password());
			pstmt.setString(5, customerVO.getCaptcha());
			pstmt.executeUpdate();
			
			//Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		}
	
	}
	
	@Override
	public void updateCk(CustomerVO customerVO) {
		try (Connection connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATECK);) {
			pstmt.setInt(1, customerVO.getCus_Ck());
			pstmt.setString(2, customerVO.getCus_Id());
			pstmt.executeUpdate();
			System.out.println(pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();

	}

	}
}
