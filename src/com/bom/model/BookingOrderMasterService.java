package com.bom.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bod.model.BookOrderDetailVO;

public class BookingOrderMasterService {
	private BookingOrderMasterDAO_interface dao;

	public BookingOrderMasterService() {
		dao = new BookingOrderMasterDAO();
	}
	public BookingOrderMasterVO addBookingOrderMaster(String b_Order_No, String cus_Id, Integer total_Price, Date start_Date, Date end_Date,
			Integer status) {
		BookingOrderMasterVO vo = new BookingOrderMasterVO();
		vo.setB_Order_No(b_Order_No);
		vo.setCus_Id(cus_Id);
		vo.setTotal_Price(total_Price);
		vo.setStart_Date(start_Date);
		vo.setEnd_Date(end_Date);
		vo.setStatus(status);
		dao.insert(vo);
		
		return vo;
	}
	
	public String addAllBookingData(BookingOrderMasterVO bomVO, List<BookOrderDetailVO> list) {
		BookingOrderMasterDAO dao = new BookingOrderMasterDAO();
 		return dao.insertWithBOD( bomVO,  list) ;
	}
	
	public BookingOrderMasterVO updateBookingOrderMaster(String b_Order_No, String cus_Id, Integer total_Price, Date start_Date, Date end_Date, Date co_Time, Integer status) {
		BookingOrderMasterVO vo = new BookingOrderMasterVO();
		vo.setB_Order_No(b_Order_No);//只寫這四行的話會出錯//痾現在又不會了
		vo.setCus_Id(cus_Id);
		vo.setTotal_Price(total_Price);
		vo.setStart_Date(start_Date);
		vo.setEnd_Date(end_Date);
		vo.setCo_Time(co_Time);
		vo.setStatus(status);
		dao.updateBookingData(vo);
		return vo;
	}
	
	public void cancelBookingOrderMaster(String b_Order_No) {//專門取消訂單用
		BookingOrderMasterVO vo = new BookingOrderMasterVO();
		
		vo.setB_Order_No(b_Order_No);
		vo.setStatus(3);//0：未入住，1:已入住，2:已退房(訂單完成) 3:已取消?
		
		dao.updateStatus(vo);
	}
	
	public BookingOrderMasterVO getOneBOM(String b_Order_No) {
		return dao.findByOrderId(b_Order_No);
	}
	
	public List<BookingOrderMasterVO> getAll() {
		return dao.getAll();
	}
	
	public List<BookingOrderMasterVO> getAllBy(Map<String, String[]> map){
		return dao.getAllBy(map);
	}
	public List<BookingOrderMasterVO> getAllByCus(String cus_id,int status){
		return dao.getAllByCus(cus_id,status);
	}

	public List<BookingOrderMasterVO> getBOMByCusIDAndCheckINStatus(String cus_Id) {
		// == 在 Integer 僅適用於-128至127之間的數字
		Integer CheckINStatus = Integer.valueOf(1);
		List<BookingOrderMasterVO> filterList = dao.getAll().stream()
													.filter(bomVO -> cus_Id.equals(bomVO.getCus_Id()))
													.filter(bomVO -> CheckINStatus == bomVO.getStatus())
													.collect(Collectors.toList());
		return filterList;
	}
}