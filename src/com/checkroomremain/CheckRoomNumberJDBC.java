package com.checkroomremain;

import static common.Common.*;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.Struct;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OracleResultSet;

public class CheckRoomNumberJDBC {
//限制：
//	1.新增超過六人以上的房型無法比對
//trigger還沒建
//要建兩個版本的組合
	OracleCallableStatement cst = null;
	OracleResultSet rs = null;
	PreparedStatement pstmt = null;
	String[] combinationArr = { "one_", "two_", "three_", "four_", "five_", "six_", "min_", "max_" };
	private static String COMBINATION_STMT = "SELECT * from combination "
			+ "where one_ <= ? and two_ <= ? and three_ <= ? and four_ <= ? "
			+ "and five_ <= ? and six_ <= ? and min_ = ? and max_ = ?  ";

	//到時候讓旅客選房型數量時，限制最大可選數量
	public List<RoomTypeRemain> getRemainNo(String startDate, String endDate) {// 篩出不同人數的房型各自的剩餘數量
		List<RoomTypeRemain> list = new ArrayList<>();
		RoomTypeRemain rr = null;
		Connection con = null;
		try {
			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

//			cst = (OracleCallableStatement)con.prepareCall(CALL);
		cst = (OracleCallableStatement) con.prepareCall("begin ROOM_REMAIN_ALL(?,?,?); end;");
			cst.setString(1, startDate);// 試試看在這裡用TO_DATE相加
			cst.setString(2, endDate);
			cst.registerOutParameter(3, OracleTypes.ARRAY, "ROOM_RE_TAB");// room_remain_table
			System.out.println(cst.executeUpdate());
			Array test1 = cst.getArray(3);
			rs = (OracleResultSet) test1.getResultSet();
			Struct struct = null;

			while (rs.next()) {
				struct = rs.getSTRUCT(2);
				Object[] obj = struct.getAttributes();
				rr = new RoomTypeRemain();
				rr.setRoom_Type_No((String) obj[0]);
				rr.setRooom_Type_Name((String) obj[1]);
				rr.setRemain(Integer.parseInt(obj[2].toString()));
				rr.setPerson_Capacity(Integer.parseInt(obj[3].toString()));
				rr.setTotal_People(Integer.parseInt(obj[4].toString()));
				list.add(rr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 提取排列組合的表格
	//這個表格應該不用給旅客看到
	public DifferentNumberRoomRemain getRemainMap(List<RoomTypeRemain> list) {// Map的key=人數，value=剩餘數量
		DifferentNumberRoomRemain difRemain = new DifferentNumberRoomRemain();
		System.out.println(difRemain);
		for (RoomTypeRemain rr : list) {
			int capacity = rr.getPerson_Capacity();
			int amount = rr.getRemain();
			switch (capacity) {
				case 1:
					difRemain.setOne(difRemain.getOne() + amount);
					break;
				case 2:
					difRemain.setTwo(difRemain.getTwo() + amount);
					break;
				case 3:
					difRemain.setThree(difRemain.getThree() + amount);
					break;
				case 4:
					difRemain.setFour(difRemain.getFour() + amount);
					break;
				case 5:
					difRemain.setFive(difRemain.getFive() + amount);
					break;
				case 6:
					difRemain.setSix(difRemain.getSix() + amount);
					break;
			}
		}
		return difRemain;
	}

	// 先不做>6的(或是說不知道怎麼查最大房數比較好，另外如果6人房後直接跳10人也不知道怎麼處理
	//要把排列組合顯示給旅客看
	public List<Combination> getCombination(DifferentNumberRoomRemain difRemain, Integer roomCount, Integer people) {
//			boolean ifHasCondition = true;//用來加where
		List<Combination> list = new ArrayList<>();
		Combination combination = null;
		ResultSet rs = null;
//		Map<String, Integer> combination = null;
		Connection con = null;
		try {
			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(COMBINATION_STMT);
			pstmt.setInt(1, difRemain.getOne());
			pstmt.setInt(2, difRemain.getTwo());
			pstmt.setInt(3, difRemain.getThree());
			pstmt.setInt(4, difRemain.getFour());
			pstmt.setInt(5, difRemain.getFive());
			pstmt.setInt(6, difRemain.getSix());
			pstmt.setInt(7, roomCount);//
			pstmt.setInt(8, people);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				combination = new Combination();
				for (int i = 1; i <= 8; i++) {// 這裡也是寫死
					int amount = rs.getInt(i);// 之後可以用ResultSetMetaData 拿欄位名稱
					switch (i) {
						case 1:
							combination.setOne(amount);
							break;
						case 2:
							combination.setTwo(amount);
							break;
						case 3:
							combination.setThree(amount);
							break;
						case 4:
							combination.setFour(amount);
							break;
						case 5:
							combination.setFive(amount);
							break;
						case 6:
							combination.setSix(amount);
							break;
						case 7:
							combination.setHowManyRooms(amount);
							break;
						case 8:
							combination.setHowManyPeople(amount);
							break;
					}
				}
				list.add(combination);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		return list;
	}

//	public Map<Integer , Integer> getRemainMap(List<RoomTypeRemain> list){//Map的key=人數，value=剩餘數量
//		Map<Integer , Integer> map = new HashMap<>();
//		for(int i = 1;i < 7; i ++) {//目前先用死的，要想辦法再多出>6人的房型時也可以增加
//			map.put(i,0);
//		}
//		for(RoomTypeRemain rr:list) {
//			int capacity = rr.getPerson_Capacity();
//			int amount = rr.getRemain();
//			map.put(capacity, map.getOrDefault(capacity, 0) + amount);
//		}
//		return map;
//	}
	// 存把不同房型的資料存map(要可以分辨是存基本人數還是可加房
	public static void main(String[] args) {
		CheckRoomNumberJDBC crn = new CheckRoomNumberJDBC();
		List<RoomTypeRemain> list = crn.getRemainNo("2020-05-05", "2020-05-08");
		
		DifferentNumberRoomRemain difRemain = crn.getRemainMap(list);
		
		List<Combination> list1 = crn.getCombination(difRemain, 3 , 8);
		for (RoomTypeRemain rr : list) {
			System.out.println(rr);
		}
		System.out.println(difRemain);
		System.out.println("總和");
		
		System.out.println(list1.size());
		for (Combination combination : list1) {
			System.out.println(combination);
		}
	}
}
