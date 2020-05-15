package com.bod.model;

import java.util.*;





public class BookOrderDetailService {
	
	private BookOrderDetailDAO_interface dao;
	
	public BookOrderDetailService() {
		dao = new BookOrderDetailJNDIDAO();
		
	}
	
	public BookOrderDetailVO addBod(String b_order_no, String room_type_no, Integer quantity, Integer total_add_people,
			Integer price ) {
		BookOrderDetailVO bodVo = new BookOrderDetailVO();
		
		bodVo.setB_order_no(b_order_no);	
		bodVo.setRoom_type_no(room_type_no);
		bodVo.setPrice(price);
		bodVo.setQuantity(quantity);
		bodVo.setTotal_add_people(total_add_people);
		dao.insert(bodVo);
		
		return bodVo;
	} 
	public BookOrderDetailVO updatabodVo(String b_order_no, String room_type_no, Integer quantity, Integer total_add_people,
			Integer price ) {
		BookOrderDetailVO bodVo = new BookOrderDetailVO();

		bodVo.setB_order_no(b_order_no);
		bodVo.setRoom_type_no(room_type_no);
		bodVo.setQuantity(quantity);
		bodVo.setTotal_add_people(total_add_people);
		bodVo.setPrice(price);
		dao.updateBookingData(bodVo);
		return bodVo;
	} 
	public void deletebodVo(String b_order_no,String room_type_no) {
		dao.delete(b_order_no,room_type_no);
	}
	public BookOrderDetailVO getOneBod(String b_order_no,String room_type_no) {
		return dao.findByPrimaryKey(b_order_no,room_type_no);
	}
	
	public List<BookOrderDetailVO>  getBookRoom(String b_order_no) {
//		List<BookOrderDetailVO> list = 	dao.findByBookOrderNo(b_order_no);
//		BookOrderDetailVO bodvo =new BookOrderDetailVO();
//		for (BookOrderDetailVO bodvo5 : list) {
//			bodvo.setB_order_no(bodvo5.getB_order_no());
//			bodvo.setRoom_type_no(bodvo5.getRoom_type_no());
//			bodvo.setQuantity(bodvo5.getQuantity());
//			bodvo.setTotal_add_people(bodvo5.getTotal_add_people());
//			bodvo.setPrice(bodvo5.getPrice());
//		}
		
		return dao.findByBookOrderNo(b_order_no);
	}
	
	
	public List<BookOrderDetailVO> getAll() {
		return dao.getAll();
	}
	
	public List<BookOrderDetailVO> getSearch(String b_order_no,String room_type_no) {
		Map<String, String[]> map = new HashMap<>();
		String[] rtName = new String[]{new String(room_type_no)};
		map.put(b_order_no, rtName);
		return dao.getSearch(map);
	}
	
}
