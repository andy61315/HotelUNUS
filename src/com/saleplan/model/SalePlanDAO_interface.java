package com.saleplan.model;

import java.util.*;

import com.sod.model.SaleOrderDetailVO;




public interface SalePlanDAO_interface {
	public int insert(SalePlanVO saleplan);
	public boolean update(SalePlanVO saleplan);
	public List<SalePlanVO> getAll();
	public List<SalePlanVO> getSreach(Map<String, String[]> map);
	public SalePlanVO findByPrimaryKey(String sapl_no);
	public SalePlanVO getOneFromName(String sapl_name);
	//------------------------------------------
    //public Set<SalePlanVO> getRoomTypeBySodNo(Integer deptno);
   
	public void delete(String sapl_no);
    //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insertWithSod(SalePlanVO salePlanVO , List<SaleOrderDetailVO> list);
    
    public SalePlanVO findByRoomType(String room_Type_No , java.sql.Date in_Date, java.sql.Date out_Date);
}
