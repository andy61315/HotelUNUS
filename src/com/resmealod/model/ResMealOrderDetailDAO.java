package com.resmealod.model;

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

import com.resmealom.model.ResMealOrderMasterDAO;
import com.resmealom.model.ResMealOrderMasterVO;

public class ResMealOrderDetailDAO implements ResMealOrderDetailDAO_interface<ResMealOrderDetailVO> {

	public ResMealOrderDetailDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ResMealOrderDetailVO> getAll() {
		// 宣告集合裝查詢到的物件們
		List<ResMealOrderDetailVO> rlist = new ArrayList<>();
		// 下sql語法查詢
		String sql_query = "select res_meal_order_no, meal_no, price, quantity "
				+ "from res_meal_order_detail";
		// 建立連線並自動關閉
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {
			// 查詢傳回ResultSet也要關閉資源
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					String resMealOrderNo = rs.getString(1);
					String mealNo = rs.getString(2);
					Integer price = rs.getInt(3);
					Integer quantity = rs.getInt(4);
					

					// 將查詢撈出的資料放入VO物件，再放入集合
					ResMealOrderDetailVO rmeal = new ResMealOrderDetailVO(resMealOrderNo, mealNo, price, quantity);
					rlist.add(rmeal);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rlist;
	}

	@Override
	public ResMealOrderDetailVO findByPrimaryKey(String id, String no) {

		ResMealOrderDetailVO rmeal = null;
		// 下sql語法以pk查詢
		String sql_query = "select res_meal_order_no, meal_no, price, quantity "
				+ "from res_meal_order_detail where res_meal_order_no = ? and meal_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {
			// 送出查詢條件(第一個問號)
			ps.setString(1, id);
			ps.setString(2, no);

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {

					String resMealOrderNo = rs.getString(1);
					String mealNo = rs.getString(2);
					Integer price = rs.getInt(3);
					Integer quantity = rs.getInt(4);
					

					rmeal = new ResMealOrderDetailVO(resMealOrderNo, mealNo, price, quantity);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rmeal;
	}

	@Override
	public boolean add(ResMealOrderDetailVO rmeal) {
		// 執行成功列數
		int rowCount = 0;
		String sql = "insert into res_meal_order_detail ( "
				+ "res_meal_order_no, meal_no, price, quantity ) " + "values ( ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {
			// getter取得物件資訊送入資料庫
			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getMealNo());
			ps.setInt(3, rmeal.getPrice());
			ps.setInt(4, rmeal.getQuantity());
			

			rowCount = ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rowCount != 0;
	}

	@Override
	public void add2(ResMealOrderDetailVO rmeal, Connection conn) {
		// 執行成功列數
		PreparedStatement ps = null;
		String sql = "insert into res_meal_order_detail ( "
				+ "res_meal_order_no, meal_no, price, quantity ) " + "values (?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			// getter取得物件資訊，且set送入資料庫
			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getMealNo());
			ps.setInt(3, rmeal.getPrice());
			ps.setInt(4, rmeal.getQuantity());


			ps.executeUpdate();

		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-訂單");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public boolean update(ResMealOrderDetailVO rmeal) {
		// 更新成功筆數
		int rowCount = 0;
		String sql = "update res_meal_order_detail " + "set price = ?, quantity = ? "
				+ "where res_meal_order_no = ? and meal_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setInt(1, rmeal.getPrice());
			ps.setInt(2, rmeal.getQuantity());
			ps.setString(3, rmeal.getResMealOrderNo());
			ps.setString(4, rmeal.getMealNo());

			rowCount = ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(String id, String no) {
		// 刪除成功筆數

		int rowCount = 0;
		String sql = "delete from res_meal_order_detail where res_meal_order_no = ? and meal_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {
			// 送入欲刪除pk
			ps.setString(1, id);
			ps.setString(2, no);
			rowCount = ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rowCount != 0;
	}

	public static void main(String[] args) {

//		ResMealOrderDetailDAO dao = new ResMealOrderDetailDAO();
//
//		// 呼叫getAll()取得所有資料
//		List<ResMealOrderDetailVO> list = dao.getAll();
//
//		// 印出集合內
//		for (ResMealOrderDetailVO aResD : list) {
//			System.out.println(aResD.getResMealOrderNo());
//			System.out.println(aResD.getMealNo());
//			System.out.println(aResD.getPrice());
//			System.out.println(aResD.getCooking());
//			System.out.println(aResD.getServed());
//			System.out.println(aResD.getCanceled());
//
//			System.out.println("---------------------");
//		}
//
//		// 取得指定ID資料
//		ResMealOrderDetailVO resd1 = dao.findByPrimaryKey("RESM-20200323-001", "M0001");
//		if (resd1 != null) {
//			System.out.print(resd1.getResMealOrderNo() + ",");
//			System.out.print(resd1.getMealNo() + ",");
//			System.out.print(resd1.getPrice() + ",");
//			System.out.print(resd1.getCooking() + ",");
//			System.out.print(resd1.getServed() + ",");
//			System.out.print(resd1.getCanceled() + ",");
//
//			System.out.println("---------------------");
//		} else {
//			System.out.println("Detail not found!!");
//		}
//
//		// 新增一筆資料
//		ResMealOrderDetailVO rest2 = new ResMealOrderDetailVO("RESM-20200323-005", "M0003", 500, 1, 1, 0);
//
//		// dao.add(rest2);
//
//		// 修改一筆資料，將價格修改(複合pk+fk)
//		ResMealOrderDetailVO rest3 = new ResMealOrderDetailVO("RESM-20200323-005", "M0003", 10000, 0, 0, 1);
//		// dao.update(rest3);
//
//		// 刪除一筆資料
//		dao.delete("RESM-20200323-005", "M0003");
	}

}
