package com.diarymessage.model;

import java.util.List;

import com.diary.model.DiaryDAO;
import com.diary.model.DiaryDAO_interface;
import com.diary.model.DiaryVO;

public class DiaryMessageService {
	private DiaryMessageDAO_interface dao;

	public DiaryMessageService() {
		dao = new DiaryMessageDAO();
	}

	public DiaryMessageVO addDiaryMessage(String cus_id, String diary_no, 
//			java.sql.Date diary_message_date,
			String diary_message_content, Integer diary_message_status) {

		DiaryMessageVO diaryMessageVO = new DiaryMessageVO();

		diaryMessageVO.setCus_id(cus_id);
		diaryMessageVO.setDiary_no(diary_no);
//		diaryMessageVO.setDiary_message_date(diary_message_date);
		diaryMessageVO.setDiary_message_content(diary_message_content);
		diaryMessageVO.setDiary_message_status(diary_message_status);
		dao.insert(diaryMessageVO);

		return diaryMessageVO;
	}

	public DiaryMessageVO updateDiaryMessage(String diary_message_no, String cus_id, String diary_no, java.sql.Date diary_message_date,
			String diary_message_content, Integer diary_message_status) {

		DiaryMessageVO diaryMessageVO = new DiaryMessageVO();

		diaryMessageVO.setDiary_message_no(diary_message_no);
		diaryMessageVO.setCus_id(cus_id);
		diaryMessageVO.setDiary_no(diary_no);
		diaryMessageVO.setDiary_message_date(diary_message_date);
		diaryMessageVO.setDiary_message_content(diary_message_content);
		diaryMessageVO.setDiary_message_status(diary_message_status);
		dao.update(diaryMessageVO);

		return diaryMessageVO;
	}

	public void deleteDiaryMessage(String diary_message_no) {
		dao.delete(diary_message_no);
	}

	public DiaryMessageVO getOneDiaryMessage(String diary_message_no) {
		return dao.findByPrimaryKey(diary_message_no);
	}

	public List<DiaryMessageVO> getAll() {
		return dao.getAll();
	}
	public List<DiaryMessageVO> getAllMsg(String diary_no) {
		return dao.getMsgByDiaryno(diary_no);
	}
}



