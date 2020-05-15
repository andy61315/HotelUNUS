package com.checkroomremain;
import static common.Common.*;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Struct;


import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OracleResultSet;

public class CheckRoomNumberOriginal {
//限制：
//	1.新增超過六人以上的房型無法比對
//trigger還沒建
//要建兩個版本的組合
	private static DataSource ds = null;
	OracleCallableStatement cst = null;
	OracleResultSet rs = null;
//	Connection con = null;
	PreparedStatement pstmt = null;
	String[] combinationArr = {"one_", "two_" ,"three_" , "four_", "five_" ,"six_" ,"min_" , "max_"}; 
	private static String COMBINATION_STMT ="SELECT * from combination "
								+ "where one_ <= ? and two_ <= ? and three_ <= ? and four_ <= ? "
								+ "and five_ <= ? and six_ <= ? and min_ = ? and max_ = ?";
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<RoomTypeRemain>getRemainNo(String startDate, String endDate) {//篩出不同人數的房型各自的剩餘數量
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		List<RoomTypeRemain> list = new ArrayList<>();
		RoomTypeRemain rr = null;
		Connection con = null;
//		List<RoomTypeRemain> remainList = new ArrayList();
		try {
			con = ds.getConnection();
		
//			cst = (OracleCallableStatement)con.prepareCall(CALL);
			cst = (OracleCallableStatement)con.prepareCall("begin ROOM_REMAIN_ALL(?,?,?); end;");
			cst.setString(1, startDate);//試試看在這裡用TO_DATE相加
			cst.setString(2, endDate);
			cst.registerOutParameter(3, OracleTypes.ARRAY,"ROOM_RE_TAB" );//room_remain_table
			System.out.println(cst.executeUpdate());
			Array test1 = cst.getArray(3);
			rs = (OracleResultSet) test1.getResultSet();
			Struct struct = null;
			
			while(rs.next()){
				struct = rs.getSTRUCT(2);
				Object[] obj = struct.getAttributes();
				rr = new RoomTypeRemain();
				rr.setRoom_Type_No((String)obj[0] );
				rr.setRemain(Integer.parseInt(obj[1].toString()));
				rr.setPerson_Capacity(Integer.parseInt(obj[2].toString()));
				rr.setTotal_People(Integer.parseInt(obj[3].toString()));
				list.add(rr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//先不做>6的(或是說不知道怎麼查最大房數比較好，另外如果6人房後直接跳10人也不知道怎麼處理
	public List <Map<String,Integer>> getCombination(Map<Integer , Integer> map, Integer roomCount, Integer people) {
//			boolean ifHasCondition = true;//用來加where
		List <Map<String,Integer>> list = new ArrayList<Map<String,Integer>>();
		ResultSet rs = null;
		Map<String,Integer> combination= null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COMBINATION_STMT);
			pstmt.setInt(1, map.get(1));
			pstmt.setInt(2, map.get(2));
			pstmt.setInt(3, map.get(3));
			pstmt.setInt(4, map.get(4));
			pstmt.setInt(5, map.get(5));
			pstmt.setInt(6, map.get(6));
			pstmt.setInt(7, roomCount);//
			pstmt.setInt(8, people);
			rs = pstmt.executeQuery();
			//設陣列比對
			while(rs.next()) {
				combination = new TreeMap<String, Integer>();
				for(int i = 0; i < 6 ; i++) {//這裡也是寫死
					int count = rs.getInt(combinationArr[i]);//之後可以用ResultSetMetaData 拿欄位名稱
					if(count != 0) {
						combination.put(String.valueOf(i+1), count);//讓index直接 = X人房
					}
				}
				combination.put("住幾房", rs.getInt(combinationArr[6]));
				combination.put("有幾人", rs.getInt(combinationArr[7]));
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
	//提取排列組合的表格
	public Map<Integer , Integer> getRemainMap(List<RoomTypeRemain> list){//Map的key=人數，value=剩餘數量
		Map<Integer , Integer> map = new HashMap<>();
		for(int i = 1;i < 7; i ++) {//目前先用死的，要想辦法再多出>6人的房型時也可以增加
			map.put(i,0);
		}
		for(RoomTypeRemain rr:list) {
			int capacity = rr.getPerson_Capacity();
			int amount = rr.getRemain();
			map.put(capacity, map.getOrDefault(capacity, 0) + amount);
		}
		return map;
	}
	//存把不同房型的資料存map(要可以分辨是存基本人數還是可加房
	public static void main(String[] args) {
		CheckRoomNumberDAO crn= new CheckRoomNumberDAO();
		List<RoomTypeRemain> list = crn.getRemainNo("2020-05-07","2020-05-13");
//		Map<Integer , Integer> map = crn.getRemainMap(list);
//		List <Map<String,Integer>> list1 = crn.getCombination(map, 3, 10);
//		for(RoomTypeRemain rr:list) {
//			System.out.println(rr);
//		}
//		
//		System.out.println("總和");
//		for(int i:map.keySet()) {
//			System.out.println(i + "  " + map.get(i));
//		}
//		System.out.println(list1.size());
//		for(Map<String,Integer> m : list1) {
//			System.out.println(m);
//			System.out.println(1);
//		}
	}
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
