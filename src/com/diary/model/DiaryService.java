package com.diary.model;

import java.util.List;
import java.util.Set;

import com.diary.model.DiaryDAO;
import com.diary.model.DiaryDAO_interface;
import com.diary.model.DiaryVO;
import com.diarymessage.model.DiaryMessageVO;


public class DiaryService {
	private DiaryDAO_interface dao;

	public DiaryService() {
		dao = new DiaryDAO();
	}

	public DiaryVO addDiary(String cus_id, java.sql.Date diary_date,
			String diary_title, String diary_content, Integer diary_status) {

		DiaryVO diaryVO = new DiaryVO();

		diaryVO.setCus_id(cus_id);
		diaryVO.setDiary_date(diary_date);
		diaryVO.setDiary_title(diary_title);
		diaryVO.setDiary_content(diary_content);
		diaryVO.setDiary_status(diary_status);
		dao.insert(diaryVO);

		return diaryVO;
	}

	public DiaryVO updateDiary(String diary_no, String diary_title, String diary_content) {

		DiaryVO diaryVO = new DiaryVO();

		diaryVO.setDiary_no(diary_no);
//		diaryVO.setCus_id(cus_id);
//		diaryVO.setDiary_date(diary_date);
		diaryVO.setDiary_title(diary_title);
		diaryVO.setDiary_content(diary_content);
//		diaryVO.setDiary_status(diary_status);
		dao.update(diaryVO);

		return dao.findByPrimaryKey(diary_no);
	}
//修改我的日誌
	public void deleteDiary(String diary_no) {
		
		DiaryVO diaryVO = new DiaryVO();
		diaryVO.setDiary_no(diary_no);
		dao.delete(diaryVO);
	}
	//查詢上架日誌
	public DiaryVO getOneDiary(String diary_no) {
		return dao.findByPrimaryKey(diary_no);
	}
	//查詢下架日誌
	public DiaryVO getAuditedDiary(String diary_no) {
		return dao.getAuditedOne(diary_no);
	}
	//查詢某日誌的留言
//	public Set<DiaryMessageVO> getMessageBydiaryno(String diary_message_no) {
//		return dao.getMessageBydiaryno(diary_message_no);
//	}

	public List<DiaryVO> getAll() {
		return dao.getAll();
	}
	public List<DiaryVO> getLatestDiaries(){
		return dao.getLatestDiaries();
	}
	public List<DiaryVO> getPopularDiaries(){
		return dao.getPopularDiaries();
	}
	public List<DiaryVO> getSome(String diary_key_name) {
		return dao.getSome(diary_key_name);
	}
	public List<DiaryVO> getMyDiary(String cus_id){
		return dao.getMyDiary(cus_id);
	}
}

