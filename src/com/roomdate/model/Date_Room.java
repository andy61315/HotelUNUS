package com.roomdate.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner; 
public class Date_Room implements Runnable {
	public static void main(String args[])  throws JSONException, ParseException {

		Date_Room r1= new Date_Room();
		Thread t1 = new Thread(r1);
		t1.start();
		}

	@Override
	public void run() {
		RoomDateJDBCDAO roomDateDAO = new RoomDateJDBCDAO();
		JSONArray jsonarray;
		
		int isHoliday =0;
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");
		// TODO Auto-generated method stub
		String date = "2020/4/2";
		String date2 = "2020/12/31";
		String date3 = "2020/4/3";
		Date date_time;
		try {
			date_time = sdf.parse(date);
			Date date_time2 = sdf.parse(date2);
			Date date_time3 = sdf.parse(date3);
			long start_date = date_time.getTime();
			long end_date = date_time2.getTime();
			long add_date = date_time3.getTime() - date_time.getTime();
			int a=0;
			
			
			
			for(long j=start_date;j<=end_date;j+=add_date) {
					  		RoomDateVO roomDateVO = new RoomDateVO();
					  		Date date_notHoliday = new Date(j);
					  		isHoliday=0;
					  		roomDateVO.setDate_time(new java.sql.Date(date_notHoliday.getTime()));
				    		roomDateVO.setIsHoliday(isHoliday);
				    		roomDateDAO.insert(roomDateVO);   
					  	a++;
					  	if(a==50) {
					  		try {
								Thread.sleep(1000);
								a=0;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					  	}
			            System.out.println(a);
			           
			        }
	
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
		
	

