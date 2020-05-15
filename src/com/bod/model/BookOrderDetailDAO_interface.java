package com.bod.model;

import java.sql.Connection;
import java.util.*;

public interface BookOrderDetailDAO_interface {
	public int insert(BookOrderDetailVO  bodvo);
	public boolean updateBookingData(BookOrderDetailVO  bodvo);
	public boolean delete(String bookOrderNo ,String roomTypeNo);
	public BookOrderDetailVO findByPrimaryKey(String b_order_no,String room_type_no);
	public List<BookOrderDetailVO> getAll();
	public List<BookOrderDetailVO> getSearch(Map<String, String[]> map);
	public List<BookOrderDetailVO>  findByBookOrderNo(String bod);
	public void insert2(BookOrderDetailVO bodvo, Connection con);
}
