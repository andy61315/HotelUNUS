package com.roomdate.model;

import static common.Common.DRIVER_CLASS;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RoomDateJDBCDAO implements RoomDateDAO_interface , Runnable{
	private static final String INSERT_STMT = 
			"INSERT INTO room_date (DATE_TIME,ISHOLIDAY) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM room_date";
	private static final String GETONE ="SELECT ISHOLIDAY FROM ROOM_DATE where date_time=?";
	private static final String UPDATE ="UPDATE room_date SET ISHOLIDAY=? where date_time=?";
	private static final String DELETE ="DELETE FROM room_date where date_time=?";
	public RoomDateJDBCDAO() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void insert(RoomDateVO roomdateVO) {
		// TODO Auto-generated method stub
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);){
			
		
			java.sql.Date date1= new java.sql.Date(roomdateVO.getDate_time().getTime());
			pstmt.setDate(1, date1);
			pstmt.setInt(2, roomdateVO.getIsHoliday());
			pstmt.executeUpdate();
		
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		
	}

	@Override
	public void update(RoomDateVO roomdateVO) {
		// TODO Auto-generated method stub
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE);){
			
				java.sql.Date date1= new java.sql.Date(roomdateVO.getDate_time().getTime());	
				pstmt.setInt(1, roomdateVO.getIsHoliday());
				pstmt.setDate(2, date1);
				
				 pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
	}

	@Override
	public void delete(Date roomdate) {
		// TODO Auto-generated method stub
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE);){
			 java.sql.Date date1= new java.sql.Date(roomdate.getTime());
				pstmt.setDate(1,date1);
				
				 pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
	}

	@Override
	public int findByPrimaryKey(Date roomdate) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		int status=0;
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GETONE);){
			
				java.sql.Date date1= new java.sql.Date(roomdate.getTime());			
				pstmt.setDate(1, date1);
				
				 pstmt.executeUpdate();
				 rs = pstmt.executeQuery();
					while(rs.next()) {
						status= rs.getInt("isHoliday");
					}
				 

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
		return status;
	}

	@Override
	public List<RoomDateVO> getAll() {
		// TODO Auto-generated method stub
		List<RoomDateVO> list = new ArrayList<>();
		ResultSet rs = null;
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);){
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RoomDateVO roomdateVO  =new RoomDateVO();
				roomdateVO.setDate_time(rs.getDate("date_time"));
				roomdateVO.setIsHoliday(rs.getInt("isHoliday"));
			}
				
				
		

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 
		
		return list;
	}
	
	public static void main(String[] args) throws JSONException, Throwable {
		
//		RoomDateJDBCDAO r1= new RoomDateJDBCDAO();
//		Thread t1 = new Thread(r1);
//		t1.start();
		RoomDateJDBCDAO roomDateDAO = new RoomDateJDBCDAO();
//		RoomDateVO roomDateVO = new RoomDateVO();
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");
		
//		roomDateDAO.findByPrimaryKey(sdf.parse("2020/4/18"));
		System.out.println(roomDateDAO.findByPrimaryKey(sdf.parse("2020/4/18")));
		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		RoomDateJDBCDAO roomDateDAO = new RoomDateJDBCDAO();
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date_time = null;
		int isHoliday =1;
		JSONArray jsonarray;
		try {
			jsonarray = new JSONArray("[{\"date\":\"2020/4/2\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"補假\",\"description\":\"\"},{\"date\":\"2020/4/3\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"放假之紀念日及節日\",\"description\":\"\"},{\"date\":\"2020/4/4\",\"name\":\"兒童節及民族掃墓節\",\"isHoliday\":\"是\",\"holidayCategory\":\"放假之紀念日及節日\",\"description\":\"全國各機關學校放假一日，兒童節與民族掃墓節同一日且逢星期六，於四月二日補假一日、四月三日放假一日。\"},{\"date\":\"2020/4/5\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/4/11\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/4/12\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/4/18\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/4/19\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/4/25\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/4/26\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/1\",\"name\":\"勞動節\",\"isHoliday\":\"是\",\"holidayCategory\":\"特定節日\",\"description\":\"勞工放假一日。\"},{\"date\":\"2020/5/2\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/3\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/9\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/10\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/16\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/17\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/23\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/24\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/30\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/5/31\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/6\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/7\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/13\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/14\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/20\",\"name\":\"\",\"isHoliday\":\"否\",\"holidayCategory\":\"補行上班日\",\"description\":\"\"},{\"date\":\"2020/6/21\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/25\",\"name\":\"端午節\",\"isHoliday\":\"是\",\"holidayCategory\":\"放假之紀念日及節日\",\"description\":\"全國各機關學校放假一日，適逢星期四，調整六月二十六日為放假日，並於六月二十日補行上班一日。\"},{\"date\":\"2020/6/26\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"調整放假日\",\"description\":\"\"},{\"date\":\"2020/6/27\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/6/28\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/4\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/5\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/11\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/12\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/18\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/19\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/25\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/7/26\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/1\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/2\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/8\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/9\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/15\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/16\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/22\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/23\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/29\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/8/30\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/3\",\"name\":\"軍人節\",\"isHoliday\":\"是\",\"holidayCategory\":\"特定節日\",\"description\":\"軍人依國防部規定辦理。\"},{\"date\":\"2020/9/5\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/6\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/12\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/13\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/19\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/20\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/9/26\",\"name\":\"\",\"isHoliday\":\"否\",\"holidayCategory\":\"補行上班日\",\"description\":\"\"},{\"date\":\"2020/9/27\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/1\",\"name\":\"中秋節\",\"isHoliday\":\"是\",\"holidayCategory\":\"放假之紀念日及節日\",\"description\":\"全國各機關學校放假一日，適逢星期四，調整十月二日為放假日，並於九月二十六日補行上班一日。\"},{\"date\":\"2020/10/2\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"調整放假日\",\"description\":\"\"},{\"date\":\"2020/10/3\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/4\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/9\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"補假\",\"description\":\"\"},{\"date\":\"2020/10/10\",\"name\":\"國慶日\",\"isHoliday\":\"是\",\"holidayCategory\":\"放假之紀念日及節日\",\"description\":\"全國各機關學校放假一日，適逢星期六，於十月九日補假一日。\"},{\"date\":\"2020/10/11\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/17\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/18\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/24\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/25\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/10/31\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/1\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/7\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/8\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/14\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/15\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/21\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/22\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/28\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/11/29\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/5\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/6\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/12\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/13\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/19\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/20\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/26\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"},{\"date\":\"2020/12/27\",\"name\":\"\",\"isHoliday\":\"是\",\"holidayCategory\":\"星期六、星期日\",\"description\":\"\"}]");
			for(int i=0;i<87;i++){  
			  	RoomDateVO roomDateVO = new RoomDateVO();
	            String date = jsonarray.getJSONObject(i).getString("date");    
	            int b=0;
	            try {
					date_time = sdf.parse(date);
					System.out.println(date_time);
		            roomDateVO.setDate_time(new java.sql.Date(date_time.getTime()));
		    		roomDateVO.setIsHoliday(isHoliday);
		    		roomDateDAO.update(roomDateVO);
		    		b++;
		    		if(b==10) {
		    			try {
							Thread.sleep(2000);
							System.out.println(b);
							b=0;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			
		    		}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		
	        }    
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		

	  
	  
	}



	@Override
	public List<RoomDateVO> getByInterval(java.sql.Date start_Date, java.sql.Date end_Date) {
		// TODO Auto-generated method stub
		return null;
	}

}
