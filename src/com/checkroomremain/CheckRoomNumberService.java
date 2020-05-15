package com.checkroomremain;

import java.sql.Date;
import java.util.List;

import com.roomdate.model.RoomDateJNDIDAO;
import com.roomdate.model.RoomDateVO;
import com.roomtype.model.RoomTypeDAO;
import com.roomtype.model.RoomTypeDAO_interface;
import com.roomtype.model.RoomTypeVO;
import com.saleplan.model.SalePlanDAO_interface;
import com.saleplan.model.SalePlanJNDIDAO;
import com.saleplan.model.SalePlanVO;

public class CheckRoomNumberService {
	private CheckRoomNumberDAO crndao;
	private SalePlanDAO_interface spdao;
	private RoomTypeDAO_interface rtdao;
	public CheckRoomNumberService() {
		crndao = new CheckRoomNumberDAO();
		spdao = new SalePlanJNDIDAO();
		rtdao = new RoomTypeDAO();
	}
	
	
	
	public Integer getPrice (String room_Type_No, List<RoomDateVO> rdList) {
		System.out.println("rdList = " + rdList);
		//計算總共的天數/日期
		java.sql.Date in_Date = rdList.get(0).getDate_time();//旅客入住
		java.sql.Date out_Date = rdList.get(rdList.size()-1).getDate_time();//checkout前一天
		//取得1.房型VO 2.符合房型的優惠專案VO
		RoomTypeVO rtVO = rtdao.findOneByNo(room_Type_No);//房型資訊

//		System.out.println("rtVO = " + rtVO);
		SalePlanVO spVO = spdao.findByRoomType(room_Type_No, in_Date, out_Date);//優惠資訊
		
		
		int length = (int)((out_Date.getTime() - in_Date.getTime()) / (86400 * 1000) + 1);//總共住幾天 (+1是因為rdList沒有取道checkout那天 
		int[] interval = new int[length];//等一下把價格資訊放進來
		int[] priceArr = {rtVO.getWorkingDay_Price(), rtVO.getHoliday_Price()};
		
		
		for(int i = 0; i < (int)length; i++) {
			int isHoliday = rdList.get(i).getIsHoliday();
			interval[i] = priceArr[isHoliday];
		}
		if(spVO != null) {//若存在優惠專案，計算優惠專案要從陣列的哪裡開始(等於計算優惠專案在哪幾天的時候會使用
			java.sql.Date begin_Date = spVO.getStart_date();//優惠開始
			java.sql.Date end_Date = spVO.getEnd_date();                        
			double discount = spVO.getSapl_discount();
			int spStart = (int)((begin_Date.getTime() - in_Date.getTime())/(86400 * 1000));
			spStart = (spStart < 0 ) ? 0 : spStart;
			
			int spEnd =  (int)((end_Date.getTime() - in_Date.getTime())/(86400 * 1000));
			spEnd = (spEnd > length - 1) ? (length - 1) : spEnd;
			
			for(int i = spStart; i <= spEnd; i++) {
				interval[i] *= discount;
			}
		}
		
		int sum = 0;
		for(int i = 0; i < length; i++) {
			sum += interval[i];
		}
		
		return sum;
		
		//先確定是否有符合他的優惠專案(期間內/房型對/狀態對
			//有，撈0.優惠名稱1.起始日2.結束日3.折扣
		//撈日期table
		
	}
}
