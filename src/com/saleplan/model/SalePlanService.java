package com.saleplan.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sod.model.SaleOrderDetailVO;



public class SalePlanService {
	private SalePlanDAO_interface dao;
	
	public SalePlanService() {
		dao = new SalePlanJNDIDAO();
	}
	
	public SalePlanVO addSal(String sapl_no, String sapl_name, String detail,java.sql.Date start_date,
			java.sql.Date end_date, Double sapl_discount, Integer status) {
		SalePlanVO SalVo = new SalePlanVO();
		SalVo.setSapl_no(sapl_no);
		SalVo.setSapl_name(sapl_name);
		SalVo.setDetail(detail);
		SalVo.setStart_date(start_date);
		SalVo.setEnd_date(end_date);
		SalVo.setSapl_discount(sapl_discount);
		SalVo.setStatus(status);
		dao.insert(SalVo);
		return SalVo;
	}
	
	public SalePlanVO updateSal(String sapl_name, String detail,java.sql.Date start_date,
			java.sql.Date end_date, Double sapl_discount, Integer status,String sapl_no) {
		SalePlanVO salVo = new SalePlanVO();
		salVo.setSapl_no(sapl_no);
		salVo.setSapl_name(sapl_name);
		salVo.setDetail(detail);
		salVo.setStart_date(start_date);
		salVo.setEnd_date(end_date);
		salVo.setSapl_discount(sapl_discount);
		salVo.setStatus(status);
		dao.update(salVo);
		
		return salVo ;
	}
	
	public List<SalePlanVO> getAll() {
		return dao.getAll();
	}
	
	public  List<SalePlanVO> getsearch(String typeName,String value){
		Map<String,String[]> map = new HashMap<>();
		String [] tyvalue = new String[] {new String(value)};
		map.put(typeName,tyvalue);
		return dao.getSreach(map);
		
	}
	
	public SalePlanVO getOneSal(String sapl_no) {
		return dao.findByPrimaryKey(sapl_no);
	}
	
	public SalePlanVO getOneSalByName(String sapl_name) {
		return dao.getOneFromName(sapl_name);
	} 
	public void insertWithSod(SalePlanVO saleplan, String[] roomTypes) {
		List<SaleOrderDetailVO> addRoomType = new ArrayList<SaleOrderDetailVO>();
		
		for(int i=0;i<roomTypes.length;i++) {
			String roomTypeId =roomTypes[i];
			SaleOrderDetailVO sod=new SaleOrderDetailVO();
			sod.setRoom_type_no(roomTypeId);
			addRoomType.add(sod);
			
		}
		System.out.println(addRoomType);
		dao.insertWithSod(saleplan, addRoomType);

	}

	
	
}
