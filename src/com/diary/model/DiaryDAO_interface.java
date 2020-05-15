package com.diary.model;

import java.util.List;
import java.util.Set;

import com.diary.model.DiaryVO;
import com.diarymessage.model.DiaryMessageVO;

public interface DiaryDAO_interface {
	public int insert(DiaryVO diaryVO);
    public void update(DiaryVO diaryVO);
    //修改狀態
    public void delete(DiaryVO diaryVO);
    public DiaryVO findByPrimaryKey(String diary_no);
    public List<DiaryVO> getAll();
    //新增關鍵字查詢方法
    public List<DiaryVO> getSome(String diary_key_name);
    //查詢最新日誌
    public List<DiaryVO> getLatestDiaries();
    //查詢熱門日誌
    public List<DiaryVO> getPopularDiaries();
    //新增查詢我的日誌
    public List<DiaryVO> getMyDiary(String diary_no);
    //新增查詢下架日誌
    public DiaryVO getAuditedOne(String diary_no);
    
}
