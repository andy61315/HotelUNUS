package com.restaurant.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RestaurantDAO implements  RestaurantDAO_interface<RestaurantVO> {
	

	public RestaurantDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<RestaurantVO> getAll() {

		List<RestaurantVO> rlist = new ArrayList<>();

		String sql_query = "select res_no, res_name, total_seats, res_contact, res_phone, res_status from restaurant";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					String resNo = rs.getString(1);
					String resName = rs.getString(2);
					Integer totalSeat = rs.getInt(3);
					String resContact = rs.getString(4);
					String resPhone = rs.getString(5);
					Integer resStatus = rs.getInt(6);
					RestaurantVO restaurant = new RestaurantVO(resNo, resName, totalSeat, resContact, resPhone,
							resStatus);
					rlist.add(restaurant);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		}
		return rlist;
	}

	@Override
	public RestaurantVO findByPrimaryKey(String id) {
		RestaurantVO restaurant = null;

		String sql_query = "select res_no, res_name, total_seats, res_contact, res_phone, res_status "
				+ "from restaurant where res_no= ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {

			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					String resNo = rs.getString(1);
					String resName = rs.getString(2);
					Integer totalSeat = rs.getInt(3);
					String resContact = rs.getString(4);
					String resPhone = rs.getString(5);
					Integer resStatus = rs.getInt(6);
					restaurant = new RestaurantVO(resNo, resName, totalSeat, resContact, resPhone, resStatus);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		
		return restaurant;
	}

	@Override
	public void add(RestaurantVO rest) {

		String sql = "insert into restaurant ( "
				+ "res_no, res_name, total_seats, res_contact, res_phone, res_status) "
				+ "values ('REST'||LPAD(TO_CHAR(SEQ_RES_NO.nextval),6,'0'), ?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			
			ps.setString(1, rest.getResName());
			ps.setInt(2, rest.getTotalSeat());
			ps.setString(3, rest.getResContact());
			ps.setString(4, rest.getResPhone());
			ps.setInt(7, rest.getResStatus());

			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());			
		}

	}

	@Override
	public void updateStatus(RestaurantVO rest) {

		String sql = "update restaurant set res_status = ? where res_no = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {
			
			ps.setInt(1, rest.getResStatus());
			ps.setString(2, rest.getResNo());

			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());			
		}

	}
	
	

	@Override
	public void delete(String id) {

		String sql = "delete from restaurant where res_no = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());			
		}

	}
	
public static void main(String[] args) {
		
		RestaurantDAO dao = new RestaurantDAO();

		// ?��?��gatAll()??��?��???��?��??
//		List<RestaurantVO> list = dao.getAll();
//		
//		//?��?��??��?�內
//		for (RestaurantVO aRest : list) {
//			System.out.print(aRest.getResNo() + ",");
//			System.out.print(aRest.getResName() + ",");
//			System.out.print(aRest.getTotalSeat() + ",");
//			System.out.print(aRest.getResContact() + ",");
//			System.out.print(aRest.getResPhone() + ",");
//			System.out.print(aRest.getResAccount() + ",");
//			System.out.print(aRest.getResPassword());
//			System.out.print(aRest.getResStatus());
//			System.out.println();
//		}
//		System.out.println("-----total " + list.size() + " book(s)-----");

		// ??��?��?��?�ID資�??(?��?��isbn??��??)
		RestaurantVO rest1 = dao.findByPrimaryKey("REST000002");
		if(rest1!=null) {
			System.out.println("Restaurant Info\n:"+rest1);
		}else {
			System.out.println("Restaurant not found!!");
		}
		        
		
		// ?��增�?筆�?��??
		RestaurantVO rest2 = new RestaurantVO();
		rest2.setResName("?��??��??");
		rest2.setTotalSeat(90);
		rest2.setResContact("Linus");
		rest2.setResPhone("062345678");
		rest2.setResStatus(0);
		
		
		//dao.add(rest2);
		
		
		
		// 傳入vo但只修改狀態ok
		RestaurantVO rest3 = new RestaurantVO("REST000002", "海...", 70, 
				"Lemontree","06-1122663",1);
		dao.updateStatus(rest3);
		
		
		
		
		// ?��?���?筆�?��??
		//dao.delete("REST00003");
		
	}

}
