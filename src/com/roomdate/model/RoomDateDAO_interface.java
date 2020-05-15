package com.roomdate.model;

import java.util.Date;
import java.util.List;

public interface RoomDateDAO_interface {
	public void insert(RoomDateVO roomdateVO);
    public void update(RoomDateVO roomVO);
    public void delete(Date roomdate);
    public int findByPrimaryKey(Date roomdate);
    public List<RoomDateVO> getAll();
    public List<RoomDateVO> getByInterval(java.sql.Date start_Date,java.sql.Date end_Date);
}
