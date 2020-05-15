package com.resmealom.model;

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

import com.meal.model.OrderMealVO;
import com.resmealod.model.ResMealOrderDetailDAO;
import com.resmealod.model.ResMealOrderDetailVO;
import com.restaurant.model.RestaurantVO;

public class ResMealOrderMasterDAO implements ResMealOrderMasterDAO_interface<ResMealOrderMasterVO> {

	public ResMealOrderMasterDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ResMealOrderMasterVO> getAll() {
		// 宣告集合裝查詢到的物件們
		List<ResMealOrderMasterVO> rlist = new ArrayList<>();
		// 下sql語法查詢
		String sql_query = "select res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement "
				+ "from res_meal_order_master";
		// 建立連線並自動關閉
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {
			// 查詢傳回ResultSet也要關閉資源
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					String resMealOrderNo = rs.getString(1);
					String bOrderNo = rs.getString(2);
					Integer tableNo = rs.getInt(3);
					Integer totalPrice = rs.getInt(4);
					Date orderDate = rs.getDate(5);
					Integer orderStatus = rs.getInt(6);
					String specialRequirement = rs.getString(7);
					// 將查詢撈出的資料放入VO物件，再放入集合
					ResMealOrderMasterVO rmeal = new ResMealOrderMasterVO(resMealOrderNo, bOrderNo, tableNo, totalPrice,
							orderDate, orderStatus, specialRequirement);
					rlist.add(rmeal);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rlist;
	}

	@Override
	public ResMealOrderMasterVO findByPrimaryKey(String id) {
		// 宣告VO物件
		ResMealOrderMasterVO rmeal = null;
		// 下sql語法以pk查詢
		String sql_query = "select res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement "
				+ "from res_meal_order_master where res_meal_order_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {
			// 送出查詢條件(第一個問號)
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					String resMealOrderNo = rs.getString(1);
					String bOrderNo = rs.getString(2);
					Integer tableNo = rs.getInt(3);
					Integer totalPrice = rs.getInt(4);
					Date orderDate = rs.getDate(5);
					Integer orderStatus = rs.getInt(6);
					String specialRequirement = rs.getString(7);

					rmeal = new ResMealOrderMasterVO(resMealOrderNo, bOrderNo, tableNo, totalPrice, orderDate,
							orderStatus, specialRequirement);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rmeal;
	}

	@Override
	public boolean add(ResMealOrderMasterVO rmeal) {
		// 執行成功列數
		int rowCount = 0;
		String sql = "insert into res_meal_order_master ( "
				+ "res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {
			// getter取得物件資訊送入資料庫
			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getbOrderNo());
			ps.setInt(3, rmeal.getTableNo());
			ps.setInt(4, rmeal.getTotalPrice());
			ps.setDate(5, rmeal.getOrderDate());
			ps.setInt(6, rmeal.getOrderStatus());
			ps.setString(7, rmeal.getSpecialRequirement());

			rowCount = ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(ResMealOrderMasterVO rmeal) {
		// 更新成功筆數
		int rowCount = 0;
		String sql = "update res_meal_order_master "
				+ "set res_meal_order_no = ?, b_order_no = ?, table_no = ?, total_price = ?, order_date = ? , order_status = ? , special_requirement = ?"
				+ "where res_meal_order_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, rmeal.getResMealOrderNo());
			ps.setString(2, rmeal.getbOrderNo());
			ps.setInt(3, rmeal.getTableNo());
			ps.setInt(4, rmeal.getTotalPrice());
			ps.setDate(5, rmeal.getOrderDate());
			ps.setInt(6, rmeal.getOrderStatus());
			ps.setString(7, rmeal.getSpecialRequirement());
			ps.setString(8, rmeal.getResMealOrderNo());

			rowCount = ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(String id) {
		// 刪除成功筆數
		int rowCount = 0;
		String sql = "delete from res_meal_order_master where res_meal_order_no = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql);) {
			// 送入欲刪除pk
			ps.setString(1, id);
			rowCount = ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rowCount != 0;
	}
	
	@Override
	public int findTotalByRoom(String roomNo) {
		Integer totalPrice =null;
		String sql_query="select TOTAL_PRICE from RES_MEAL_ORDER_MASTER where B_ORDER_NO=?";
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_query);) {
			// 送出查詢條件(第一個問號)
			ps.setString(1, roomNo);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					
					totalPrice = rs.getInt(1);
					
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return totalPrice;
		
	}

	//新增要用這個
	public void insertWithDetails(ResMealOrderMasterVO resmomVO, List<OrderMealVO> list) {

		String sql = "INSERT INTO res_meal_order_master (res_meal_order_no, b_order_no, table_no, total_price, order_date, order_status, special_requirement) "
				+ "VALUES ('RESM-'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||TO_CHAR(SEQ_RES_MEAL_ORDER_NO.NEXTVAL,'FM000'), ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);

			String cols[] = { "RES_MEAL_ORDER_NO" };
			ps = conn.prepareStatement(sql, cols);
			ps.setString(1, resmomVO.getbOrderNo());
			ps.setInt(2, resmomVO.getTableNo());
			ps.setInt(3, resmomVO.getTotalPrice());
			ps.setDate(4, resmomVO.getOrderDate());
			ps.setInt(5, resmomVO.getOrderStatus());
			ps.setString(6, resmomVO.getSpecialRequirement());
			ps.executeUpdate();

			String next_resMealOrderNo = null;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				next_resMealOrderNo = rs.getString(1);
				System.out.println("自增主鍵值(剛新增成功的訂單編號)" + next_resMealOrderNo);
			} else {
				System.out.println("未取得主鍵值");
			}
			rs.close();

			// 同時新增明細
			ResMealOrderDetailDAO dao = new ResMealOrderDetailDAO();
			System.out.println("集合內有明細"
					+ " :" + list.size());

			for (OrderMealVO aDetail : list) {
				ResMealOrderDetailVO detail =new ResMealOrderDetailVO();
				detail.setResMealOrderNo(next_resMealOrderNo);
				detail.setMealNo(aDetail.getMeal_no());
				detail.setPrice(aDetail.getPrice());
				detail.setQuantity(aDetail.getQuantity());
				dao.add2(detail, conn);
			}

			// 2●設定於 ps.executeUpdate()之後
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("集合B= " + list.size());
			System.out.println("新增訂單編號" + next_resMealOrderNo + "時，共有明細" + list.size() + "筆，同時被新增");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public static void main(String[] args) {
		
		

		ResMealOrderMasterDAO dao = new ResMealOrderMasterDAO();
		
		int test= dao.findTotalByRoom("BM-20200321-001");
		System.out.println(test);
		
//		ResMealOrderMasterVO resmom = new ResMealOrderMasterVO();
//		resmom.setbOrderNo("BM-20200321-009");
//		resmom.setTableNo(5);
//		resmom.setTotalPrice(900);
//		resmom.setOrderDate(java.sql.Date.valueOf("2001-01-15"));
//		resmom.setOrderStatus(1);
//		resmom.setSpecialRequirement("無糖去冰");
//		
//		List<ResMealOrderDetailVO> detailList =new ArrayList<ResMealOrderDetailVO>();
//		ResMealOrderDetailVO resmod1 = new ResMealOrderDetailVO();
//		resmod1.setMealNo("M0001");
//		resmod1.setPrice(150);
//		resmod1.setCooking(1);
//		resmod1.setServed(0);
//		resmod1.setCanceled(0);
//		
//		ResMealOrderDetailVO resmod2 = new ResMealOrderDetailVO();
//		resmod2.setMealNo("M0005");
//		resmod2.setPrice(750);
//		resmod2.setCooking(1);
//		resmod2.setServed(0);
//		resmod2.setCanceled(0);
//		
//		detailList.add(resmod1);
//		detailList.add(resmod2);
//		
//		dao.insertWithDetails(resmom, detailList);
		
		// 呼叫gatAll()取得所有資料
		//List<ResMealOrderMasterVO> list = dao.getAll();

		// 印出集合內
//		for (ResMealOrderMasterVO aResM : list) {
//			System.out.println(aResM.getResMealOrderNo());
//			System.out.println(aResM.getbOrderNo());
//			System.out.println(aResM.getTableNo());
//			System.out.println(aResM.getTotalPrice());
//			System.out.println(aResM.getOrderDate());
//			System.out.println(aResM.getOrderStatus());
//			System.out.println(aResM.getSpecialRequirement());
//			System.out.println("---------------------");
//		}

		// 取得指定ID資料
//		ResMealOrderMasterVO resm1 = dao.findByPrimaryKey("RESM-20200323-001");
//		if (resm1 != null) {
//			System.out.print(resm1.getResMealOrderNo() + ",");
//			System.out.print(resm1.getbOrderNo() + ",");
//			System.out.print(resm1.getTableNo() + ",");
//			System.out.print(resm1.getTotalPrice() + ",");
//			System.out.print(resm1.getOrderDate() + ",");
//			System.out.print(resm1.getOrderStatus() + ",");
//			System.out.println(resm1.getSpecialRequirement());
//			System.out.println("---------------------");
//		} else {
//			System.out.println("Master not found!!");
//		}

		// 新增一筆資料
//		ResMealOrderMasterVO rest2 = new ResMealOrderMasterVO("RESM-20200323-006", "BM-20200321-006", 7, 300,
//				Date.valueOf("2020-6-12"), 1, "無");
//
//		dao.add(rest2);

		// 修改一筆資料，將價格修改
//		ResMealOrderMasterVO rest3 = new ResMealOrderMasterVO("RESM-20200323-006", "BM-20200321-006", 7, 300,
//				Date.valueOf("2020-5-12"), 2, "升級豪華套餐");
//		dao.update(rest3);

		// 刪除一筆資料
		// dao.delete("RESM-20200323-006");
	}

	

	

}
