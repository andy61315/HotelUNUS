package com.checkroomremain;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Struct;


import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OracleResultSet;

public class CheckRoomNumberDAO {
//限制：
//	1.新增超過六人以上的房型無法比對
//trigger還沒建
//要建兩個版本的組合
	private static DataSource ds = null;
	CallableStatement cst = null;
	OracleResultSet rs = null;
	PreparedStatement pstmt = null;
	String[] combinationArr = {"one_", "two_" ,"three_" , "four_", "five_" ,"six_" ,"min_" , "max_"}; 
	private static String COMBINATION_STMT ="SELECT * from combination "
								+ "where one_ <= ? and two_ <= ? and three_ <= ? and four_ <= ? "
								+ "and five_ <= ? and six_ <= ? and min_ = ? and max_ = ? ORDER BY EMPTY_COUNT DESC";
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//到時候讓旅客選房型數量時，限制最大可選數量
	public List<RoomTypeRemain>getRemainNo(String startDate, String endDate) {//篩出不同房型各自的剩餘數量
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		List<RoomTypeRemain> list = new ArrayList<>();
		RoomTypeRemain rr = null;
//		Connection con = null;
//		List<RoomTypeRemain> remainList = new ArrayList();
		try (Connection con = ds.getConnection();){
			
		
//			cst = (OracleCallableStatement)con.prepareCall(CALL);
		cst = (CallableStatement)con.prepareCall("begin ROOM_REMAIN_ALL(?,?,?); end;");
//		cst = (OracleCallableStatement)con.prepareCall("begin ROOM_REMAIN_ALL(?,?,?); end;");
			cst.setString(1, startDate);//試試看在這裡用TO_DATE相加
			cst.setString(2, endDate);
			cst.registerOutParameter(3, OracleTypes.ARRAY,"ROOM_RE_TAB" );//room_remain_table
//			System.out.println();
			cst.executeUpdate();
			Array test1 = cst.getArray(3);
			rs = (OracleResultSet) test1.getResultSet();
			Struct struct = null;
			
			while(rs.next()){
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
		}
		//System.out.println(list);
		return list;
	}
	
	//提取排列組合的表格
	//這個表格應該不用給旅客看到
	//EX：雙人房剩幾間 / 三人房剩幾間 / 所有的雙人房都算在雙人房
		public DifferentNumberRoomRemain getRemainMap(List<RoomTypeRemain> list) {// Map的key=人數，value=剩餘數量
			DifferentNumberRoomRemain difRemain = new DifferentNumberRoomRemain();
//			//System.out.println(difRemain);
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
		
		
		public List<Combination> getCombination(DifferentNumberRoomRemain difRemain, Integer roomCount, Integer people) {
//				boolean ifHasCondition = true;//用來加where
			List<Combination> list = new ArrayList<>();
			Combination combination = null;
			ResultSet rs = null;
//			Map<String, Integer> combination = null;
			Connection con = null;
			try {
				con = ds.getConnection();
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
	
	//存把不同房型的資料存map(要可以分辨是存基本人數還是可加房
//	public static void main(String[] args) {
//		CheckRoomNumberJDBC crn = new CheckRoomNumberJDBC();
//		List<RoomTypeRemain> list = crn.getRemainNo("2020-05-07", "2020-05-13");
//		DifferentNumberRoomRemain difRemain = crn.getRemainMap(list);
//		List<Combination> list1 = crn.getCombination(difRemain, 3 , 10);
//		for (RoomTypeRemain rr : list) {
//			System.out.println(rr);
//		}
//		System.out.println(difRemain);
////		System.out.println("總和");
////		for(int i:map.keySet()) {
////			System.out.println(i + "  " + map.get(i));
////		}
////		System.out.println(list1.size());
//		for (Combination combination : list1) {
//			System.out.println(combination);
//		}
//	}
}
//
//class RoomTypeRemain{
//	String room_Type_No;
//	Integer remain;
//	Integer person_Capacity;
//	Integer total_People;
//	public RoomTypeRemain(String room_Type_No, Integer remain, Integer person_Capacity, Integer total_People) {
//		super();
//		this.room_Type_No = room_Type_No;
//		this.remain = remain;
//		this.person_Capacity = person_Capacity;
//		this.total_People = total_People;
//	}
//	public RoomTypeRemain() {
//		super();
//	}
//	public String getRoom_Type_No() {
//		return room_Type_No;
//	}
//	public void setRoom_Type_No(String room_Type_No) {
//		this.room_Type_No = room_Type_No;
//	}
//	public Integer getRemain() {
//		return remain;
//	}
//	public void setRemain(Integer remain) {
//		this.remain = remain;
//	}
//	public Integer getPerson_Capacity() {
//		return person_Capacity;
//	}
//	public void setPerson_Capacity(Integer person_Capacity) {
//		this.person_Capacity = person_Capacity;
//	}
//	public Integer getTotal_People() {
//		return total_People;
//	}
//	public void setTotal_People(Integer total_People) {
//		this.total_People = total_People;
//	}
//	@Override
//	public String toString() {
//		return "RoomTypeRemain [room_Type_No=" + room_Type_No + ", remain=" + remain + ", person_Capacity="
//				+ person_Capacity + ", total_People=" + total_People + "]";
//	}
//}
