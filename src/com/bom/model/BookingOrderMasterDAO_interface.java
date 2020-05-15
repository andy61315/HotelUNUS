package com.bom.model;

import java.util.List;
import java.util.Map;

import com.bod.model.BookOrderDetailVO;

public interface BookingOrderMasterDAO_interface {
	public void insert(BookingOrderMasterVO bomVO);
	public void updateBookingData(BookingOrderMasterVO bomVO);//訂房資料修改(頭尾日期/總金額)
	public void updateStatus(BookingOrderMasterVO bomVO);//訂房訂單狀態更改
//	public void delete(String b_Order_No);
	public BookingOrderMasterVO findByOrderId(String b_Order_No);
	public List<BookingOrderMasterVO> getAll();
	public List<BookingOrderMasterVO> getAllBy(Map<String, String[]> map); 
	public String insertWithBOD(BookingOrderMasterVO bomVO, List<BookOrderDetailVO> list);
	public List<BookingOrderMasterVO> getAllByCus(String cus_id,int status); 
}
